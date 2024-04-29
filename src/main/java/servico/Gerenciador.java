package servico;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import entidades.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Gerenciador {
    private Pesquisador pesquisadorLogado;
    private final ArrayList<Pesquisador> pesquisadores;

    public Gerenciador(){
        this.pesquisadores = new ArrayList<>();
    }

    public void preCadastro(){

        File folder = new File("files/.");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < Objects.requireNonNull(listOfFiles).length; i++) {
            String nomeDoArquivo = listOfFiles[i].getName();
            boolean isXml = nomeDoArquivo.endsWith(".xml");
            if (listOfFiles[i].isFile() && isXml) {
                carregaDadosDoCurriculo(listOfFiles[i]);
            }
        }
    }

    private void carregaDadosDoCurriculo(File arquivo){
        carregaNomeDoPesquisador(arquivo);
        carregaTrabalhosEmEventos(arquivo);
        carregaArtigosPublicados(arquivo);
        carregaLivrosPublicados(arquivo);
        carregaCapitulosPublicados(arquivo);
        this.pesquisadorLogado = null;
    }

    public void escolherPesquisador(){
        if (pesquisadores.isEmpty()) {
            System.out.println("Não há pesquisador cadastrados\n");
        } else {
            Scanner entrada = new Scanner(System.in);
            System.out.println("Escolha o pesquisador para fazer login: \n");

            int i = 1;
            for (Pesquisador pesquisador : pesquisadores) {
                System.out.println(" " + i + " - " + pesquisador.getNome());
                i++;
            }

            System.out.println("\n");

            int resposta = entrada.nextInt();

            boolean respostaValida = false;

            while(!respostaValida){
                if(resposta <= pesquisadores.size() && resposta >= 1){
                    respostaValida = true;
                } else {
                    System.out.println("Informe um valor válido!\n");
                    resposta = entrada.nextInt();
                }
            }

            pesquisadorLogado = pesquisadores.get(resposta - 1);

            System.out.println("\nLogin do pesquisador '" + pesquisadorLogado.getNome() + "' realizado com sucesso!");
        }
    }

    public void gerarRelatorio(){
        if (pesquisadores.isEmpty() || pesquisadorLogado == null) {
            System.out.println("Não há pesquisador logado\n");
        } else {
            System.out.println(
                    """
                    Escolha uma das opções a seguir:
                    1-Gerar Relatório por ano;
                    2-Gerar Relatório quantitativo por ano e tipo;
                    """);

            Scanner entrada = new Scanner(System.in);
            int escolhaDoUsuario = entrada.nextInt();

            switch (escolhaDoUsuario) {
                case 1 -> {
                    System.out.println(gerarRelatorioPorAno());
                    System.out.println("Desejas salvar o relatório? (1-Sim/2-Não)");
                    int salvar = entrada.nextInt();
                    if(salvar == 1) salvarRelatorio(gerarRelatorioPorAno());
                }

                case 2 -> {
                    System.out.print("\nInforme o ano: \n");
                    int ano = entrada.nextInt();

                    System.out.println(gerarRelatorioQuantitativoPorTipoEAno(ano));
                    System.out.println("\nDesejas salvar o relatório? (1-Sim/2-Não)");
                    int salvar = entrada.nextInt();
                    if(salvar == 1) {
                        String relatorio = gerarRelatorioQuantitativoPorTipoEAno(ano);
                        salvarRelatorio(relatorio);
                    }
                }
                default -> System.out.println("Insira um valor válido.");
            }
        }
    }

    private String gerarRelatorioPorAno(){
        StringBuilder resultado = new StringBuilder();

        ArrayList<Integer> anos = new ArrayList<>();
        anos.add(this.pesquisadorLogado.getProducoesBibliograficas().get(0).getAno());

        for(ProducaoBibliografica producaoBibliografica : this.pesquisadorLogado.getProducoesBibliograficas()){
            boolean adicionar = true;
            for(Integer ano : anos){
                if (ano.equals(producaoBibliografica.getAno())) {
                    adicionar = false;
                    break;
                }
            }
            if(adicionar) anos.add(producaoBibliografica.getAno());
        }

        for(Integer ano : anos){
            resultado.append("\n").append(ano).append(": \n");
            for(ProducaoBibliografica producaoBibliografica : this.pesquisadorLogado.getProducoesBibliograficas()){
                resultado.append(" Titulo da Produção: ").append(producaoBibliografica.getTitulo()).append("; \n" +
                        "").append(" Autores: ").append(producaoBibliografica.autoresToString()).append(" \n" +
                        "").append(" Tipo: ").append(producaoBibliografica.getTipo());


                resultado.append("\n\n");
            }
        }

        return resultado.toString();
    }

    private String gerarRelatorioQuantitativoPorTipoEAno(int ano){
        ArrayList<ProducaoBibliografica> artigosPublicados = new ArrayList<>();
        ArrayList<ProducaoBibliografica> trabalhosEmEventos = new ArrayList<>();
        ArrayList<ProducaoBibliografica> capitulosPublicados = new ArrayList<>();
        ArrayList<ProducaoBibliografica> livrosPublicados = new ArrayList<>();
        for (ProducaoBibliografica producao : this.pesquisadorLogado.getProducoesBibliograficas()) {
            if (producao instanceof ArtigoPublicado) {
                if (producao.getAno().equals(ano)) {
                    artigosPublicados.add(producao);
                }
            } else if (producao instanceof TrabalhoEmEvento) {
                if (producao.getAno().equals(ano)) {
                    trabalhosEmEventos.add(producao);
                }
            } else if (producao instanceof LivroPublicado) {
                if (producao.getAno().equals(ano)) {
                    livrosPublicados.add(producao);
                }
            } else if (producao instanceof CapituloPublicado) {
                if (producao.getAno().equals(ano)) {
                    capitulosPublicados.add(producao);
                }
            }
        }

        return "No ano " + ano + ", o Pesquisador " + this.pesquisadorLogado.getNome() + " fez: \n" +
                artigosPublicados.size() + " produções do tipo 'Artigo Publicado'; \n" +
                trabalhosEmEventos.size() + " produções do tipo 'Trabalho em Evento'; \n" +
                livrosPublicados.size() + " produções do tipo 'Livro Publicado'; \n" +
                capitulosPublicados.size() + " produções do tipo 'Capitulo Publicado'.";

    }

    private void salvarRelatorio(String relatorio){
        try{
            Scanner entrada = new Scanner(System.in);
            System.out.println("Informe o nome do novo arquivo: \n");
            String nome = entrada.nextLine();

            com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4, 25, 20, 25, 20);


            File arquivoTemp = new File("relatorios salvos/" + nome + ".pdf");
            boolean arquivoExiste = arquivoTemp.exists();


            if(arquivoExiste){
                System.out.println("O arquivo '" + nome + "' já existe. Você deseja sobrescrevê-lo? (1-Sim/2-Não).");
                int escolhaDoUsuario = entrada.nextInt();
                switch (escolhaDoUsuario){
                    case 1 -> {
                        PdfWriter.getInstance(document, new FileOutputStream("relatorios salvos/" + nome + ".pdf"));
                        System.out.println("O arquivo '" + nome + "' foi salvo com sucesso.");
                    }
                    case 2 -> System.out.println("O relatório não foi salvo.");
                    default -> System.out.println("Insira um valor válido.");
                }
            }else {
                PdfWriter.getInstance(document, new FileOutputStream("relatorios salvos/" + nome + ".pdf"));
                System.out.println("O arquivo '" + nome + "' foi salvo com sucesso.");
            }

            document.open();
            document.add(new Paragraph(new Phrase(10f,relatorio, FontFactory.getFont(FontFactory.TIMES_ROMAN,10))));
            document.close();

        }catch (FileNotFoundException eita){
            System.out.println("Não foi possível abrir ou alterar o arquivo.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void carregaNomeDoPesquisador(File arquivo){
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(arquivo);

            doc.getDocumentElement().normalize();

            String dados = "DADOS-GERAIS";

            NodeList dadosGerais = doc.getElementsByTagName(dados);

            String nomeDoPesquisador;
            nomeDoPesquisador = dadosGerais.item(0).getAttributes().getNamedItem("NOME-COMPLETO").getTextContent();

            Pesquisador pesquisador = new Pesquisador(nomeDoPesquisador);
            pesquisadores.add(pesquisador);

            this.pesquisadorLogado = pesquisador;

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private void carregaTrabalhosEmEventos(File arquivo){
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(arquivo);

            doc.getDocumentElement().normalize();

            String trabalhoEmEventos = "TRABALHO-EM-EVENTOS";

            NodeList trabalhosEmEventos = doc.getElementsByTagName(trabalhoEmEventos);

            for (int temp = 0; temp < trabalhosEmEventos.getLength(); temp++) {
                ArrayList<String> arrayListAutores = new ArrayList<>();

                Node node = trabalhosEmEventos.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    String titulo = "";
                    String ano = "";
                    NodeList dadosBasicos = ((Element) node).getElementsByTagName("DADOS-BASICOS-DO-TRABALHO");
                    for (int i = 0; i < dadosBasicos.getLength(); i++) {
                        titulo = dadosBasicos.item(i).getAttributes().getNamedItem("TITULO-DO-TRABALHO").getTextContent();
                        ano = dadosBasicos.item(i).getAttributes().getNamedItem("ANO-DO-TRABALHO").getTextContent();
                    }

                    NodeList autores = ((Element) node).getElementsByTagName("AUTORES");
                    for (int i = 0; i < autores.getLength(); i++) {
                        String autor = autores.item(i).getAttributes().getNamedItem("NOME-COMPLETO-DO-AUTOR").getTextContent();
                        arrayListAutores.add(autor);
                    }

                    ProducaoBibliografica producao = new TrabalhoEmEvento(arrayListAutores,titulo,ano);
                    this.pesquisadorLogado.addProducao(producao);
                }

            }


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private void carregaArtigosPublicados(File arquivo){
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(arquivo);

            doc.getDocumentElement().normalize();

            String artigoPublicado = "ARTIGO-PUBLICADO";

            NodeList artigosPublicados = doc.getElementsByTagName(artigoPublicado);

            for (int temp = 0; temp < artigosPublicados.getLength(); temp++) {
                ArrayList<String> arrayListAutores = new ArrayList<>();

                Node node = artigosPublicados.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    String titulo = "";
                    String ano = "";
                    NodeList dadosBasicos = ((Element) node).getElementsByTagName("DADOS-BASICOS-DO-ARTIGO");
                    for (int i = 0; i < dadosBasicos.getLength(); i++) {
                        titulo = dadosBasicos.item(i).getAttributes().getNamedItem("TITULO-DO-ARTIGO").getTextContent();
                        ano = dadosBasicos.item(i).getAttributes().getNamedItem("ANO-DO-ARTIGO").getTextContent();
                    }

                    NodeList autores = ((Element) node).getElementsByTagName("AUTORES");
                    for (int i = 0; i < autores.getLength(); i++) {
                        String autor = autores.item(i).getAttributes().getNamedItem("NOME-COMPLETO-DO-AUTOR").getTextContent();
                        arrayListAutores.add(autor);
                    }

                    ProducaoBibliografica producao = new ArtigoPublicado(arrayListAutores,titulo,ano);
                    this.pesquisadorLogado.addProducao(producao);
                }

            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private void carregaLivrosPublicados(File arquivo){
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(arquivo);

            doc.getDocumentElement().normalize();

            String livroPublicado = "LIVRO-PUBLICADO-OU-ORGANIZADO";

            NodeList livrosPublicados = doc.getElementsByTagName(livroPublicado);

            for (int temp = 0; temp < livrosPublicados.getLength(); temp++) {
                ArrayList<String> arrayListAutores = new ArrayList<>();

                Node node = livrosPublicados.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    String titulo = "";
                    String ano = "";
                    NodeList dadosBasicos = ((Element) node).getElementsByTagName("DADOS-BASICOS-DO-LIVRO");
                    for (int i = 0; i < dadosBasicos.getLength(); i++) {
                        titulo = dadosBasicos.item(i).getAttributes().getNamedItem("TITULO-DO-LIVRO").getTextContent();
                        ano = dadosBasicos.item(i).getAttributes().getNamedItem("ANO").getTextContent();
                    }

                    NodeList autores = ((Element) node).getElementsByTagName("AUTORES");
                    for (int i = 0; i < autores.getLength(); i++) {
                        String autor = autores.item(i).getAttributes().getNamedItem("NOME-COMPLETO-DO-AUTOR").getTextContent();
                        arrayListAutores.add(autor);
                    }

                    ProducaoBibliografica producao = new LivroPublicado(arrayListAutores,titulo,ano);
                    this.pesquisadorLogado.addProducao(producao);
                }

            }


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private void carregaCapitulosPublicados(File arquivo){
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(arquivo);

            doc.getDocumentElement().normalize();

            String livroPublicado = "CAPITULO-DE-LIVRO-PUBLICADO";

            NodeList livrosPublicados = doc.getElementsByTagName(livroPublicado);

            for (int temp = 0; temp < livrosPublicados.getLength(); temp++) {
                ArrayList<String> arrayListAutores = new ArrayList<>();

                Node node = livrosPublicados.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    String titulo = "";
                    String ano = "";
                    NodeList dadosBasicos = ((Element) node).getElementsByTagName("DADOS-BASICOS-DO-CAPITULO");
                    for (int i = 0; i < dadosBasicos.getLength(); i++) {
                        titulo = dadosBasicos.item(i).getAttributes().getNamedItem("TITULO-DO-CAPITULO-DO-LIVRO").getTextContent();
                        ano = dadosBasicos.item(i).getAttributes().getNamedItem("ANO").getTextContent();
                    }

                    NodeList autores = ((Element) node).getElementsByTagName("AUTORES");
                    for (int i = 0; i < autores.getLength(); i++) {
                        String autor = autores.item(i).getAttributes().getNamedItem("NOME-COMPLETO-DO-AUTOR").getTextContent();
                        arrayListAutores.add(autor);
                    }

                    ProducaoBibliografica producao = new CapituloPublicado(arrayListAutores,titulo,ano);
                    this.pesquisadorLogado.addProducao(producao);
                }

            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
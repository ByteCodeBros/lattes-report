package entidades;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class AtuacoesProfissionais {
    private ArrayList<AtuacaoProfissional> vetAtuacaoProfissional;

    public AtuacoesProfissionais() {
        this.vetAtuacaoProfissional = new ArrayList<>();
    }

    public AtuacoesProfissionais(Document doc) {
        this();
        NodeList nodeListAtuacao = doc.getElementsByTagName("ATUACAO-PROFISSIONAL");
        for (int i = 0; i < nodeListAtuacao.getLength(); i++) {       
            NodeList nodeList = doc.getElementsByTagName("VINCULOS");
            for (int j = 0; j < nodeList.getLength(); j++) {
                try {
                    AtuacaoProfissional atuacaoProfissional = new AtuacaoProfissional();
                    atuacaoProfissional.setNomeInstituicao(nodeListAtuacao.item(i).getAttributes().getNamedItem("NOME-INSTITUICAO").getTextContent());
                    atuacaoProfissional.setVinculo(nodeList.item(j).getAttributes()
                            .getNamedItem("OUTRO-ENQUADRAMENTO-FUNCIONAL-INFORMADO").getTextContent() + ". "
                            + nodeList.item(j).getAttributes().getNamedItem("OUTRAS-INFORMACOES").getTextContent());
                    atuacaoProfissional.setMesInicio(nodeList.item(j).getAttributes()
                    .getNamedItem("MES-INICIO").getTextContent());
                    atuacaoProfissional.setAnoInicio(nodeList.item(j).getAttributes()
                    .getNamedItem("ANO-INICIO").getTextContent());
                    atuacaoProfissional.setMesFim(nodeList.item(j).getAttributes()
                    .getNamedItem("MES-FIM").getTextContent());
                    atuacaoProfissional.setAnoFim(nodeList.item(j).getAttributes()
                    .getNamedItem("ANO-FIM").getTextContent());
                    this.vetAtuacaoProfissional.add(atuacaoProfissional);
                } catch (Exception e) {
                }
            }
        }

        // NodeList nodeList =
        // doc.getElementsByTagName("ENSINO-FUNDAMENTAL-PRIMEIRO-GRAU");
        // try {
        // this.ensinoFundamental =
        // nodeList.item(0).getAttributes().getNamedItem("NOME-INSTITUICAO").getTextContent();
        // } catch (Exception e) {
        // }

        // nodeList = doc.getElementsByTagName("ENSINO-MEDIO-SEGUNDO-GRAU");
        // try {
        // this.ensinoMedio =
        // nodeList.item(0).getAttributes().getNamedItem("NOME-INSTITUICAO").getTextContent();
        // } catch (Exception e) {
        // }

        // nodeList = doc.getElementsByTagName("CURSO-TECNICO-PROFISSIONALIZANTE");
        // for (int i = 0; i < nodeList.getLength(); i++) {
        // try {
        // vetTecnico.add(((nodeList.item(i).getAttributes().getNamedItem("NOME-CURSO").getTextContent()
        // == null ||
        // nodeList.item(i).getAttributes().getNamedItem("NOME-CURSO").getTextContent().isBlank()
        // ||
        // nodeList.item(i).getAttributes().getNamedItem("NOME-CURSO").getTextContent().isEmpty())
        // ?
        // nodeList.item(i).getAttributes().getNamedItem("NOME-INSTITUICAO").getTextContent()
        // :
        // nodeList.item(i).getAttributes().getNamedItem("NOME-CURSO").getTextContent()));
        // } catch (Exception e) {
        // }
        // }
        // nodeList = doc.getElementsByTagName("GRADUACAO");
        // for (int i = 0; i < nodeList.getLength(); i++) {
        // try {
        // vetGraduacao.add(nodeList.item(i).getAttributes().getNamedItem("NOME-CURSO").getTextContent());
        // } catch (Exception e) {
        // }
        // }
        // nodeList = doc.getElementsByTagName("MESTRADO");
        // for (int i = 0; i < nodeList.getLength(); i++) {
        // try {
        // vetMestrado.add(nodeList.item(i).getAttributes().getNamedItem("NOME-CURSO").getTextContent());
        // } catch (Exception e) {
        // }
        // }
        // nodeList = doc.getElementsByTagName("DOUTORADO");
        // for (int i = 0; i < nodeList.getLength(); i++) {
        // try {
        // vetDoutorado.add(nodeList.item(i).getAttributes().getNamedItem("NOME-CURSO").getTextContent());
        // } catch (Exception e) {
        // }
        // }
    }

    public ArrayList<AtuacaoProfissional> getVetAtuacaoProfissional() {
        return vetAtuacaoProfissional;
    }

    public void setVetAtuacaoProfissional(ArrayList<AtuacaoProfissional> vetAtuacaoProfissional) {
        this.vetAtuacaoProfissional = vetAtuacaoProfissional;
    }

    @Override
    public String toString() {
        String resultado = "";
        for (AtuacaoProfissional atuacaoProfissional : this.vetAtuacaoProfissional) {
            resultado += atuacaoProfissional.toString()+"\n\n";
        }   
        return resultado;
    }

}

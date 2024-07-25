package entidades;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class FormacaoAcademica {
    private ArrayList<String> vetGraduacao;
    private ArrayList<String> vetEspecializacao;
    private ArrayList<String> vetMestrado;
    private ArrayList<String> vetDoutorado;
    private ArrayList<String> vetTecnico;
    private String ensinoMedio;
    private String ensinoFundamental;

    public FormacaoAcademica(){
        this.vetTecnico = new ArrayList<>();
        this.vetGraduacao = new ArrayList<>();
        this.vetMestrado = new ArrayList<>();
        this.vetEspecializacao = new ArrayList<>();
        this.vetDoutorado = new ArrayList<>();    
        
        
    }

    public FormacaoAcademica(Document doc) {
        this();
        NodeList nodeList = doc.getElementsByTagName("ENSINO-FUNDAMENTAL-PRIMEIRO-GRAU");
        try {
            this.ensinoFundamental = nodeList.item(0).getAttributes().getNamedItem("NOME-INSTITUICAO").getTextContent();
        } catch (Exception e) {
        }   

        nodeList = doc.getElementsByTagName("ENSINO-MEDIO-SEGUNDO-GRAU");      
        try {
            this.ensinoMedio = nodeList.item(0).getAttributes().getNamedItem("NOME-INSTITUICAO").getTextContent();
        } catch (Exception e) {
        }            
        
        nodeList = doc.getElementsByTagName("CURSO-TECNICO-PROFISSIONALIZANTE");
        for (int i = 0; i < nodeList.getLength(); i++) {
            try {
                vetTecnico.add(((nodeList.item(i).getAttributes().getNamedItem("NOME-CURSO").getTextContent() == null || nodeList.item(i).getAttributes().getNamedItem("NOME-CURSO").getTextContent().isBlank() || nodeList.item(i).getAttributes().getNamedItem("NOME-CURSO").getTextContent().isEmpty()) ? nodeList.item(i).getAttributes().getNamedItem("NOME-INSTITUICAO").getTextContent() : nodeList.item(i).getAttributes().getNamedItem("NOME-CURSO").getTextContent()));                
            } catch (Exception e) {
            }            
        } 
        nodeList = doc.getElementsByTagName("GRADUACAO");
        for (int i = 0; i < nodeList.getLength(); i++) {
            try {
                vetGraduacao.add(nodeList.item(i).getAttributes().getNamedItem("NOME-CURSO").getTextContent());                
            } catch (Exception e) {
            }            
        } 
        nodeList = doc.getElementsByTagName("MESTRADO");
        for (int i = 0; i < nodeList.getLength(); i++) {
            try {
                vetMestrado.add(nodeList.item(i).getAttributes().getNamedItem("NOME-CURSO").getTextContent());                
            } catch (Exception e) {
            }            
        }  
        nodeList = doc.getElementsByTagName("DOUTORADO");
        for (int i = 0; i < nodeList.getLength(); i++) {
            try {
                vetDoutorado.add(nodeList.item(i).getAttributes().getNamedItem("NOME-CURSO").getTextContent());                
            } catch (Exception e) {
            }            
        }       
    }

    public ArrayList<String> getVetGraduacao() {
        return vetGraduacao;
    }

    public void setVetGraduacao(ArrayList<String> vetGraduacao) {
        this.vetGraduacao = vetGraduacao;
    }

    public ArrayList<String> getVetEspecializacao() {
        return vetEspecializacao;
    }

    public void setVetEspecializacao(ArrayList<String> vetEspecializacao) {
        this.vetEspecializacao = vetEspecializacao;
    }

    public ArrayList<String> getVetDoutorado() {
        return vetDoutorado;
    }

    public void setVetDoutorado(ArrayList<String> vetDoutorado) {
        this.vetDoutorado = vetDoutorado;
    }

    public ArrayList<String> getVetTecnico() {
        return vetTecnico;
    }

    public void setVetTecnico(ArrayList<String> vetTecnico) {
        this.vetTecnico = vetTecnico;
    }

    public String getEnsinoMedio() {
        return ensinoMedio;
    }

    public void setEnsinoMedio(String ensinoMedio) {
        this.ensinoMedio = ensinoMedio;
    }

    public String getEnsinoFundamental() {
        return ensinoFundamental;
    }

    public void setEnsinoFundamental(String ensinoFundamental) {
        this.ensinoFundamental = ensinoFundamental;
    }

    @Override
    public String toString() {
        return "FormacaoAcademica [vetGraduacao=" + vetGraduacao + ", vetEspecializacao=" + vetEspecializacao
                + ", vetMestrado=" + vetMestrado + ", vetDoutorado=" + vetDoutorado + ", vetTecnico=" + vetTecnico
                + ", ensinoMedio=" + ensinoMedio + ", ensinoFundamental=" + ensinoFundamental + "]";
    }

    
}

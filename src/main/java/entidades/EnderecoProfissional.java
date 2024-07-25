package entidades;

import org.w3c.dom.NodeList;

public class EnderecoProfissional {
    private String nomeInstituicao;
    private String nomeOrgao;
    private String logradouro;
    private String pais;
    private String uf;
    private String telefone;
    private String cep;
    private String cidade;
    private String bairro;
    private String ddd;
    private String homePage;

    public EnderecoProfissional() {
        
    }
    
    public EnderecoProfissional(NodeList node) {
        this.nomeInstituicao = node.item(0).getAttributes().getNamedItem("NOME-INSTITUICAO-EMPRESA").getTextContent();        
        this.nomeOrgao = node.item(0).getAttributes().getNamedItem("NOME-ORGAO").getTextContent();                
        this.logradouro = node.item(0).getAttributes().getNamedItem("LOGRADOURO-COMPLEMENTO").getTextContent();        
        this.cep = node.item(0).getAttributes().getNamedItem("CEP").getTextContent();        
        this.pais = node.item(0).getAttributes().getNamedItem("PAIS").getTextContent();        
        this.uf = node.item(0).getAttributes().getNamedItem("UF").getTextContent();        
        this.cidade = node.item(0).getAttributes().getNamedItem("CIDADE").getTextContent();        
        this.bairro = node.item(0).getAttributes().getNamedItem("BAIRRO").getTextContent();
        this.ddd = node.item(0).getAttributes().getNamedItem("DDD").getTextContent();
        this.telefone = node.item(0).getAttributes().getNamedItem("TELEFONE").getTextContent();
        this.homePage = node.item(0).getAttributes().getNamedItem("HOME-PAGE").getTextContent();
    }

        
    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
    
    public String getNomeInstituicao() {
        return nomeInstituicao;
    }
    public void setNomeInstituicao(String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
    }
    public String getNomeOrgao() {
        return nomeOrgao;
    }
    public void setNomeOrgao(String nomeOrgao) {
        this.nomeOrgao = nomeOrgao;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getDdd() {
        return ddd;
    }
    public void setDdd(String ddd) {
        this.ddd = ddd;
    }
    public String getHomePage() {
        return homePage;
    }
    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

 

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "\nEndereço Profissional:\n"+nomeInstituicao + "\n" + nomeOrgao + "\n"
                + logradouro + "\n" + pais + "\n" + cidade + " - "+uf + "\n" + cep + "\n"
                + cidade + "\n" + bairro + ", (" + ddd + ") "+telefone+"\n" + homePage+"\n";
    }

    


}

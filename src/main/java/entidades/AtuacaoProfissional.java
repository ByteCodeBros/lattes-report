package entidades;

public class AtuacaoProfissional {
    private String nomeInstituicao;
    private String vinculo;
    private String mesInicio;
    private String anoInicio;
    private String mesFim;
    private String anoFim;

    public String getNomeInstituicao() {
        return nomeInstituicao;
    }
    public void setNomeInstituicao(String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
    }
    public String getVinculo() {
        return vinculo;
    }
    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }
    public String getMesInicio() {
        return mesInicio;
    }
    public void setMesInicio(String mesInicio) {
        this.mesInicio = mesInicio;
    }
    public String getAnoInicio() {
        return anoInicio;
    }
    public void setAnoInicio(String anoInicio) {
        this.anoInicio = anoInicio;
    }
    public String getMesFim() {
        return mesFim;
    }
    public void setMesFim(String mesFim) {
        this.mesFim = mesFim;
    }
    public String getAnoFim() {
        return anoFim;
    }
    public void setAnoFim(String anoFim) {
        this.anoFim = anoFim;
    }
    @Override
    public String toString() {
        return nomeInstituicao + "\n" + vinculo + "\nMês de Início: "
                + mesInicio + " Ano de Início: " + anoInicio + "\nMês de Fim: " + mesFim + " Ano de Fim: " + anoFim + "\n";    }

   
  
    
    
}

package entidades;

import java.util.ArrayList;

public class Pesquisador {
    private final String nome;
    private final ArrayList<ProducaoBibliografica> producoesBibliograficas;

    public Pesquisador(String nome){
        this.producoesBibliograficas = new ArrayList<>();
        this.nome = nome;
    }

    public void addProducao(ProducaoBibliografica producaoBibliografica){
        this.producoesBibliograficas.add(producaoBibliografica);
    }

    public ArrayList<ProducaoBibliografica> getProducoesBibliograficas() {
        return producoesBibliograficas;
    }

    public String getNome(){
        return this.nome;
    }


}

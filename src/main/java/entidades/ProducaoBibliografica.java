package entidades;

import java.util.ArrayList;

public abstract class ProducaoBibliografica {
    protected ArrayList<String> autores;
    protected String titulo;
    protected Integer ano;
    protected ArrayList<String> detalhamento;

    public ArrayList<String> getDetalhamento() {
        return detalhamento;
    }

    public void setDetalhamento(ArrayList<String> detalhamento) {
        this.detalhamento = detalhamento;
    }

    public ProducaoBibliografica(ArrayList<String> autores, String titulo, String ano) {
        this.autores = autores;
        this.titulo = titulo;
        this.ano = Integer.parseInt(ano);
        this.detalhamento = new ArrayList<>();
    }

    public String autoresToString(){
        // StringBuilder autoresString = new StringBuilder();
        // Set<String> vetAutores = Set.copyOf(this.autores);

        // for (int i = 0; i < this.autores.size(); i++) {
        // // Iterator<String> iterator = vetAutores.iterator();
        // // while(iterator.hasNext()){
        //     autoresString.append(this.autores.get(i)).append(" | ");
        //     // autoresString.append(iterator.next()).append(" | ");
        // }

        // autoresString.append(this.autores.get(this.autores.size()-1)).append(";");

        return this.autores.toString().replace("[", "").replace("]", "").replace(";", "");
    }

    public Integer getAno() {
        return ano;
    }

    public ArrayList<String> getAutores() {
        return autores;
    }

    public String getTitulo() {
        return titulo;
    }

    public abstract String getTipo();

    @Override
    public String toString() {
        return "ProducaoBibliografica{" +
                "autores=" + autores +
                ", titulo='" + titulo + '\'' +
                ", ano='" + ano + '\'';
    }
}

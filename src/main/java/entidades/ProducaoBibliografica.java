package entidades;

import java.util.ArrayList;

public abstract class ProducaoBibliografica {
    protected ArrayList<String> autores;
    protected String titulo;
    protected Integer ano;

    public ProducaoBibliografica(ArrayList<String> autores, String titulo, String ano) {
        this.autores = autores;
        this.titulo = titulo;
        this.ano = Integer.parseInt(ano);
    }

    public String autoresToString(){
        StringBuilder autoresString = new StringBuilder();

        for (int i = 0; i < this.autores.size()-2; i++) {
            autoresString.append(this.autores.get(i)).append(" | ");
        }

        autoresString.append(this.autores.get(this.autores.size()-1)).append(";");

        return autoresString.toString();
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

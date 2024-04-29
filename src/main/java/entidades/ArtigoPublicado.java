package entidades;

import java.util.ArrayList;

public class ArtigoPublicado extends ProducaoBibliografica{
    public ArtigoPublicado(ArrayList<String> autores, String titulo, String ano) {
        super(autores, titulo, ano);
    }

    @Override
    public String getTipo() {
        return "Artigo Publicado; ";
    }

    @Override
    public String toString() {
        return super.toString() +
                ", tipo=ARTIGOPUBLICADO" +
                '}';
    }
}

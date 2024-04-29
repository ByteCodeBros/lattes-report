package entidades;

import java.util.ArrayList;

public class CapituloPublicado extends ProducaoBibliografica{
    public CapituloPublicado(ArrayList<String> autores, String titulo, String ano) {
        super(autores, titulo, ano);
    }

    @Override
    public String getTipo() {
        return "Capítulo Publicado; ";
    }

    @Override
    public String toString() {
        return super.toString() +
                ", tipo=CAPITULO-PUBLICADO" +
                '}';
    }
}

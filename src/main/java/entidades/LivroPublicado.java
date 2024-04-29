package entidades;

import java.util.ArrayList;

public class LivroPublicado extends ProducaoBibliografica{
    public LivroPublicado(ArrayList<String> autores, String titulo, String ano) {
        super(autores, titulo, ano);
    }

    @Override
    public String getTipo() {
        return "Livro Publicado; ";
    }

    @Override
    public String toString() {
        return super.toString() +
                ", tipo=LIVRO-PUBLICADO" +
                '}';
    }
}

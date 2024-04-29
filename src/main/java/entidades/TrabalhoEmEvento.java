package entidades;

import java.util.ArrayList;

public class TrabalhoEmEvento extends ProducaoBibliografica{
    public TrabalhoEmEvento(ArrayList<String> autores, String titulo, String ano) {
        super(autores, titulo, ano);
    }

    @Override
    public String getTipo() {
        return "Trabalho em Evento; ";
    }

    @Override
    public String toString() {
        return super.toString() +
                ", tipo=TRABALHO-EM-EVENTO" +
                '}';
    }
}

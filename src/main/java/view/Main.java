package view;

import servico.Gerenciador;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Gerenciador gerenciador = new Gerenciador();
        gerenciador.preCadastro();
        System.out.println("\nBEM-VINDO AO GERADOR DE RELATÓRIOS LATTES");

        boolean loopMain = true;
        while(loopMain) {
            System.out.println(
                    """
                    
                    Escolha uma das opções a seguir:
                    1-Escolher pesquisador;
                    2-Gerar relatório;
                    3-Sair.
                    """);

            Scanner entrada = new Scanner(System.in);
            int escolhaDoUsuario = entrada.nextInt();

            switch (escolhaDoUsuario) {
                case 1 -> gerenciador.escolherPesquisador();
                case 2 -> gerenciador.gerarRelatorio();
                case 3 -> loopMain = false;
                default -> System.out.println("Insira um valor válido\n");
            }
        }
    }
}

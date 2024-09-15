import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Exercicio1.GerenciadorTarefas gerenciadorTarefas = new Exercicio1.GerenciadorTarefas();
        Exercicio2.HistoricoNavegacao historicoNavegacao = new Exercicio2.HistoricoNavegacao(5);
        Exercicio3.TextEditor editorTexto3 = new Exercicio3.TextEditor();
        Exercicio4.TextEditor editorTexto4 = new Exercicio4.TextEditor();

        while (true) {
            LimpaConsole.limpar();
            System.out.println("Escolha uma opção:");
            System.out.println("1. Gerenciar Tarefas");
            System.out.println("2. Histórico de Navegação");
            System.out.println("3. Controle de Reversão");
            System.out.println("4. Editor de Texto com Undo e Redo");
            System.out.println("5. Jogo de Cartas");
            System.out.println("6. Simulador de Fila de Atendimento");
            System.out.println("7. Sistema de Gerenciamento de Impressões");
            System.out.println("8. Fila de Processos");
            System.out.println("0. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            LimpaConsole.limpar(); // Limpar o console antes de entrar na opção

            switch (opcao) {
                case 1:
                    Exercicio1.executar(gerenciadorTarefas);
                    break;
                case 2:
                    Exercicio2.executar(historicoNavegacao);
                    break;
                case 3:
                    Exercicio3.executar(editorTexto3);
                    break;
                case 4:
                    Exercicio4.executar(editorTexto4);
                    break;
                case 5:
                    Exercicio5.main(null);
                    break;
                case 6:
                    Exercicio6.main(null);
                    break;
                case 7:
                    Exercicio7.main(null);
                    break;
                case 8:
                    Exercicio8.main(null);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
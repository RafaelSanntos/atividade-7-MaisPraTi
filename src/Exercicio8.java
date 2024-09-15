import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Exercicio8 {
    static class Processo {
        String nome;

        public Processo(String nome) {
            this.nome = nome;
        }
    }

    static class GerenciadorProcessos {
        Queue<Processo> fila = new LinkedList<>();

        public GerenciadorProcessos() {
            // Adicionando alguns processos iniciais
            adicionarProcesso("Processo 1");
            adicionarProcesso("Processo 2");
            adicionarProcesso("Processo 3");
        }

        public void adicionarProcesso(String nome) {
            fila.add(new Processo(nome));
            System.out.println("Processo \"" + nome + "\" adicionado à fila.");
        }

        public void executarProcesso() {
            Processo processo = fila.poll();
            if (processo != null) {
                System.out.println("Executando: " + processo.nome);
            } else {
                System.out.println("Nenhum processo na fila.");
            }
        }

        public void listarProcessos() {
            if (fila.isEmpty()) {
                System.out.println("Nenhum processo na fila.");
            } else {
                System.out.println("Processos na fila:");
                for (Processo processo : fila) {
                    System.out.println("- " + processo.nome);
                }
            }
        }
    }

    public static void executar(GerenciadorProcessos gerenciador) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nGerenciamento de Processos:");
            gerenciador.listarProcessos();
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Adicionar processo");
            System.out.println("2. Executar processo");
            System.out.println("3. Voltar para o menu principal");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do processo:");
                    String nome = scanner.nextLine();
                    gerenciador.adicionarProcesso(nome);
                    break;
                case 2:
                    gerenciador.executarProcesso();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void main(String[] args) {
        GerenciadorProcessos gerenciador = new GerenciadorProcessos();
        executar(gerenciador);
    }
}

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Exercicio7 {
    static class TrabalhoImpressao {
        String nome;

        public TrabalhoImpressao(String nome) {
            this.nome = nome;
        }
    }

    static class GerenciamentoImpressao {
        Queue<TrabalhoImpressao> fila = new LinkedList<>();

        public void adicionarTrabalho(String nome) {
            TrabalhoImpressao trabalho = new TrabalhoImpressao(nome);
            fila.add(trabalho);
        }

        public void processarTrabalho() {
            TrabalhoImpressao trabalho = fila.poll();
            if (trabalho != null) {
                System.out.println("Processando: " + trabalho.nome);
            } else {
                System.out.println("Nenhum trabalho na fila.");
            }
        }

        public void listarTrabalhos() {
            for (TrabalhoImpressao trabalho : fila) {
                System.out.println(trabalho.nome);
            }
        }
    }

    public static void executar(GerenciamentoImpressao gerenciamento) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Gerenciamento de Impressão:");
            gerenciamento.listarTrabalhos();
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Adicionar trabalho de impressão");
            System.out.println("2. Processar trabalho de impressão");
            System.out.println("3. Voltar para o menu principal");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do trabalho de impressão:");
                    String nome = scanner.nextLine();
                    gerenciamento.adicionarTrabalho(nome);
                    System.out.println("Trabalho de impressão adicionado");
                    break;
                case 2:
                    gerenciamento.processarTrabalho();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void main(String[] args) {
        GerenciamentoImpressao gerenciamento = new GerenciamentoImpressao();
        executar(gerenciamento);
    }
}

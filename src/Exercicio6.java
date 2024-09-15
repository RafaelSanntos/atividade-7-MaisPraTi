import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Exercicio6 {
    static class Cliente {
        String nome;

        public Cliente(String nome) {
            this.nome = nome;
        }
    }

    static class FilaAtendimento {
        Queue<Cliente> fila = new LinkedList<>();

        public void adicionarCliente(String nome) {
            Cliente cliente = new Cliente(nome);
            fila.add(cliente);
        }

        public void chamarProximoCliente() {
            Cliente cliente = fila.poll();
            if (cliente != null) {
                System.out.println("Atendendo: " + cliente.nome);
            } else {
                System.out.println("Nenhum cliente na fila.");
            }
        }

        public void listarClientes() {
            for (Cliente cliente : fila) {
                System.out.println(cliente.nome);
            }
        }
    }

    public static void executar(FilaAtendimento fila) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Fila de Atendimento:");
            fila.listarClientes();
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Adicionar cliente");
            System.out.println("2. Chamar próximo cliente");
            System.out.println("3. Voltar para o menu principal");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do cliente:");
                    String nome = scanner.nextLine();
                    fila.adicionarCliente(nome);
                    System.out.println("Cliente adicionado");
                    break;
                case 2:
                    fila.chamarProximoCliente();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void main(String[] args) {
        FilaAtendimento fila = new FilaAtendimento();
        executar(fila);
    }
}

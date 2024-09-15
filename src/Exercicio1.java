import java.util.Scanner;

public class Exercicio1 {
    static class Tarefa {
        String descricao;
        boolean concluida;
        Tarefa proxima;

        public Tarefa(String descricao) {
            this.descricao = descricao;
            this.concluida = false;
            this.proxima = null;
        }
    }

    static class GerenciadorTarefas {
        Tarefa primeira;

        public void adicionarTarefa(String descricao) {
            Tarefa novaTarefa = new Tarefa(descricao);
            if (primeira == null) {
                primeira = novaTarefa;
            } else {
                Tarefa atual = primeira;
                while (atual.proxima != null) {
                    atual = atual.proxima;
                }
                atual.proxima = novaTarefa;
            }
        }

        public void removerTarefa(int indice) {
            if (primeira == null) return;

            if (indice == 1) {
                primeira = primeira.proxima;
                return;
            }

            Tarefa atual = primeira;
            for (int i = 1; i < indice - 1; i++) {
                if (atual.proxima == null) return;
                atual = atual.proxima;
            }

            if (atual.proxima != null) {
                atual.proxima = atual.proxima.proxima;
            }
        }

        public void marcarConcluida(int indice) {
            Tarefa atual = primeira;
            for (int i = 1; i < indice; i++) {
                if (atual == null) return;
                atual = atual.proxima;
            }
            if (atual != null) {
                atual.concluida = true;
            }
        }

        public void listarTarefas() {
            if (primeira == null) {
                System.out.println("Nenhuma tarefa adicionada!");
            } else {
                Tarefa atual = primeira;
                int indice = 1;
                while (atual != null) {
                    System.out.println(indice + ". " + atual.descricao + (atual.concluida ? " (Concluída)" : ""));
                    atual = atual.proxima;
                    indice++;
                }
            }
        }

        public boolean tarefaExiste(int indice) {
            Tarefa atual = primeira;
            for (int i = 1; i < indice; i++) {
                if (atual == null) return false;
                atual = atual.proxima;
            }
            return atual != null;
        }
    }

    public static void executar(GerenciadorTarefas gerenciador) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            LimpaConsole.limpar();
            System.out.println("Lista de tarefas:");
            gerenciador.listarTarefas();
            System.out.println("\nEscolha uma opção:");
            if (gerenciador.primeira == null) {
                System.out.println("1. Adicionar tarefa");
                System.out.println("2. Voltar para o menu principal");
            } else {
                System.out.println("1. Adicionar tarefa");
                System.out.println("2. Remover tarefa");
                System.out.println("3. Marcar tarefa como concluída");
                System.out.println("4. Voltar para o menu principal");
            }

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    LimpaConsole.limpar();
                    System.out.println("Digite a descrição da tarefa:");
                    String descricao = scanner.nextLine();
                    gerenciador.adicionarTarefa(descricao);
                    LimpaConsole.limpar();
                    System.out.println("Tarefa adicionada");
                    break;
                case 2:
                    if (gerenciador.primeira == null) {
                        return;
                    } else {
                        while (true) {
                            LimpaConsole.limpar();
                            System.out.println("Lista de tarefas:");
                            gerenciador.listarTarefas();
                            System.out.println("\nDigite o número da tarefa que deseja remover:");
                            int indice = scanner.nextInt();
                            scanner.nextLine(); // Consumir a nova linha
                            if (gerenciador.tarefaExiste(indice)) {
                                gerenciador.removerTarefa(indice);
                                LimpaConsole.limpar();
                                System.out.println("Tarefa " + indice + " removida");
                                break;
                            } else {
                                LimpaConsole.limpar();
                                System.out.println("Tarefa " + indice + " não existe. Tente novamente.");
                            }
                        }
                    }
                    break;
                case 3:
                    if (gerenciador.primeira != null) {
                        while (true) {
                            LimpaConsole.limpar();
                            System.out.println("Lista de tarefas:");
                            gerenciador.listarTarefas();
                            System.out.println("\nDigite o número da tarefa a ser marcada como concluída:");
                            int indice = scanner.nextInt();
                            scanner.nextLine(); // Consumir a nova linha
                            if (gerenciador.tarefaExiste(indice)) {
                                gerenciador.marcarConcluida(indice);
                                LimpaConsole.limpar();
                                System.out.println("Tarefa " + indice + " marcada como concluída");
                                break;
                            } else {
                                LimpaConsole.limpar();
                                System.out.println("Tarefa " + indice + " não existe. Tente novamente.");
                            }
                        }
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}

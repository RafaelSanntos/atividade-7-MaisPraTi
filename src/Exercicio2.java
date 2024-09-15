import java.util.Scanner;

public class Exercicio2 {
    static class URL {
        String endereco;
        URL proxima;

        public URL(String endereco) {
            this.endereco = endereco;
            this.proxima = null;
        }
    }

    static class HistoricoNavegacao {
        URL primeira;
        int tamanhoMaximo;
        int tamanhoAtual;

        public HistoricoNavegacao(int tamanhoMaximo) {
            this.tamanhoMaximo = tamanhoMaximo;
            this.tamanhoAtual = 0;
        }

        public void adicionarURL(String endereco) {
            URL novaURL = new URL(endereco);
            if (primeira == null) {
                primeira = novaURL;
            } else {
                URL atual = primeira;
                while (atual.proxima != null) {
                    atual = atual.proxima;
                }
                atual.proxima = novaURL;
            }
            tamanhoAtual++;
            if (tamanhoAtual > tamanhoMaximo) {
                removerURLAntiga();
            }
        }

        public void removerURLAntiga() {
            if (primeira != null) {
                primeira = primeira.proxima;
                tamanhoAtual--;
            }
        }

        public void listarHistorico() {
            if (primeira == null) {
                System.out.println("Nada no histórico");
            } else {
                URL atual = primeira;
                int indice = 1;
                while (atual != null) {
                    System.out.println(indice + ". " + atual.endereco);
                    atual = atual.proxima;
                    indice++;
                }
            }
        }

        public boolean urlExiste(int indice) {
            URL atual = primeira;
            for (int i = 1; i < indice; i++) {
                if (atual == null) return false;
                atual = atual.proxima;
            }
            return atual != null;
        }
    }

    public static void executar(HistoricoNavegacao historico) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            LimpaConsole.limpar();
            System.out.println("Histórico de navegação:");
            historico.listarHistorico();
            System.out.println("\nEscolha uma opção:");
            if (historico.primeira == null) {
                System.out.println("1. Adicionar URL");
                System.out.println("2. Voltar para o menu principal");
            } else {
                System.out.println("1. Adicionar URL");
                System.out.println("2. Remover URL");
                System.out.println("3. Voltar para o menu principal");
            }

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    LimpaConsole.limpar();
                    System.out.println("Digite o endereço da URL:");
                    String endereco = scanner.nextLine();
                    historico.adicionarURL(endereco);
                    LimpaConsole.limpar();
                    System.out.println("URL adicionada");
                    break;
                case 2:
                    if (historico.primeira == null) {
                        return;
                    } else {
                        while (true) {
                            LimpaConsole.limpar();
                            System.out.println("Histórico de navegação:");
                            historico.listarHistorico();
                            System.out.println("\nDigite o número da URL que deseja remover:");
                            int indice = scanner.nextInt();
                            scanner.nextLine(); // Consumir a nova linha
                            if (historico.urlExiste(indice)) {
                                historico.removerURLAntiga();
                                LimpaConsole.limpar();
                                System.out.println("URL " + indice + " removida");
                                break;
                            } else {
                                LimpaConsole.limpar();
                                System.out.println("URL " + indice + " não existe. Tente novamente.");
                            }
                        }
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void main(String[] args) {
        HistoricoNavegacao historico = new HistoricoNavegacao(5);
        executar(historico);
    }
}

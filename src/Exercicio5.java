import java.util.Scanner;

public class Exercicio5 {
    static class Carta {
        String nome;
        Carta proxima;

        public Carta(String nome) {
            this.nome = nome;
            this.proxima = null;
        }
    }

    static class Mao {
        Carta primeira;

        public void adicionarCarta(String nome) {
            Carta novaCarta = new Carta(nome);
            if (primeira == null) {
                primeira = novaCarta;
            } else {
                Carta atual = primeira;
                while (atual.proxima != null) {
                    atual = atual.proxima;
                }
                atual.proxima = novaCarta;
            }
        }

        public void removerCarta(String nome) {
            if (primeira == null) return;

            if (primeira.nome.equals(nome)) {
                primeira = primeira.proxima;
                return;
            }

            Carta atual = primeira;
            while (atual.proxima != null && !atual.proxima.nome.equals(nome)) {
                atual = atual.proxima;
            }

            if (atual.proxima != null) {
                atual.proxima = atual.proxima.proxima;
            }
        }

        public void listarCartas() {
            if (primeira == null) {
                System.out.println("Nenhuma carta na mão!");
            } else {
                Carta atual = primeira;
                while (atual != null) {
                    System.out.println(atual.nome);
                    atual = atual.proxima;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Mao mao = new Mao();

        mao.adicionarCarta("Ás de Espadas");
        mao.adicionarCarta("Rei de Copas");

        System.out.println("Cartas na mão:");
        mao.listarCartas();

        System.out.println("\nRemovendo 'Ás de Espadas'...");
        mao.removerCarta("Ás de Espadas");

        System.out.println("Cartas na mão:");
        mao.listarCartas();
    }
}

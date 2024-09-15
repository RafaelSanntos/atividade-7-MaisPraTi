import java.util.Scanner;

public class Exercicio3 {
    // Classe para representar uma ação (inserção ou remoção de texto)
    static class Action {
        String type;  // Tipo de ação: "insert" ou "delete"
        String text;  // O texto inserido ou deletado
        int position; // A posição no texto onde a ação foi realizada
        Action next;  // Referência para o próximo nó (ação)

        public Action(String type, String text, int position) {
            this.type = type;
            this.text = text;
            this.position = position;
            this.next = null;
        }
    }

    // Classe que representa a lista encadeada para o sistema de undo
    static class UndoStack {
        private Action top;  // Referência para o topo da lista (última ação realizada)

        // Adiciona uma nova ação ao topo da lista
        public void push(String type, String text, int position) {
            Action newAction = new Action(type, text, position);
            newAction.next = top;
            top = newAction;
        }

        // Desfaz a última ação e retorna a ação desfeita
        public Action pop() {
            if (top == null) {
                return null;  // Não há ação para desfazer
            }
            Action lastAction = top;
            top = top.next;  // Move o topo para o próximo nó
            return lastAction;
        }

        // Verifica se a lista de ações está vazia
        public boolean isEmpty() {
            return top == null;
        }
    }

    // Classe que simula o editor de texto
    static class TextEditor {
        private StringBuilder text;  // Texto sendo editado
        private UndoStack undoStack; // Pilha de ações para desfazer

        public TextEditor() {
            this.text = new StringBuilder();
            this.undoStack = new UndoStack();
        }

        // Método para inserir texto no editor
        public void insert(String newText) {
            if (text.length() > 0) {
                text.append(" ");
            }
            int position = text.length();
            text.append(newText);
            // Armazena a ação de inserção no undo stack
            undoStack.push("insert", newText, position);
        }

        // Método para deletar todo o texto
        public void delete() {
            String deletedText = text.toString();
            text.setLength(0);
            // Armazena a ação de deleção no undo stack
            undoStack.push("delete", deletedText, 0);
        }

        // Método para desfazer a última ação
        public void undo() {
            Action lastAction = undoStack.pop();
            if (lastAction == null) {
                System.out.println("Nada para desfazer.");
                return;
            }

            // Desfaz a ação dependendo do tipo
            if (lastAction.type.equals("insert")) {
                text.delete(lastAction.position, lastAction.position + lastAction.text.length());
            } else if (lastAction.type.equals("delete")) {
                text.insert(lastAction.position, lastAction.text);
            }
        }

        // Exibe o texto atual no editor
        public void showText() {
            System.out.println("Texto atual: " + text.toString());
        }
    }

    public static void executar(TextEditor editor) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            LimpaConsole.limpar();
            System.out.println("Editor de Texto:");
            editor.showText();
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Inserir texto");
            System.out.println("2. Deletar texto");
            System.out.println("3. Desfazer última ação");
            System.out.println("4. Voltar para o menu principal");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    LimpaConsole.limpar();
                    System.out.println("Digite o texto a ser inserido:");
                    String texto = scanner.nextLine();
                    editor.insert(texto);
                    LimpaConsole.limpar();
                    System.out.println("Texto inserido");
                    break;
                case 2:
                    LimpaConsole.limpar();
                    editor.delete();
                    System.out.println("Texto deletado");
                    break;
                case 3:
                    LimpaConsole.limpar();
                    editor.undo();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        executar(editor);
    }
}

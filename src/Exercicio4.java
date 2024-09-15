import java.util.Scanner;

public class Exercicio4 {
    // Classe para representar uma ação (inserção ou remoção de texto)
    static class Action {
        String type;  // Tipo de ação: "insert" ou "delete"
        String text;  // O texto inserido ou deletado
        int position; // A posição no texto onde a ação foi realizada
        Action next;  // Referência para o próximo nó (ação)
        Action prev;  // Referência para o nó anterior (ação)

        public Action(String type, String text, int position) {
            this.type = type;
            this.text = text;
            this.position = position;
            this.next = null;
            this.prev = null;
        }
    }

    // Classe que representa a lista duplamente encadeada para o sistema de undo e redo
    static class UndoRedoStack {
        private Action current;  // Referência para a ação atual
        private Action redoTop;  // Referência para a ação do topo da pilha de redo

        // Adiciona uma nova ação ao topo da lista
        public void push(String type, String text, int position) {
            Action newAction = new Action(type, text, position);
            if (current != null) {
                current.next = newAction;
                newAction.prev = current;
            }
            current = newAction;
            redoTop = null;  // Limpa a pilha de redo quando uma nova ação é adicionada
        }

        // Desfaz a última ação e retorna a ação desfeita
        public Action undo() {
            if (current == null) {
                return null;  // Não há ação para desfazer
            }
            Action lastAction = current;
            current = current.prev;  // Move para a ação anterior
            if (current != null) {
                current.next = null;
            }
            lastAction.next = redoTop;  // Adiciona a ação desfeita ao topo da pilha de redo
            if (redoTop != null) {
                redoTop.prev = lastAction;
            }
            redoTop = lastAction;
            return lastAction;
        }

        // Refaz a última ação desfeita e retorna a ação refeita
        public Action redo() {
            if (redoTop == null) {
                return null;  // Não há ação para refazer
            }
            Action nextAction = redoTop;
            redoTop = redoTop.next;  // Move para a próxima ação na pilha de redo
            if (redoTop != null) {
                redoTop.prev = null;
            }
            nextAction.next = null;
            if (current != null) {
                current.next = nextAction;
                nextAction.prev = current;
            }
            current = nextAction;
            return nextAction;
        }

        // Verifica se há ações para desfazer
        public boolean canUndo() {
            return current != null;
        }

        // Verifica se há ações para refazer
        public boolean canRedo() {
            return redoTop != null;
        }
    }

    // Classe que simula o editor de texto
    static class TextEditor {
        private StringBuilder text;  // Texto sendo editado
        private UndoRedoStack undoRedoStack; // Pilha de ações para desfazer e refazer

        public TextEditor() {
            this.text = new StringBuilder();
            this.undoRedoStack = new UndoRedoStack();
        }

        // Método para inserir texto no editor
        public void insert(String newText) {
            if (text.length() > 0) {
                text.append(" ");
            }
            int position = text.length();
            text.append(newText);
            // Armazena a ação de inserção no undoRedoStack
            undoRedoStack.push("insert", newText, position);
        }

        // Método para deletar todo o texto
        public void delete() {
            String deletedText = text.toString();
            text.setLength(0);
            // Armazena a ação de deleção no undoRedoStack
            undoRedoStack.push("delete", deletedText, 0);
        }

        // Método para desfazer a última ação
        public void undo() {
            Action lastAction = undoRedoStack.undo();
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

        // Método para refazer a última ação desfeita
        public void redo() {
            Action nextAction = undoRedoStack.redo();
            if (nextAction == null) {
                System.out.println("Nada para refazer.");
                return;
            }

            // Refaz a ação dependendo do tipo
            if (nextAction.type.equals("insert")) {
                text.insert(nextAction.position, nextAction.text);
            } else if (nextAction.type.equals("delete")) {
                text.delete(nextAction.position, nextAction.position + nextAction.text.length());
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
            System.out.println("4. Refazer última ação");
            System.out.println("5. Voltar para o menu principal");

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
                    LimpaConsole.limpar();
                    editor.redo();
                    break;
                case 5:
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

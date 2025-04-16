
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Biblioteca biblio = new Biblioteca();

    public static void main(String[] args) {
        String menu = """
                ===== SYSBIBLIO =====
                Escolha uma das opções abaixo:
                1 - Adicionar Novo Livro
                2 - Pesquisar Livro por Título
                3 - Listar Todos os Livros
                4 - Remover Livro por Título
                0 - Sair
                """;
        int opcao;
        Scanner lerTeclado = new Scanner(System.in);
        do {
            // System.out.println(menu);
            // opcao = lerTeclado.nextInt();
            // lerTeclado.nextLine();
            opcao = inputNumerico(lerTeclado, menu);
            switch (opcao) {
                case 1:
                    System.out.println("Opção 1 - Adicionar Novo Livro");
                    adicionar(lerTeclado);
                    break;
                case 2:
                    System.out.println("Opção 2 - Pesquisar Livro por Título");
                    break;
                case 3:
                    System.out.println("Opção 3 - Listar Todos os Livros");
                    pesquisarTodos();
                    break;
                case 4:
                    System.out.println("Opção 4 - Remover Livro por Título");
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    private static void adicionar(Scanner lerTeclado) {
        System.out.println("Digite o título do livro:");
        String titulo = lerTeclado.nextLine();
        System.out.println("Digite o autor do livro:");
        String autor = lerTeclado.nextLine();
        int anoPublicacao = inputNumerico(lerTeclado, "Digite o ano da publicação:");
        int numeroPaginas = inputNumerico(lerTeclado, "Digite o número de páginas:");

        Livro novoLivro = new Livro();
        novoLivro.setTitulo(titulo);
        novoLivro.setAutor(autor);
        novoLivro.setAnoPublicacao(anoPublicacao);
        novoLivro.setNumeroPaginas(numeroPaginas);

        try {
            biblio.adicionar(novoLivro);
            System.out.println("Livro adicionado com Sucesso!");
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public static void pesquisarTodos() {
        List<Livro> livros = biblio.pesquisarTodos();
        if (livros.isEmpty()) {
            System.out.println("NENHUM LIVRO CADASTRADO!");
        } else {
            for (Livro livro : livros) {
                System.out.println(livro.getTitulo());
            }
        }

    }

    private static int inputNumerico(Scanner lerTeclado, String mensagem) {

        int valor = 0;
        boolean entradaValida = false;
        System.out.println(mensagem);
        do {
            String valorStr = lerTeclado.nextLine();
            try {
                valor = Integer.parseInt(valorStr);
                entradaValida = true;
            } catch (Exception e) {
                System.out.println("Erro. Por favor informe um número Inteiro");
            }
        } while (!entradaValida);
        return valor;
    }
}

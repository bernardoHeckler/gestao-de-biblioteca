import java.util.List;
import java.util.Scanner;

public class Main {

    public static final String limpeConsole = "\033[H\033[2J";

    // Limpar a tela
    public static void limparTela() {
        System.out.print(limpeConsole);
        System.out.flush();
    }

    private static Biblioteca biblio = new Biblioteca();

    public static void main(String[] args) {
        limparTela();
        String menu = "====== SYSBIBLIO ======\n" +
                "Escolha uma das opções abaixo:\n" +
                "1 - Adicionar novo livro\n" +
                "2 - Pesquisar livro por título\n" +
                "3 - Listar todos os livros\n" +
                "4 - Remover livro por título\n" +
                "0 - Sair\n";
        int opcao;
        Scanner lerTeclado = new Scanner(System.in);
        do {
            // System.out.println(menu);
            // opcao = lerTeclado.nextInt();
            // lerTeclado.nextLine(); //Limpar buffer entrada do console (teclado)
            opcao = inputNumerico(lerTeclado, menu);
            switch (opcao) {
                case 1:
                    limparTela();
                    adicionar(lerTeclado);
                    break;
                case 3:
                    limparTela();
                    pesquisarTodos();
                    break;
                case 0:
                    limparTela();
                    System.out.println("Encerrando programa ...");
                    break;
                default:
                    limparTela();
                    System.out.println("Opção inválida");
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

        Livro novoLivro;

        int tipoLivro = inputNumerico(lerTeclado, "Qual o tipo do livro: 1-Físico, 2 Digital");
        if (tipoLivro == 1) {
            novoLivro = new LivroFisico();
            System.out.println("Digite as dimensões do livro:");
            String dimensoes = lerTeclado.nextLine();
            int numeroExemplares = inputNumerico(lerTeclado, "Digite o número de exemplares:");

            LivroFisico novoLivroFisico = (LivroFisico) novoLivro;
            novoLivroFisico.setDimensoes(dimensoes);
            novoLivroFisico.setNumeroExemplares(numeroExemplares);
            // ((LivroFisico) novoLivro).setDimensoes(dimensoes);
        } else {
            novoLivro = new LivroDigital();
            System.out.println("Digite o formato do arquivo:");
            String formatoArquivo = lerTeclado.nextLine();

            LivroDigital novoLivroDigital = (LivroDigital) novoLivro;
            novoLivroDigital.setFormatoArquivo(formatoArquivo);

        }

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
            System.out.println("NENHUM LIVRO CADASTRADO");
        } else {
            System.out.println("Livros Cadastrados:");
            for (Livro livro : livros) {
                System.out.println(livro.toString());
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

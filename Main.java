import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final String limpeConsole = "\033[H\033[2J";

    // Limpar a tela
    public static void limparTela() {
        System.out.print(limpeConsole);
        System.out.flush();
    }

    private static final Biblioteca biblioteca = new Biblioteca();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        limparTela();
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcaoMenu();
            processarOpcao(opcao);
            if (opcao != 0) {
                pausar();
            }
            limparTela();
        } while (opcao != 0);
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("====== SYSBIBLIO ======");
        System.out.println("1 - Adicionar Livro");
        System.out.println("2 - Pesquisar Livro por Título");
        System.out.println("3 - Pesquisar Livro por Autor");
        System.out.println("4 - Listar Todos os Livros");
        System.out.println("5 - Remover Livros por Título");
        System.out.println("6 - Listar Livros por Ano de Publicação");
        System.out.println("0 - Sair");
        System.out.println("=======================");
    }

    private static int lerOpcaoMenu() {
        int opcao = -1;
        boolean entradaValida = false;
        do {
            try {
                System.out.print("Digite a opção desejada: ");
                opcao = scanner.nextInt();
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Digite um número inteiro.");
                scanner.next(); // Limpa o buffer do scanner
            }
            scanner.nextLine(); // Consome a quebra de linha
        } while (!entradaValida);
        return opcao;
    }

    private static void processarOpcao(int opcao) {
        limparTela();
        switch (opcao) {
            case 1:
                adicionarLivro();
                break;
            case 2:
                pesquisarLivroPorTitulo();
                break;
            case 3:
                pesquisarLivroPorAutor();
                break;
            case 4:
                listarTodosOsLivros();
                break;
            case 5:
                removerLivrosPorTitulo();
                break;
            case 6:
                listarLivrosPorAnoPublicacao();
                break;
            case 0:
                System.out.println("Encerrando o sistema...");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void adicionarLivro() {
        try {
            System.out.println("Adicionar Novo Livro");
            System.out.print("Digite o título: ");
            String titulo = scanner.nextLine().trim();
            System.out.print("Digite o autor: ");
            String autor = scanner.nextLine().trim();
            int anoPublicacao = lerInteiro("Digite o ano de publicação: ", 1400, java.time.LocalDate.now().getYear());
            int numeroPaginas = lerInteiroPositivo("Digite o número de páginas: ");

            System.out.println("Tipo de Livro:");
            System.out.println("1 - Físico");
            System.out.println("2 - Digital");
            int tipoLivro = lerOpcao(1, 2, "Escolha o tipo de livro (1 ou 2): ");

            Livro novoLivro;
            if (tipoLivro == 1) {
                System.out.print("Digite as dimensões: ");
                String dimensoes = scanner.nextLine().trim();
                int numeroExemplares = lerInteiroPositivo("Digite o número de exemplares: ");
                novoLivro = new LivroFisico(titulo, autor, anoPublicacao, numeroPaginas, dimensoes, numeroExemplares);
            } else {
                double tamanhoArquivo = lerDoublePositivo("Digite o tamanho do arquivo (MB): ");
                String formatoArquivo = scanner.nextLine().trim();
                novoLivro = new LivroDigital(titulo, autor, anoPublicacao, numeroPaginas, formatoArquivo, tamanhoArquivo);
            }

            biblioteca.adicionarLivro(novoLivro);
            System.out.println("Livro adicionado com sucesso!");

        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao adicionar livro: " + e.getMessage());
        }
    }

    private static void pesquisarLivroPorTitulo() {
        System.out.println("Pesquisar Livro por Título");
        System.out.print("Digite o título a pesquisar: ");
        String titulo = scanner.nextLine().trim();
        List<Livro> resultados = biblioteca.pesquisarLivroPorTitulo(titulo);
        exibirResultadosDaPesquisa(resultados);
    }

    private static void pesquisarLivroPorAutor() {
        System.out.println("Pesquisar Livro por Autor");
        System.out.print("Digite o autor a pesquisar: ");
        String autor = scanner.nextLine().trim();
        List<Livro> resultados = biblioteca.pesquisarLivroPorAutor(autor);
        exibirResultadosDaPesquisa(resultados);
    }

    private static void listarTodosOsLivros() {
        System.out.println("Listar Todos os Livros");
        List<Livro> livros = biblioteca.listarTodosOsLivros();
        exibirResultadosDaPesquisa(livros);
    }

    private static void removerLivrosPorTitulo() {
        System.out.println("Remover Livros por Título");
        System.out.print("Digite o título dos livros a remover: ");
        String titulo = scanner.nextLine().trim();
        int livrosRemovidos = biblioteca.removerLivrosPorTitulo(titulo);
        System.out.println("Livros removidos: " + livrosRemovidos);
    }

    private static void listarLivrosPorAnoPublicacao() {
        System.out.println("Listar Livros por Ano de Publicação");
        int ano = lerInteiro("Digite o ano de publicação: ", 1400, java.time.LocalDate.now().getYear());
        List<Livro> resultados = biblioteca.listarLivrosPorAnoPublicacao(ano);
        exibirResultadosDaPesquisa(resultados);
    }

    private static void exibirResultadosDaPesquisa(List<Livro> resultados) {
        if (resultados.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            System.out.println("Resultados da pesquisa:");
            for (Livro livro : resultados) {
                System.out.println(livro.toString() + " (Tempo de Publicação: " + livro.calcularTempoPublicacao() + " anos)");
            }
        }
    }

    // Métodos utilitários para ler entradas do usuário com validação
    private static int lerInteiro(String mensagem, int min, int max) {
        int valor = 0;
        boolean entradaValida = false;
        do {
            try {
                System.out.print(mensagem);
                valor = scanner.nextInt();
                if (valor >= min && valor <= max) {
                    entradaValida = true;
                } else {
                    System.out.println("Erro: Valor fora do intervalo válido (" + min + " - " + max + ").");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Digite um número inteiro.");
                scanner.next(); // Limpa o buffer do scanner
            }
            scanner.nextLine(); // Consome a quebra de linha
        } while (!entradaValida);
        return valor;
    }

    private static int lerInteiroPositivo(String mensagem) {
        int valor = 0;
        boolean entradaValida = false;
        do {
            try {
                System.out.print(mensagem);
                valor = scanner.nextInt();
                if (valor > 0) {
                    entradaValida = true;
                } else {
                    System.out.println("Erro: Valor deve ser positivo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Digite um número inteiro.");
                scanner.next(); // Limpa o buffer do scanner
            }
            scanner.nextLine(); // Consome a quebra de linha
        } while (!entradaValida);
        return valor;
    }

    private static double lerDoublePositivo(String mensagem) {
        double valor = 0;
        boolean entradaValida = false;
        do {
            try {
                System.out.print(mensagem);
                valor = scanner.nextDouble();
                if (valor > 0) {
                    entradaValida = true;
                } else {
                    System.out.println("Erro: Valor deve ser positivo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Digite um número.");
                scanner.next(); // Limpa o buffer do scanner
            }
            scanner.nextLine(); // Consome a quebra de linha
        } while (!entradaValida);
        return valor;
    }

    private static int lerOpcao(int min, int max, String mensagem) {
        int opcao = 0;
        boolean entradaValida = false;
        do {
            opcao = lerInteiro(mensagem, min, max);
            if (opcao >= min && opcao <= max) {
                entradaValida = true;
            } else {
                System.out.println("Erro: Opção inválida.");
            }
        } while (!entradaValida);
        return opcao;
    }

    private static void pausar() {
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
    }
}
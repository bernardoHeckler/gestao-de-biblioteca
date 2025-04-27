// Atividade Prática feita em Grupo
// Alunos: Bernardo Antunes Heckler; Gilmar Biolch; Gustavo Francisco;
// RA's: 1137118; 1137267; 1137279.

import java.util.InputMismatchException; // Utilizado em de caso um tipo de dado não for o esperado, esperava int e não string
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

    private static void opcaoContinuar() {
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
    }

    public static void main(String[] args) {
        limparTela();
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcaoMenu();
            processarOpcao(opcao);
            if (opcao != 0) {
                opcaoContinuar();
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
                scanner.next();
            }
            scanner.nextLine();
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
                System.out.println("Opção Inválida.");
        }
    }

    private static void adicionarLivro() {
        try {
            System.out.println("Adicionar Novo Livro");

            System.out.print("Título: ");
            String titulo = scanner.nextLine().trim();

            System.out.print("Autor: ");
            String autor = scanner.nextLine().trim();

            System.out.print("Ano de Publicação: ");
            int anoPublicacao = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Número de Páginas: ");
            int numeroPaginas = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Tipo de Livro (1-Físico, 2-Digital): ");
            int tipoLivro = scanner.nextInt();
            scanner.nextLine();

            Livro novoLivro;

            if (tipoLivro == 1) {
                System.out.print("\nDimensões(Ex: '20cm x 14cm x 2cm'): ");
                String dimensoes = scanner.nextLine().trim();

                System.out.print("\nExemplares(Ex: 5 exemplares): ");
                int numeroExemplares = scanner.nextInt();
                scanner.nextLine();

                novoLivro = new LivroFisico(titulo, autor, anoPublicacao, numeroPaginas, dimensoes, numeroExemplares);
            } else {
                System.out.print("\nDigite o Tamanho(Ex: '2.5MB', '10.1MB'): ");
                double tamanhoArquivo = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("\nFormato(Ex: 'PDF', 'ePub', 'MOBI'): ");
                String formatoArquivo = scanner.nextLine().trim();

                novoLivro = new LivroDigital(titulo, autor, anoPublicacao, numeroPaginas, formatoArquivo,
                        tamanhoArquivo);
            }

            biblioteca.adicionarLivro(novoLivro);
            System.out.println("Livro foi adicionado!");

        } catch (Exception e) {
            System.out.println("Erro ao adicionar livro.");
        }
    }

    private static void pesquisarLivroPorTitulo() {
        System.out.println("Pesquisar Livro por Título");
        System.out.print("Digite o título a pesquisar: ");
        String titulo = scanner.nextLine().trim();

        if (titulo.isEmpty()) {
            System.out.println("Erro: O termo de pesquisa não pode ser vazio.");
        } else {
            List<Livro> resultados = biblioteca.pesquisarLivroPorTitulo(titulo);
            exibirResultadosDaPesquisa(resultados);
        }
    }

    private static void pesquisarLivroPorAutor() {
        System.out.println("Pesquisar Livro por Autor");
        System.out.print("Digite o autor a pesquisar: ");
        String autor = scanner.nextLine().trim();

        if (autor.isEmpty()) {
            System.out.println("Erro: O termo de pesquisa não pode ser vazio.");
        } else {
            List<Livro> resultados = biblioteca.pesquisarLivroPorAutor(autor);
            exibirResultadosDaPesquisa(resultados);
        }
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
            System.out.println("Nenhum livro foi Encontrado!");
        } else {
            limparTela();
            System.out.println("RESULTADO DA PESQUISA:");
            for (Livro livro : resultados) {
                System.out.print(
                        livro.toString() + "\nTempo de Publicação: " + livro.calcularTempoPublicacao() + " anos");
                System.out.println("\n=====================================");

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
                scanner.next();
            }
            scanner.nextLine();
        } while (!entradaValida);
        return valor;
    }
}
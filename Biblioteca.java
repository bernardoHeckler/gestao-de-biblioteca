import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    // gerenciamento do acervo de livros

    private List<Livro> acervo = new ArrayList<>();
    private static final int ANO_PUBLICACAO_MINIMO = 1400; // ano mínimo válido para publicação de um livro.

    public void adicionarLivro(Livro livro) throws IllegalArgumentException {
        validarLivro(livro);
        for (Livro livroExistente : acervo) {
            if (livroExistente.getTitulo().equalsIgnoreCase(livro.getTitulo())) {
                throw new IllegalArgumentException(
                        "Erro de Cadastro: Livro com o título '" + livro.getTitulo() + "' JÁ EXISTE!");
            }
        }
        acervo.add(livro);
    } // adiciona um livro ao acervo

    public List<Livro> pesquisarLivroPorTitulo(String titulo) {
        List<Livro> achouLivroTitulo = new ArrayList<>();
        for (Livro livro : acervo) {
            // titulo do livro contem o titulo buscado.
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                achouLivroTitulo.add(livro);
            }
        }
        return achouLivroTitulo;
    }

    public List<Livro> pesquisarLivroPorAutor(String autor) {
        List<Livro> achouLivroPeloAutor = new ArrayList<>();
        for (Livro livro : acervo) {
            // autor do livro contem o autor buscado.
            if (livro.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                achouLivroPeloAutor.add(livro);
            }
        }
        return achouLivroPeloAutor;
    }

    public List<Livro> listarTodosOsLivros() {
        return new ArrayList<>(acervo); // Retorna uma cópia para evitar modificação externa
    }

    public int removerLivrosPorTitulo(String titulo) {
        for (Livro livro : acervo) {
            if (livro.getTitulo().toLowerCase().equalsIgnoreCase(titulo.toLowerCase())) {
                acervo.remove(livro);
                return 1; // Retorna 1 para indicar que um livro foi removido
            }
        }
        return 0; // Retorna 0 se nenhum livro foi encontrado com o título
    }

    public List<Livro> listarLivrosPorAnoPublicacao(int ano) {
        List<Livro> anoPublicado = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getAnoPublicacao() == ano) {
                anoPublicado.add(livro);
            }
        }
        return anoPublicado;
    }

    // IllegalArgumentException é uma exceção personalizada para lidar com erros de
    // validação;
    // validações que vão gerar exceções se não forem atendidas.
    private void validarLivro(Livro livro) throws IllegalArgumentException {
        if (livro.getTitulo() == null || livro.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: Título do livro é OBRIGATÓRIO!");
        }
        if (livro.getAutor() == null || livro.getAutor().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: Autor do livro é OBRIGATÓRIO!");
        }
        if (livro.getAnoPublicacao() < ANO_PUBLICACAO_MINIMO
                || livro.getAnoPublicacao() > java.time.LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Erro: Ano de publicação INVÁLIDO!");
        }
        if (livro.getNumeroPaginas() <= 0) {
            throw new IllegalArgumentException("Erro: Número de páginas deve ser POSITIVO.");
        }
    }
}
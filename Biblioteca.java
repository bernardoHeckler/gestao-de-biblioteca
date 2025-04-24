import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private List<Livro> acervo = new ArrayList<>();
    private static final int ANO_PUBLICACAO_MINIMO = 1400;

    public void adicionarLivro(Livro livro) throws IllegalArgumentException {
        validarLivro(livro);
        if (acervo.stream().anyMatch(l -> l.getTitulo().equalsIgnoreCase(livro.getTitulo()))) {
            throw new IllegalArgumentException("Erro: Livro com o título '" + livro.getTitulo() + "' já existe.");
        }
        acervo.add(livro);
    }

    public List<Livro> pesquisarLivroPorTitulo(String titulo) {
        List<Livro> resultados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultados.add(livro);
            }
        }
        return resultados;
    }

    public List<Livro> pesquisarLivroPorAutor(String autor) {
        List<Livro> resultados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                resultados.add(livro);
            }
        }
        return resultados;
    }

    public List<Livro> listarTodosOsLivros() {
        return new ArrayList<>(acervo); // Retorna uma cópia para evitar modificação externa
    }

    public int removerLivrosPorTitulo(String titulo) {
        int count = 0;
        List<Livro> livrosParaRemover = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                livrosParaRemover.add(livro);
                count++;
            }
        }
        acervo.removeAll(livrosParaRemover);
        return count;
    }

    public List<Livro> listarLivrosPorAnoPublicacao(int ano) {
        List<Livro> resultados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getAnoPublicacao() == ano) {
                resultados.add(livro);
            }
        }
        return resultados;
    }

    private void validarLivro(Livro livro) throws IllegalArgumentException {
        if (livro.getTitulo() == null || livro.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: Título do livro é obrigatório.");
        }
        if (livro.getAutor() == null || livro.getAutor().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: Autor do livro é obrigatório.");
        }
        if (livro.getAnoPublicacao() < ANO_PUBLICACAO_MINIMO || livro.getAnoPublicacao() > java.time.LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Erro: Ano de publicação inválido.");
        }
        if (livro.getNumeroPaginas() <= 0) {
            throw new IllegalArgumentException("Erro: Número de páginas deve ser positivo.");
        }
    }
}
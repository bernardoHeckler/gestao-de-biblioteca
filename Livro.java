import java.time.LocalDate;

public abstract class Livro { // classe abstrata

    private String titulo;
    private String autor;
    private int anoPublicacao;
    private int numeroPaginas;

    public Livro(String titulo, String autor, int anoPublicacao, int numeroPaginas) { // construtor do Livro
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.numeroPaginas = numeroPaginas;
    }

    public abstract String getTipoLivro(); // método abstrato que pega o tipo do Livro (físico ou digital)

    public final int calcularTempoPublicacao() {
        return LocalDate.now().getYear() - this.anoPublicacao;
    } // retornando valor do ano atual menos o ano de publicação

    @Override
    public String toString() {
        return "\nTítulo: " + titulo +
                "\nAutor: " + autor +
                "\nAno: " + anoPublicacao +
                "\nPáginas: " + numeroPaginas;
    } // sobreposição de método

    // parte dos GETTERS
    public String getTitulo() {
        return titulo;
    } // retorna o título do livro

    public String getAutor() {
        return autor;
    } // retorna o autor do livro

    public int getAnoPublicacao() {
        return anoPublicacao;
    } // retorna o ano de publicação do livro

    public int getNumeroPaginas() {
        return numeroPaginas;
    } // retorna o número de páginas do livro

    // parte dos SETTERS
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    } // altera o autor do livro

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    } // altera o ano de publicação do livro

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    } // altera o número de páginas do livro
}
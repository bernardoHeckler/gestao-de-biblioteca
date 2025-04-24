public final class LivroFisico extends Livro {

    private String dimensoes;
    private int numeroExemplares;

    public LivroFisico(String titulo, String autor, int anoPublicacao, int numeroPaginas, String dimensoes, int numeroExemplares) {
        super(titulo, autor, anoPublicacao, numeroPaginas);
        this.dimensoes = dimensoes;
        this.numeroExemplares = numeroExemplares;
    }

    @Override
    public String getTipoLivro() {
        return "Físico";
    }

    @Override
    public String toString() {
        return super.toString() + ", Dimensões: " + dimensoes + ", Exemplares: " + numeroExemplares;
    }

    public String getDimensoes() {
        return dimensoes;
    }

    public int getNumeroExemplares() {
        return numeroExemplares;
    }

    public void setDimensoes(String dimensoes) {
        this.dimensoes = dimensoes;
    }

    public void setNumeroExemplares(int numeroExemplares) {
        this.numeroExemplares = numeroExemplares;
    }
}
public final class LivroFisico extends Livro {
    // classe final

    private String dimensoes;
    private int numeroExemplares;

    public LivroFisico(String titulo, String autor, int anoPublicacao, int numeroPaginas, String dimensoes,
            int numeroExemplares) {
        super(titulo, autor, anoPublicacao, numeroPaginas);
        this.dimensoes = dimensoes;
        this.numeroExemplares = numeroExemplares;
    } // construtor

    @Override
    public String getTipoLivro() {
        return "Físico";
    } // sobreposição de método

    @Override
    public String toString() {
        return super.toString() + "\nDimensões: " + dimensoes
                + "\nExemplares: " + numeroExemplares;
    } // sobreposição de método

    // parte dos GETTERS
    public String getDimensoes() {
        return dimensoes;
    }

    public int getNumeroExemplares() {
        return numeroExemplares;
    }

    // parte dos SETTERS
    public void setDimensoes(String dimensoes) {
        this.dimensoes = dimensoes;
    }

    public void setNumeroExemplares(int numeroExemplares) {
        this.numeroExemplares = numeroExemplares;
    }
}
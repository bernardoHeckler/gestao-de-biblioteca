public final class LivroDigital extends Livro {
    // classe final

    private String formatoArquivo;
    private double tamanhoArquivo;

    public LivroDigital(String titulo, String autor, int anoPublicacao, int numeroPaginas, String formatoArquivo,
            double tamanhoArquivo) {
        super(titulo, autor, anoPublicacao, numeroPaginas);
        this.formatoArquivo = formatoArquivo;
        this.tamanhoArquivo = tamanhoArquivo;
    } // construtor

    @Override
    public String getTipoLivro() {
        return "Digital";
    } // sobreposição de método

    @Override
    public String toString() {
        return super.toString() + "\nFormato: " + formatoArquivo
                + "\nTamanho: " + tamanhoArquivo + "MB";
    } // sobreposição de método

    // parte dos GETTERS
    public String getFormatoArquivo() {
        return formatoArquivo;
    }

    public double getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    // parte dos SETTERS
    public void setFormatoArquivo(String formatoArquivo) {
        this.formatoArquivo = formatoArquivo;
    }

    public void setTamanhoArquivo(double tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }
}
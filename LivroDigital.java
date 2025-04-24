public final class LivroDigital extends Livro {

    private String formatoArquivo;
    private double tamanhoArquivo;

    public LivroDigital(String titulo, String autor, int anoPublicacao, int numeroPaginas, String formatoArquivo, double tamanhoArquivo) {
        super(titulo, autor, anoPublicacao, numeroPaginas);
        this.formatoArquivo = formatoArquivo;
        this.tamanhoArquivo = tamanhoArquivo;
    }

    @Override
    public String getTipoLivro() {
        return "Digital";
    }

    @Override
    public String toString() {
        return super.toString() + ", Formato: " + formatoArquivo + ", Tamanho: " + tamanhoArquivo + "MB";
    }

    public String getFormatoArquivo() {
        return formatoArquivo;
    }

    public double getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    public void setFormatoArquivo(String formatoArquivo) {
        this.formatoArquivo = formatoArquivo;
    }

    public void setTamanhoArquivo(double tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }
}
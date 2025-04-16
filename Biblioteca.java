
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private List<Livro> acervo = new ArrayList<>();

    public Livro adicionar(Livro novoLivro) throws Exception {
        if (novoLivro.getTitulo() == null || novoLivro.getTitulo().isEmpty())
            throw new Exception("Título Inválido!");
        
            acervo.add(novoLivro);

        return novoLivro;
    }

    public List<Livro> pesquisaPorTitulo(String titulo) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                livrosEncontrados.add(livro);
            }
        }
        return livrosEncontrados;
    }
    
    public List<Livro> pesquisarTodos() {
        return acervo;
    }
    
}

package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogoFilmes {
    private Map<String, Filme> filmes;

    public CatalogoFilmes() {

        filmes = new HashMap<>();
    }
    public List<Filme> listarTodosOsFilmes (){
        return new ArrayList<>(filmes.values());
    }

    public void cadastrarFilme(Filme filme) {
        filmes.put(filme.getNome().toLowerCase(), filme);
    }

    public Filme buscarFilme(String nome) {
        return filmes.get(nome.toLowerCase());
    }
}

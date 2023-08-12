package Models;

import java.util.HashMap;
import java.util.Map;

public class CatalogoFilmes {
    private Map<String, Filme> filmes;

    public CatalogoFilmes() {

        filmes = new HashMap<>();
    }

    public void cadastrarFilme(Filme filme) {
        filmes.put(filme.getNome().toLowerCase(), filme);
    }

    public Filme buscarFilme(String nome) {
        return filmes.get(nome.toLowerCase());
    }
}

package Ui;

import Models.Filme; // Importa a classe Filme
import java.util.List; // Importa a classe List

// Definição da interface PagedList
public interface PagedList {

    // Método para listar filmes com base na página e tamanho
    List<Filme> listarFilmes(int pagina, int tamanho);
}

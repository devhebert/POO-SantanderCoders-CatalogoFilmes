package Views;

import Models.Ator;
import Models.CatalogoFilmes;
import Models.Diretor;
import Models.Filme;
import Ui.EditItemCallback;
import Ui.FilmeUi;

public class FilmeView {
    CatalogoFilmes catalogo = new CatalogoFilmes();
    Diretor diretor1 = new Diretor("Diretor 1");
    Diretor diretor2 = new Diretor("Diretor 2");
    Ator ator1 = new Ator("Ator 1");
    Ator ator2 = new Ator("Ator 2");

    Filme filme1 = new Filme("Filme 1", "01/01/2023", 1000000, "Descrição do Filme 1", diretor1);
    Filme filme2 = new Filme("Filme 2", "02/01/2023", 1500000, "Descrição do Filme 2", diretor2);

    public void exibirFilme() {
        // Criação de uma instância de FilmeUi com um callback para edição
        FilmeUi filmeUi = new FilmeUi(filme1.getNome(), filme1, new EditItemCallback<Filme>() {
            @Override
            public void remove(Filme item) {
                System.out.println(item.getNome() + " removido.");
            }

            @Override
            public void add(Filme item) {
                System.out.println(item.getNome() + " adicionado.");
            }

            @Override
            public boolean isNew(Filme item) {
                return !item.equals(filme1);
            }
        });

        // Exibe a interface do filme usando a instância de FilmeUi
        filmeUi.show();
    }
}

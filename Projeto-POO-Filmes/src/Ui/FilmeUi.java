package Ui;

import Models.Ator;
import Models.Diretor;
import Models.Filme;
import Util.ConsoleUIHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilmeUi extends EditItemUI<Filme> {

    // Construtor da classe FilmeUi
    public FilmeUi(String titulo, Filme item, EditItemCallback<Filme> editItemCallback) {
        super(titulo, item, editItemCallback); // Chama o construtor da superclasse BasicUI com o título
    }

    // Método para desenhar o conteúdo da interface
    @Override
    public int drawItemDetails() {
        // Mostra informações específicas do filme
        int linhas = 0;
        ConsoleUIHelper.drawWithRightPadding("Nome: " + item.getNome(), colunas, ' ');
        linhas++;
        ConsoleUIHelper.drawWithRightPadding("Data de Lançamento: " + item.getDataLancamento(), colunas, ' ');
        linhas++;
        ConsoleUIHelper.drawWithRightPadding("Orçamento: " + item.getOrcamento(), colunas, ' ');
        linhas++;

        if (item.getDescricao().length() > 120) {
            ConsoleUIHelper.drawWithRightPadding("Descrição: " + item.getDescricao().substring(0, 120), colunas, ' ');
            ConsoleUIHelper.drawWithRightPadding("    " + item.getDescricao().substring(121), colunas, ' ');
            linhas += 2;
        } else {
            ConsoleUIHelper.drawWithRightPadding("Descrição: " + item.getDescricao(), colunas, ' ');
            linhas++;
        }

        ConsoleUIHelper.drawWithRightPadding("Diretor: " + item.getDiretor().getNome(), colunas, ' ');
        linhas++;

        // Mostra os atores do filme, caso haja
        if (!item.getAtores().isEmpty()) {
            String atores = item.getAtores().stream().map(Ator::getNome).collect(Collectors.joining(", "));
            ConsoleUIHelper.drawWithRightPadding("Atores: " + atores, colunas, ' ');
        } else {
            ConsoleUIHelper.drawWithRightPadding("Atores: Nenhum ator registrado", colunas, ' ');
        }

        return linhas; // Retorna o número de linhas do conteúdo exibido
    }

    // Define quantas linhas o menu deve ocupar
    @Override
    public int menuLines() {
        // Se for um novo filme, ocupa 8 linhas, senão, ocupa 10
        return editItemCallback.isNew(item) ? 8 : 10;
    }

    // Método chamado quando o usuário escolhe "Sair" ou termina de editar um novo filme
    public void exitAndSave() {
        // Se for um novo filme, adiciona-o, caso contrário, apenas sai
        if (editItemCallback.isNew(item)) {
            editItemCallback.add(item);
        }
    }


    @Override
    public String[] fillFieldsNames() {
        return new String[]{"Nome","Data de Lançamento","Orçamento","Descrição","Diretor","Atores"};
    }

    @Override
    public void fillField(Filme item, int option) {
        switch (option) {
            case 0 -> alterarNome(); // Alterar data de nome
            case 1 -> alterarDataLancamento(); // Alterar data de lançamento
            case 2 -> alterarOrcamento(); // Alterar orçamento
            case 3 -> alterarDescricao(); // Alterar descrição
            case 4 -> alterarDiretor(); // Alterar diretor
            case 5 -> alterarAtores();  // Alterar atores
        }
    }

    @Override
    protected void newItem() {
        Filme newFilme = new Filme();
        newFilme.setNome("Altere para o novo nome");
        FilmeUi newItemUI = new FilmeUi(titulo, newFilme, editItemCallback);
        newItemUI.show(); // Chame o método show() para exibir a interface do novo filme
    }

    // Métodos para alterar informações do filme
    private void alterarNome() {
        // Pergunta ao usuário o novo nome e atualiza o filme
        String value = ConsoleUIHelper.askSimpleInput("Informe nome:");
        item.setNome(value);
    }

    private void alterarDataLancamento() {
        // Pergunta ao usuário a nova data de lançamento e atualiza o filme
        String value = ConsoleUIHelper.askSimpleInput("Informe a data de lançamento:");
        item.setDataLancamento(value);
    }

    private void alterarOrcamento() {
        // Pergunta ao usuário o novo orçamento e atualiza o filme
        String value = ConsoleUIHelper.askSimpleInput("Informe o orçamento:");
        item.setOrcamento(Double.parseDouble(value));
    }

    private void alterarDescricao() {
        // Pergunta ao usuário a nova descrição e atualiza o filme
        String value = ConsoleUIHelper.askSimpleInput("Informe a descrição:");
        item.setDescricao(value);
    }

    private void alterarDiretor() {
        // Pergunta ao usuário o nome do novo diretor, cria um objeto Diretor e atualiza o filme
        String novoNomeDiretor = ConsoleUIHelper.askSimpleInput("Informe o nome do diretor:");
        Diretor novoDiretor = new Diretor(novoNomeDiretor);
        item.setDiretor(novoDiretor);
    }

    private void alterarAtores() {
        // Pergunta ao usuário o(s) nome(s) dos atores, separados por vírgula, cria objetos Ator e atualiza o filme
        String novoAtoresString = ConsoleUIHelper.askSimpleInput("Informe o(s) ator(es), separados por vírgula:");
        String[] atoresArray = novoAtoresString.split(",");
        List<Ator> novoAtores = new ArrayList<>();

        for (String atorNome : atoresArray) {
            Ator novoAtor = new Ator(atorNome.trim());
            novoAtores.add(novoAtor);
        }

        item.setAtores(novoAtores);
    }
}

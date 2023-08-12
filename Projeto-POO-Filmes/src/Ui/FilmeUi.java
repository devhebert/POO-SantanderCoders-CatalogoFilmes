package Ui;

import Models.Ator;
import Models.Diretor;
import Models.Filme;
import Util.ConsoleUIHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilmeUi extends BasicUI {
    // A classe FilmeUi herda de BasicUI e possui atributos específicos
    private final Filme filme;
    private final EditItemCallback<Filme> editItemCallback;

    // Construtor da classe FilmeUi
    public FilmeUi(String titulo, Filme filme, EditItemCallback<Filme> editItemCallback) {
        super(titulo); // Chama o construtor da superclasse BasicUI com o título
        this.filme = filme; // Armazena o filme passado como parâmetro
        this.editItemCallback = editItemCallback; // Armazena o callback para edição
    }

    // Método para desenhar o conteúdo da interface
    @Override
    public int drawContent() {
        // Mostra informações específicas do filme
        ConsoleUIHelper.drawWithRightPadding("Nome: " + filme.getNome(), colunas, ' ');
        ConsoleUIHelper.drawWithRightPadding("Data de Lançamento: " + filme.getDataLancamento(), colunas, ' ');
        ConsoleUIHelper.drawWithRightPadding("Orçamento: " + filme.getOrcamento(), colunas, ' ');
        ConsoleUIHelper.drawWithRightPadding("Descrição: " + filme.getDescricao(), colunas, ' ');
        ConsoleUIHelper.drawWithRightPadding("Diretor: " + filme.getDiretor().getNome(), colunas, ' ');

        // Mostra os atores do filme, caso haja
        if (!filme.getAtores().isEmpty()) {
            String atores = filme.getAtores().stream().map(Ator::getNome).collect(Collectors.joining(", "));
            ConsoleUIHelper.drawWithRightPadding("Atores: " + atores, colunas, ' ');
        } else {
            ConsoleUIHelper.drawWithRightPadding("Atores: Nenhum ator registrado", colunas, ' ');
        }

        return 6; // Retorna o número de linhas do conteúdo exibido
    }

    // Define quantas linhas o menu deve ocupar
    @Override
    public int menuLines() {
        // Se for um novo filme, ocupa 8 linhas, senão, ocupa 10
        return editItemCallback.isNew(filme) ? 8 : 10;
    }

    // Método chamado quando o usuário escolhe "Sair" ou termina de editar um novo filme
    public void exitAndSave() {
        // Se for um novo filme, adiciona-o, caso contrário, apenas sai
        if (editItemCallback.isNew(filme)) {
            editItemCallback.add(filme);
        }
    }

    // Método para desenhar o menu
    @Override
    public boolean drawMenu() {
        String[] options;
        // Define as opções do menu com base no status do filme (novo ou existente)
        if (editItemCallback.isNew(filme)) {
            options = new String[]{"Sair", "Alterar nome", "Alterar data de lançamento", "Alterar orçamento", "Alterar descrição", "Alterar diretor", "Alterar atores"};
        } else {
            options = new String[]{"Novo", "Alterar nome", "Alterar data de lançamento", "Alterar orçamento", "Alterar descricao", "Alterar diretor", "Alterar atores", "Apagar", "Sair"};
        }
        // Pergunta ao usuário qual opção deseja
        int option = ConsoleUIHelper.askChooseOption("Escolha uma opção", options);
        boolean keepShowing = true;
        // Executa a ação de acordo com a opção escolhida
        switch (option) {
            case 0: // Sair ou finalizar edição do novo filme
                if (editItemCallback.isNew(filme)) {
                    exitAndSave();
                    keepShowing = false;
                } else { // Criar e exibir um novo filme
                    Filme newFilme = new Filme();
                    newFilme.setNome("Altere para o novo nome");
                    FilmeUi newItemUI = new FilmeUi(titulo, newFilme, editItemCallback);
                    newItemUI.show(); // Chame o método show() para exibir a interface do novo filme
                }
                break;
            case 1: // Alterar nome do filme
                alterarNome();
                break;
            case 2: // Alterar data de lançamento
                alterarDataLancamento();
                break;
            case 3: // Alterar orçamento
                alterarOrcamento();
                break;
            case 4: // Alterar descrição
                alterarDescricao();
                break;
            case 5: // Alterar diretor
                alterarDiretor();
                break;
            case 6: // Alterar atores
                alterarAtores();
                break;
            case 7: // Apagar o filme
                boolean confirm = ConsoleUIHelper.askConfirm("Confirma a exclusão do filme " + filme.toString() + "?", "Sim", "Não");
                if (confirm) {
                    keepShowing = false;
                    editItemCallback.remove(filme);
                    ConsoleUIHelper.showMessageAndWait("Filme apagado!", 5);
                }
            default: // Sair ou finalizar edição
                exitAndSave();
                keepShowing = false;
        }
        return keepShowing;
    }

    // Métodos para alterar informações do filme
    private void alterarNome() {
        // Pergunta ao usuário o novo nome e atualiza o filme
        String value = ConsoleUIHelper.askSimpleInput("Informe nome:");
        filme.setNome(value);
    }

    private void alterarDataLancamento() {
        // Pergunta ao usuário a nova data de lançamento e atualiza o filme
        String value = ConsoleUIHelper.askSimpleInput("Informe a data de lançamento:");
        filme.setDataLancamento(value);
    }

    private void alterarOrcamento() {
        // Pergunta ao usuário o novo orçamento e atualiza o filme
        String value = ConsoleUIHelper.askSimpleInput("Informe o orçamento:");
        filme.setOrcamento(Double.parseDouble(value));
    }

    private void alterarDescricao() {
        // Pergunta ao usuário a nova descrição e atualiza o filme
        String value = ConsoleUIHelper.askSimpleInput("Informe a descrição:");
        filme.setDescricao(value);
    }

    private void alterarDiretor() {
        // Pergunta ao usuário o nome do novo diretor, cria um objeto Diretor e atualiza o filme
        String novoNomeDiretor = ConsoleUIHelper.askSimpleInput("Informe o nome do diretor:");
        Diretor novoDiretor = new Diretor(novoNomeDiretor);
        filme.setDiretor(novoDiretor);
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

        filme.setAtores(novoAtores);
    }
}
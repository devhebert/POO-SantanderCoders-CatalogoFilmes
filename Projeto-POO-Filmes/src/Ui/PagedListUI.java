package Ui;

import Models.Filme;
import Util.ConsoleUIHelper;

import java.util.List;

public class PagedListUI extends BasicUI {

    // Atributos
    protected final int PAGE_SIZE;
    protected int curPage;
    protected PagedList pageSource;
    private List<Filme> dataList;

    // Construtor para título e fonte de dados
    public PagedListUI(String titulo, PagedList pageSource) {
        this(DEFAULT_COLUMNS, DEFAULT_ROWS, titulo, pageSource);
    }

    // Construtor com colunas, linhas, título e fonte de dados
    public PagedListUI(int colunas, int linhas, String titulo, PagedList pageSource) {
        super(colunas, linhas, titulo);
        PAGE_SIZE = linhas - 4; // Define o tamanho da página (linhas - 4)
        curPage = 1; // Página atual começa como 1
        this.pageSource = pageSource; // Define a fonte de dados da página
    }

    // Método para desenhar o conteúdo da página
    @Override
    public int drawContent() {
        dataList = pageSource.listarFilmes(curPage, PAGE_SIZE); // Obtém os dados da página atual
        for (int i = 0; i < dataList.size(); i++) {
            Filme filme = dataList.get(i);
            ConsoleUIHelper.drawWithRightPadding(i + " -> " + filme.toString(), colunas, ' ');
            // Desenha cada item da lista com padding à direita
        }
        return dataList.size(); // Retorna o número de itens desenhados
    }

    // Método para número de linhas do menu
    @Override
    public int menuLines() {
        return 6;
    }

    // Método para desenhar o menu
    @Override
    public boolean drawMenu() {
        int option = ConsoleUIHelper.askChooseOption(
                "Escolha uma opção",
                "Página Anterior",
                "Página Seguinte",
                "Novo item",
                "Ver detalhes",
                "Sair");
        switch (option) {
            case 0: {
                previousPage();
                break;
            }
            case 1: {
                nextPage();
                break;
            }
            case 2: {
                addItem();
                break;
            }
            case 3: {
                seeItem();
                break;
            }
            default: {
                return false; // Sair do menu
            }
        }
        return true; // Continuar no menu
    }

    // Método para ver detalhes de um item
    private void seeItem() {
        int op = ConsoleUIHelper.askNumber("Informe o item a exibir").intValue();
        if (op >= 0 && op < dataList.size()) { // Verifica se o número do item é válido
            System.out.println(dataList.get(op)); // Exibe os detalhes do item
        } else {
            ConsoleUIHelper.showMessageAndWait("Item inválido, por favor informe um item válido!", 10);
            ConsoleUIHelper.clearScreen(); // Mensagem de erro e limpa a tela
        }
    }

    // Métodos vazios para adicionar, avançar página e voltar página
    private void addItem() {

    }

    private void nextPage() {

    }

    private void previousPage() {

    }
}

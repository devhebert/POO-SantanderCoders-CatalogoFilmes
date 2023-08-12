package Ui;

import Util.ConsoleUIHelper; // Importa a classe ConsoleUIHelper

// Classe abstrata BasicUI
public abstract class BasicUI {

    // Atributos padrões para colunas e linhas
    protected static final int DEFAULT_COLUMNS = 120;
    protected static final int DEFAULT_ROWS = 24;
    protected int colunas;
    protected int linhas;
    protected String titulo;

    // Construtor com título
    public BasicUI(String titulo) {
        this(DEFAULT_COLUMNS, DEFAULT_ROWS, titulo);
    }

    // Construtor com colunas, linhas e título
    public BasicUI(int colunas, int linhas, String titulo) {
        this.colunas = colunas;
        this.linhas = linhas;
        this.titulo = titulo;
    }

    // Método para exibir a interface
    public void show() {
        do {
            ConsoleUIHelper.drawHeader(titulo, colunas); // Desenha o cabeçalho com o título
            int linesUsed = 3 + drawContent() + menuLines(); // Calcula o número de linhas usadas

            ConsoleUIHelper.fillVSpace(linhas - linesUsed, colunas); // Preenche espaço vazio para ajustar altura
            ConsoleUIHelper.drawLine(colunas); // Desenha uma linha horizontal
            if (!drawMenu()) { // Desenha o menu e verifica se deve continuar
                break; // Sai do loop se o menu retornar false
            }
        } while (true); // Loop infinito para manter a interface ativa
    }

    // Métodos abstratos para desenhar o conteúdo, número de linhas do menu e desenhar o menu
    public abstract int drawContent();

    public abstract int menuLines();

    public abstract boolean drawMenu();
}

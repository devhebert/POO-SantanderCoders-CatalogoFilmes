package Util;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUIHelper {

    // Método para limpar a tela do console
    public static void clearScreen() {
        System.out.print("\033[H\033[2J"); // Código para limpar a tela
        System.out.flush(); // Limpa o buffer do console
    }

    // Pede uma entrada simples ao usuário e retorna a entrada
    public static String askSimpleInput(String message) {
        System.out.printf("%s%n# : ", message); // Exibe a mensagem de entrada
        return new Scanner(System.in).nextLine().trim(); // Lê a entrada do usuário e remove espaços extras
    }

    // Pede uma entrada ao usuário até que não esteja vazia ou alcance o número máximo de tentativas
    public static String askNoEmptyInput(String message, int retries) {
        System.out.printf("%s%n# : ", message); // Exibe a mensagem de entrada
        Scanner sc = new Scanner(System.in);
        String input;
        int tries = 0;
        do {
            input = sc.nextLine().trim(); // Lê a entrada do usuário e remove espaços extras
            tries++;
        } while (input.isBlank() && retries > 0 && tries < retries); // Continua pedindo até não estar vazio ou atingir o limite de tentativas
        return input;
    }

    // Pede ao usuário que escolha uma opção a partir de uma lista
    public static int askChooseOption(String message, String... options) {
        System.out.printf("%s%n# : ", message); // Exibe a mensagem de entrada
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%d - %s%n# : ", i, options[i]); // Exibe as opções numeradas
        }
        Scanner sc = new Scanner(System.in);
        int choose;
        do {
            try {
                choose = sc.nextInt(); // Lê a escolha do usuário
            } catch (InputMismatchException e) {
                choose = -9;
            }
        } while (choose < 0 || choose >= options.length); // Continua pedindo até receber uma escolha válida
        return choose;
    }

    // Pede confirmação ao usuário (sim ou não) e retorna true se for sim
    public static boolean askConfirm(String message, String yes, String no) {
        String[] op = new String[2];
        op[0] = yes;
        op[1] = no;
        return askChooseOption(message, yes, no) == 0; // Pede a escolha do usuário e retorna true se for a primeira opção (sim)
    }

    // Pede um número BigDecimal ao usuário e retorna o número inserido
    public static BigDecimal askNumber(String message) {
        System.out.printf("%s%n# : ", message); // Exibe a mensagem de entrada
        Scanner sc = new Scanner(System.in);
        BigDecimal number;
        do {
            try {
                number = sc.nextBigDecimal(); // Lê o número BigDecimal do usuário
            } catch (InputMismatchException e) {
                number = null;
            }
        } while (number == null); // Continua pedindo até receber um número válido
        return number;
    }

    // Desenha texto com preenchimento à direita e retorna o número de linhas
    public static int drawWithRightPadding(String text, int width, char pad) {
        int count = 0;
        do {
            int limit = Math.min(text.length(), width - 4); // Calcula o tamanho máximo da linha
            String row = text.substring(0, limit); // Extrai parte do texto para a linha
            text = text.substring(row.length()); // Remove a parte do texto que foi usada
            row = "# " + row + String.valueOf(pad).repeat(width - row.length() - 4) + " #"; // Formata a linha com preenchimento
            System.out.println(row); // Exibe a linha formatada
            count++;
        } while (!text.isEmpty()); // Repete até todo o texto ser desenhado
        return count; // Retorna o número de linhas desenhadas
    }

    // Desenha texto centralizado com preenchimento e retorna o número de linhas
    public static int drawWithPadding(String text, int width) {
        int count = 0;
        do {
            int limit = Math.min(text.length(), width - 4); // Calcula o tamanho máximo da linha
            String row = text.substring(0, limit); // Extrai parte do texto para a linha
            text = text.substring(row.length()); // Remove a parte do texto que foi usada
            int padding = (width - row.length()) / 2; // Calcula o espaço de preenchimento para centralizar
            row = "#" + " ".repeat(padding - 1) + row; // Adiciona o preenchimento à esquerda
            row = row + " ".repeat(width - row.length() - 1) + "#"; // Adiciona o preenchimento à direita
            System.out.println(row); // Exibe a linha formatada
            count++;
        } while (!text.isEmpty()); // Repete até todo o texto ser desenhado
        return count; // Retorna o número de linhas desenhadas
    }

    // Desenha um cabeçalho centralizado
    public static void drawHeader(String title, int width) {
        drawLine(width); // Desenha uma linha horizontal
        drawWithPadding(title, width); // Desenha o título centralizado
        drawLine(width); // Desenha outra linha horizontal
    }

    // Desenha uma linha horizontal
    public static void drawLine(int width) {
        System.out.println("#".repeat(width)); // Exibe uma linha de "#" repetidos
    }

    // Preenche espaço vertical com linhas vazias
    public static void fillVSpace(int lines, int width) {
        if (lines > 0) {
            drawWithPadding(" ".repeat(lines * width), width); // Desenha linhas vazias para preencher espaço vertical
        }
    }

    // Mostra uma mensagem e pausa o programa por um número específico de segundos
    public static void showMessageAndWait(String message, int seconds) {
        try {
            System.out.println(message); // Exibe a mensagem
            Thread.sleep(seconds * 1000L); // Pausa o programa por segundos * 1000 milissegundos
        } catch (InterruptedException ignored) {
        }
    }
}

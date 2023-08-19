import java.util.Scanner;
import Views.FilmeView;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FilmeView filmeView = new FilmeView();
        boolean continuar = true;

        while (continuar) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Criar filme");
            System.out.println("2 - Criar diretor");
            System.out.println("3 - Criar ator");
            System.out.println("4 - Sair");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    filmeView.exibirFilme();
                    break;
                case 2:
                    System.out.println("Aqui vamos criar o diretor"); // verificar com o professor como fazer a junção talvez
                    break;
                case 3:
                    System.out.println("Aqui vamos criar os atores"); // verificar com o professor como fazer a junção talvez
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Escolha novamente.");
                    break;
            }
        }

        System.out.println("Programa encerrado.");
    }
}
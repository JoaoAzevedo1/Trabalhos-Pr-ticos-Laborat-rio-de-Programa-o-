package exBanco;

import java.util.Scanner;

public class Application {
    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        Banco c1 = new Banco(1, 100, "Sede", 1, "Banco Exemplo", 1);
        boolean executando = true;

        try {
            while (executando) {
                exibirMenu();
                int op = lerOpcao();

                switch (op) {
                    case 1:
                        if (c1.estaEncerrada()) {
                            System.out.println("Operação inválida: a conta está encerrada.");
                            break;
                        }
                        c1.creditar(lerValor("Valor para creditar: "));
                        System.out.println(c1.consultarSaldo());
                        break;
                    case 2:
                        if (c1.estaEncerrada()) {
                            System.out.println("Operação inválida: a conta está encerrada.");
                            break;
                        }
                        c1.debitar(lerValor("Valor para debitar: "));
                        System.out.println(c1.consultarSaldo());
                        break;
                    case 3:
                        System.out.println(c1.consultarSaldo());
                        break;
                    case 4:
                        try {
                            double valorDevolvido = c1.encerrarConta();
                            System.out.println(c1.textoEncerrar(valorDevolvido));
                            executando = false;
                        } catch (IllegalStateException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 5:
                        System.out.println("Programa encerrado.");
                        executando = false;
                        break;
                    default:
                        // lerOpcao já limita as opções; este caso protege alterações futuras.
                        System.out.println("Opção inválida.");
                }
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um problema inesperado: " + e.getMessage());
        } finally {
            SC.close();
        }
    }

    private static void exibirMenu() {
        System.out.println("\n1 - Creditar");
        System.out.println("2 - Debitar");
        System.out.println("3 - Consultar saldo");
        System.out.println("4 - Encerrar conta");
        System.out.println("5 - Sair");
    }

    private static int lerOpcao() {
        while (true) {
            System.out.print("Escolha uma opção: ");
            try {
                int opcao = Integer.parseInt(SC.nextLine().trim());
                if (opcao >= 1 && opcao <= 5) {
                    return opcao;
                }
                System.out.println("Opção inválida. Digite um número de 1 a 5.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite apenas um número inteiro.");
            }
        }
    }

    private static double lerValor(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                double valor = Double.parseDouble(SC.nextLine().trim().replace(',', '.'));
                if (Double.isFinite(valor) && valor > 0) {
                    return valor;
                }
                System.out.println("Digite um valor finito e maior que zero.");
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Exemplo aceito: 25,50.");
            }
        }
    }
}

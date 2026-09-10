package exBanco;

/**
 * Representa uma conta bancária e as operações que podem ser realizadas nela.
 */
public class Banco {
    public static final int TIPO_ENCERRADA = 4;

    private final int numero;
    private final int agNumero;
    private final String agNome;
    private final int bancoNumero;
    private final String bancoNome;
    private int tipo;
    private double saldo;

    /** Cria uma conta com valores padrão e saldo inicial zero. */
    public Banco() {
        this(0, 0, "", 0, "", 0);
    }

    /** Cria uma conta com os dados informados e saldo inicial igual a zero. */
    public Banco(int numero, int agNumero, String agNome, int bancoNumero,
                 String bancoNome, int tipo) {
        this.numero = numero;
        this.agNumero = agNumero;
        this.agNome = agNome;
        this.bancoNumero = bancoNumero;
        this.bancoNome = bancoNome;
        this.tipo = tipo;
        this.saldo = 0.0;
    }

    public int getNumero() {
        return numero;
    }

    public int getAgNumero() {
        return agNumero;
    }

    public String getAgNome() {
        return agNome;
    }

    public int getBancoNumero() {
        return bancoNumero;
    }

    public String getBancoNome() {
        return bancoNome;
    }

    public int getTipo() {
        return tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean estaEncerrada() {
        return tipo == TIPO_ENCERRADA;
    }

    /** Credita um valor positivo, desde que a conta esteja ativa. */
    public void creditar(double valor) {
        if (valor <= 0 || estaEncerrada()) {
            return;
        }
        saldo += valor;
    }

    /** Debita um valor positivo, desde que a conta esteja ativa. */
    public void debitar(double valor) {
        if (valor <= 0 || estaEncerrada()) {
            return;
        }
        saldo -= valor;
    }

    public String consultarSaldo() {
        return String.format("Conta %d - saldo atual: R$ %.2f", numero, saldo);
    }

    /**
     * Encerra a conta quando ela não possui saldo negativo.
     *
     * @return o valor devolvido ao encerrar a conta
     * @throws IllegalStateException se a conta possuir saldo negativo ou já estiver encerrada
     */
    public double encerrarConta() {
        if (estaEncerrada()) {
            throw new IllegalStateException("A conta já está encerrada.");
        }
        if (saldo < 0) {
            throw new IllegalStateException("Não é possível encerrar uma conta com saldo negativo.");
        }

        double valorDevolvido = saldo;
        tipo = TIPO_ENCERRADA;
        saldo = 0.0;
        return valorDevolvido;
    }

    public String textoEncerrar(double valorDevolvido) {
        return String.format(
                "Conta %d encerrada. Tipo: %d. Saldo devolvido: R$ %.2f.",
                numero, tipo, valorDevolvido);
    }
}

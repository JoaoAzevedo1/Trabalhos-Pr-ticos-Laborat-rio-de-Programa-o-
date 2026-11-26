package exception;

public class LivroIndisponivelException extends RuntimeException {
    public LivroIndisponivelException(String titulo) {
        super("O livro '" + titulo + "' está indisponível para empréstimo.");
    }
}

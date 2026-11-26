package exception;

public class EmprestimoNaoEncontradoException extends RuntimeException {
    public EmprestimoNaoEncontradoException(int id) {
        super("Empréstimo com ID " + id + " não encontrado.");
    }
}

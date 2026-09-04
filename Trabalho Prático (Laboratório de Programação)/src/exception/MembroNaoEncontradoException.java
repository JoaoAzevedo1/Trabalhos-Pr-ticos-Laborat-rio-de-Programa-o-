package exception;

public class MembroNaoEncontradoException extends RuntimeException {
    public MembroNaoEncontradoException(int id) {
        super("Membro com ID " + id + " não encontrado.");
    }
}

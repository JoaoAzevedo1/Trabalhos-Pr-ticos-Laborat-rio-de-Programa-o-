package service;

import exception.EmprestimoNaoEncontradoException;
import exception.LivroIndisponivelException;
import exception.OperacaoInvalidaException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Emprestimo;
import model.Livro;
import model.Membro;

/** Aplica as regras que relacionam livros, membros e empréstimos. */
public class EmprestimoService {
    private final BibliotecaService bibliotecaService;
    private final List<Emprestimo> emprestimos = new ArrayList<>();

    public EmprestimoService(BibliotecaService bibliotecaService) {
        if (bibliotecaService == null) {
            throw new IllegalArgumentException("O serviço da biblioteca é obrigatório.");
        }
        this.bibliotecaService = bibliotecaService;
    }

    public Emprestimo realizarEmprestimo(int id, int livroId, int membroId) {
        if (emprestimos.stream().anyMatch(item -> item.getId() == id)) {
            throw new OperacaoInvalidaException("Já existe empréstimo com ID " + id + ".");
        }

        Livro livro = bibliotecaService.buscarLivro(livroId);
        Membro membro = bibliotecaService.buscarMembro(membroId);
        if (!livro.isDisponivel()) {
            throw new LivroIndisponivelException(livro.getTitulo());
        }

        // A alteração ocorre junto da criação para impedir dois empréstimos ativos.
        livro.emprestar();
        Emprestimo emprestimo = new Emprestimo(id, livro, membro, LocalDate.now());
        emprestimos.add(emprestimo);
        return emprestimo;
    }

    public Emprestimo buscarEmprestimo(int id) {
        return emprestimos.stream()
                .filter(emprestimo -> emprestimo.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EmprestimoNaoEncontradoException(id));
    }

    public void encerrarEmprestimo(int id) {
        Emprestimo emprestimo = buscarEmprestimo(id);
        if (!emprestimo.isAtivo()) {
            throw new OperacaoInvalidaException("O empréstimo já está encerrado.");
        }
        emprestimo.encerrar(LocalDate.now());
        emprestimo.getLivro().devolver();
    }

    public List<Emprestimo> listarEmprestimos() {
        return List.copyOf(emprestimos);
    }
}

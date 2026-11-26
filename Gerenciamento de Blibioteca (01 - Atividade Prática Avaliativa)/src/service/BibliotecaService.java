package service;

import exception.LivroNaoEncontradoException;
import exception.MembroNaoEncontradoException;
import exception.OperacaoInvalidaException;
import java.util.ArrayList;
import java.util.List;
import model.Livro;
import model.Membro;

/** Mantém e gerencia o catálogo e os membros em memória. */
public class BibliotecaService {
    private final List<Livro> livros = new ArrayList<>();
    private final List<Membro> membros = new ArrayList<>();

    public void adicionarLivro(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("O livro é obrigatório.");
        }
        if (livros.stream().anyMatch(item -> item.getId() == livro.getId())) {
            throw new OperacaoInvalidaException("Já existe livro com ID " + livro.getId() + ".");
        }
        livros.add(livro);
    }

    public Livro buscarLivro(int id) {
        return livros.stream()
                .filter(livro -> livro.getId() == id)
                .findFirst()
                .orElseThrow(() -> new LivroNaoEncontradoException(id));
    }

    public void editarLivro(int id, String novoTitulo, String novoAutor) {
        Livro livro = buscarLivro(id);
        livro.setTitulo(novoTitulo);
        livro.setAutor(novoAutor);
    }

    public void removerLivro(int id) {
        Livro livro = buscarLivro(id);
        if (!livro.isDisponivel()) {
            throw new OperacaoInvalidaException(
                    "Não é possível remover um livro que está emprestado.");
        }
        livros.remove(livro);
    }

    public List<Livro> listarLivros() {
        return List.copyOf(livros);
    }

    public void cadastrarMembro(Membro membro) {
        if (membro == null) {
            throw new IllegalArgumentException("O membro é obrigatório.");
        }
        if (membros.stream().anyMatch(item -> item.getId() == membro.getId())) {
            throw new OperacaoInvalidaException("Já existe membro com ID " + membro.getId() + ".");
        }
        membros.add(membro);
    }

    public Membro buscarMembro(int id) {
        return membros.stream()
                .filter(membro -> membro.getId() == id)
                .findFirst()
                .orElseThrow(() -> new MembroNaoEncontradoException(id));
    }

    public void editarMembro(int id, String novoNome, String novoEmail) {
        Membro membro = buscarMembro(id);
        membro.setNome(novoNome);
        membro.setEmail(novoEmail);
    }

    public List<Membro> listarMembros() {
        return List.copyOf(membros);
    }
}

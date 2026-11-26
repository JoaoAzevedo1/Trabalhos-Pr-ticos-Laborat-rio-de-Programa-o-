package model;

import interfaces.Exibivel;
import java.time.LocalDate;

public class Emprestimo implements Exibivel {
    private final int id;
    private final Livro livro;
    private final Membro membro;
    private final LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(int id, Livro livro, Membro membro, LocalDate dataEmprestimo) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID do empréstimo deve ser positivo.");
        }
        if (livro == null || membro == null || dataEmprestimo == null) {
            throw new IllegalArgumentException("Livro, membro e data são obrigatórios.");
        }
        this.id = id;
        this.livro = livro;
        this.membro = membro;
        this.dataEmprestimo = dataEmprestimo;
    }

    public int getId() {
        return id;
    }

    public Livro getLivro() {
        return livro;
    }

    public Membro getMembro() {
        return membro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public boolean isAtivo() {
        return dataDevolucao == null;
    }

    // Encerra uma única vez e preserva o histórico do empréstimo.
    public void encerrar(LocalDate dataDevolucao) {
        if (!isAtivo()) {
            throw new IllegalStateException("O empréstimo já foi encerrado.");
        }
        if (dataDevolucao == null || dataDevolucao.isBefore(dataEmprestimo)) {
            throw new IllegalArgumentException("Data de devolução inválida.");
        }
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public String exibirInformacoes() {
        return "Emprestimo{id=" + id + ", livro='" + livro.getTitulo()
                + "', membro='" + membro.getNome() + "', dataEmprestimo="
                + dataEmprestimo + ", dataDevolucao=" + dataDevolucao
                + ", ativo=" + isAtivo() + "}";
    }

    @Override
    public String toString() {
        return exibirInformacoes();
    }
}

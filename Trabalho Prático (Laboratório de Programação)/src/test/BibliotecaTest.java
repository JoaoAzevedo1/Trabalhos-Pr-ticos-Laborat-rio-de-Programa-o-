package test;

import exception.EmprestimoNaoEncontradoException;
import exception.LivroIndisponivelException;
import exception.LivroNaoEncontradoException;
import exception.MembroNaoEncontradoException;
import exception.OperacaoInvalidaException;
import model.Emprestimo;
import model.Livro;
import model.Membro;
import service.BibliotecaService;
import service.EmprestimoService;

/** Runner de testes simples, sem bibliotecas externas. */
public class BibliotecaTest {
    private static int totalTestes;

    public static void main(String[] args) {
        BibliotecaService biblioteca = new BibliotecaService();
        EmprestimoService emprestimos = new EmprestimoService(biblioteca);

        Livro livro1 = new Livro(1, "Dom Casmurro", "Machado de Assis");
        Livro livro2 = new Livro(2, "Iracema", "José de Alencar");
        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);
        verificar(biblioteca.listarLivros().size() == 2,
                "cadastro e listagem de livros");

        biblioteca.editarLivro(1, "Dom Casmurro - Revisado", "Machado de Assis");
        verificar("Dom Casmurro - Revisado".equals(biblioteca.buscarLivro(1).getTitulo()),
                "edição de livro");

        biblioteca.removerLivro(2);
        verificar(biblioteca.listarLivros().size() == 1,
                "remoção de livro");
        esperarExcecao(LivroNaoEncontradoException.class,
                () -> biblioteca.buscarLivro(2), "busca de livro inexistente");

        Membro membro = new Membro(1, "Ana Silva", "ana@email.com");
        biblioteca.cadastrarMembro(membro);
        verificar(biblioteca.listarMembros().size() == 1,
                "cadastro e listagem de membros");

        biblioteca.editarMembro(1, "Ana Souza", "ana.souza@email.com");
        verificar("Ana Souza".equals(biblioteca.buscarMembro(1).getNome()),
                "edição de membro");
        esperarExcecao(MembroNaoEncontradoException.class,
                () -> biblioteca.buscarMembro(99), "busca de membro inexistente");

        Emprestimo emprestimo = emprestimos.realizarEmprestimo(1, 1, 1);
        verificar(emprestimo.isAtivo() && !livro1.isDisponivel(),
                "realização de empréstimo e indisponibilidade do livro");
        verificar(emprestimos.listarEmprestimos().size() == 1,
                "listagem de empréstimos");

        esperarExcecao(LivroIndisponivelException.class,
                () -> emprestimos.realizarEmprestimo(2, 1, 1),
                "tentativa de emprestar livro indisponível");
        esperarExcecao(OperacaoInvalidaException.class,
                () -> biblioteca.removerLivro(1),
                "tentativa de remover livro emprestado");

        emprestimos.encerrarEmprestimo(1);
        verificar(!emprestimo.isAtivo() && livro1.isDisponivel()
                        && emprestimo.getDataDevolucao() != null,
                "encerramento de empréstimo e disponibilidade do livro");
        esperarExcecao(OperacaoInvalidaException.class,
                () -> emprestimos.encerrarEmprestimo(1),
                "tentativa de encerrar empréstimo já encerrado");
        esperarExcecao(EmprestimoNaoEncontradoException.class,
                () -> emprestimos.encerrarEmprestimo(99),
                "busca de empréstimo inexistente");

        System.out.println("Todos os " + totalTestes + " testes passaram.");
    }

    private static void verificar(boolean condicao, String descricao) {
        totalTestes++;
        if (!condicao) {
            throw new AssertionError("Falha no teste: " + descricao);
        }
        System.out.println("[OK] " + descricao);
    }

    private static void esperarExcecao(
            Class<? extends Throwable> tipoEsperado, Runnable acao, String descricao) {
        totalTestes++;
        try {
            acao.run();
        } catch (Throwable erro) {
            if (tipoEsperado.isInstance(erro)) {
                System.out.println("[OK] " + descricao);
                return;
            }
            throw new AssertionError(
                    "Falha no teste '" + descricao + "': exceção "
                            + erro.getClass().getSimpleName() + " não era esperada.", erro);
        }
        throw new AssertionError(
                "Falha no teste '" + descricao + "': a exceção "
                        + tipoEsperado.getSimpleName() + " não foi lançada.");
    }
}

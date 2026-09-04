package app;

import exception.LivroIndisponivelException;
import interfaces.Exibivel;
import java.util.List;
import model.Emprestimo;
import model.Funcionario;
import model.Livro;
import model.Membro;
import model.Usuario;
import service.BibliotecaService;
import service.EmprestimoService;

/** Executa um roteiro completo para demonstrar as funcionalidades do sistema. */
public class Main {
    public static void main(String[] args) {
        BibliotecaService biblioteca = new BibliotecaService();
        EmprestimoService emprestimos = new EmprestimoService(biblioteca);

        Livro livro1 = new Livro(1, "Dom Casmurro", "Machado de Assis");
        Livro livro2 = new Livro(2, "O Cortiço", "Aluísio Azevedo");
        Livro livroTemporario = new Livro(3, "Livro temporário", "Autor temporário");
        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);
        biblioteca.adicionarLivro(livroTemporario);
        biblioteca.editarLivro(2, "O Cortiço - Edição Revisada", "Aluísio Azevedo");
        biblioteca.removerLivro(3);

        Membro membro = new Membro(1, "Ana Silva", "ana@email.com");
        biblioteca.cadastrarMembro(membro);
        biblioteca.editarMembro(1, "Ana Souza", "ana.souza@email.com");

        System.out.println("=== LIVROS ===");
        exibirTodos(biblioteca.listarLivros());
        System.out.println("\n=== MEMBROS ===");
        exibirTodos(biblioteca.listarMembros());

        Emprestimo emprestimo = emprestimos.realizarEmprestimo(1, 1, 1);
        System.out.println("\n=== EMPRÉSTIMO REALIZADO ===");
        System.out.println(emprestimo.exibirInformacoes());

        try {
            emprestimos.realizarEmprestimo(2, 1, 1);
        } catch (LivroIndisponivelException excecao) {
            System.out.println("\nErro esperado: " + excecao.getMessage());
        }

        emprestimos.encerrarEmprestimo(1);
        System.out.println("\n=== EMPRÉSTIMOS APÓS DEVOLUÇÃO ===");
        exibirTodos(emprestimos.listarEmprestimos());

        // A mesma chamada executa versões diferentes conforme o tipo concreto.
        List<Usuario> usuarios = List.of(
                membro,
                new Funcionario(2, "Carlos Lima", "Bibliotecário"));
        System.out.println("\n=== POLIMORFISMO ===");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.exibirInformacoes());
        }
    }

    private static void exibirTodos(List<? extends Exibivel> itens) {
        for (Exibivel item : itens) {
            System.out.println(item.exibirInformacoes());
        }
    }
}

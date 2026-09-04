package model;

import interfaces.Exibivel;

public class Livro implements Exibivel {
    private final int id;
    private String titulo;
    private String autor;
    private boolean disponivel;

    public Livro(int id, String titulo, String autor) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID do livro deve ser positivo.");
        }
        this.id = id;
        setTitulo(titulo);
        setAutor(autor);
        this.disponivel = true;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = validarTexto(titulo, "título");
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = validarTexto(autor, "autor");
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    // A disponibilidade só é alterada pelas regras do serviço de empréstimos.
    public void emprestar() {
        if (!disponivel) {
            throw new IllegalStateException("O livro já está indisponível.");
        }
        disponivel = false;
    }

    public void devolver() {
        if (disponivel) {
            throw new IllegalStateException("O livro já está disponível.");
        }
        disponivel = true;
    }

    private static String validarTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("O campo " + campo + " é obrigatório.");
        }
        return valor.trim();
    }

    @Override
    public String exibirInformacoes() {
        return "Livro{id=" + id + ", titulo='" + titulo + "', autor='" + autor
                + "', disponivel=" + disponivel + "}";
    }

    @Override
    public String toString() {
        return exibirInformacoes();
    }
}

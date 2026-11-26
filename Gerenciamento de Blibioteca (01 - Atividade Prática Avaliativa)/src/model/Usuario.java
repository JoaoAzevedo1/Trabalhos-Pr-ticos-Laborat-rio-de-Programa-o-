package model;

import interfaces.Exibivel;

/** Reúne os dados e comportamentos comuns aos usuários da biblioteca. */
public abstract class Usuario implements Exibivel {
    private final int id;
    private String nome;

    protected Usuario(int id, String nome) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID do usuário deve ser positivo.");
        }
        this.id = id;
        setNome(nome);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = validarTexto(nome, "nome");
    }

    protected static String validarTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("O campo " + campo + " é obrigatório.");
        }
        return valor.trim();
    }
}

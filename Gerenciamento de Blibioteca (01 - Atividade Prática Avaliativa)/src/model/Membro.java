package model;

public class Membro extends Usuario {
    private String email;

    public Membro(int id, String nome, String email) {
        super(id, nome);
        setEmail(email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String emailValidado = validarTexto(email, "e-mail");
        if (!emailValidado.contains("@")) {
            throw new IllegalArgumentException("O e-mail deve conter '@'.");
        }
        this.email = emailValidado;
    }

    @Override
    public String exibirInformacoes() {
        return "Membro{id=" + getId() + ", nome='" + getNome()
                + "', email='" + email + "'}";
    }

    @Override
    public String toString() {
        return exibirInformacoes();
    }
}

package model;

public class Funcionario extends Usuario {
    private String cargo;

    public Funcionario(int id, String nome, String cargo) {
        super(id, nome);
        setCargo(cargo);
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = validarTexto(cargo, "cargo");
    }

    @Override
    public String exibirInformacoes() {
        return "Funcionario{id=" + getId() + ", nome='" + getNome()
                + "', cargo='" + cargo + "'}";
    }

    @Override
    public String toString() {
        return exibirInformacoes();
    }
}

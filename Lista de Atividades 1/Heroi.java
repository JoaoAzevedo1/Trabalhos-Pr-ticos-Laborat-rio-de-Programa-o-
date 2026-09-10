public abstract class Heroi {
    private String nome;
    private int vida;
    private int energia;

    public Heroi(String nome, int vida, int energia) {
        this.nome = nome;
        this.vida = vida;
        this.energia = energia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public void apresentarSe() {
        System.out.println("Heroi: " + nome);
        System.out.println("Vida: " + vida);
        System.out.println("Energia: " + energia);
    }

    public abstract void atacar();
}

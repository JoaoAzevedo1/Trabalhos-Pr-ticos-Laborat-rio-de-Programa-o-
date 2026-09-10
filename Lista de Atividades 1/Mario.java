public class Mario extends Heroi {
    public Mario(String nome, int vida, int energia) {
        super(nome, vida, energia);
    }

    @Override
    public void atacar() {
        System.out.println(getNome() + " ataca pulando sobre o inimigo!");
    }
}

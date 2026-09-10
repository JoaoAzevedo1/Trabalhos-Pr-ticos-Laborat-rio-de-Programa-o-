public class Goku extends Heroi {
    public Goku(String nome, int vida, int energia) {
        super(nome, vida, energia);
    }

    @Override
    public void atacar() {
        System.out.println(getNome() + " ataca com o Kamehameha!");
    }
}

public class LigaDosHerois {
    public static void main(String[] args) {
        Heroi[] herois = {
            new Goku("Goku", 100, 100),
            new Sonic("Sonic", 80, 120),
            new Mario("Mario", 90, 80)
        };

        for (Heroi heroi : herois) {
            heroi.apresentarSe();
            heroi.atacar();
            System.out.println();
        }
    }
}

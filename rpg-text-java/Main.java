
public class Main {
    public static void main(String[] args) {
        try {
            Game jogo = new Game();
            jogo.iniciar();
        } catch (Exception e) {
            System.out.println("❌ Ocorreu um erro inesperado: " + e.getMessage());
            System.out.println("Por favor, reinicie o jogo.");
        }
    }
}
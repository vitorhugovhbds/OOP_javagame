
import java.util.Scanner;

public class GameMenu {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void mostrarMenuPrincipal() {
        System.out.println("\n🎮 === MENU PRINCIPAL ===");
        System.out.println("1. 🚀 Iniciar Novo Jogo");
        System.out.println("2. 📖 Ver Instruções");
        System.out.println("3. 🏆 Créditos");
        System.out.println("4. 🚪 Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    public static void mostrarMenuCriacaoPersonagem() {
        System.out.println("\n👤 === CRIAÇÃO DE PERSONAGEM ===");
        System.out.println("Escolha sua classe:");
        System.out.println("1. 🛡️  Guerreiro - Forte e Resistente");
        System.out.println("2. 🔮 Mago - Poder Mágico Elevado");
        System.out.println("3. 🏹 Arqueiro - Preciso e Ágil");
        System.out.print("Escolha sua classe (1-3): ");
    }
    
    public static String lerNomePersonagem() {
        System.out.print("Digite o nome do seu personagem: ");
        return scanner.nextLine().trim();
    }
    
    public static int lerOpcao(int min, int max) {
        while (true) {
            try {
                int opcao = scanner.nextInt();
                scanner.nextLine();
                if (opcao >= min && opcao <= max) {
                    return opcao;
                } else {
                    System.out.print("Opção inválida! Escolha entre " + min + " e " + max + ": ");
                }
            } catch (Exception e) {
                System.out.print("Entrada inválida! Digite um número: ");
                scanner.nextLine();
            }
        }
    }
    
    public static void pausa() {
        System.out.println("\nPressione Enter para continuar...");
        scanner.nextLine();
    }
}
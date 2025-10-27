import java.util.Scanner;

public class Game {
    private Personagem jogador;
    private boolean jogoRodando;
    private int areaAtual;
    
    public void iniciar() {
        jogoRodando = true;
        System.out.println("🎮 RPG DE TEXTO - A JORNADA DA PEDRA DA ETERNIDADE 🎮");
        
        while (jogoRodando) {
            GameMenu.mostrarMenuPrincipal();
            int opcao = GameMenu.lerOpcao(1, 4);
            
            switch (opcao) {
                case 1:
                    iniciarNovoJogo();
                    break;
                case 2:
                    System.out.println("\n⚔️ Instruções: Derrote inimigos, colete itens, complete a missão!");
                    GameMenu.pausa();
                    break;
                case 3:
                    System.out.println("\n👨‍💻 Desenvolvido para aprendizado de OOP em Java");
                    GameMenu.pausa();
                    break;
                case 4:
                    jogoRodando = false;
                    System.out.println("👋 Até logo!");
                    break;
            }
        }
    }
    
    private void iniciarNovoJogo() {
        criarPersonagem();
        GameStory.mostrarIntroducao(jogador.getNome());
        GameMenu.pausa();
        
        // Sistema de progressão por áreas
        areaAtual = 1;
        while (jogoRodando && jogador.estaVivo() && areaAtual <= 3) {
            switch (areaAtual) {
                case 1:
                    if (explorarFloresta()) areaAtual++;
                    break;
                case 2:
                    if (explorarCaverna()) areaAtual++;
                    break;
                case 3:
                    if (enfrentarChefeFinal()) areaAtual++;
                    break;
            }
            GameMenu.pausa();
        }
        
        finalizarJogo();
    }
    
    private void criarPersonagem() {
        GameMenu.mostrarMenuCriacaoPersonagem();
        int classe = GameMenu.lerOpcao(1, 3);
        String nome = GameMenu.lerNomePersonagem();
        
        switch (classe) {
            case 1:
                jogador = new Guerreiro(nome);
                break;
            case 2:
                jogador = new Mago(nome);
                break;
            case 3:
                jogador = new Arqueiro(nome);
                break;
        }
        
        // Itens iniciais
        jogador.getInventario().adicionarItem(new Item("Poção de Cura", "Cura 20 HP", "cura", 3));
        jogador.getInventario().adicionarItem(new Item("Erva Curativa", "Cura 15 HP", "cura", 2));
        
        System.out.println("\n✅ Personagem criado: " + jogador);
        GameMenu.pausa();
    }
    
    private boolean explorarFloresta() {
        Scanner scanner = new Scanner(System.in);
        
        while (jogador.estaVivo()) {
            GameStory.mostrarFlorestaMisteriosa();
            System.out.println("\n🌲 O que você faz na floresta?");
            System.out.println("1. 🔍 Explorar");
            System.out.println("2. 🎒 Ver Inventário");
            System.out.println("3. ❤️  Ver Status");
            System.out.println("4. 🚶 Seguir para Caverna");
            System.out.print("Escolha: ");
            
            int escolha = scanner.nextInt();
            
            switch (escolha) {
                case 1:
                    if (!eventoFloresta()) return false;
                    break;
                case 2:
                    gerenciarInventario();
                    break;
                case 3:
                    mostrarStatus();
                    break;
                case 4:
                    System.out.println("\n⭐ Você encontra a entrada da Caverna Sombria...");
                    return true;
                default:
                    System.out.println("❌ Opção inválida!");
            }
        }
        return false;
    }
    
    private boolean eventoFloresta() {
        int evento = Dice.roll(3);
        
        switch (evento) {
            case 1:
                GameStory.encontroComLobo();
                Inimigo lobo = new Inimigo("Lobo da Floresta", "Animal", 
                    GameConstants.LOBO_HP, GameConstants.LOBO_ATK, GameConstants.LOBO_DEF, 1);
                
                int resultadoBatalha = CombatSystem.batalhar(jogador, lobo);
                
                if (resultadoBatalha == 2) { // Fugiu
                    System.out.println("🏃 Você fugiu do lobo...");
                    return true;
                } else if (resultadoBatalha == 0 || !jogador.estaVivo()) { // Derrota
                    return false;
                }
                // Vitória - continua
                break;
                
            case 2:
                GameStory.encontrouBau();
                System.out.println("🎁 Você encontrou uma poção forte!");
                jogador.getInventario().adicionarItem(new Item("Poção Forte", "Cura 30 HP", "cura", 1));
                break;
                
            case 3:
                GameStory.encontrouPocao();
                System.out.println("🧪 Você encontrou ervas medicinais!");
                jogador.getInventario().adicionarItem(new Item("Erva Curativa", "Cura 15 HP", "cura", 2));
                break;
        }
        return true;
    }
    
    private boolean explorarCaverna() {
        GameStory.mostrarCavernaSombria();
        
        // Chefe da caverna - Goblin
        GameStory.encontroComGoblin();
        Inimigo goblin = new Inimigo("Goblin Guardião", "Goblin",
            GameConstants.GOBLIN_HP, GameConstants.GOBLIN_ATK, GameConstants.GOBLIN_DEF, 2);
        
        // Goblin tem itens especiais
        goblin.getInventario().adicionarItem(new Item("Poção Forte", "Cura 30 HP", "cura", 1));
        goblin.getInventario().adicionarItem(new Item("Adaga Afiada", "Aumenta ataque", "ataque", 1));
        
        int resultadoBatalha = CombatSystem.batalhar(jogador, goblin);
        
        if (resultadoBatalha == 2) { // Fugiu
            System.out.println("🏃 Você fugiu da caverna...");
            return false;
        } else if (resultadoBatalha == 0 || !jogador.estaVivo()) { // Derrota
            return false;
        } else if (resultadoBatalha == 1) { // Vitória - SÓ AQUI SAQUEIA
            // Saquear o goblin - APENAS SE VENCEU
            System.out.println("💎 Saqueando o Goblin...");
            System.out.println("  → Poção Forte");
            System.out.println("  → Adaga Afiada");
            jogador.getInventario().adicionarItem(new Item("Poção Forte", "Cura 30 HP", "cura", 1));
            jogador.getInventario().adicionarItem(new Item("Adaga Afiada", "Aumenta ataque", "ataque", 1));
        }
        
        System.out.println("\n🗺️ Você encontra um mapa que leva ao Castelo Antigo...");
        return true;
    }
    
    private boolean enfrentarChefeFinal() {
        Scanner scanner = new Scanner(System.in);
        GameStory.mostrarCasteloAntigo();
        
        System.out.println("🔮 Duas portas se apresentam:");
        System.out.println("1. Porta das Runas Místicas");
        System.out.println("2. Porta das Marcas de Batalha");
        System.out.print("Qual porta escolhe? (1-2): ");
        
        int escolha = scanner.nextInt();
        
        if (escolha == 1) {
            GameStory.portaMistica();
            System.out.println("📚 Você encontra pergaminhos mágicos!");
            jogador.getInventario().adicionarItem(new Item("Pergaminho Mágico", "Aumenta ataque", "ataque", 2));
        } else {
            GameStory.portaBatalha();
            System.out.println("⚔️ Uma armadura animada ataca!");
            Inimigo armadura = new Inimigo("Armadura Animada", "Constructo", 80, 15, 12, 3);
            
            int resultadoBatalha = CombatSystem.batalhar(jogador, armadura);
            
            if (resultadoBatalha == 2) { // Fugiu
                System.out.println("🏃 Você fugiu do castelo...");
                return false;
            } else if (resultadoBatalha == 0 || !jogador.estaVivo()) { // Derrota
                return false;
            }
            // Se vitória, continua
        }
        
        // CHEFE FINAL
        GameStory.mostrarSalaoDoTrono();
        GameStory.encontroComGuardiao();
        Inimigo guardiao = new Inimigo("Guardião Ancestral", "Espírito",
            GameConstants.GUARDIAO_HP, GameConstants.GUARDIAO_ATK, GameConstants.GUARDIAO_DEF, 5);
        
        guardiao.getInventario().adicionarItem(new Item("Pedra da Eternidade", "Artefato lendário", "poder", 1));
        
        System.out.println("\n⚠️  BATALHA FINAL!");
        int resultadoFinal = CombatSystem.batalhar(jogador, guardiao);
        
        if (resultadoFinal == 1 && jogador.estaVivo()) { // Vitória
            GameStory.finalVitoria();
            return true;
        } else {
            return false;
        }
    }
    
    // MÉTODO CORRIGIDO - INVENTÁRIO FUNCIONAL
    private void gerenciarInventario() {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n🎒 === GERENCIAR INVENTÁRIO ===");
            System.out.println("Itens totais: " + jogador.getInventario().getQuantidadeTotal());
            System.out.println("1. 📋 Listar Itens");
            System.out.println("2. 🧪 Usar Item");
            System.out.println("3. ↩️  Voltar");
            System.out.print("Escolha: ");
            
            int escolha = scanner.nextInt();
            scanner.nextLine();
            
            switch (escolha) {
                case 1:
                    System.out.println("\n📋 === ITENS NO INVENTÁRIO ===");
                    jogador.getInventario().listarItens();
                    break;
                case 2:
                    usarItemInventario();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("❌ Opção inválida!");
            }
        }
    }
    
    // MÉTODO CORRIGIDO - AGORA VAI FUNCIONAR!
    private void usarItemInventario() {
        if (!jogador.getInventario().temItens()) {
            System.out.println("📭 Seu inventário está vazio!");
            return;
        }
        
        Scanner scanner = new Scanner(System.in);
        
        // Primeiro listamos os itens
        System.out.println("\n🧪 === USAR ITEM ===");
        System.out.println("Seus itens:");
        jogador.getInventario().listarItens();
        
        System.out.print("📝 Digite o NOME do item que deseja usar: ");
        String nomeItem = scanner.nextLine();
        
        Item item = jogador.getInventario().getItem(nomeItem);
        if (item != null) {
            System.out.println("\n🎯 Usando " + item.getNome() + "...");
            item.usar(jogador);
            
            // Atualizar o inventário após usar
            if (item.getQuantidade() <= 0) {
                jogador.getInventario().removerItem(item.getNome());
                System.out.println("✅ Item usado e removido do inventário!");
            } else {
                System.out.println("✅ Item usado! Restam: " + item.getQuantidade());
            }
        } else {
            System.out.println("❌ Item não encontrado! Verifique o nome digitado.");
        }
    }
    
    private void mostrarStatus() {
        System.out.println("\n📊 === STATUS ===");
        System.out.println(jogador);
        System.out.println("Ataque: " + jogador.calcularAtaque() + " (Base: " + jogador.getAtaque() + ")");
        System.out.println("Defesa: " + jogador.calcularDefesa() + " (Base: " + jogador.getDefesa() + ")");
        System.out.println("Itens no inventário: " + jogador.getInventario().getQuantidadeTotal());
        System.out.println("=================");
    }
    
    private void finalizarJogo() {
        if (jogador.estaVivo() && areaAtual > 3) {
            GameStory.finalVitoria();
            System.out.println("\n🏆 PARABÉNS! VOCÊ COMPLETOU A JORNADA!");
        } else {
            GameStory.finalDerrota();
            System.out.println("\n💀 Fim do jogo...");
        }
        jogoRodando = false;
    }
}
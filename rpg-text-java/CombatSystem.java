import java.util.List;
import java.util.Scanner;

public class CombatSystem {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static int batalhar(Personagem jogador, Inimigo inimigo) {
        System.out.println("\n⚔️ === BATALHA CONTRA " + inimigo.getNome().toUpperCase() + " ===");
        
        int turno = 1;
        while (jogador.estaVivo() && inimigo.estaVivo()) {
            System.out.println("\n--- Turno " + turno + " ---");
            System.out.println("❤️  " + jogador.getNome() + ": " + jogador.getPontosVida() + "/" + jogador.getPontosVidaMax() + " HP");
            System.out.println("💀 " + inimigo.getNome() + ": " + inimigo.getPontosVida() + " HP");
            
            // Turno do jogador - agora retorna int
            int resultadoTurno = turnoJogador(jogador, inimigo);
            
            if (resultadoTurno == 2) { // Fugiu com sucesso
                return 2;
            }
            if (resultadoTurno == 3) { // Usou item e continua batalha
                // Continua a batalha normalmente
            }
            if (!inimigo.estaVivo()) break;
            
            // Turno do inimigo
            if (jogador.estaVivo()) {
                turnoInimigo(jogador, inimigo);
            }
            
            turno++;
        }
        
        return verificarResultado(jogador, inimigo);
    }
    
    private static int turnoJogador(Personagem jogador, Inimigo inimigo) {
        System.out.println("\n🎯 SUA VEZ:");
        System.out.println("1. ⚔️  Atacar");
        System.out.println("2. 💨 Fugir");
        System.out.println("3. 🎒 Inventário");
        System.out.print("Escolha uma ação: ");
        
        int escolha = scanner.nextInt();
        scanner.nextLine();
        
        switch (escolha) {
            case 1:
                atacarJogador(jogador, inimigo);
                return 1; // Continuar batalha
            case 2:
                if (tentarFugir()) {
                    return 2; // Fugiu com sucesso
                } else {
                    return 1; // Falhou em fugir, continua batalha
                }
            case 3:
                return usarItemBatalha(jogador, inimigo); // Usar item do inventário
            default:
                System.out.println("❌ Opção inválida! Atacando automaticamente.");
                atacarJogador(jogador, inimigo);
                return 1;
        }
    }
    
    private static int usarItemBatalha(Personagem jogador, Inimigo inimigo) {
        if (!jogador.getInventario().temItens()) {
            System.out.println("📭 Seu inventário está vazio!");
            return 1; // Volta para batalha
        }
        
        System.out.println("\n🎒 === INVENTÁRIO NA BATALHA ===");
        jogador.getInventario().listarItens();
        System.out.println("0. ↩️  Voltar para batalha");
        System.out.print("Escolha o NÚMERO do item para usar: ");
        
        try {
            int escolha = scanner.nextInt();
            scanner.nextLine();
            
            if (escolha == 0) {
                System.out.println("↩️  Voltando para batalha...");
                return 1;
            }
            
            // Buscar o item pelo índice
            List<Item> itens = jogador.getInventario().getItens();
            if (escolha > 0 && escolha <= itens.size()) {
                Item itemSelecionado = itens.get(escolha - 1);
                
                System.out.println("\n🎯 Usando " + itemSelecionado.getNome() + " durante a batalha...");
                
                // Verificar se é item de cura
                if (itemSelecionado.getEfeito().equalsIgnoreCase("cura")) {
                    int vidaAntes = jogador.getPontosVida();
                    int vidaMax = jogador.getPontosVidaMax();
                    
                    // Calcular cura real sem ultrapassar o máximo
                    int curaPotencial = 0;
                    if (itemSelecionado.getNome().contains("Poção Forte")) {
                        curaPotencial = GameConstants.POCAO_FORTE_CURA;
                    } else if (itemSelecionado.getNome().contains("Poção")) {
                        curaPotencial = GameConstants.POCAO_CURA;
                    } else {
                        curaPotencial = GameConstants.ERVA_CURA;
                    }
                    
                    int curaReal = Math.min(curaPotencial, vidaMax - vidaAntes);
                    
                    if (curaReal > 0) {
                        jogador.curar(curaReal);
                        System.out.println("✨ " + jogador.getNome() + " recuperou " + curaReal + " HP!");
                        System.out.println("❤️  HP atual: " + jogador.getPontosVida() + "/" + vidaMax);
                        
                        // Remover uma unidade do item
                        jogador.getInventario().removerItem(itemSelecionado.getNome());
                    } else {
                        System.out.println("💤 " + jogador.getNome() + " já está com a vida cheia!");
                        System.out.println("❤️  HP: " + jogador.getPontosVida() + "/" + vidaMax);
                    }
                    
                    return 3; // Item usado, continua batalha
                } else {
                    // Para outros tipos de item (ataque, defesa, etc.)
                    itemSelecionado.usar(jogador);
                    System.out.println("🔮 Efeito aplicado: " + itemSelecionado.getEfeito());
                    
                    // Remover item se acabou
                    if (itemSelecionado.getQuantidade() <= 0) {
                        jogador.getInventario().removerItem(itemSelecionado.getNome());
                    }
                    
                    return 3; // Item usado, continua batalha
                }
            } else {
                System.out.println("❌ Número de item inválido!");
                return usarItemBatalha(jogador, inimigo); // Tenta novamente
            }
        } catch (Exception e) {
            System.out.println("❌ Entrada inválida!");
            scanner.nextLine();
            return usarItemBatalha(jogador, inimigo); // Tenta novamente
        }
    }
    
    private static void atacarJogador(Personagem jogador, Inimigo inimigo) {
        int dadoJogador = Dice.rollD20();
        int ataqueJogador = jogador.calcularAtaque() + dadoJogador;
        int defesaInimigo = inimigo.calcularDefesa();
        
        System.out.println("🎲 Você rolou " + dadoJogador + " no D20!");
        
        if (ataqueJogador > defesaInimigo) {
            int dano = ataqueJogador - defesaInimigo;
            System.out.println("✅ Acertou! Causou " + dano + " de dano!");
            inimigo.receberDano(dano);
        } else {
            System.out.println("❌ O inimigo se defendeu do seu ataque!");
        }
    }
    
    private static void turnoInimigo(Personagem jogador, Inimigo inimigo) {
        System.out.println("\n💀 VEZ DO " + inimigo.getNome().toUpperCase());
        
        int dadoInimigo = Dice.rollD20();
        int ataqueInimigo = inimigo.calcularAtaque() + dadoInimigo;
        int defesaJogador = jogador.calcularDefesa();
        
        System.out.println("🎲 " + inimigo.getNome() + " rolou " + dadoInimigo + " no D20!");
        
        if (ataqueInimigo > defesaJogador) {
            int dano = ataqueInimigo - defesaJogador;
            System.out.println("✅ " + inimigo.getNome() + " acertou! Causou " + dano + " de dano!");
            jogador.receberDano(dano);
        } else {
            System.out.println("❌ Você se defendeu do ataque inimigo!");
        }
    }
    
    private static boolean tentarFugir() {
        System.out.println("💨 Tentando fugir...");
        int rolagem = Dice.rollD10();
        
        if (rolagem > 5) {
            System.out.println("✅ Fuga bem-sucedida!");
            return true;
        } else {
            System.out.println("❌ Falha na fuga!");
            return false;
        }
    }
    
    private static int verificarResultado(Personagem jogador, Inimigo inimigo) {
        if (jogador.estaVivo()) {
            System.out.println("\n🎉 VITÓRIA! Você derrotou " + inimigo.getNome() + "!");
            return 1; // Vitória
        } else {
            System.out.println("\n💀 DERROTA... " + inimigo.getNome() + " foi demais para você.");
            return 0; // Derrota
        }
    }
}
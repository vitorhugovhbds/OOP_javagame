// Importações necessárias para a classe CombatSystem
import java.util.List; // Para usar List na manipulação de itens do inventário
import java.util.Scanner; // Para entrada de dados do usuário durante o combate

// Classe responsável por gerenciar todo o sistema de combate do RPG
public class CombatSystem {
    // Scanner estático para entrada de dados, compartilhado por todos os métodos
    private static final Scanner scanner = new Scanner(System.in);
    
    // Método principal que controla toda a batalha entre jogador e inimigo
    public static int batalhar(Personagem jogador, Inimigo inimigo) {
        // Cabeçalho da batalha com emojis para melhor experiência visual
        System.out.println("\n⚔️ === BATALHA CONTRA " + inimigo.getNome().toUpperCase() + " ===");
        
        // Contador de turnos para acompanhar o progresso da batalha
        int turno = 1;
        // Loop principal da batalha - continua enquanto ambos estiverem vivos
        while (jogador.estaVivo() && inimigo.estaVivo()) {
            // Exibe informações do turno atual e status dos combatentes
            System.out.println("\n--- Turno " + turno + " ---");
            System.out.println("❤️  " + jogador.getNome() + ": " + jogador.getPontosVida() + "/" + jogador.getPontosVidaMax() + " HP");
            System.out.println("💀 " + inimigo.getNome() + ": " + inimigo.getPontosVida() + " HP");
            
            // Turno do jogador - agora retorna int
            int resultadoTurno = turnoJogador(jogador, inimigo);
            
            // Verifica se o jogador fugiu com sucesso
            if (resultadoTurno == 2) { // Fugiu com sucesso
                return 2; // Retorna código de fuga bem-sucedida
            }
            // Verifica se o jogador usou item e continua batalha
            if (resultadoTurno == 3) { // Usou item e continua batalha
                // Continua a batalha normalmente - não faz nada especial
            }
            // Verifica se o inimigo morreu durante o turno do jogador
            if (!inimigo.estaVivo()) break; // Sai do loop se inimigo morreu
            
            // Turno do inimigo - só executa se o jogador ainda estiver vivo
            if (jogador.estaVivo()) {
                turnoInimigo(jogador, inimigo);
            }
            
            // Incrementa o contador de turnos para o próximo ciclo
            turno++;
        }
        
        // Após o término da batalha, verifica e retorna o resultado
        return verificarResultado(jogador, inimigo);
    }
    
    // Método que controla o turno do jogador durante a batalha
    private static int turnoJogador(Personagem jogador, Inimigo inimigo) {
        // Apresenta as opções de ação para o jogador
        System.out.println("\n🎯 SUA VEZ:");
        System.out.println("1. ⚔️  Atacar");
        System.out.println("2. 💨 Fugir");
        System.out.println("3. 🎒 Inventário");
        System.out.print("Escolha uma ação: ");
        
        // Lê a escolha do jogador
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer do scanner
        
        // Switch para processar a escolha do jogador
        switch (escolha) {
            case 1:
                // Executa ataque do jogador
                atacarJogador(jogador, inimigo);
                return 1; // Continuar batalha
            case 2:
                // Tenta fugir da batalha
                if (tentarFugir()) {
                    return 2; // Fugiu com sucesso
                } else {
                    return 1; // Falhou em fugir, continua batalha
                }
            case 3:
                // Abre o inventário para usar itens
                return usarItemBatalha(jogador, inimigo); // Usar item do inventário
            default:
                // Opção inválida - ataca automaticamente
                System.out.println("❌ Opção inválida! Atacando automaticamente.");
                atacarJogador(jogador, inimigo);
                return 1;
        }
    }
    
    // Método para usar itens do inventário durante a batalha
    private static int usarItemBatalha(Personagem jogador, Inimigo inimigo) {
        // Verifica se o inventário tem itens disponíveis
        if (!jogador.getInventario().temItens()) {
            System.out.println("📭 Seu inventário está vazio!");
            return 1; // Volta para batalha
        }
        
        // Exibe interface do inventário em batalha
        System.out.println("\n🎒 === INVENTÁRIO NA BATALHA ===");
        jogador.getInventario().listarItens(); // Lista todos os itens disponíveis
        System.out.println("0. ↩️  Voltar para batalha");
        System.out.print("Escolha o NÚMERO do item para usar: ");
        
        // Bloco try-catch para tratamento de entradas inválidas
        try {
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            
            // Verifica se o jogador escolheu voltar
            if (escolha == 0) {
                System.out.println("↩️  Voltando para batalha...");
                return 1; // Retorna para batalha
            }
            
            // Buscar o item pelo índice
            List<Item> itens = jogador.getInventario().getItens();
            // Verifica se a escolha está dentro dos limites válidos
            if (escolha > 0 && escolha <= itens.size()) {
                Item itemSelecionado = itens.get(escolha - 1); // Ajusta índice para base 0
                
                System.out.println("\n🎯 Usando " + itemSelecionado.getNome() + " durante a batalha...");
                
                // VERIFICAR SE É ITEM DE CURA - MANTIDO O CÓDIGO ORIGINAL
                if (itemSelecionado.getEfeito().equalsIgnoreCase("cura")) {
                    // Armazena valores para cálculo de cura
                    int vidaAntes = jogador.getPontosVida();
                    int vidaMax = jogador.getPontosVidaMax();
                    
                    // Calcular cura real sem ultrapassar o máximo - CORREÇÃO APLICADA AQUI
                    int curaPotencial = 0;
                    // Determina valor de cura baseado no nome do item
                    if (itemSelecionado.getNome().contains("Poção Forte")) {
                        curaPotencial = GameConstants.POCAO_FORTE_CURA;
                    } else if (itemSelecionado.getNome().contains("Poção")) {
                        curaPotencial = GameConstants.POCAO_CURA;
                    } else {
                        curaPotencial = GameConstants.ERVA_CURA;
                    }
                    
                    // CORREÇÃO: Usar o método curar que já controla o máximo automaticamente
                    jogador.curar(curaPotencial);
                    
                    // Calcula quanto de vida foi realmente recuperada
                    int vidaRecuperada = jogador.getPontosVida() - vidaAntes;
                    
                    // Verifica se houve cura efetiva
                    if (vidaRecuperada > 0) {
                        System.out.println("✨ " + jogador.getNome() + " recuperou " + vidaRecuperada + " HP!");
                        System.out.println("❤️  HP atual: " + jogador.getPontosVida() + "/" + vidaMax);
                        
                        // Remover uma unidade do item do inventário
                        jogador.getInventario().removerItem(itemSelecionado.getNome());
                    } else {
                        // Mensagem quando o personagem já está com vida cheia
                        System.out.println("💤 " + jogador.getNome() + " já está com a vida cheia!");
                        System.out.println("❤️  HP: " + jogador.getPontosVida() + "/" + vidaMax);
                    }
                    
                    return 3; // Item usado, continua batalha
                } else {
                    // Para outros tipos de item (ataque, defesa, etc.)
                    itemSelecionado.usar(jogador);
                    System.out.println("🔮 Efeito aplicado: " + itemSelecionado.getEfeito());
                    
                    // Remover item se acabou (quantidade chegou a zero)
                    if (itemSelecionado.getQuantidade() <= 0) {
                        jogador.getInventario().removerItem(itemSelecionado.getNome());
                    }
                    
                    return 3; // Item usado, continua batalha
                }
            } else {
                // Número de item inválido - recursão para tentar novamente
                System.out.println("❌ Número de item inválido!");
                return usarItemBatalha(jogador, inimigo); // Tenta novamente
            }
        } catch (Exception e) {
            // Tratamento de exceção para entradas não numéricas
            System.out.println("❌ Entrada inválida!");
            scanner.nextLine(); // Limpa o buffer com erro
            return usarItemBatalha(jogador, inimigo); // Tenta novamente
        }
    }
    
    // Método que executa o ataque do jogador contra o inimigo
    private static void atacarJogador(Personagem jogador, Inimigo inimigo) {
        // Rola o dado D20 para o ataque
        int dadoJogador = Dice.rollD20();
        // Calcula o valor total do ataque (ataque base + bônus + dado)
        int ataqueJogador = jogador.calcularAtaque() + dadoJogador;
        // Obtém a defesa do inimigo
        int defesaInimigo = inimigo.calcularDefesa();
        
        System.out.println("🎲 Você rolou " + dadoJogador + " no D20!");
        
        // Verifica se o ataque supera a defesa
        if (ataqueJogador > defesaInimigo) {
            // Calcula o dano baseado na diferença entre ataque e defesa
            int dano = ataqueJogador - defesaInimigo;
            System.out.println("✅ Acertou! Causou " + dano + " de dano!");
            inimigo.receberDano(dano); // Aplica o dano ao inimigo
        } else {
            System.out.println("❌ O inimigo se defendeu do seu ataque!");
        }
    }
    
    // Método que executa o turno do inimigo
    private static void turnoInimigo(Personagem jogador, Inimigo inimigo) {
        System.out.println("\n💀 VEZ DO " + inimigo.getNome().toUpperCase());
        
        // Rola o dado D20 para o ataque do inimigo
        int dadoInimigo = Dice.rollD20();
        // Calcula o ataque total do inimigo
        int ataqueInimigo = inimigo.calcularAtaque() + dadoInimigo;
        // Obtém a defesa do jogador
        int defesaJogador = jogador.calcularDefesa();
        
        System.out.println("🎲 " + inimigo.getNome() + " rolou " + dadoInimigo + " no D20!");
        
        // Verifica se o ataque do inimigo supera a defesa do jogador
        if (ataqueInimigo > defesaJogador) {
            // Calcula o dano sofrido pelo jogador
            int dano = ataqueInimigo - defesaJogador;
            System.out.println("✅ " + inimigo.getNome() + " acertou! Causou " + dano + " de dano!");
            jogador.receberDano(dano); // Aplica o dano ao jogador
        } else {
            System.out.println("❌ Você se defendeu do ataque inimigo!");
        }
    }
    
    // Método para tentar fugir da batalha
    private static boolean tentarFugir() {
        System.out.println("💨 Tentando fugir...");
        // Rola D10 para determinar sucesso da fuga
        int rolagem = Dice.rollD10();
        
        // Chance de 50% de fuga bem-sucedida (6-10 no D10)
        if (rolagem > 5) {
            System.out.println("✅ Fuga bem-sucedida!");
            return true;
        } else {
            System.out.println("❌ Falha na fuga!");
            return false;
        }
    }
    
    // Método para verificar e exibir o resultado final da batalha
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

/*
COMENTÁRIO FINAL - COMBATSYSTEM.JAVA:

A classe CombatSystem é o núcleo do sistema de combate do RPG, implementando um sistema
baseado em turnos com rolagem de dados, similar aos sistemas de RPG de mesa tradicionais.

ARQUITETURA DO SISTEMA DE COMBATE:

1. SISTEMA BASEADO EM TURNOS:
   - Loop principal controlado por while que alterna entre turnos do jogador e inimigo
   - Contador de turnos para acompanhamento do progresso da batalha
   - Verificações de estado de vida em cada iteração

2. MECÂNICA DE ATAQUE E DEFESA:
   - Uso de D20 para determinar sucesso dos ataques
   - Cálculo: AtaqueTotal = AtaqueBase + BônusClasse + RolagemD20
   - Dano = AtaqueTotal - DefesaTotal (se positivo)
   - Sistema favorece estratégia sobre sorte pura

3. SISTEMA DE INVENTÁRIO EM BATALHA:
   - Integração completa com sistema de itens durante combate
   - Interface numérica para seleção de itens
   - Tratamento especial para itens de cura com controle de vida máxima
   - Recursão para tratamento de entradas inválidas

4. MECÂNICA DE FUGA:
   - Chance baseada em rolagem de D10 (50% de sucesso)
   - Implementa risco/recompensa estratégico
   - Retorna código específico para controle de fluxo do jogo

5. TRATAMENTO DE ERROS E VALIDAÇÃO:
   - Try-catch para entradas não numéricas
   - Validação de índices de itens
   - Recuperação graciosa de erros de entrada

CÓDIGOS DE RETORNO DO SISTEMA:
- 0: Derrota (jogador morreu)
- 1: Vitória (inimigo derrotado)
- 2: Fuga bem-sucedida
- 3: Item usado (continua batalha)

PADRÕES DE PROJETO APLICADOS:
- Métodos estáticos para funcionalidade pura (stateless)
- Separação de responsabilidades em métodos especializados
- Recursão para repetição de entrada inválida
- Encapsulamento através de métodos privados

BALANCEAMENTO E ESTRATÉGIA:
- D20 proporciona variabilidade controlada (5-25 de variação)
- Bônus de classe garantem diferenciação entre arquétipos
- Sistema de cura preventivo evita waste de recursos
- Chance de fuga balanceada para não ser abusada

Esta implementação demonstra um sistema de combate robusto e expansível, preparado
para adição de novas mecânicas como habilidades especiais, efeitos de status e
sistemas mais complexos de dano e defesa.
*/
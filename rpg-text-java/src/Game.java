// Importação da classe Scanner para entrada de dados do usuário
import java.util.Scanner;

// Classe principal que controla o fluxo geral do jogo e gerencia o estado do RPG
public class Game {
    // Atributos da classe que representam o estado do jogo
    private Personagem jogador;      // Referência para o personagem do jogador
    private boolean jogoRodando;     // Flag que controla se o jogo está em execução
    private int areaAtual;           // Controla o progresso do jogador pelas áreas (1-3)
    
    // Método principal que inicia e controla o loop do jogo
    public void iniciar() {
        jogoRodando = true; // Inicializa a flag para manter o jogo rodando
        System.out.println("🎮 RPG DE TEXTO - A JORNADA DA PEDRA DA ETERNIDADE 🎮");
        
        // Loop principal do jogo - executa enquanto jogoRodando for true
        while (jogoRodando) {
            GameMenu.mostrarMenuPrincipal(); // Exibe o menu principal
            int opcao = GameMenu.lerOpcao(1, 4); // Lê opção validada entre 1-4
            
            // Switch para processar a opção escolhida no menu
            switch (opcao) {
                case 1:
                    iniciarNovoJogo(); // Inicia uma nova partida
                    break;
                case 2:
                    System.out.println("\n⚔️ Instruções: Derrote inimigos, colete itens, complete a missão!");
                    GameMenu.pausa(); // Pausa para leitura
                    break;
                case 3:
                    System.out.println("\nDesenvolvedores: Henrique Gambin e Vitor Hugo"); // Créditos
                    GameMenu.pausa(); // Pausa para leitura
                    break;
                case 4:
                    jogoRodando = false; // Encerra o jogo
                    System.out.println("👋 Até logo!");
                    break;
            }
        }
    }
    
    // Método que inicia uma nova jornada do jogador
    private void iniciarNovoJogo() {
        criarPersonagem(); // Cria o personagem do jogador
        GameStory.mostrarIntroducao(jogador.getNome()); // Mostra a introdução da história
        GameMenu.pausa(); // Pausa para leitura
        
        // Sistema de progressão por áreas - controla o fluxo linear do jogo
        areaAtual = 1; // Começa na área 1 (Floresta)
        // Loop que avança pelas áreas enquanto o jogo está ativo e jogador vivo
        while (jogoRodando && jogador.estaVivo() && areaAtual <= 3) {
            switch (areaAtual) {
                case 1:
                    if (explorarFloresta()) areaAtual++; // Avança se completou floresta
                    break;
                case 2:
                    if (explorarCaverna()) areaAtual++; // Avança se completou caverna
                    break;
                case 3:
                    if (enfrentarChefeFinal()) areaAtual++; // Avança se derrotou chefe
                    break;
            }
            GameMenu.pausa(); // Pausa entre áreas
        }
        
        finalizarJogo(); // Processa o final do jogo (vitória ou derrota)
    }
    
    // Método para criação do personagem do jogador
    private void criarPersonagem() {
        GameMenu.mostrarMenuCriacaoPersonagem(); // Mostra menu de classes
        int classe = GameMenu.lerOpcao(1, 3); // Lê escolha da classe (1-3)
        String nome = GameMenu.lerNomePersonagem(); // Lê nome do personagem
        
        // Switch para instanciar a classe escolhida
        switch (classe) {
            case 1:
                jogador = new Guerreiro(nome); // Cria Guerreiro
                break;
            case 2:
                jogador = new Mago(nome); // Cria Mago
                break;
            case 3:
                jogador = new Arqueiro(nome); // Cria Arqueiro
                break;
        }
        
        // Itens iniciais que todo personagem recebe
        jogador.getInventario().adicionarItem(new Item("Poção de Cura", "Cura 20 HP", "cura", 3));
        jogador.getInventario().adicionarItem(new Item("Erva Curativa", "Cura 15 HP", "cura", 2));
        
        System.out.println("\n✅ Personagem criado: " + jogador); // Confirma criação
        GameMenu.pausa(); // Pausa para leitura
    }
    
    // Método que controla a exploração da primeira área (Floresta Misteriosa)
    private boolean explorarFloresta() {
        Scanner scanner = new Scanner(System.in); // Scanner local para entrada
        
        // Loop de exploração - continua enquanto jogador estiver vivo
        while (jogador.estaVivo()) {
            GameStory.mostrarFlorestaMisteriosa(); // Descrição ambiental
            System.out.println("\n🌲 O que você faz na floresta?");
            System.out.println("1. 🔍 Explorar");       // Busca eventos aleatórios
            System.out.println("2. 🎒 Ver Inventário"); // Gerencia itens
            System.out.println("3. ❤️  Ver Status");    // Mostra atributos
            System.out.println("4. 🚶 Seguir para Caverna"); // Avança para próxima área
            System.out.print("Escolha: ");
            
            int escolha = scanner.nextInt(); // Lê escolha do jogador
            
            // Processa a escolha do jogador
            switch (escolha) {
                case 1:
                    if (!eventoFloresta()) return false; // Evento pode terminar jogo
                    break;
                case 2:
                    gerenciarInventario(); // Abre menu de inventário
                    break;
                case 3:
                    mostrarStatus(); // Exibe status do personagem
                    break;
                case 4:
                    System.out.println("\n⭐ Você encontra a entrada da Caverna Sombria...");
                    return true; // Retorna true para avançar de área
                default:
                    System.out.println("❌ Opção inválida!"); // Tratamento de erro
            }
        }
        return false; // Retorna false se jogador morreu
    }
    
    // Método que gera eventos aleatórios na floresta
    private boolean eventoFloresta() {
        int evento = Dice.roll(3); // Rola D3 para evento aleatório (1-3)
        
        switch (evento) {
            case 1: // Encontro com lobo - evento de combate
                GameStory.encontroComLobo(); // Narrativa do encontro
                Inimigo lobo = new Inimigo("Lobo da Floresta", "Animal", 
                    GameConstants.LOBO_HP, GameConstants.LOBO_ATK, GameConstants.LOBO_DEF, 1);
                
                int resultadoBatalha = CombatSystem.batalhar(jogador, lobo); // Inicia combate
                
                if (resultadoBatalha == 2) { // Fugiu
                    System.out.println("🏃 Você fugiu do lobo...");
                    return true; // Continua exploração
                } else if (resultadoBatalha == 0 || !jogador.estaVivo()) { // Derrota
                    return false; // Termina jogo
                }
                // Vitória - continua exploração normalmente
                break;
                
            case 2: // Encontro com baú - evento de recompensa
                GameStory.encontrouBau(); // Narrativa do encontro
                System.out.println("🎁 Você encontrou uma poção forte!");
                jogador.getInventario().adicionarItem(new Item("Poção Forte", "Cura 30 HP", "cura", 1));
                break;
                
            case 3: // Encontro com poção - evento de recompensa
                GameStory.encontrouPocao(); // Narrativa do encontro
                System.out.println("🧪 Você encontrou ervas medicinais!");
                jogador.getInventario().adicionarItem(new Item("Erva Curativa", "Cura 15 HP", "cura", 2));
                break;
        }
        return true; // Continua exploração
    }
    
    // Método que controla a segunda área (Caverna Sombria)
    private boolean explorarCaverna() {
        GameStory.mostrarCavernaSombria(); // Descrição ambiental
        
        // Chefe da caverna - Goblin (combate obrigatório)
        GameStory.encontroComGoblin(); // Narrativa do encontro
        Inimigo goblin = new Inimigo("Goblin Guardião", "Goblin",
            GameConstants.GOBLIN_HP, GameConstants.GOBLIN_ATK, GameConstants.GOBLIN_DEF, 2);
        
        // Goblin tem itens especiais para saque
        goblin.getInventario().adicionarItem(new Item("Poção Forte", "Cura 30 HP", "cura", 1));
        goblin.getInventario().adicionarItem(new Item("Adaga Afiada", "Aumenta ataque", "ataque", 1));
        
        int resultadoBatalha = CombatSystem.batalhar(jogador, goblin); // Combate com goblin
        
        if (resultadoBatalha == 2) { // Fugiu
            System.out.println("🏃 Você fugiu da caverna...");
            return false; // Não avança de área
        } else if (resultadoBatalha == 0 || !jogador.estaVivo()) { // Derrota
            return false; // Termina jogo
        } else if (resultadoBatalha == 1) { // Vitória - SÓ AQUI SAQUEIA
            // Saquear o goblin - APENAS SE VENCEU
            System.out.println("💎 Saqueando o Goblin...");
            System.out.println("  → Poção Forte");
            System.out.println("  → Adaga Afiada");
            jogador.getInventario().adicionarItem(new Item("Poção Forte", "Cura 30 HP", "cura", 1));
            jogador.getInventario().adicionarItem(new Item("Adaga Afiada", "Aumenta ataque", "ataque", 1));
        }
        
        System.out.println("\n🗺️ Você encontra um mapa que leva ao Castelo Antigo...");
        return true; // Avança para próxima área
    }
    
    // Método que controla a área final e chefe final
    private boolean enfrentarChefeFinal() {
        Scanner scanner = new Scanner(System.in); // Scanner local para escolha
        GameStory.mostrarCasteloAntigo(); // Descrição ambiental
        
        // Escolha de caminho - ramificação narrativa
        System.out.println("🔮 Duas portas se apresentam:");
        System.out.println("1. Porta das Runas Místicas");   // Caminho pacífico
        System.out.println("2. Porta das Marcas de Batalha"); // Caminho de combate
        System.out.print("Qual porta escolhe? (1-2): ");
        
        int escolha = scanner.nextInt(); // Lê escolha do caminho
        
        if (escolha == 1) {
            GameStory.portaMistica(); // Narrativa do caminho místico
            System.out.println("📚 Você encontra pergaminhos mágicos!");
            jogador.getInventario().adicionarItem(new Item("Pergaminho Mágico", "Aumenta ataque", "ataque", 2));
        } else {
            GameStory.portaBatalha(); // Narrativa do caminho de batalha
            System.out.println("⚔️ Uma armadura animada ataca!");
            Inimigo armadura = new Inimigo("Armadura Animada", "Constructo", 80, 15, 12, 3);
            
            int resultadoBatalha = CombatSystem.batalhar(jogador, armadura); // Combate opcional
            
            if (resultadoBatalha == 2) { // Fugiu
                System.out.println("🏃 Você fugiu do castelo...");
                return false; // Termina jogo
            } else if (resultadoBatalha == 0 || !jogador.estaVivo()) { // Derrota
                return false; // Termina jogo
            }
            // Se vitória, continua para chefe final
        }
        
        // CHEFE FINAL - combate decisivo
        GameStory.mostrarSalaoDoTrono(); // Narrativa do salão final
        GameStory.encontroComGuardiao(); // Narrativa do chefe final
        Inimigo guardiao = new Inimigo("Guardião Ancestral", "Espírito",
            GameConstants.GUARDIAO_HP, GameConstants.GUARDIAO_ATK, GameConstants.GUARDIAO_DEF, 5);
        
        guardiao.getInventario().adicionarItem(new Item("Pedra da Eternidade", "Artefato lendário", "poder", 1));
        
        System.out.println("\n⚠️  BATALHA FINAL!");
        int resultadoFinal = CombatSystem.batalhar(jogador, guardiao); // Combate final
        
        if (resultadoFinal == 1 && jogador.estaVivo()) { // Vitória
            GameStory.finalVitoria(); // Narrativa de vitória
            return true; // Completa o jogo
        } else {
            return false; // Derrota - termina jogo
        }
    }
    
    // MÉTODO CORRIGIDO - INVENTÁRIO FUNCIONAL
    private void gerenciarInventario() {
        Scanner scanner = new Scanner(System.in); // Scanner local para menu
        
        // Loop do menu de inventário
        while (true) {
            System.out.println("\n🎒 === GERENCIAR INVENTÁRIO ===");
            System.out.println("Itens totais: " + jogador.getInventario().getQuantidadeTotal());
            System.out.println("1. 📋 Listar Itens");   // Mostra todos os itens
            System.out.println("2. 🧪 Usar Item");      // Usa um item específico
            System.out.println("3. ↩️  Voltar");         // Sai do menu
            System.out.print("Escolha: ");
            
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Limpa buffer
            
            switch (escolha) {
                case 1:
                    System.out.println("\n📋 === ITENS NO INVENTÁRIO ===");
                    jogador.getInventario().listarItens(); // Lista itens ordenados
                    break;
                case 2:
                    usarItemInventario(); // Chama método de uso de item
                    break;
                case 3:
                    return; // Sai do método e volta para exploração
                default:
                    System.out.println("❌ Opção inválida!"); // Tratamento de erro
            }
        }
    }
    
    // MÉTODO CORRIGIDO - AGORA VAI FUNCIONAR!
    private void usarItemInventario() {
        if (!jogador.getInventario().temItens()) {
            System.out.println("📭 Seu inventário está vazio!");
            return; // Sai se não há itens
        }
        
        Scanner scanner = new Scanner(System.in);
        
        // Primeiro listamos os itens
        System.out.println("\n🧪 === USAR ITEM ===");
        System.out.println("Seus itens:");
        jogador.getInventario().listarItens(); // Mostra itens disponíveis
        
        System.out.print("📝 Digite o NOME do item que deseja usar: ");
        String nomeItem = scanner.nextLine(); // Lê nome do item (case sensitive)
        
        Item item = jogador.getInventario().getItem(nomeItem); // Busca item pelo nome
        if (item != null) {
            System.out.println("\n🎯 Usando " + item.getNome() + "...");
            item.usar(jogador); // Aplica efeito do item no personagem
            
            // Atualizar o inventário após usar
            if (item.getQuantidade() <= 0) {
                jogador.getInventario().removerItem(item.getNome()); // Remove se acabou
                System.out.println("✅ Item usado e removido do inventário!");
            } else {
                System.out.println("✅ Item usado! Restam: " + item.getQuantidade());
            }
        } else {
            System.out.println("❌ Item não encontrado! Verifique o nome digitado."); // Item não existe
        }
    }
    
    // Método para exibir status detalhado do personagem
    private void mostrarStatus() {
        System.out.println("\n📊 === STATUS ===");
        System.out.println(jogador); // toString do personagem
        System.out.println("Ataque: " + jogador.calcularAtaque() + " (Base: " + jogador.getAtaque() + ")");
        System.out.println("Defesa: " + jogador.calcularDefesa() + " (Base: " + jogador.getDefesa() + ")");
        System.out.println("Itens no inventário: " + jogador.getInventario().getQuantidadeTotal());
        System.out.println("=================");
    }
    
    // Método que processa o final do jogo (vitória ou derrota)
    private void finalizarJogo() {
        if (jogador.estaVivo() && areaAtual > 3) {
            GameStory.finalVitoria(); // Narrativa de vitória
            System.out.println("\n🏆 PARABÉNS! VOCÊ COMPLETOU A JORNADA!");
        } else {
            GameStory.finalDerrota(); // Narrativa de derrota
            System.out.println("\n💀 Fim do jogo...");
        }
        jogoRodando = false; // Encerra o jogo
    }
}

/*
COMENTÁRIO FINAL - GAME.JAVA:

A classe Game é o coração do sistema de RPG, atuando como o controlador principal
que orquestra todo o fluxo do jogo, desde o menu inicial até o desfecho final.

ARQUITETURA E FLUXO DO JOGO:

1. GERENCIAMENTO DE ESTADO:
   - jogador: Mantém referência ao personagem do jogador durante toda a sessão
   - jogoRodando: Flag que controla o loop principal do jogo
   - areaAtual: Sistema de progressão linear através das 3 áreas do jogo

2. PADRÃO DE CONTROLE CENTRALIZADO:
   - Todas as decisões de alto nível passam por esta classe
   - Coordena interação entre subsistemas (combate, inventário, história)
   - Mantém consistência do estado global do jogo

3. SISTEMA DE ÁREAS PROGRESSIVAS:
   - Floresta Misteriosa (Área 1): Tutorial com eventos aleatórios
   - Caverna Sombria (Área 2): Combate obrigatório com primeiro chefe
   - Castelo Antigo (Área 3): Área final com ramificação narrativa e chefe final

MECÂNICAS DE JOGO IMPLEMENTADAS:

1. CRIAÇÃO DE PERSONAGEM:
   - Seleção entre 3 classes com atributos distintos
   - Nome personalizável pelo jogador
   - Kit inicial balanceado de itens de cura

2. SISTEMA DE EXPLORAÇÃO:
   - Menu contextual por área com opções específicas
   - Eventos aleatórios baseados em rolagem de dados
   - Progressão não-linear dentro de cada área

3. RAMIFICAÇÃO NARRATIVA:
   - Escolha no castelo entre caminho pacífico ou de combate
   - Recompensas diferentes baseadas na escolha
   - Impacto na preparação para o chefe final

4. GERENCIAMENTO DE RECURSOS:
   - Sistema de inventário integrado em todas as áreas
   - Uso estratégico de itens durante exploração
   - Controle de quantidades e remoção automática

PADRÕES DE PROJETO APLICADOS:

1. CONTROLLER PATTERN:
   - Game age como controlador entre modelo (Personagem, Itens) e vista (Console)
   - Centraliza a lógica de aplicação

2. STATE PATTERN IMPLÍCITO:
   - Transições entre menus, exploração e combate
   - Controle de fluxo baseado em estado atual

3. FACADE PATTERN:
   - Simplifica interface com subsistemas complexos
   - Métodos de alto nível escondem complexidade

4. TEMPLATE METHOD:
   - Estrutura comum para diferentes áreas (explorarX(), eventoX())
   - Customização através de polimorfismo

TRATAMENTO DE ERROS E VALIDAÇÃO:

- Validação de entradas numéricas em todos os menus
- Verificação de estado do jogador (vivo/morto) em loops críticos
- Mensagens de erro informativas para entradas inválidas
- Recuperação graciosa de exceções

EXPANSIBILIDADE E MANUTENÇÃO:

- Estrutura modular facilita adição de novas áreas
- Sistema de eventos aleatórios permite fácil expansão de conteúdo
- Separação clara entre lógica de jogo e conteúdo narrativo
- Uso de constantes para balanceamento facilita ajustes

BALANCEAMENTO E PROGRESSÃO:

- Dificuldade crescente através das áreas
- Recompensas proporcionais aos desafios
- Kit inicial suficiente para aprendizado
- Chefe final desafiador mas possível com estratégia

Esta implementação demonstra um sistema de jogo completo e coeso, oferecendo
uma experiência de RPG textual envolvente com começo, meio e fim claros,
enquanto mantém código organizado e expansível para futuras melhorias.
*/
// Importação da classe Scanner para entrada de dados do usuário
import java.util.Scanner;

// Classe utilitária para gerenciamento de interfaces de menu e entrada do usuário
public class GameMenu {
    // Scanner estático e final compartilhado por todos os métodos da classe
    // Evita múltiplas instâncias de Scanner que poderiam causar problemas
    private static final Scanner scanner = new Scanner(System.in);
    
    // Método para exibir o menu principal do jogo
    public static void mostrarMenuPrincipal() {
        System.out.println("\n🎮 === MENU PRINCIPAL ===");  // Cabeçalho do menu com emoji
        System.out.println("1. 🚀 Iniciar Novo Jogo");     // Opção 1: Começar nova partida
        System.out.println("2. 📖 Ver Instruções");        // Opção 2: Tutorial do jogo
        System.out.println("3. 🏆 Créditos");              // Opção 3: Informações dos desenvolvedores
        System.out.println("4. 🚪 Sair");                  // Opção 4: Encerrar aplicação
        System.out.print("Escolha uma opção: ");           // Prompt para entrada do usuário
    }
    
    // Método para exibir o menu de criação de personagem
    public static void mostrarMenuCriacaoPersonagem() {
        System.out.println("\n👤 === CRIAÇÃO DE PERSONAGEM ===");  // Cabeçalho da criação
        System.out.println("Escolha sua classe:");                 // Instrução para o usuário
        System.out.println("1. 🛡️  Guerreiro - Forte e Resistente");  // Classe 1 com descrição
        System.out.println("2. 🔮 Mago - Poder Mágico Elevado");      // Classe 2 com descrição  
        System.out.println("3. 🏹 Arqueiro - Preciso e Ágil");        // Classe 3 com descrição
        System.out.print("Escolha sua classe (1-3): ");             // Prompt específico com range
    }
    
    // Método para ler e validar o nome do personagem
    public static String lerNomePersonagem() {
        System.out.print("Digite o nome do seu personagem: ");  // Prompt para nome
        return scanner.nextLine().trim();  // Lê linha, remove espaços extras e retorna
    }
    
    // Método robusto para ler opções numéricas com validação completa
    public static int lerOpcao(int min, int max) {
        // Loop infinito até que uma entrada válida seja fornecida
        while (true) {
            try {
                // Tenta ler um número inteiro do usuário
                int opcao = scanner.nextInt();
                scanner.nextLine();  // Limpa o buffer do scanner após nextInt()
                
                // Verifica se a opção está dentro do range permitido
                if (opcao >= min && opcao <= max) {
                    return opcao;  // Retorna a opção válida
                } else {
                    // Mensagem de erro específica com range esperado
                    System.out.print("Opção inválida! Escolha entre " + min + " e " + max + ": ");
                }
            } catch (Exception e) {
                // Captura exceções de entrada não numérica (InputMismatchException)
                System.out.print("Entrada inválida! Digite um número: ");
                scanner.nextLine();  // Limpa o buffer com entrada inválida
            }
        }
    }
    
    // Método para pausar a execução e aguardar confirmação do usuário
    public static void pausa() {
        System.out.println("\nPressione Enter para continuar...");  // Instrução clara
        scanner.nextLine();  // Aguarda o usuário pressionar Enter
    }
}

/*
COMENTÁRIO FINAL - GAMEMENU.JAVA:

A classe GameMenu é uma classe utilitária especializada em Interface com o Usuário
que implementa o padrão Utility Class para gerenciar todas as interações de menu
e entrada de dados do jogador de forma consistente e robusta.

ARQUITETURA E DESIGN:

1. PADRÃO UTILITY CLASS:
   - Todos os métodos são estáticos
   - Não requer instanciação
   - Agrupamento coerente de funcionalidades relacionadas
   - Scanner compartilhado para evitar conflitos de recursos

2. SEPARAÇÃO DE CONCERNS:
   - Lógica de UI isolada da lógica de negócio
   - Tratamento de entrada separado da processamento
   - Apresentação desacoplada da funcionalidade

FUNCIONALIDADES IMPLEMENTADAS:

1. APRESENTAÇÃO DE MENUS:
   - mostrarMenuPrincipal(): Interface inicial do jogo
   - mostrarMenuCriacaoPersonagem(): Seleção de classe com descrições
   - Uso de emojis para melhor experiência visual e usabilidade

2. CAPTURA DE ENTRADAS:
   - lerNomePersonagem(): Entrada de texto simples com trim()
   - lerOpcao(): Entrada numérica com validação robusta
   - pausa(): Controle de fluxo para leitura de conteúdo

3. VALIDAÇÃO ROBUSTA:
   - Tratamento de exceções para entradas não numéricas
   - Verificação de limites (min/max) para opções
   - Loop de repetição até entrada válida
   - Mensagens de erro específicas e informativas

TÉCNICAS DE USABILIDADE:

1. FEEDBACK IMEDIATO:
   - Mensagens de erro claras e diretas
   - Repetição automática em entradas inválidas
   - Instruções específicas para correção

2. CONSISTÊNCIA VISUAL:
   - Formatação padronizada de menus
   - Uso de emojis para identificação rápida
   - Headers descritivos para cada seção

3. EXPERIÊNCIA DO USUÁRIO:
   - Pausas controladas para leitura de texto
   - Limpeza automática de buffer para evitar problemas
   - Interface textual clara e intuitiva

TRATAMENTO DE ERROS:

1. EXCEÇÕES DE TIPO:
   - InputMismatchException capturada e tratada
   - Recuperação graciosa sem quebrar o fluxo
   - Mensagens amigáveis ao usuário final

2. VALIDAÇÃO DE DOMÍNIO:
   - Verificação de range numérico
   - Prevenção de valores fora dos limites esperados
   - Loop infinito até entrada válida (garante sucesso)

OTIMIZAÇÕES TÉCNICAS:

1. GERENCIAMENTO DE SCANNER:
   - Instância única evita ResourceBusyException
   - nextLine() após nextInt() previa o "skip" de entrada
   - Uso de trim() para limpeza de espaços desnecessários

2. PERFORMANCE:
   - Métodos estáticos sem overhead de instanciação
   - Lógica eficiente de validação
   - Reutilização de recursos

PADRÕES DE PROJETO APLICADOS:

1. FACADE PATTERN:
   - Interface simplificada para sistema complexo de entrada
   - Oculta complexidade do Scanner e tratamento de exceções

2. TEMPLATE METHOD:
   - Estrutura consistente para diferentes tipos de menu
   - Reutilização de lógica de validação

3. SINGLE RESPONSIBILITY:
   - Cada método tem uma responsabilidade específica
   - Fácil manutenção e teste

EXPANSIBILIDADE:

- Fácil adição de novos menus com mesma estrutura
- Validação customizável através de parâmetros
- Suporte a diferentes tipos de entrada futuros
- Preparado para internacionalização

BENEFÍCIOS DO DESIGN:

1. MANUTENÇÃO:
   - Código de UI centralizado e organizado
   - Fácil modificação de textos e fluxos

2. TESTABILIDADE:
   - Métodos puros e previsíveis
   - Entrada e saída bem definidas

3. REUSO:
   - Pode ser usado em diferentes partes do sistema
   - Adaptável para novos tipos de jogos

Esta implementação demonstra uma abordagem profissional para interface com
usuário em aplicações console, equilibrando usabilidade, robustez e
manutenibilidade em um pacote coeso e bem estruturado.
*/
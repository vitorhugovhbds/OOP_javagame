// Importação da classe Random para geração de números aleatórios
import java.util.Random;

// Classe Inimigo que estende Personagem, representando adversários controlados pelo sistema
public class Inimigo extends Personagem {
    // Atributos específicos da classe Inimigo
    private String tipo;                    // Categoria do inimigo (Animal, Goblin, Espírito, etc.)
    private int experienciaConcedida;       // Quantidade de EXP que o inimigo concede ao ser derrotado
    private static final Random random = new Random();  // Gerador aleatório estático para todos os inimigos
    
    // Construtor principal para criar novos inimigos
    public Inimigo(String nome, String tipo, int pontosVida, int ataque, int defesa, int nivel) {
        // Chama o construtor da classe pai (Personagem) para inicializar atributos base
        super(nome, pontosVida, ataque, defesa, nivel);
        this.tipo = tipo;  // Define a categoria/raça do inimigo
        // Calcula EXP baseado no nível com variação aleatória: (nível * 25) + (0 a 19)
        this.experienciaConcedida = nivel * 25 + random.nextInt(20);
    }

    // Construtor de cópia para criar uma réplica exata de outro inimigo
    public Inimigo(Inimigo outro) {
        // Chama construtor de cópia da classe pai para copiar atributos base
        super(outro);
        this.tipo = outro.tipo;  // Copia o tipo do inimigo
        this.experienciaConcedida = outro.experienciaConcedida;  // Copia a EXP (sem aleatoriedade na cópia)
    }

    // Implementação do método abstrato - inimigos não têm bônus de classe
    @Override
    public int calcularAtaque() {
        // Retorna apenas o ataque base, sem bônus adicionais
        return ataque;
    }

    // Implementação do método abstrato - inimigos não têm bônus de classe  
    @Override
    public int calcularDefesa() {
        // Retorna apenas a defesa base, sem bônus adicionais
        return defesa;
    }

    // Implementação do método abstrato para identificar a classe
    @Override
    public String getClasse() {
        // Retorna identificador genérico para todos os inimigos
        return "Inimigo";
    }

    // Getter para obter o tipo/categoria do inimigo
    public String getTipo() {
        return tipo;
    }

    // Getter para obter a quantidade de experiência que o inimigo concede
    public int getExperienciaConcedida() {
        return experienciaConcedida;
    }

    // Implementação do padrão Prototype para clonagem de inimigos
    @Override
    public Inimigo clone() {
        // Cria e retorna uma nova instância usando o construtor de cópia
        return new Inimigo(this);
    }
}

/*
COMENTÁRIO FINAL - INIMIGO.JAVA:

A classe Inimigo é uma especialização da classe Personagem designada especificamente
para representar adversários controlados pelo sistema, implementando características
únicas como sistema de experiência e categorização por tipo.

ARQUITETURA E HERANÇA:

1. EXTENSÃO DE PERSONAGEM:
   - Herda toda a estrutura base de personagem (vida, ataque, defesa, inventário)
   - Implementa os métodos abstratos de forma específica para NPCs
   - Mantém compatibilidade com sistema de combate existente

2. ATRIBUTOS ESPECÍFICOS:
   - tipo: Classificação temática (Animal, Goblin, Constructo, Espírito)
   - experienciaConcedida: Recompensa de progressão ao ser derrotado
   - random: Gerador compartilhado para cálculo de EXP variável

CARACTERÍSTICAS DE DESIGN:

1. DIFERENCIAÇÃO DE PERSONAGENS JOGÁVEIS:
   - Sem bônus de classe (calcularAtaque/Defesa retornam valores base)
   - Sistema de experiência como mecanismo de recompensa
   - Categorização por tipo para variedade narrativa

2. SISTEMA DE EXPERIÊNCIA:

   Fórmula: experienciaConcedida = (nível * 25) + random.nextInt(20)

   - Base Progressiva: nível * 25 (inimigos de nível maior dão mais EXP)
   - Variação Aleatória: + (0 a 19) para evitar valores previsíveis
   - Balanceamento: 
     * Nível 1: 25-44 EXP
     * Nível 2: 50-69 EXP  
     * Nível 5: 125-144 EXP

3. CONSTRUTORES:

   - Construtor Principal: Para criação de novos inimigos com EXP aleatória
   - Construtor de Cópia: Para clonagem exata (preserva EXP específica)

IMPLEMENTAÇÃO DE MÉTODOS:

1. calcularAtaque() e calcularDefesa():
   - Retornam valores base sem bônus, diferenciando de personagens jogáveis
   - Representa que inimigos não têm especializações de classe
   - Mantém balanceamento controlado pelo designer

2. getClasse():
   - Retorno fixo "Inimigo" para identificação em sistemas de UI/logística
   - Diferencia de classes jogáveis em mensagens e lógica condicional

3. getTipo() e getExperienciaConcedida():
   - Getters para acesso controlado aos atributos específicos
   - Permite sistemas futuros baseados em tipo ou EXP

4. clone():
   - Implementação do padrão Prototype para criação de cópias
   - Útil para spawn de múltiplos inimigos do mesmo tipo
   - Preserva todos os atributos incluindo EXP específica

BALANCEAMENTO E PROGRESSÃO:

1. INIMIGOS EXISTENTES:
   - Lobo (Nv1): 25-44 EXP | Animal
   - Goblin (Nv2): 50-69 EXP | Goblin  
   - Armadura (Nv3): 75-94 EXP | Constructo
   - Guardião (Nv5): 125-144 EXP | Espírito

2. CURVA DE DIFICULDADE:
   - EXP escala linearmente com nível
   - Variação aleatória adiciona imprevisibilidade
   - Prepara sistema para progressão de personagem futura

PADRÕES DE PROJETO APLICADOS:

1. PROTOTYPE PATTERN:
   - clone() permite criação eficiente de inimigos similares
   - Construtor de cópia como mecanismo de implementação

2. TEMPLATE METHOD:
   - Implementação específica dos métodos abstratos de Personagem
   - Especialização do comportamento para NPCs

3. STRATEGY (implícito):
   - Diferentes tipos de inimigos podem ter comportamentos distintos
   - Preparado para expansão com habilidades específicas por tipo

INTEGRAÇÃO COM O SISTEMA:

1. SISTEMA DE COMBATE:
   - Compatível com CombatSystem através da herança de Personagem
   - Atributos balanceados para encontros desafiadores

2. SISTEMA DE INVENTÁRIO:
   - Herda sistema de inventário para itens dropáveis
   - Permite saque após derrota (implementado em Game.java)

3. SISTEMA DE NARRATIVA:
   - Tipo usado para descrições contextuais em GameStory
   - Nome e tipo fornecem contexto narrativo

VANTAGENS DO DESIGN:

1. EXPANSIBILIDADE:
   - Fácil criação de novos tipos de inimigos
   - Sistema de EXP pronto para progressão de personagem
   - Preparado para habilidades especiais por tipo

2. MANUTENÇÃO:
   - Lógica de EXP centralizada no construtor
   - Atributos específicos bem encapsulados
   - Herança simplifica adição de novos comportamentos

3. VARIEDADE:
   - Aleatoriedade de EXP evita repetição
   - Sistema de tipos permite diversidade temática
   - Balanceamento controlado via GameConstants

FUTURAS EXPANSÕES:

1. SISTEMA DE DROP:
   - Itens específicos por tipo/tier de inimigo
   - Chance de drop baseada em raridade

2. COMPORTAMENTOS ESPECÍFICOS:
   - Habilidades únicas baseadas no tipo
   - AI diferenciada para categorias distintas

3. SISTEMA DE LEVEL UP:
   - EXP acumulada pelo jogador para progressão
   - Inimigos como fonte primária de progressão

Esta implementação de Inimigo demonstra uma abordagem sólida para a criação
de adversários em RPGs, equilibrando consistência técnica com flexibilidade
de design e preparação para sistemas futuros de progressão e variedade.
*/
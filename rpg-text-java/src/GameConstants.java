// Classe de constantes do jogo - centraliza todos os valores numéricos para balanceamento
public class GameConstants {
    // Configurações de Personagem - VIDA MÁXIMA
    // Guerreiro: Tanque com alta vida e defesa, ataque moderado
    public static final int GUERREIRO_HP = 120;      // Pontos de vida inicial do Guerreiro
    public static final int GUERREIRO_HP_MAX = 120;  // Vida máxima do Guerreiro (para controle de cura)
    
    // Mago: Glass cannon com baixa vida, alto ataque, defesa frágil
    public static final int MAGO_HP = 80;            // Pontos de vida inicial do Mago
    public static final int MAGO_HP_MAX = 80;        // Vida máxima do Mago
    
    // Arqueiro: Balanceado com vida média, bom ataque, defesa razoável
    public static final int ARQUEIRO_HP = 100;       // Pontos de vida inicial do Arqueiro
    public static final int ARQUEIRO_HP_MAX = 100;   // Vida máxima do Arqueiro
    
    // Atributos de ataque base para cada classe (sem bônus de classe)
    public static final int GUERREIRO_ATK = 15;      // Ataque base do Guerreiro
    public static final int GUERREIRO_DEF = 12;      // Defesa base do Guerreiro
    
    public static final int MAGO_ATK = 20;           // Ataque base do Mago (mais alto)
    public static final int MAGO_DEF = 8;            // Defesa base do Mago (mais baixa)
    
    public static final int ARQUEIRO_ATK = 18;       // Ataque base do Arqueiro
    public static final int ARQUEIRO_DEF = 10;       // Defesa base do Arqueiro
    
    // Sistema de Combate - configurações de dados e mecânicas
    public static final int DADO_ATAQUE = 20;        // Dado usado para ataques (D20)
    public static final int DADO_FUGA = 10;          // Dado usado para tentativas de fuga (D10)
    public static final int CHANCE_FUGA = 5;         // Limite para fuga bem-sucedida (rolagem > 5 = 50% chance)
    
    // Itens - valores de cura para diferentes tipos de itens curativos
    public static final int POCAO_CURA = 20;         // Cura base da Poção de Cura comum
    public static final int ERVA_CURA = 15;          // Cura da Erva Curativa (menor que poção)
    public static final int POCAO_FORTE_CURA = 30;   // Cura da Poção Forte (item raro)
    
    // Inimigos - atributos balanceados para encontros progressivos
    // Lobo: Inimigo inicial fraco para tutorial
    public static final int LOBO_HP = 40;            // Vida do Lobo da Floresta
    public static final int LOBO_ATK = 8;            // Ataque do Lobo
    public static final int LOBO_DEF = 5;            // Defesa do Lobo
    
    // Goblin: Primeiro mini-chefe com dificuldade moderada
    public static final int GOBLIN_HP = 60;          // Vida do Goblin Guardião
    public static final int GOBLIN_ATK = 12;         // Ataque do Goblin
    public static final int GOBLIN_DEF = 8;          // Defesa do Goblin
    
    // Guardião: Chefe final desafiador
    public static final int GUARDIAO_HP = 100;       // Vida do Guardião Ancestral
    public static final int GUARDIAO_ATK = 18;       // Ataque do Guardião
    public static final int GUARDIAO_DEF = 15;       // Defesa do Guardião
    
    // Níveis - sistema de progressão de personagem
    public static final int NIVEL_INICIAL = 1;       // Nível inicial de todos os personagens
}

/*
COMENTÁRIO FINAL - GAMECONSTANTS.JAVA:

A classe GameConstants é uma classe utilitária fundamental que implementa o padrão
de design Constants Class, centralizando todos os valores numéricos do jogo para
facilitar balanceamento, manutenção e consistência.

ARQUITETURA E ORGANIZAÇÃO:

1. PADRÃO CONSTANTS CLASS:
   - Todos os campos são public, static e final
   - Classe não instanciável (construtor padrão implícito)
   - Agrupamento lógico por seções comentadas
   - Nomenclatura consistente em UPPER_SNAKE_CASE

2. SEÇÕES ORGANIZACIONAIS:
   - Personagens Jogáveis: Atributos das 3 classes
   - Sistema de Combate: Configurações de dados e mecânicas
   - Itens: Valores de efeitos de itens
   - Inimigos: Atributos dos adversários
   - Progressão: Sistema de níveis

BALANCEAMENTO DE CLASSES:

1. GUERREIRO (Tanque):
   - Vida: 120 (Mais alto) | Ataque: 15 | Defesa: 12
   - Arquétipo: Resistente, pode errar mais, dano consistente
   - Bônus de classe: +5 ataque, +3 defesa

2. MAGO (Glass Cannon):
   - Vida: 80 (Mais baixo) | Ataque: 20 | Defesa: 8
   - Arquétipo: Alto dano, frágil, requer posicionamento
   - Bônus de classe: +8 ataque, +2 defesa

3. ARQUEIRO (Balanceado):
   - Vida: 100 (Médio) | Ataque: 18 | Defesa: 10
   - Arquétipo: Versátil, bom em várias situações
   - Bônus de classe: +6 ataque, +1 defesa

SISTEMA DE COMBATE:

- DADO_ATAQUE: D20 - fornece variabilidade (1-20) + atributos
- DADO_FUGA: D10 - chance binária simples (50% sucesso)
- Progressão de dificuldade através dos inimigos

PROGRESSÃO DE INIMIGOS:

1. LOBO (Fácil):
   - HP: 40 | ATK: 8 | DEF: 5
   - Encontro tutorial, pode ser derrotado rapidamente

2. GOBLIN (Médio):
   - HP: 60 | ATK: 12 | DEF: 8  
   - Primeiro desafio real, requer estratégia básica

3. GUARDIÃO (Difícil):
   - HP: 100 | ATK: 18 | DEF: 15
   - Teste final de todas as mecânicas aprendidas

SISTEMA DE ITENS:

- Cura progressiva: Erva (15) < Poção (20) < Poção Forte (30)
- Balanceamento around da vida máxima das classes
- Distribuição estratégica durante o jogo

VANTAGENS DO DESIGN:

1. CENTRALIZAÇÃO:
   - Todos os valores balanceáveis em um único arquivo
   - Fácil ajuste de dificuldade sem modificar lógica

2. CONSISTÊNCIA:
   - Valores uniformes em todo o sistema
   - Eliminação de "números mágicos" no código

3. MANUTENÇÃO:
   - Ajustes de balanceamento sem risco de quebrar lógica
   - Visão geral completa do ecossistema numérico do jogo

4. PERFORMANCE:
   - Constantes em tempo de compilação
   - Acesso direto sem overhead

PRINCÍPIOS DE BALANCEAMENTO APLICADOS:

1. CURVA DE DIFICULDADE:
   - Inimigos progressivamente mais fortes
   - Vida do jogador vs dano dos inimigos balanceado

2. RISCO/RECOMPENSA:
   - Classes com trade-offs claros
   - Itens com valores proporcionais à raridade

3. VARIEDADE ESTRATÉGICA:
   - Cada classe viable com abordagem diferente
   - Inimigos requerem táticas específicas

EXPANSIBILIDADE:

- Fácil adição de novas classes, inimigos ou itens
- Sistema preparado para níveis adicionais
- Possibilidade de dificuldades múltiplas

Esta implementação exemplifica boas práticas de design de jogos, onde o
balanceamento é tratado como concern separado da lógica de jogo, permitindo
iteração rápida e manutenção simplificada do sistema como um todo.
*/
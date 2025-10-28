// Declaração da classe Guerreiro que herda da classe abstrata Personagem
public class Guerreiro extends Personagem {
    
    // Construtor principal da classe Guerreiro
    public Guerreiro(String nome) {
        // Chama o construtor da classe pai (Personagem) com parâmetros específicos
        // Usa constantes da classe GameConstants para garantir consistência no balanceamento
        super(nome, GameConstants.GUERREIRO_HP, GameConstants.GUERREIRO_ATK, GameConstants.GUERREIRO_DEF, GameConstants.NIVEL_INICIAL);
    }

    // Construtor de cópia para criar uma cópia profunda de outro objeto Guerreiro
    public Guerreiro(Guerreiro outro) {
        // Delega para o construtor de cópia da classe pai
        // Isso garante que todos os atributos herdados sejam copiados corretamente
        super(outro);
    }

    // Método sobrescrito para calcular o valor total de ataque do Guerreiro
    @Override
    public int calcularAtaque() {
        // Retorna o ataque base mais um bônus específico da classe Guerreiro
        // +5 representa o treinamento em armas pesadas e força física
        return ataque + 5;
    }

    // Método sobrescrito para calcular o valor total de defesa do Guerreiro
    @Override
    public int calcularDefesa() {
        // Retorna a defesa base mais um bônus específico da classe Guerreiro  
        // +3 representa a habilidade com armaduras e escudos
        return defesa + 3;
    }

    // Método sobrescrito para retornar o identificador textual da classe
    @Override
    public String getClasse() {
        // Retorna a string que identifica esta classe em interfaces e lógica de jogo
        return "Guerreiro";
    }

    // Implementação do método clone do padrão Prototype
    @Override
    public Guerreiro clone() {
        // Cria e retorna uma nova instância usando o construtor de cópia
        // Isso permite criar cópias independentes para sistema de save points
        return new Guerreiro(this);
    }
}

/*
COMENTÁRIO FINAL - GUERREIRO.JAVA:

A classe Guerreiro é uma das três implementações concretas da classe abstrata Personagem,
representando o arquétipo do combatente corpo-a-corpo resistente e poderoso, seguindo
o paradigma do "tanque" em sistemas de RPG tradicionais.

ARQUITETURA E HERANÇA:

1. EXTENSÃO DE PERSONAGEM:
   - Herda todos os atributos e comportamentos base da classe abstrata
   - Implementa os métodos abstratos obrigatórios
   - Especializa o comportamento para o arquétipo específico

2. CONSTRUTORES:
   - Construtor Principal: Recebe apenas o nome e inicializa com constantes
     específicas, demonstrando bom uso de encapsulamento via GameConstants
   - Construtor de Cópia: Implementa o padrão de protótipo para permitir
     criação de cópias profundas do objeto

BALANCEAMENTO E ATRIBUTOS:

1. ATRIBUTOS BASE (GameConstants):
   - Vida: 120 (mais alto entre as classes)
   - Ataque Base: 15 (moderado)
   - Defesa Base: 12 (alta)

2. BÔNUS DE CLASSE:
   - Ataque Total: +5 (ataque base 15 + 5 = 20)
   - Defesa Total: +3 (defesa base 12 + 3 = 15)

3. PERFIL ESTRATÉGICO:
   - Arquétipo: Tanque/Defensor
   - Força: Alta sobrevivência, pode cometer erros táticos
   - Fraqueza: Dano por turno menor que outras classes
   - Jogabilidade: Ideal para jogadores defensivos ou iniciantes

IMPLEMENTAÇÃO DE MÉTODOS:

1. calcularAtaque():
   - Bônus moderado de +5 refletindo força bruta
   - Dano consistente mas não explosivo
   - Balanceado para combates prolongados

2. calcularDefesa():
   - Bônus significativo de +3 representando proficiência em armaduras
   - Maior capacidade de absorver dano
   - Permite mais turnos de sobrevivência em combate

3. getClasse():
   - Retorna identificador para sistemas de UI e lógica condicional
   - Usado em mensagens, salvamento e identificação visual

4. clone():
   - Implementação do padrão Prototype para sistema de save points
   - Permite criar snapshots do personagem em diferentes momentos
   - Usa construtor de cópia para garantir independência das instâncias

PADRÕES DE PROJETO APLICADOS:

1. TEMPLATE METHOD:
   - Personagem define estrutura, Guerreiro implementa especializações
   - Polimorfismo através de métodos abstratos sobrescritos

2. PROTOTYPE:
   - clone() permite criação de cópias para sistema de salvamento
   - Construtor de cópia como mecanismo de implementação

3. STRATEGY (implícito):
   - Diferentes classes implementam estratégias de combate distintas
   - Guerreiro foca em defesa e resistência

INTEGRAÇÃO COM O SISTEMA:

1. COMBATE:
   - Alta defesa reduz frequência de dano recebido
   - Vida alta permite maior margem de erro
   - Ideal para enfrentar inimigos com alto dano

2. PROGRESSÃO:
   - Atributos base preparados para sistema de level up futuro
   - Balanceado contra inimigos definidos em GameConstants

3. INVENTÁRIO:
   - Pode beneficiar-se mais de itens ofensivos para compensar dano
   - Itens defensivos têm valor marginal devido à alta defesa natural

BENEFÍCIOS DE JOGABILIDADE:

1. PARA INICIANTES:
   - Maior tolerância a erros devido à alta vida/defesa
   - Curva de aprendizado mais suave
   - Estratégia simples: atacar e aguentar dano

2. PARA EXPERIENTES:
   - Permite estratégias de "aggro" e proteção de aliados (em expansões)
   - Eficiente em combates prolongados contra chefes

3. BALANCEAMENTO GRUPO (futuro):
   - Função clara de tanque em party
   - Sinergia com classes de suporte e dano

CONFORMIDADE COM OOP:

- Herança bem aplicada para especialização
- Encapsulamento através de constantes centralizadas
- Polimorfismo através de métodos sobrescritos
- Coesão alta - classe tem responsabilidade única e bem definida

EXPANSIBILIDADE:

- Preparada para sistema de habilidades especiais
- Pode evoluir para subclasses (Paladino, Berserker)
- Atributos base permitem adição de equipamentos
- Clone() pronto para sistema de save states

Esta implementação do Guerreiro demonstra um design sólido e balanceado,
oferecendo uma opção viável e distintiva dentro do ecossistema de classes
do RPG, enquanto mantém conformidade com todos os princípios OOP e padrões
de design aplicáveis.
*/
// Declaração da classe Arqueiro que herda de Personagem
public class Arqueiro extends Personagem {
    
    // Construtor principal da classe Arqueiro
    public Arqueiro(String nome) {
        // Chama o construtor da classe pai (Personagem) com parâmetros específicos para Arqueiro
        super(nome, GameConstants.ARQUEIRO_HP, GameConstants.ARQUEIRO_ATK, GameConstants.ARQUEIRO_DEF, GameConstants.NIVEL_INICIAL);
    }

    // Construtor de cópia para criar uma cópia de outro objeto Arqueiro
    public Arqueiro(Arqueiro outro) {
        // Chama o construtor de cópia da classe pai
        super(outro);
    }

    // Método sobrescrito para calcular o ataque do Arqueiro
    @Override
    public int calcularAtaque() {
        // Retorna o ataque base mais um bônus específico da classe Arqueiro
        return ataque + 6;
    }

    // Método sobrescrito para calcular a defesa do Arqueiro
    @Override
    public int calcularDefesa() {
        // Retorna a defesa base mais um bônus específico da classe Arqueiro
        return defesa + 1;
    }

    // Método sobrescrito para retornar o nome da classe
    @Override
    public String getClasse() {
        // Retorna a string identificando a classe
        return "Arqueiro";
    }

    // Método sobrescrito do padrão de protótipo para clonagem
    @Override
    public Arqueiro clone() {
        // Cria e retorna uma nova instância usando o construtor de cópia
        return new Arqueiro(this);
    }
}

/*
COMENTÁRIO FINAL - ARQUEIRO.JAVA:

A classe Arqueiro é uma das três subclasses concretas de Personagem que implementam 
especializações específicas para o sistema de RPG. Esta classe representa o arquétipo 
do combatente à distância, caracterizado por alto dano ofensivo mas defesa moderada.

ESTRUTURA E IMPLEMENTAÇÃO:

1. HERANÇA: Estende Personagem, herdando todos os atributos e métodos base enquanto
   especializa o comportamento específico do arquétipo arqueiro.

2. CONSTRUTORES:
   - Construtor Principal: Recebe apenas o nome e inicializa com constantes específicas
     da classe, demonstrando bom encapsulamento ao usar GameConstants.
   - Construtor de Cópia: Implementa o padrão de protótipo permitindo criar cópias
     profundas do objeto para sistema de save points.

3. MÉTODOS ESPECIALIZADOS:
   - calcularAtaque(): Adiciona +6 de bônus, refletindo a especialização em dano 
     à distância (maior bônus entre as classes).
   - calcularDefesa(): Adiciona +1 de bônus, representando a agilidade limitada
     para defesa.
   - getClasse(): Retorna identificador textual para sistemas de UI e lógica de jogo.

4. PADRÃO PROTÓTIPO:
   - clone(): Implementa clonagem usando o construtor de cópia, permitindo criar
     cópias independentes do personagem para sistema de salvamento ou duplicação.

BALANCEAMENTO DE CLASSE:
O Arqueiro segue o paradigma de "glass cannon" (vidro e canhão) - alto dano 
(ataque +6) mas defesa relativamente baixa (+1), incentivando estratégias de 
combate à distância e posicionamento tático.

CONFORMIDADE COM OOP:
- Herança bem aplicada
- Polimorfismo através dos métodos sobrescritos
- Encapsulamento através do uso de constantes
- Reutilização de código através da herança
*/
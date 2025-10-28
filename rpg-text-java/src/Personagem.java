// Importação da classe Objects para métodos utilitários como hashCode e equals
import java.util.Objects;

// Classe abstrata Personagem que define a estrutura base para todos os personagens do RPG
public abstract class Personagem implements Cloneable {
    // Atributos protegidos - acessíveis pelas subclasses mas não publicamente
    protected String nome;           // Nome do personagem
    protected int pontosVida;        // Vida atual do personagem
    protected int pontosVidaMax;     // Vida máxima do personagem (CORREÇÃO ADICIONADA)
    protected int ataque;            // Valor base de ataque
    protected int defesa;            // Valor base de defesa
    protected int nivel;             // Nível do personagem
    protected Inventario inventario; // Sistema de inventário do personagem

    // Construtor principal para criar novos personagens
    public Personagem(String nome, int pontosVida, int ataque, int defesa, int nivel) {
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.pontosVidaMax = pontosVida; // Vida máxima igual à vida inicial - CORREÇÃO IMPORTANTE
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = nivel;
        this.inventario = new Inventario(); // Cada personagem tem seu próprio inventário
    }

    // Construtor de cópia para criar cópias profundas de personagens
    public Personagem(Personagem outro) {
        this.nome = outro.nome;
        this.pontosVida = outro.pontosVida;
        this.pontosVidaMax = outro.pontosVidaMax; // Copia vida máxima também
        this.ataque = outro.ataque;
        this.defesa = outro.defesa;
        this.nivel = outro.nivel;
        // Cria nova instância de inventário usando construtor de cópia
        this.inventario = new Inventario(outro.inventario);
    }

    // Métodos abstratos - devem ser implementados pelas subclasses
    public abstract int calcularAtaque();  // Calcula ataque total com bônus de classe
    public abstract int calcularDefesa();  // Calcula defesa total com bônus de classe
    public abstract String getClasse();    // Retorna nome da classe especializada

    // Métodos concretos - implementação comum para todas as subclasses

    // Verifica se o personagem está vivo
    public boolean estaVivo() {
        return pontosVida > 0;  // Retorna true se vida maior que zero
    }

    // Aplica dano ao personagem
    public void receberDano(int dano) {
        pontosVida -= dano;  // Subtrai o dano da vida atual
        // Garante que a vida não fique negativa
        if (pontosVida < 0) pontosVida = 0;
    }

    // CORREÇÃO APLICADA: Método para curar o personagem com controle de vida máxima
    public void curar(int cura) {
        int vidaAntes = pontosVida;  // Armazena vida antes da cura para cálculo
        pontosVida += cura;          // Aplica a cura
        
        // CORREÇÃO: Garantir que não ultrapasse a vida máxima da classe
        if (pontosVida > pontosVidaMax) {
            pontosVida = pontosVidaMax;  // Limita ao máximo
        }
        
        // Calcula quanto de vida foi realmente recuperado
        int vidaRecuperada = pontosVida - vidaAntes;
        
        // Feedback visual baseado no resultado da cura
        if (vidaRecuperada > 0) {
            System.out.println("✨ " + nome + " recuperou " + vidaRecuperada + " HP!");
        } else {
            System.out.println("💤 " + nome + " já está com a vida cheia!");
        }
    }

    // Getters para acesso controlado aos atributos

    public String getNome() { return nome; }
    public int getPontosVida() { return pontosVida; }
    public int getPontosVidaMax() { return pontosVidaMax; } // NOVO GETTER - CORREÇÃO IMPORTANTE
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
    public int getNivel() { return nivel; }
    public Inventario getInventario() { return inventario; }

    // Representação textual do personagem para exibição
    @Override
    public String toString() {
        return nome + " (" + getClasse() + " Nv." + nivel + ") - HP: " + pontosVida + "/" + pontosVidaMax + ", ATK: " + ataque + ", DEF: " + defesa;
    }

    // Método equals para comparar personagens baseado em atributos essenciais
    @Override
    public boolean equals(Object obj) {
        // Otimização para mesma referência
        if (this == obj) return true;
        // Verificação de null e compatibilidade de classe
        if (obj == null || getClass() != obj.getClass()) return false;
        // Cast seguro após verificação
        Personagem that = (Personagem) obj;
        // Comparação baseada em atributos numéricos e nome
        return pontosVida == that.pontosVida &&
               ataque == that.ataque &&
               defesa == that.defesa &&
               nivel == that.nivel &&
               Objects.equals(nome, that.nome);
    }

    // HashCode consistente com equals para uso em coleções
    @Override
    public int hashCode() {
        // Gera hash baseado nos mesmos campos usados no equals
        return Objects.hash(nome, pontosVida, ataque, defesa, nivel);
    }

    // Implementação do padrão Prototype para clonagem de personagens
    @Override
    public Personagem clone() {
        try {
            // Clone superficial da instância
            Personagem clone = (Personagem) super.clone();
            // Clona o inventário para garantir independência (cópia profunda)
            clone.inventario = this.inventario.clone();
            return clone;  // Retorna a cópia independente
        } catch (CloneNotSupportedException e) {
            // Teoricamente impossível pois implementamos Cloneable
            throw new AssertionError();
        }
    }
}

/*
COMENTÁRIO FINAL - PERSONAGEM.JAVA:

A classe Personagem é a classe abstrata fundamental do sistema de RPG, atuando
como a base de toda a hierarquia de personagens e implementando o padrão
Template Method para definir uma estrutura comum enquanto permite especializações.

ARQUITETURA E DESIGN:

1. CLASSE ABSTRATA BASE:
   - Define interface comum para todos os personagens
   - Implementa funcionalidades compartilhadas
   - Delega especializações para subclasses através de métodos abstratos

2. PADRÃO TEMPLATE METHOD:
   - Estrutura comum definida na classe base
   - Métodos abstratos forçam implementação específica nas subclasses
   - Combinação de métodos concretos e abstratos para flexibilidade

ATRIBUTOS E ESTADO:

1. ATRIBUTOS PROTEGIDOS:
   - Acessíveis por subclasses mas não publicamente
   - Balance entre encapsulamento e extensibilidade
   - pontosVidaMax adicionado como CORREÇÃO CRÍTICA

2. SISTEMA DE VIDA (CORREÇÃO APLICADA):
   - Antes: Sistema incompleto sem controle de vida máxima
   - Depois: pontosVidaMax permite cura controlada e balanceamento
   - Impacto: Resolve problema de vida infinita com poções

MÉTODOS PRINCIPAIS:

1. MÉTODOS ABSTRATOS (Template Method):
   - calcularAtaque(): Permite bônus específicos por classe
   - calcularDefesa(): Permite especializações defensivas
   - getClasse(): Identificação polimórfica do tipo

2. MÉTODOS CONCRETOS (Funcionalidade Compartilhada):
   - estaVivo(): Lógica comum de estado
   - receberDano(): Mecânica universal de dano
   - curar(): CORREÇÃO - agora controla vida máxima

3. MÉTODO curar() - CORREÇÃO DETALHADA:
   - Problema anterior: Vida podia ultrapassar máximo com múltiplas curas
   - Solução: pontosVidaMax + verificação explícita + feedback visual
   - vidaRecuperada calculada para mostrar cura real (não potencial)

PADRÕES DE PROJETO IMPLEMENTADOS:

1. TEMPLATE METHOD PATTERN:
   - Estrutura fixa com pontos de customização
   - Reuso máximo de código comum
   - Força consistência entre subclasses

2. PROTOTYPE PATTERN:
   - clone() permite criação de cópias para save points
   - Cópia profunda do inventário mantém independência
   - Essencial para sistema de salvamento

3. COMPOSITION OVER INHERITANCE:
   - Inventario como componente separado
   - Flexibilidade para modificar comportamento de inventário

4. DEFENSIVE PROGRAMMING:
   - Verificação de null em equals()
   - Controle de limites em receberDano() e curar()
   - Cópia defensiva no construtor de cópia

SISTEMA DE INVENTÁRIO:

1. COMPOSIÇÃO:
   - Cada personagem tem seu próprio inventário
   - Separação clara de responsabilidades
   - Permite diferentes estratégias de inventário por classe

2. CÓPIA PROFUNDA:
   - Inventário clonado independentemente
   - Evita compartilhamento de estado entre cópias
   - Essencial para sistema de saque e save points

CORREÇÕES CRÍTICAS APLICADAS:

1. PONTOSVIDAMAX:
   - Adicionado para controle explícito de vida máxima
   - Inicializado igual a pontosVida inicial
   - Getter adicionado para acesso controlado

2. MÉTODO CURAR():
   - Reescrevido completamente com controle de máximo
   - Feedback visual informativo
   - Cálculo de cura real vs cura potencial

3. CONSTRUTOR DE CÓPIA:
   - Agora copia pontosVidaMax também
   - Garante consistência entre original e cópia

BENEFÍCIOS DO DESIGN:

1. EXTENSIBILIDADE:
   - Fácil criação de novas classes de personagem
   - Preparado para sistema de raças/arquétipos adicionais
   - Métodos abstratos garantem contrato consistente

2. MANUTENÇÃO:
   - Lógica comum centralizada na classe base
   - Correções aplicam-se a todas as subclasses automaticamente
   - Encapsulamento protege estado interno

3. CONSISTÊNCIA:
   - Todas as subclasses seguem mesma interface
   - Comportamento polimórfico previsível
   - Sistema de tipos forte e explícito

4. PREPARADO PARA EXPANSÕES:
   - Sistema de level up futuro
   - Equipamentos e atributos derivados
   - Status effects e buffs/debuffs

IMPACTO NO SISTEMA:

A correção do sistema de vida (pontosVidaMax + curar()) resolve um problema
fundamental de balanceamento que afetava toda a economia de itens do jogo.
Agora:

- Poções não podem mais causar overflow de vida
- Estratégia de cura torna-se mais tática
- Balanceamento entre classes é preservado
- Feedback ao jogador é mais informativo

Esta implementação de Personagem demonstra um design maduro e bem estruturado,
servindo como base sólida para todo o sistema de RPG enquanto mantém flexibilidade
para crescimento futuro e aderência a princípios sólidos de orientação a objetos.
*/
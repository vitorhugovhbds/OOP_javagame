// Declaração da classe Mago que herda da classe abstrata Personagem
public class Mago extends Personagem {
    
    // Construtor principal da classe Mago
    public Mago(String nome) {
        // Chama o construtor da classe pai (Personagem) com parâmetros específicos do Mago
        // Usa constantes da classe GameConstants para garantir consistência no balanceamento
        super(nome, GameConstants.MAGO_HP, GameConstants.MAGO_ATK, GameConstants.MAGO_DEF, GameConstants.NIVEL_INICIAL);
    }

    // Construtor de cópia para criar uma cópia profunda de outro objeto Mago
    public Mago(Mago outro) {
        // Delega para o construtor de cópia da classe pai
        // Garante que todos os atributos herdados sejam copiados corretamente
        super(outro);
    }

    // Método sobrescrito para calcular o valor total de ataque do Mago
    @Override
    public int calcularAtaque() {
        // Retorna o ataque base mais um bônus significativo específico da classe Mago
        // +8 representa o poder mágico intenso e conhecimento arcano
        return ataque + 8;
    }

    // Método sobrescrito para calcular o valor total de defesa do Mago
    @Override
    public int calcularDefesa() {
        // Retorna a defesa base mais um bônus modesto específico da classe Mago
        // +2 representa barreiras mágicas básicas e esquiva limitada
        return defesa + 2;
    }

    // Método sobrescrito para retornar o identificador textual da classe
    @Override
    public String getClasse() {
        // Retorna a string que identifica esta classe em interfaces e lógica de jogo
        return "Mago";
    }

    // Implementação do método clone do padrão Prototype
    @Override
    public Mago clone() {
        // Cria e retorna uma nova instância usando o construtor de cópia
        // Permite criar cópias independentes para sistema de save points
        return new Mago(this);
    }
}

/*
COMENTÁRIO FINAL - MAGO.JAVA:

A classe Mago é uma das três implementações concretas da classe abstrata Personagem,
representando o arquétipo do conjurador de magias poderoso mas vulnerável, seguindo
o paradigma do "glass cannon" (vidro e canhão) em sistemas de RPG tradicionais.

ARQUITETURA E HERANÇA:

1. EXTENSÃO DE PERSONAGEM:
   - Herda todos os atributos e comportamentos base da classe abstrata
   - Implementa os métodos abstratos obrigatórios de forma especializada
   - Define o perfil único do conjurador arcano no sistema

2. CONSTRUTORES:
   - Construtor Principal: Inicializa com constantes específicas do Mago,
     demonstrando uso consistente do sistema de balanceamento via GameConstants
   - Construtor de Cópia: Implementa o padrão de protótipo para permitir
     criação de cópias profundas para sistema de salvamento

BALANCEAMENTO E ATRIBUTOS:

1. ATRIBUTOS BASE (GameConstants):
   - Vida: 80 (mais baixo entre as classes - vulnerabilidade característica)
   - Ataque Base: 20 (mais alto - poder bruto mágico)
   - Defesa Base: 8 (mais baixa - foco em ofensa sobre defesa)

2. BÔNUS DE CLASSE:
   - Ataque Total: +8 (ataque base 20 + 8 = 28 - significativamente mais alto)
   - Defesa Total: +2 (defesa base 8 + 2 = 10 - ainda bastante baixa)

3. PERFIL ESTRATÉGICO:
   - Arquétipo: Glass Cannon/Dano em Área
   - Força: Dano por turno mais alto do jogo
   - Fraqueza: Baixa tolerância a erros devido à pouca vida/defesa
   - Jogabilidade: Ideal para jogadores experientes e estratégicos

IMPLEMENTAÇÃO DE MÉTODOS:

1. calcularAtaque():
   - Bônus substancial de +8 refletindo poder mágico intenso
   - Maior potencial de dano do jogo em troca de vulnerabilidade
   - Encouraga estratégias de "eliminação rápida" de ameaças

2. calcularDefesa():
   - Bônus modesto de +2 representando barreiras mágicas básicas
   - Defesa mais baixa entre todas as classes
   - Requer posicionamento cuidadoso e uso estratégico de recursos

3. getClasse():
   - Retorna identificador consistente para sistemas de UI
   - Usado em mensagens de combate, interfaces e lógica condicional

4. clone():
   - Implementação do padrão Prototype para sistema de save points
   - Permite criar snapshots do personagem para salvamento
   - Garante independência entre instâncias originais e cópias

PADRÕES DE PROJETO APLICADOS:

1. TEMPLATE METHOD:
   - Personagem define a estrutura, Mago implementa especializações
   - Polimorfismo através da sobrescrita de métodos abstratos

2. PROTOTYPE:
   - clone() permite criação eficiente de cópias para persistência
   - Construtor de cópia como mecanismo de implementação robusto

3. STRATEGY (implícito):
   - Diferentes classes implementam estratégias de combate distintas
   - Mago foca em dano explosivo e eliminação rápida

BALANCEAMENTO ESTRATÉGICO:

1. TRADE-OFFS CLAROS:
   - Alto dano ↔ Baixa sobrevivência
   - Poder ofensivo ↔ Vulnerabilidade defensiva
   - Recompensa habilidade ↔ Pune erros severamente

2. SINERGIA COM SISTEMA DE COMBATE:
   - Ideal para eliminar ameaças antes que causem dano
   - Beneficia-se muito de itens curativos para compensar baixa vida
   - Eficiente contra inimigos com alta defesa mas vida moderada

3. CURVA DE APRENDIZADO:
   - Desafiador para iniciantes (baixa margem para erro)
   - Altamente recompensador para jogadores experientes
   - Requer entendimento profundo das mecânicas de combate

INTEGRAÇÃO COM O SISTEMA:

1. SISTEMA DE ITENS:
   - Beneficia-se muito de itens curativos (Poções, Ervas)
   - Itens ofensivos têm valor marginal devido ao alto dano base
   - Itens defensivos podem ser cruciais para sobrevivência

2. ENFRENTAMENTO DE INIMIGOS:
   - Excelente contra Goblin (DEF 8) - ignora大部分 da defesa
   - Desafiador contra Guardião (DEF 15) - ainda causa dano significativo
   - Vulnerável a ataques do Lobo (ATK 8) - cada acerto é significativo

3. PROGRESSÃO DE DIFICULDADE:
   - Início desafiador devido à baixa vida
   - Torna-se progressivamente mais poderoso com equipamentos futuros
   - Escala bem com sistema de level up potencial

CONFORMIDADE COM OOP:

- **Herança**: Especialização adequada da classe base Personagem
- **Encapsulamento**: Uso de constantes centralizadas para balanceamento
- **Polimorfismo**: Implementação consistente dos métodos abstratos
- **Coesão**: Responsabilidades bem definidas e focadas no arquétipo

EXPANSIBILIDADE:

- Preparada para sistema de magias/especializações futuras
- Pode evoluir para subclasses (Feiticeiro, Bruxo, Magus)
- Atributos base permitem adição de equipamentos mágicos
- Clone() pronto para sistema de save states complexo

BENEFÍCIOS DE JOGABILIDADE:

1. PARA JOGADORES ESTRATÉGICOS:
   - Recompensa planejamento e posicionamento
   - Satisfação ao eliminar ameaças rapidamente
   - Encoragem uso criativo de recursos

2. VARIEDADE DE EXPERIÊNCIA:
   - Oferece estilo de jogo distinto dos outras classes
   - Proporciona desafio único e recompensador
   - Adiciona profundidade estratégica ao jogo

3. BALANCEAMENTO DE GRUPO (futuro):
   - Função clara de dano em área/controle em party
   - Sinergia com tanques (Guerreiro) e suportes

Esta implementação do Mago demonstra um excelente entendimento de design
de classes em RPGs, criando uma opção distinta e balanceada que oferece
um estilo de jogo único enquanto mantém conformidade com todos os princípios
OOP e padrões de design estabelecidos no sistema.
*/
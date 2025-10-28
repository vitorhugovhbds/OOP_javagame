// Importação da classe Random para geração de números aleatórios
import java.util.Random;

// Classe utilitária para simulação de rolagem de dados no sistema de RPG
public class Dice {
    // Instância estática e final de Random para garantir mesma semente durante a execução
    // 'static': compartilhada por todas as instâncias da classe
    // 'final': não pode ser reatribuída, garantindo consistência nas rolagens
    private static final Random random = new Random();
    
    // Método genérico para rolar dados com qualquer número de lados
    // 'static': pode ser chamado sem instanciar a classe
    public static int roll(int lados) {
        // random.nextInt(lados) gera número de 0 (inclusive) a lados (exclusive)
        // +1 ajusta para o range 1-lados (inclusive), simulando dado real
        return random.nextInt(lados) + 1;
    }
    
    // Método especializado para rolar dado de 20 lados (D20)
    // D20 é padrão em sistemas de RPG para testes de habilidade/ataque
    public static int rollD20() {
        // Delega para o método roll com parâmetro 20
        return roll(20);
    }
    
    // Método especializado para rolar dado de 10 lados (D10)
    // Usado para testes de fuga e outros eventos com probabilidade decimal
    public static int rollD10() {
        // Delega para o método roll com parâmetro 10
        return roll(10);
    }
    
    // Método especializado para rolar dado de 6 lados (D6)
    // Dado tradicional, poderia ser usado para dano de armas simples
    public static int rollD6() {
        // Delega para o método roll com parâmetro 6
        return roll(6);
    }
}

/*
COMENTÁRIO FINAL - DICE.JAVA:

A classe Dice é uma classe utilitária fundamental para o sistema de RPG, implementando
o mecanismo de aleatoriedade baseado em rolagem de dados que é característico de jogos
do gênero. Esta classe segue o padrão de design Utility Class.

CARACTERÍSTICAS TÉCNICAS:

1. PADRÃO UTILITY CLASS:
   - Todos os métodos são estáticos
   - Construtor padrão implícito (não instanciável)
   - Agrupamento de funcionalidades relacionadas
   - Uso através de Dice.rollD20() sem necessidade de instância

2. IMPLEMENTAÇÃO DE RANDOM:
   - Uso de java.util.Random como gerador pseudo-aleatório
   - Instância única (static final) garante sequência consistente
   - Evita problemas de sincronização por ser estática

3. SEMÂNTICA DE DADOS DE RPG:
   - Métodos nomeados com convenção de RPG (D20, D10, D6)
   - Range inclusivo (1-N) simulando dados físicos reais
   - Interface intuitiva para desenvolvedores familiarizados com RPG

FUNCIONALIDADES ESPECÍFICAS:

1. roll(int lados):
   - Método base flexível que permite qualquer tipo de dado
   - Útil para expansões futuras com dados não convencionais
   - Implementação matemática correta: nextInt(n) + 1

2. rollD20():
   - Dado principal para sistema de combate
   - Range: 1-20 (crítico natural em 20, falha em 1)
   - Base para testes de ataque, defesa e habilidades

3. rollD10():
   - Usado para mecânicas de porcentagem (fuga, chance de encontro)
   - Range: 1-10 (50% = >5, 30% = >7, etc.)
   - Permite granularidade decimal simples

4. rollD6():
   - Dado tradicional para dano de armas simples
   - Range: 1-6 (média 3.5)
   - Pode ser usado para efeitos variáveis futuros

VANTAGENS DO DESIGN:

1. ENCAPSULAMENTO:
   - Esconde complexidade da geração de números aleatórios
   - Interface simples e semântica para o domínio do problema

2. REUTILIZAÇÃO:
   - Pode ser usado em qualquer parte do sistema
   - Fácil expansão para novos tipos de dados

3. CONSISTÊNCIA:
   - Mesma semente durante execução garante reproducibilidade
   - Comportamento previsível em testes

4. PERFORMANCE:
   - Métodos estáticos evitam overhead de instanciação
   - Random compartilhado evita custo de criação múltipla

USO NO SISTEMA:

- CombatSystem: D20 para ataques, D10 para fugas
- Eventos aleatórios: Encontros, tesouros, armadilhas
- Expansões futuras: D4, D8, D12 para diferentes mecânicas

POSSÍVEIS MELHORIAS FUTURAS:

1. Sistema de rolagens complexas (2D6, 3D10, etc.)
2. Modificadores de rolagem (+2, -1, vantagem/desvantagem)
3. Registro de rolagens para debugging
4. Diferentes geradores (true random para online)

Esta implementação é elegante, eficiente e semanticamente correta para o domínio
de RPG, servindo como base sólida para todo o sistema de aleatoriedade do jogo.
*/
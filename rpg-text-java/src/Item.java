import java.util.Objects;

public class Item implements Comparable<Item>, Cloneable {
    private String nome;
    private String descricao;
    private String efeito;
    private int quantidade;
    
    public Item(String nome, String descricao, String efeito, int quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.efeito = efeito;
        this.quantidade = quantidade;
    }
    
    // Método para usar sem personagem (mantido para compatibilidade)
    public void usar() {
        if (quantidade <= 0) {
            System.out.println("Não há mais " + nome + " para usar!");
            return;
        }
        System.out.println("Usou: " + nome);
        quantidade--;
    }
    
    // Método para usar com personagem (CORRIGIDO)
    public void usar(Personagem personagem) {
    if (quantidade <= 0) {
        System.out.println("Não há mais " + nome + " para usar!");
        return;
    }
    
    switch (efeito.toLowerCase()) {
        case "cura":
            int cura = 0;
            if (nome.contains("Poção Forte")) {
                cura = GameConstants.POCAO_FORTE_CURA;
            } else if (nome.contains("Poção")) {
                cura = GameConstants.POCAO_CURA;
            } else {
                cura = GameConstants.ERVA_CURA;
            }
            
            // CORREÇÃO: Usar o método curar que já controla o máximo
            personagem.curar(cura);
            break;
            
        case "ataque":
            System.out.println("💪 " + personagem.getNome() + " usou " + nome + " e sente seu ataque aumentar!");
            break;
            
        default:
            System.out.println("🔮 " + personagem.getNome() + " usou " + nome + "!");
    }
    quantidade--;
}
    
    public void incrementarQuantidade(int quantidade) {
        this.quantidade += quantidade;
    }
    
    // Getters
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public String getEfeito() { return efeito; }
    public int getQuantidade() { return quantidade; }
    
    @Override
    public String toString() {
        return nome + " - " + descricao + " (" + quantidade + ")";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item item = (Item) obj;
        return Objects.equals(nome, item.nome) && Objects.equals(efeito, item.efeito);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(nome, efeito);
    }
    
    @Override
    public int compareTo(Item outro) {
        return this.nome.compareTo(outro.nome);
    }
    
    @Override
    public Item clone() {
        try {
            return (Item) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
/*
COMENTÁRIO FINAL - ITEM.JAVA:

A classe Item é uma classe fundamental do sistema de RPG que modela os itens
consumíveis e equipáveis do jogo, implementando várias interfaces importantes
para integração com o sistema de coleções do Java e padrões de design.

ARQUITETURA E DESIGN:

1. MODELAGEM DE DOMÍNIO:
   - Atributos representam propriedades essenciais de itens de RPG
   - Separação clara entre identidade (nome), descrição (descricao) e 
     funcionalidade (efeito, quantidade)
   - Design preparado para expansão com novos tipos de efeitos

2. IMPLEMENTAÇÃO DE INTERFACES:
   - Comparable: Para ordenação natural em coleções
   - Cloneable: Para suporte a cópias no sistema de inventário
   - equals/hashCode: Para operações em coleções baseadas em hash

SISTEMA DE EFEITOS IMPLEMENTADO:

1. SISTEMA DE CURA (CORRIGIDO):
   - Lógica centralizada no método usar(Personagem)
   - Detecção automática do tipo de item de cura pelo nome
   - Uso do método curar() do Personagem que já controla vida máxima
   - Valores balanceados via GameConstants

2. TIPOS DE EFEITOS:
   - "cura": Efeito implementado com valores específicos
   - "ataque": Placeholder para sistema futuro de buffs
   - default: Comportamento genérico para novos tipos

MÉTODOS CRÍTICOS E SUAS IMPLICAÇÕES:

1. usar(Personagem) - CORREÇÃO APLICADA:
   - Antes: Cálculo manual de cura que podia ultrapassar vida máxima
   - Depois: Delegação para personagem.curar() que controla limites
   - Resultado: Sistema robusto e consistente

2. equals() e hashCode():
   - Baseados em nome e efeito - dois itens são iguais se compartilham
     esses atributos, independente de quantidade ou descrição
   - Consistência garantida entre equals e hashCode
   - Essencial para funcionamento do Inventario.adicionarItem()

3. compareTo():
   - Ordenação natural por nome para interface organizada
   - Implementação delegada para String.compareTo()
   - Usado pelo Collections.sort() no Inventario

4. clone():
   - Clone superficial adequado para classe com campos imutáveis
   - Suporte ao padrão Prototype do Inventario
   - Garante independência entre itens no inventário

PADRÕES DE PROJETO APLICADOS:

1. PROTOTYPE PATTERN:
   - clone() permite criação eficiente de cópias
   - Essencial para sistema de consolidação de itens no inventário

2. STRATEGY PATTERN (implícito):
   - Diferentes comportamentos baseados no tipo de efeito
   - switch actúa como selector de estratégia

3. COMPARABLE PATTERN:
   - Ordenação consistente para interface de usuário
   - Integração com APIs de coleções do Java

DETALHES DE IMPLEMENTAÇÃO:

1. GERENCIAMENTO DE QUANTIDADE:
   - incrementarQuantidade() permite operações positivas e negativas
   - Controle centralizado no uso para evitar quantidades negativas
   - Verificação de disponibilidade antes do uso

2. TRATAMENTO DE CASE:
   - efeito.toLowerCase() para case-insensitivity
   - Flexibilidade na definição de tipos de efeito

3. MENSAGENS AO USUÁRIO:
   - Feedback visual com emojis para melhor experiência
   - Mensagens específicas por tipo de efeito
   - Confirmação de uso bem-sucedido

INTEGRAÇÃO COM O SISTEMA:

1. INVENTARIO:
   - equals() usado para consolidação de itens iguais
   - compareTo() usado para ordenação na listagem
   - clone() usado para adição segura de itens

2. SISTEMA DE COMBATE:
   - usar(Personagem) integrado com CombatSystem
   - Efeitos aplicados durante batalha
   - Controle de quantidades em tempo real

3. SISTEMA DE CURAS:
   - Integração com Personagem.curar() para controle de vida máxima
   - Valores balanceados via GameConstants
   - Feedback visual do resultado da cura

VANTAGENS DO DESIGN:

1. COESÃO ALTA:
   - Todos os métodos relacionam-se com o conceito de "item"
   - Responsabilidades bem definidas e focadas

2. ACOPLAMENTO BAIXO:
   - Conhece apenas Personagem e GameConstants necessários
   - Não depende de outras partes do sistema

3. EXTENSIBILIDADE:
   - Fácil adição de novos tipos de efeitos
   - Preparado para sistema de equipamentos futuros
   - Estrutura flexível para novos atributos

4. REUSO:
   - Pode ser usado por jogadores e inimigos
   - Sistema de efeitos genérico e reutilizável

CORREÇÕES E MELHORIAS:

A correção no método usar(Personagem) resolve um problema crítico de
balanceamento onde a vida podia ultrapassar o máximo permitido. A nova
implementação:

- Delega o controle para Personagem.curar() que já tem a lógica de limite
- Mantém a detecção automática do valor de cura baseado no nome
- Preserva a interface existente sem quebrar compatibilidade

Esta implementação de Item demonstra um design maduro e bem estruturado,
oferecendo uma base sólida para o sistema de itens do RPG enquanto mantém
flexibilidade para expansões futuras e integração harmoniosa com o resto
do sistema.
*/
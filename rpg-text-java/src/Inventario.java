// Importações necessárias para as coleções e utilitários do Java
import java.util.*;

// Classe Inventario que gerencia a coleção de itens de um personagem, implementando Cloneable
public class Inventario implements Cloneable {
    private List<Item> itens;  // Lista que armazena todos os itens do inventário
    
    // Construtor padrão que inicializa uma lista vazia de itens
    public Inventario() {
        this.itens = new ArrayList<>();  // ArrayList para acesso eficiente e ordenação
    }
    
    // Construtor de cópia para criar uma cópia profunda de outro inventário
    public Inventario(Inventario outro) {
        this.itens = new ArrayList<>();  // Nova lista independente
        // Itera sobre todos os itens do inventário original
        for (Item item : outro.itens) {
            // Adiciona uma cópia profunda de cada item (clone())
            this.itens.add(item.clone());
        }
    }
    
    // Método para adicionar um item ao inventário, com consolidação de itens iguais
    public void adicionarItem(Item novoItem) {
        // Percorre a lista existente procurando por item igual
        for (Item item : itens) {
            // Usa equals() definido na classe Item para verificar igualdade
            if (item.equals(novoItem)) {
                // Se encontrou item igual, incrementa a quantidade
                item.incrementarQuantidade(novoItem.getQuantidade());
                return;  // Sai do método após consolidar
            }
        }
        // Se não encontrou item igual, adiciona novo item (com clone para independência)
        itens.add(novoItem.clone());
    }
    
    // Método para remover uma unidade de um item pelo nome
    public boolean removerItem(String nomeItem) {
        // Usa Iterator para remoção segura durante iteração
        Iterator<Item> iterator = itens.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            // Comparação case-insensitive para maior usabilidade
            if (item.getNome().equalsIgnoreCase(nomeItem)) {
                if (item.getQuantidade() > 1) {
                    // Se tem mais de uma unidade, decrementa quantidade
                    item.incrementarQuantidade(-1);
                    System.out.println("✅ Usou 1 " + item.getNome() + ". Restam: " + item.getQuantidade());
                } else {
                    // Se é a última unidade, remove o item completamente
                    iterator.remove();
                    System.out.println("✅ Usou o último " + item.getNome());
                }
                return true;  // Indica remoção bem-sucedida
            }
        }
        // Se chegou aqui, item não foi encontrado
        System.out.println("❌ Item não encontrado: " + nomeItem);
        return false;  // Indica falha na remoção
    }
    
    // Método para buscar um item pelo nome (case-sensitive)
    public Item getItem(String nomeItem) {
        // Percorre a lista procurando item com nome exato
        for (Item item : itens) {
            if (item.getNome().equalsIgnoreCase(nomeItem)) {
                return item;  // Retorna referência ao item encontrado
            }
        }
        return null;  // Retorna null se não encontrado
    }
    
    // Método para listar todos os itens do inventário de forma ordenada
    public void listarItens() {
        // Verifica se o inventário está vazio
        if (itens.isEmpty()) {
            System.out.println("Inventário vazio.");
            return;  // Sai early se não há itens
        }
        
        // Ordena os itens usando o compareTo() definido na classe Item
        Collections.sort(itens);
        System.out.println("=== INVENTÁRIO ===");
        // Lista numerada dos itens (base 1 para melhor usabilidade)
        for (int i = 0; i < itens.size(); i++) {
            System.out.println((i + 1) + ". " + itens.get(i));  // Usa toString() do Item
        }
    }
    
    // Método para verificar se o inventário contém algum item
    public boolean temItens() {
        return !itens.isEmpty();  // Retorna true se a lista não estiver vazia
    }
    
    // MÉTODO ADICIONADO PARA RESOLVER O ERRO - calcula quantidade total de todos os itens
    public int getQuantidadeTotal() {
        int total = 0;
        // Soma as quantidades de todos os itens
        for (Item item : itens) {
            total += item.getQuantidade();
        }
        return total;  // Retorna o somatório
    }
    
    // Implementação do método clone() para o padrão Prototype
    @Override
    public Inventario clone() {
        try {
            // Clone superficial da instância atual
            Inventario clone = (Inventario) super.clone();
            // Cria nova lista para o clone
            clone.itens = new ArrayList<>();
            // Clona cada item individualmente (cópia profunda)
            for (Item item : this.itens) {
                clone.itens.add(item.clone());
            }
            return clone;  // Retorna o inventário clonado
        } catch (CloneNotSupportedException e) {
            // Teoricamente impossível pois implementamos Cloneable
            throw new AssertionError();
        }
    }
    
    // Getter que retorna uma cópia defensiva da lista de itens
    public List<Item> getItens() {
        return new ArrayList<>(itens); // Retorna uma cópia da lista para evitar modificações externas
    }
}

/*
COMENTÁRIO FINAL - INVENTARIO.JAVA:

A classe Inventario é um componente central do sistema de RPG que implementa um
sistema sofisticado de gerenciamento de itens com quantidades, seguindo princípios
sólidos de design orientado a objetos e padrões de projeto estabelecidos.

ARQUITETURA E ESTRUTURA DE DADOS:

1. IMPLEMENTAÇÃO DE LISTA:
   - Usa ArrayList para O(1) acesso por índice e eficiência em iteração
   - Mantém ordem de inserção com possibilidade de ordenação
   - Balance entre performance e flexibilidade

2. SISTEMA DE QUANTIDADES:
   - Itens iguais são consolidados em uma única entrada
   - Controle granular de quantidades por item
   - Remoção inteligente (decremento ou remoção completa)

PADRÕES DE PROJETO IMPLEMENTADOS:

1. PROTOTYPE PATTERN:
   - clone() cria cópias profundas independentes
   - Construtor de cópia para inicialização alternativa
   - Essencial para sistema de saque e save points

2. ITERATOR PATTERN:
   - Uso de Iterator para remoção segura durante iteração
   - Evita ConcurrentModificationException

3. DEFENSIVE COPYING:
   - getItens() retorna cópia da lista para encapsulamento
   - clone() garante independência de objetos copiados

4. COMPARABLE PATTERN:
   - Integração com Collections.sort() para ordenação
   - Itens ordenados por nome via compareTo() da classe Item

FUNCIONALIDADES PRINCIPAIS:

1. ADIÇÃO INTELIGENTE:
   - Verifica existência via equals() antes de adicionar
   - Consolida quantidades para itens duplicados
   - Clone de itens para evitar aliasing

2. REMOÇÃO SEGURA:
   - Iterator para remoção durante iteração
   - Decremento ou remoção baseado na quantidade
   - Feedback visual para o usuário

3. BUSCA E CONSULTA:
   - Busca case-insensitive para melhor UX
   - Listagem ordenada para fácil navegação
   - Métodos de verificação de estado (temItens())

4. CÓPIA E CLONAGEM:
   - Cópia profunda para independência de objetos
   - Suporte a sistema de saque de inimigos
   - Preparado para sistema de save states

DETALHES DE IMPLEMENTAÇÃO:

1. TRATAMENTO DE CASE:
   - equalsIgnoreCase() na busca por nome
   - Balance entre usabilidade e performance

2. GERENCIAMENTO DE MEMÓRIA:
   - Clone de itens evita referências compartilhadas
   - Cópia defensiva protege estado interno
   - ArrayList com growth dinâmico

3. FEEDBACK AO USUÁRIO:
   - Mensagens informativas em operações críticas
   - Listagem numerada para seleção fácil
   - Indicadores visuais (emojis) para melhor experiência

INTEGRAÇÃO COM O SISTEMA:

1. SISTEMA DE COMBATE:
   - Acesso rápido a itens durante batalha
   - Remoção automática ao usar itens

2. SISTEMA DE EXPLORAÇÃO:
   - Adição de itens encontrados
   - Listagem para gerenciamento estratégico

3. SISTEMA DE INIMIGOS:
   - Clone() para saque de itens de inimigos derrotados
   - Inventário compartilhado entre Personagem e Inimigo

VANTAGENS DO DESIGN:

1. ENCAPSULAMENTO:
   - Estado interno protegido contra modificação externa
   - Operações validadas e controladas

2. EXTENSIBILIDADE:
   - Fácil adição de novos tipos de operações
   - Preparado para categorização de itens
   - Suporte a limites de capacidade futuros

3. ROBUSTEZ:
   - Tratamento de casos edge (lista vazia, item não encontrado)
   - Código seguro contra modificação concorrente
   - Clone profundo evita efeitos colaterais

4. USABILIDADE:
   - Interface intuitiva e consistente
   - Feedback adequado ao usuário
   - Ordenação para fácil navegação

CASOS DE USO ESPECÍFICOS:

1. SAQUE DE INIMIGOS:
   - clone() usado para copiar itens do inimigo para jogador
   - Evita compartilhamento de referências

2. SAVE GAME:
   - Inventário completo pode ser clonado para save state
   - Independência entre estado salvo e estado atual

3. GERENCIAMENTO DE RECURSOS:
   - Controle preciso de quantidades de itens consumíveis
   - Estratégia de uso baseada em quantidades disponíveis

Esta implementação de Inventario demonstra um design maduro e bem pensado,
equilibrando complexidade funcional com simplicidade de uso, enquanto mantém
altos padrões de encapsulamento, performance e preparação para expansões futuras.
*/
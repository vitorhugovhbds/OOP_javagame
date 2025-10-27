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
    
    // Método para usar com personagem (NOVO)
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
                personagem.curar(cura);
                System.out.println("✨ " + personagem.getNome() + " usou " + nome + " e recuperou " + cura + " HP!");
                break;
                
            case "ataque":
                System.out.println("💪 " + personagem.getNome() + " usou " + nome + " e sente seu ataque aumentar!");
                // Em uma versão mais complexa, aplicaríamos um buff temporário
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
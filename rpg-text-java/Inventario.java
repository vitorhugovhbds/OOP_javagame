import java.util.*;

public class Inventario implements Cloneable {
    private List<Item> itens;
    
    public Inventario() {
        this.itens = new ArrayList<>();
    }
    
    // Construtor de cópia
    public Inventario(Inventario outro) {
        this.itens = new ArrayList<>();
        for (Item item : outro.itens) {
            this.itens.add(item.clone());
        }
    }
    
    public void adicionarItem(Item novoItem) {
        for (Item item : itens) {
            if (item.equals(novoItem)) {
                item.incrementarQuantidade(novoItem.getQuantidade());
                return;
            }
        }
        itens.add(novoItem.clone());
    }
    
    public boolean removerItem(String nomeItem) {
        Iterator<Item> iterator = itens.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.getNome().equalsIgnoreCase(nomeItem)) {
                if (item.getQuantidade() > 1) {
                    item.incrementarQuantidade(-1);
                    System.out.println("✅ Usou 1 " + item.getNome() + ". Restam: " + item.getQuantidade());
                } else {
                    iterator.remove();
                    System.out.println("✅ Usou o último " + item.getNome());
                }
                return true;
            }
        }
        System.out.println("❌ Item não encontrado: " + nomeItem);
        return false;
    }
    
    public Item getItem(String nomeItem) {
        for (Item item : itens) {
            if (item.getNome().equalsIgnoreCase(nomeItem)) {
                return item;
            }
        }
        return null;
    }
    
    public void listarItens() {
        if (itens.isEmpty()) {
            System.out.println("Inventário vazio.");
            return;
        }
        
        Collections.sort(itens);
        System.out.println("=== INVENTÁRIO ===");
        for (int i = 0; i < itens.size(); i++) {
            System.out.println((i + 1) + ". " + itens.get(i));
        }
    }
    
    public boolean temItens() {
        return !itens.isEmpty();
    }
    
    // MÉTODO ADICIONADO PARA RESOLVER O ERRO
    public int getQuantidadeTotal() {
        int total = 0;
        for (Item item : itens) {
            total += item.getQuantidade();
        }
        return total;
    }
    
    @Override
    public Inventario clone() {
        try {
            Inventario clone = (Inventario) super.clone();
            clone.itens = new ArrayList<>();
            for (Item item : this.itens) {
                clone.itens.add(item.clone());
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    
    public List<Item> getItens() {
        return new ArrayList<>(itens); // Retorna uma cópia da lista
    }
}
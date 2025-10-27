import java.util.Objects;

public abstract class Personagem implements Cloneable {
    protected String nome;
    protected int pontosVida;
    protected int pontosVidaMax;
    protected int ataque;
    protected int defesa;
    protected int nivel;
    protected Inventario inventario;

    public Personagem(String nome, int pontosVida, int ataque, int defesa, int nivel) {
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.pontosVidaMax = pontosVida; // Vida máxima igual à vida inicial
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = nivel;
        this.inventario = new Inventario();
    }

    // Construtor de cópia
    public Personagem(Personagem outro) {
        this.nome = outro.nome;
        this.pontosVida = outro.pontosVida;
        this.pontosVidaMax = outro.pontosVidaMax;
        this.ataque = outro.ataque;
        this.defesa = outro.defesa;
        this.nivel = outro.nivel;
        this.inventario = new Inventario(outro.inventario);
    }

    // Métodos abstratos
    public abstract int calcularAtaque();
    public abstract int calcularDefesa();
    public abstract String getClasse();

    // Métodos concretos
    public boolean estaVivo() {
        return pontosVida > 0;
    }

    public void receberDano(int dano) {
        pontosVida -= dano;
        if (pontosVida < 0) pontosVida = 0;
    }

    public void curar(int cura) {
        pontosVida += cura;
        // IMPEDIR QUE A VIDA ULTRAPASSE O MÁXIMO
        if (pontosVida > pontosVidaMax) {
            pontosVida = pontosVidaMax;
        }
    }

    // NOVO MÉTODO: Verificar cura necessária sem ultrapassar o máximo
    public int calcularCuraNecessaria(int cura) {
        int vidaFaltante = pontosVidaMax - pontosVida;
        return Math.min(cura, vidaFaltante);
    }

    // Getters
    public String getNome() { return nome; }
    public int getPontosVida() { return pontosVida; }
    public int getPontosVidaMax() { return pontosVidaMax; } // NOVO GETTER
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
    public int getNivel() { return nivel; }
    public Inventario getInventario() { return inventario; }

    @Override
    public String toString() {
        return nome + " (" + getClasse() + " Nv." + nivel + ") - HP: " + pontosVida + "/" + pontosVidaMax + ", ATK: " + ataque + ", DEF: " + defesa;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Personagem that = (Personagem) obj;
        return pontosVida == that.pontosVida &&
               ataque == that.ataque &&
               defesa == that.defesa &&
               nivel == that.nivel &&
               Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, pontosVida, ataque, defesa, nivel);
    }

    @Override
    public Personagem clone() {
        try {
            Personagem clone = (Personagem) super.clone();
            clone.inventario = this.inventario.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
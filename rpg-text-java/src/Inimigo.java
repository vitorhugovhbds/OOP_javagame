
import java.util.Random;

public class Inimigo extends Personagem {
    private String tipo;
    private int experienciaConcedida;
    private static final Random random = new Random();
    
    public Inimigo(String nome, String tipo, int pontosVida, int ataque, int defesa, int nivel) {
        super(nome, pontosVida, ataque, defesa, nivel);
        this.tipo = tipo;
        this.experienciaConcedida = nivel * 25 + random.nextInt(20);
    }

    // Construtor de cópia
    public Inimigo(Inimigo outro) {
        super(outro);
        this.tipo = outro.tipo;
        this.experienciaConcedida = outro.experienciaConcedida;
    }

    @Override
    public int calcularAtaque() {
        return ataque;
    }

    @Override
    public int calcularDefesa() {
        return defesa;
    }

    @Override
    public String getClasse() {
        return "Inimigo";
    }

    public String getTipo() {
        return tipo;
    }

    public int getExperienciaConcedida() {
        return experienciaConcedida;
    }

    @Override
    public Inimigo clone() {
        return new Inimigo(this);
    }
}
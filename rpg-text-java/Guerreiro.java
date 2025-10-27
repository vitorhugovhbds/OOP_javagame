public class Guerreiro extends Personagem {
    
    public Guerreiro(String nome) {
        super(nome, GameConstants.GUERREIRO_HP, GameConstants.GUERREIRO_ATK, GameConstants.GUERREIRO_DEF, GameConstants.NIVEL_INICIAL);
    }

    public Guerreiro(Guerreiro outro) {
        super(outro);
    }

    @Override
    public int calcularAtaque() {
        return ataque + 5;
    }

    @Override
    public int calcularDefesa() {
        return defesa + 3;
    }

    @Override
    public String getClasse() {
        return "Guerreiro";
    }

    @Override
    public Guerreiro clone() {
        return new Guerreiro(this);
    }
}
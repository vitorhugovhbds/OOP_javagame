public class Arqueiro extends Personagem {
    
    public Arqueiro(String nome) {
        super(nome, GameConstants.ARQUEIRO_HP, GameConstants.ARQUEIRO_ATK, GameConstants.ARQUEIRO_DEF, GameConstants.NIVEL_INICIAL);
    }

    public Arqueiro(Arqueiro outro) {
        super(outro);
    }

    @Override
    public int calcularAtaque() {
        return ataque + 6;
    }

    @Override
    public int calcularDefesa() {
        return defesa + 1;
    }

    @Override
    public String getClasse() {
        return "Arqueiro";
    }

    @Override
    public Arqueiro clone() {
        return new Arqueiro(this);
    }
}
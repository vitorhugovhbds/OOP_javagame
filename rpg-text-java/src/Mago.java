public class Mago extends Personagem {
    
    public Mago(String nome) {
        super(nome, GameConstants.MAGO_HP, GameConstants.MAGO_ATK, GameConstants.MAGO_DEF, GameConstants.NIVEL_INICIAL);
    }

    public Mago(Mago outro) {
        super(outro);
    }

    @Override
    public int calcularAtaque() {
        return ataque + 8;
    }

    @Override
    public int calcularDefesa() {
        return defesa + 2;
    }

    @Override
    public String getClasse() {
        return "Mago";
    }

    @Override
    public Mago clone() {
        return new Mago(this);
    }
}
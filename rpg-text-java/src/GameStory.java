
public class GameStory {
    
    // Introdução e história principal
    public static void mostrarIntroducao(String nomeJogador) {
        System.out.println("\n📖 === A JORNADA DA PEDRA DA ETERNIDADE ===");
        System.out.println("Você, " + nomeJogador + ", acorda em uma floresta misteriosa.");
        System.out.println("Lendas antigas falam sobre a Pedra da Eternidade,");
        System.out.println("um artefato capaz de conceder imenso poder.");
        System.out.println("Sua missão é encontrá-la antes que as forças das trevas o façam.");
        System.out.println("A aventura começa agora...");
        System.out.println("=============================================\n");
    }
    
    // Descrições das áreas
    public static void mostrarFlorestaMisteriosa() {
        System.out.println("\n🌲 === FLORESTA MISTERIOSA ===");
        System.out.println("Árvores anciãs sussurram segredos ao vento.");
        System.out.println("A luz do sol mal penetra a densa copa das árvores.");
        System.out.println("Sons estranhos ecoam entre os troncos milenares.");
        System.out.println("Você sente que não está sozinho aqui...");
    }
    
    public static void mostrarCavernaSombria() {
        System.out.println("\n🕳️ === CAVERNA SOMBRIA ===");
        System.out.println("Escuridão e umidade permeiam o ar pesado.");
        System.out.println("Goteiras ecoam como tambores na escuridão.");
        System.out.println("Olhos brilhantes observam você das profundezas.");
        System.out.println("Cuidado com o que pode estar escondido nas sombras...");
    }
    
    public static void mostrarCasteloAntigo() {
        System.out.println("\n🏰 === CASTELO ANTIGO ===");
        System.out.println("Torres imponentes desafiam o tempo e a erosão.");
        System.out.println("Estandartes em farrapos balançam com o vento.");
        System.out.println("O silêncio é quebrado apenas por seus passos nos corredores.");
        System.out.println("Segredos ancestrais aguardam por trás de cada porta...");
    }
    
    public static void mostrarSalaoDoTrono() {
        System.out.println("\n💎 === SALÃO DO TRONO ===");
        System.out.println("Mármore e ouro decoram o vasto salão.");
        System.out.println("No centro, um pedestal brilha com luz própria.");
        System.out.println("A Pedra da Eternidade pulsava com energia cósmica.");
        System.out.println("Mas grandes tesouros têm grandes guardiões...");
    }
    
    // Eventos e encontros
    public static void encontroComLobo() {
        System.out.println("\n🐺 Um Lobo da Floresta emerge dos arbustos!");
        System.out.println("Seus olhos brilham com fome e seus dentes afiados");
        System.out.println("reluzem sob os raios de sol que filtram pelas árvores.");
    }
    
    public static void encontroComGoblin() {
        System.out.println("\n👺 Um Goblin Guardião bloqueia seu caminho!");
        System.out.println("Armado com uma clava improvisada, ele rosna");
        System.out.println("enquanto protege seu território na caverna.");
    }
    
    public static void encontroComArmadura() {
        System.out.println("\n⚔️ Uma Armadura Animada ganha vida!");
        System.out.println("Metal range enquanto a armadura vazia se move");
        System.out.println("com propósito mortal, espada em punho.");
    }
    
    public static void encontroComGuardiao() {
        System.out.println("\n👁️ O Guardião Ancestral se materializa!");
        System.out.println("Uma forma etérea de pura energia mágica");
        System.out.println("toma forma para proteger o artefato sagrado.");
        System.out.println("A batalha final começa!");
    }
    
    // Descobertas e tesouros
    public static void encontrouBau() {
        System.out.println("\n🎁 Você encontrou um baú escondido!");
        System.out.println("Ele está coberto de poeira, mas intacto.");
        System.out.println("O que será que há dentro?");
    }
    
    public static void encontrouPocao() {
        System.out.println("\n🧪 Você encontrou uma poção brilhante!");
        System.out.println("Ela emana uma luz suave e aconchegante.");
        System.out.println("Parece ter propriedades curativas.");
    }
    
    public static void encontrouMapa() {
        System.out.println("\n🗺️ Você encontrou um mapa antigo!");
        System.out.println("Ele mostra a localização do Castelo Antigo");
        System.out.println("e rotas secretas através das montanhas.");
    }
    
    // Finais
    public static void finalVitoria() {
        System.out.println("\n✨ === VITÓRIA! ===");
        System.out.println("A Pedra da Eternidade brilha em suas mãos!");
        System.out.println("Sua energia curativa se espalha por todas as terras.");
        System.out.println("A paz retorna e você é aclamado como herói!");
        System.out.println("Sua jornada se tornará lenda para as futuras gerações.");
    }
    
    public static void finalDerrota() {
        System.out.println("\n💀 === FIM DA JORNADA ===");
        System.out.println("As trevas consomem as terras...");
        System.out.println("A Pedra da Eternidade permanece perdida.");
        System.out.println("Mas novas aventuras aguardam por outro herói.");
        System.out.println("Talvez em outra vida, outra chance...");
    }
    
    // Diálogos e interações
    public static void portaMistica() {
        System.out.println("\n🔮 A Porta das Runas Místicas");
        System.out.println("Runas antigas brilham suavemente na madeira.");
        System.out.println("Elas parecem convidar você a entrar...");
    }
    
    public static void portaBatalha() {
        System.out.println("\n⚔️ A Porta das Marcas de Batalha");
        System.out.println("Garras e marcas de espada cobrem a superfície.");
        System.out.println("Um aviso silencioso do perigo que aguarda...");
    }
    
    public static void descanso() {
        System.out.println("\n💤 Você encontra um local seguro para descansar.");
        System.out.println("O cansaço da jornada vai embora lentamente...");
    }
}
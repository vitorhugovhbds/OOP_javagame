
import java.util.Random;

public class Dice {
    private static final Random random = new Random();
    
    public static int roll(int lados) {
        return random.nextInt(lados) + 1;
    }
    
    public static int rollD20() {
        return roll(20);
    }
    
    public static int rollD10() {
        return roll(10);
    }
    
    public static int rollD6() {
        return roll(6);
    }
}
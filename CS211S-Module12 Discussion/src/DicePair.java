import java.util.Random;


public class DicePair extends Thread {
    private int numRolls;
    private int doublesCount;

    public DicePair(int numRolls) {
        this.numRolls = numRolls;
        this.doublesCount = 0;
    }

    @Override
    public void run() {
        Random rand = new Random();
        for (int i = 0; i < numRolls; i++) {
            int dice1 = rand.nextInt(6) + 1;
            int dice2 = rand.nextInt(6) + 1;
            if (dice1 == dice2) {
                doublesCount++;
            }
        }
    }

    public int getDoublesCount() {
        return doublesCount;
    }
}

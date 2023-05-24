import java.util.Random;

public class Coin {
   private int numFlips;
   private boolean heads;
   
   public Coin() {
      numFlips = 0;
      heads = true;
   }
   public int getNumFlips() { return numFlips; }
   public boolean isHeads() { return heads; }

   public void flip() {
      numFlips++;
      heads = (new Random()).nextBoolean();
   }
}

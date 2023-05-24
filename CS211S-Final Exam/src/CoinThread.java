public class CoinThread extends Thread {
   private Coin coin;

   public CoinThread(Coin coin) {
      this.coin = coin;
   }

   public void run() {
      coin.flip();
 }
   
   public static void main(String[] args) {
	      Coin coin = new Coin();
	      for(int i=0; i<100; i++) {
	         Thread thread = new CoinThread(coin);
	         thread.start();
	      }
	      System.out.println(coin.getNumFlips());
	   }
	}
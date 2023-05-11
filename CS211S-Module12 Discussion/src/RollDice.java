import java.util.Arrays;

public class RollDice {

	public static void main(String[] args) throws InterruptedException {
		int numRolls = 100000;
		int numPair  = 10;
		
		DicePair[] dice = new DicePair[numPair];
		for (int i = 0; i < numPair; i++) {
			dice[i] = new DicePair(numRolls);
		}
		// start all threads
		for (DicePair pair : dice) {
			pair.start();
		}
		// wait for all threads to finish
		for (DicePair pair : dice) {
			pair.join();
		}

		int[] doublesCount = new int[numPair];
		for (int i = 0; i < numPair; i++) {
			doublesCount[i] = dice[i].getDoublesCount();
		}
		
		int maxPairs = Arrays.stream(doublesCount).max().getAsInt();
		int minPairs = Arrays.stream(doublesCount).min().getAsInt();
		double avgPairs =  Arrays.stream(doublesCount).average().getAsDouble();
		
		System.out.println("After " + numRolls + " rolls for " + numPair + " dice:");
		System.out.printf("Max pairs: %6d Min pairs: %6d Average: %.2f", maxPairs, minPairs, avgPairs);
	}

}

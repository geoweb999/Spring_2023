import java.util.*;

public class PartTwoGenericMethodDriver {

	public static final boolean REMOVE_WINNER = true;
	public static final boolean DO_NOT_REMOVE_WINNER = !REMOVE_WINNER;
	public static final boolean ALLOW_DUPLICATES = true;
	public static final boolean NO_DUPLICATES = !ALLOW_DUPLICATES;
	private static boolean allTestsPassed = true;
	
	// YOUR GENERIC METHOD GOES HERE!
	// NOTE: the rest of this class will not compile until you write your 
	// generic method header (with at least an empty method body).

	public static <T> List<T> selectUniqueWinners(RandomDrawing<T> drawingList, int numWinners) {
		Set<T> winnersList = new HashSet<T>();
		if (numWinners > drawingList.size()) {
			numWinners = drawingList.size();
		}

		Object item;
		for (int i = 0; i < numWinners; i++) {
			boolean drawDone = false;
			while (!drawDone) {
				item = (T) drawingList.selectWinner(REMOVE_WINNER);
				if (winnersList.contains(item)) {
					drawDone = false;
					drawingList.addEntry((T) item);
				} else {
					drawDone = true;
					winnersList.add((T) item);
				}
			}
		}
		List<T> winners = new ArrayList<T>(winnersList);
		return winners;
	}

	public static void main(String[] args) {
		int numWinners = 0;
		
		RandomDrawing<String> stringDrawingWithDuplicates = new RandomDrawing<String>(ALLOW_DUPLICATES);
		fillDrawingWithEntries(stringDrawingWithDuplicates, "String drawing with duplicates");
		// parameter 2: the number of winners to draw
		// parameter 3: whether this drawing can successfully generate the required unique winner list
		testUniqueWinnersMethod(stringDrawingWithDuplicates, 5, true); 
		
		RandomDrawing<Integer> numberDrawingWithDuplicates = new RandomDrawing<Integer>(ALLOW_DUPLICATES);
		fillDrawingWithEntries(numberDrawingWithDuplicates, 100, "Integer drawing with duplicates");
		testUniqueWinnersMethod(numberDrawingWithDuplicates, 3, true);

		
		System.out.println("\n---------------TEST DESCRIPTION: Small drawing: added 3 unique entries and asked for 3 unique winners---------------");
		RandomDrawing<String> smallDrawingA = new RandomDrawing<String>(ALLOW_DUPLICATES);
		numWinners = 3;
		smallDrawingA.addEntry("Entry1");
		smallDrawingA.addEntry("Entry2");
		smallDrawingA.addEntry("Entry3");
        testUniqueWinnersMethod(smallDrawingA, numWinners, true);
        
        
		System.out.println("\n---------------TEST DESCRIPTION: Large size drawing: added multiple entries of 3 unique entries and asked for 3 unique winners---------------");
		RandomDrawing<String> smallDrawingB = new RandomDrawing<String>(ALLOW_DUPLICATES);
		numWinners = 3;
		for(int i=0; i<100; i++) {
			smallDrawingB.addEntry("Entry1");
			smallDrawingB.addEntry("Entry2");
		}
		smallDrawingB.addEntry("Entry3");
        testUniqueWinnersMethod(smallDrawingB, numWinners, true);

        
		System.out.println("\n---------------TEST DESCRIPTION: Smallish drawing: only 3 *unique* entries added, but each is added many times (meaining there are many duplicates)---------------");
		RandomDrawing<String> smallDrawingC = new RandomDrawing<String>(ALLOW_DUPLICATES);
		numWinners = 3;
		smallDrawingC.addEntry("Entry1");
		smallDrawingC.addEntry("Entry2");
		smallDrawingC.addEntry("Entry3");
		Random generator = new Random();
		for(int i=0; i<27; i++) {
			int entryNumber = generator.nextInt(3)+1;
			smallDrawingC.addEntry("Entry"+entryNumber);
		}
		testUniqueWinnersMethod(smallDrawingC, numWinners, true);
		
		
		System.out.println("\n---------------TEST DESCRIPTION: Not enough entries (3 entries, ask for 4 winners): Code should take some logical action but should NOT return a list with duplicate winners or enter an infinite loop.---------------");
		RandomDrawing<String> insufficientEntriesDrawing = new RandomDrawing<String>(ALLOW_DUPLICATES);
		numWinners = 4;
		insufficientEntriesDrawing.addEntry("Entry1");
		insufficientEntriesDrawing.addEntry("Entry2");
        insufficientEntriesDrawing.addEntry("Entry3");
        testUniqueWinnersMethod(insufficientEntriesDrawing, numWinners, false);
        // IMPORTANT NOTE: Depending on your solution, your program might end here.
        // If it does, you should then comment out this test above so you can run the rest of the driver. 

        
		System.out.println("\n---------------TEST DESCRIPTION: Not enough unique entries (3 unique entries, each added multiple times, ask for 4 winners): Code should take some logical action but should NOT return a list with duplicate winners or enter an infinite loop.---------------");
		RandomDrawing<String> insufficientUniqueEntriesDrawingA = new RandomDrawing<String>(ALLOW_DUPLICATES);
		numWinners = 4;
		insufficientUniqueEntriesDrawingA.addEntry("Entry1");
		insufficientUniqueEntriesDrawingA.addEntry("Entry2");
		insufficientUniqueEntriesDrawingA.addEntry("Entry3");
		generator = new Random();
		for(int i=0; i<27; i++) {
			int entryNumber = generator.nextInt(3)+1;
			insufficientUniqueEntriesDrawingA.addEntry("Entry"+entryNumber);
		}
        testUniqueWinnersMethod(insufficientUniqueEntriesDrawingA, numWinners, false);
        // IMPORTANT NOTE: Depending on your solution, your program might end here.
        // If it does, you should then comment out this test above so you can run the rest of the driver. 

		
		System.out.println("\n---------------TEST DESCRIPTION: Not enough unique entries (1 unique entry added multiple times, ask for 2 winners): Code should take some logical action but should NOT return a list with duplicate winners or enter an infinite loop.---------------");
		RandomDrawing<String> insufficientUniqueEntriesDrawingB = new RandomDrawing<String>(ALLOW_DUPLICATES);
		numWinners = 2;
		generator = new Random();
		for(int i=0; i<10; i++) {
			insufficientUniqueEntriesDrawingB.addEntry("Entry1");
		}
        testUniqueWinnersMethod(insufficientUniqueEntriesDrawingB, numWinners, false);
        // IMPORTANT NOTE: Depending on your solution, your program might end here.
        // If it does, you should then comment out this test above so you can run the rest of the driver. 

        
		System.out.println("\n---------------TEST DESCRIPTION: Empty Drawing: Code should take some logical action but should NOT enter an infinite loop.---------------");
		RandomDrawing<Integer> emptyDrawing = new RandomDrawing<Integer>(ALLOW_DUPLICATES);
		numWinners = 3;
        testUniqueWinnersMethod(emptyDrawing, numWinners, false);
        // IMPORTANT NOTE: Depending on your solution, your program might end here.
        // If it does, you should then comment out this test above so you can run the rest of the driver. 
	

		System.out.println("\n\n-----------------------------TESTING COMPLETE-----------------------------");
		if(allTestsPassed) {
			System.out.println("----------Summary---------- \nAll Part Two automated tests have passed.\nBe sure to manually review your code for style and efficiency.");
		} else {
			System.out.flush();
			System.err.println("**********Summary********** ERROR: There is failure in at least one automated test. Review the output above for details.");
		}
	}
	
	
	/*----------------------------------------------------------------------------------------------------------*/
	/* TESTER METHODS */
	/*----------------------------------------------------------------------------------------------------------*/
	/*
	 * The methods below are designed to help support the tests cases run from main. You don't
	 * need to use, modify, or understand these methods. You can safely ignore them. :) 
	 * 
	 */	
	public static void fillDrawingWithEntries(RandomDrawingInterface<?> box, String testDescription) {
		fillDrawingWithEntries(box, 0, testDescription);
	}

	public static void fillDrawingWithEntries(RandomDrawingInterface<?> box, int number, String testDescription) {
		PartOneRandomDrawingDriver.fillDrawingWithEntries(box, number, testDescription, false);	
	}
	
	public static void printTestingDescription(RandomDrawingInterface<?> drawing, int numWinners, boolean shouldPass) {
		System.out.println("Size of drawing = " + drawing.size());
		System.out.println("Unique winners requested = " + numWinners);
		System.out.println("Can this unique winner list be obtained? " + 
				(shouldPass ? "yes!" : "no- drawing is invalid for the request"));
		System.out.print("Entries: "); 
		drawing.displayEntries();
	}
	public static <T extends Comparable<? super T>> void testUniqueWinnersMethod(RandomDrawing<T> randomDrawing, int numWinners, boolean shouldPass) {
		printTestingDescription(randomDrawing, numWinners, shouldPass);

		System.out.println("\n-----Invoking the static unique winners method.");
		int expectedSize = randomDrawing.size() - numWinners;
		List<T> winners = selectUniqueWinners(randomDrawing, numWinners);
		if(shouldPass) {
			Collections.sort(winners); // for display purposes only
			System.out.println("Expected size after draw = " + expectedSize);
			System.out.println("  Actual size after draw = " + randomDrawing.size());
		
			if(randomDrawing.size()>expectedSize) {			
				System.out.println("*****TEST FAILED: the winners were not removed from the RandomDrawing object.");
				allTestsPassed = false;
			} else if(randomDrawing.size()<expectedSize) {
				System.out.println("*****TEST FAILED: entries that were not the winners were also removed from the RandomDrawing object.");
				allTestsPassed = false;
			}
		
		
			System.out.println("List of winners should contain " + numWinners + " unique winners: ");
			System.out.println(winners);
			if(winners.size()!=numWinners) {
				System.out.println("*****TEST FAILED: the method did not draw " + numWinners + " winners.");
				allTestsPassed = false;
			} else if(winners.stream().distinct().count()!=numWinners) {
				System.out.println("*****TEST FAILED: not all winners are unique.");
				allTestsPassed = false;
			}	
		} else { // !shouldPass
			System.out.println("Winners:\n" + winners);
		}
	}


}

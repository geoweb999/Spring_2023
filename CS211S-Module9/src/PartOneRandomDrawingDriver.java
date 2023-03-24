import java.util.*;

public class PartOneRandomDrawingDriver {

	public static final boolean REMOVE_WINNER = true;
	public static final boolean DO_NOT_REMOVE_WINNER = !REMOVE_WINNER;
	public static final boolean ALLOW_DUPLICATES = true;
	public static final boolean NO_DUPLICATES = !ALLOW_DUPLICATES;
	public static boolean allTestsPassed = true;

	public static void main(String[] args) {
		
		/*
		 * The code below adds entries to a RandomDrawing object. 
		 * Strings are added manually. Integers are chosen randomly
		 * and added. The code then invokes the testDrawMethod 
		 * method to test the functionality of your draw(boolean) method.
		 */
		RandomDrawing<String> stringDrawingWithDuplicates = new RandomDrawing<String>(ALLOW_DUPLICATES);
		fillDrawingWithEntries(stringDrawingWithDuplicates, "String drawing with duplicates allowed");
		testDrawMethod(stringDrawingWithDuplicates);

		RandomDrawing<String> stringDrawingWithNoDuplicates = new RandomDrawing<String>(NO_DUPLICATES);
		fillDrawingWithEntries(stringDrawingWithNoDuplicates, "String drawing with NO duplicates allowed");
		testDrawMethod(stringDrawingWithNoDuplicates);

		RandomDrawing<Integer> numberDrawingWithDuplicates = new RandomDrawing<Integer>(ALLOW_DUPLICATES);
		fillDrawingWithEntries(numberDrawingWithDuplicates, 100, "Integer drawing with duplicates allowed"); // the second parameter is the number of entries to add
		testDrawMethod(numberDrawingWithDuplicates);
		
		RandomDrawing<Integer> numberDrawingWithNoDuplicates = new RandomDrawing<Integer>(NO_DUPLICATES);
		fillDrawingWithEntries(numberDrawingWithNoDuplicates, 10, "Integer drawing with NO duplicates allowed");
		testDrawMethod(numberDrawingWithNoDuplicates);
	
		RandomDrawing<Integer> emptyIntegerDrawing = new RandomDrawing<Integer>(ALLOW_DUPLICATES);
		fillDrawingWithEntries(emptyIntegerDrawing, 0, "empty drawing");
		testDrawMethod(emptyIntegerDrawing);
 

		// SUGGESTION: CREATE A RANDOMBOX THAT HOLDS SOME OTHER TYPE- ANY CLASS YOU'VE GOT!
		// MANUALLY ADD ENTRIES TO THE BOX. THEN INVOKE THE testDrawMethod METHOD!
		// For example: 
		// RandomDrawing<Employee> employeeDrawing = new RandomDrawing<Employee>(NO_DUPLICATES);
		// employeeDrawing.add(new Employee(...));
		// employeeDrawing.add(new Employee(...));
		// employeeDrawing.add(new Employee(...));
		// testDrawMethod(employeeDrawing);


		System.out.println("\n\n-----------------------------TESTING COMPLETE-----------------------------");
		if(allTestsPassed && PartOneRandomDrawingDriver.allTestsPassed) {
			System.out.println("----------Summary---------- \nAll Part One automated tests have passed.\nBe sure to manually review your code for style and efficiency.");
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
	public static boolean duplicateTestPassed = true;
	public static void fillDrawingWithEntries(RandomDrawingInterface<?> box, String testDescription, boolean print) {
		fillDrawingWithEntries(box, 0, testDescription, print);
	}

	public static void fillDrawingWithEntries(RandomDrawingInterface<?> box, String testDescription) {
		fillDrawingWithEntries(box, 0, testDescription, true);
	}
	public static void fillDrawingWithEntries(RandomDrawingInterface<?> box, int number, String testDescription) {
		fillDrawingWithEntries(box, number, testDescription, true);
	}
	public static void fillDrawingWithEntries(RandomDrawingInterface<?> box, int number, String testDescription, boolean print) {
		System.out.println("\n---------------TEST DESCRIPTION: " + testDescription + "---------------");
		System.out.println("\n-----Adding entries.");
		if(testDescription.contains("String")) {
			addStringEntriesToDrawing((RandomDrawingInterface<String>)box, print);
		} else if(testDescription.contains("Integer")) {
			addIntegerEntriesToDrawing((RandomDrawingInterface<Integer>)box, number, print);
		} 
		
		if(print) {
			System.out.print("Entries: ");
			box.displayEntries();
		}
	
	}
	public static void addStringEntriesToDrawing(RandomDrawingInterface<String> wordBox, boolean print) {
		wordBox.addEntry("Adam Zapel"); wordBox.addEntry("Al Dente"); wordBox.addEntry("Al Fresco"); wordBox.addEntry("Al K. Seltzer"); wordBox.addEntry("Alf A. Romeo"); wordBox.addEntry("Amanda Lynn"); wordBox.addEntry("Anita Job"); wordBox.addEntry("Anna Conda"); wordBox.addEntry("Anna Graham"); wordBox.addEntry("Anna Prentice "); wordBox.addEntry("Anna Sasin"); wordBox.addEntry("Anne Teak"); wordBox.addEntry("B.A. Ware"); wordBox.addEntry("Barb Dwyer"); wordBox.addEntry("Barb E. Dahl"); wordBox.addEntry("Barbara Seville"); wordBox.addEntry("Barry Cade"); wordBox.addEntry("Bea Minor"); wordBox.addEntry("Dee Major"); wordBox.addEntry("Beau Tye"); wordBox.addEntry("Bill Board"); wordBox.addEntry("Cara Van"); wordBox.addEntry("Chris P. Bacon"); wordBox.addEntry("Constance Noring"); wordBox.addEntry("Crystal Ball"); wordBox.addEntry("Crystal Glass"); wordBox.addEntry("Earl Lee Riser"); wordBox.addEntry("Easton West "); wordBox.addEntry("Ferris Wheeler"); wordBox.addEntry("Flint Sparks"); wordBox.addEntry("Franklin Stein "); wordBox.addEntry("Gene Poole"); wordBox.addEntry("Heidi Clare"); wordBox.addEntry("Helen Back"); wordBox.addEntry("Helen Wiells"); wordBox.addEntry("Holly McRell"); wordBox.addEntry("Holly Wood"); wordBox.addEntry("Jack Pott"); wordBox.addEntry("Joe Kerr"); wordBox.addEntry("Joy Rider"); wordBox.addEntry("Justin Case"); wordBox.addEntry("Justin Time"); wordBox.addEntry("Kandi Apple"); wordBox.addEntry("Laura Norder"); wordBox.addEntry("Leigh King "); wordBox.addEntry("Luke Warm"); wordBox.addEntry("Marsha Mellow"); wordBox.addEntry("Marshall Law"); wordBox.addEntry("Marty Graw"); wordBox.addEntry("Olive Branch"); wordBox.addEntry("Paige Turner"); wordBox.addEntry("Pepe Roni"); wordBox.addEntry("Price Wright"); wordBox.addEntry("Rocky Beach"); wordBox.addEntry("Sandy Beach"); wordBox.addEntry("Sal A. Mander"); wordBox.addEntry("Stanley Cupp"); wordBox.addEntry("Tom Morrow"); wordBox.addEntry("Warren Peace"); wordBox.addEntry("Will Power"); wordBox.addEntry("X. Benedict");
		String repeatedEntry = "Pete Repeaty";
		wordBox.addEntry(repeatedEntry);
		duplicateTestPassed = true;
		for(int i=0; i<3; i++) {
			boolean repeatedAddResult = wordBox.addEntry(repeatedEntry);
			if(duplicateTestPassed && repeatedAddResult==true && !wordBox.allowsDuplicates()) {
				allTestsPassed = false;
				duplicateTestPassed = false;
			}
		}
		
		if(wordBox.allowsDuplicates() && wordBox.size()!=65) {
			allTestsPassed = false;
			System.out.println("*****TEST FAILED: box that allows duplicates did not accept all entries.\n");

		} else if(!wordBox.allowsDuplicates() && wordBox.size()!=62) {
			allTestsPassed = false;
			if(wordBox.size()<62 ) {
				System.out.println("*****TEST FAILED: box that does not allow duplicates did not accept all non-duplicate entries.\n");
			} else { //wordBox.size()>62
				System.out.println("*****TEST FAILED: box that does not allow duplicates accepted some duplicate entries.\n");
				
			}

		} else {
			if(print) {
				System.out.println("Items added successfully. wordBox.size()=" + wordBox.size());
			}
		}
	}
	public static void addIntegerEntriesToDrawing(RandomDrawingInterface<Integer> numbersBox, int numberOfNumbers, boolean print) {
		duplicateTestPassed = true;
		Random generator = new Random();
		List<Integer> fillList = new ArrayList<Integer>();
		int maxNumber;
		if(numbersBox.allowsDuplicates()) {
			maxNumber = numberOfNumbers * 10;
		} else {
			maxNumber = numberOfNumbers + 2;
		}
		int expectedSize = numberOfNumbers;
		for(int i=0; i<numberOfNumbers; i++) {
			int numberEntry = generator.nextInt(maxNumber);
			if(!numbersBox.allowsDuplicates() && fillList.contains(numberEntry)) {
				expectedSize--;
			}
			boolean addResult = numbersBox.addEntry(numberEntry);
			if(duplicateTestPassed && addResult==true && !numbersBox.allowsDuplicates() && fillList.contains(numberEntry)) {
				allTestsPassed = false;
				duplicateTestPassed = false;
			}
			fillList.add(numberEntry);			
		}
		
		if(numbersBox.size()!=expectedSize && print) {
			allTestsPassed = false;
			System.out.println("*****TEST FAILED: box does not contain the expected number of entries.");
		} else {
			if(print) {
				System.out.println("Items added successfully. numbersBox().size=" + numbersBox.size());
			}
		}
	}
	public static <T> void testDrawMethod(RandomDrawingInterface<T> randomDrawing) {
		System.out.println("\n-----Invoking the draw(boolean) method.");
		if(!duplicateTestPassed) {
			System.out.println("*****TEST FAILED: duplicates were added to a drawing that should not allow duplicates.\n");
			duplicateTestPassed = true;
		}
		int beforeSize = randomDrawing.size();
		int expectedSizeAfterRemove = beforeSize-1;
		if(expectedSizeAfterRemove<0) {
			expectedSizeAfterRemove = 0;
		}
		System.out.println("Before draw, size is " + beforeSize);
		T winner = randomDrawing.selectWinner(REMOVE_WINNER);
		System.out.println("Drawing, with removal. Winner = " + winner);			
		System.out.println("   Expected size after draw = " + expectedSizeAfterRemove);
		System.out.println("     Actual size after draw = " + randomDrawing.size());
		if(randomDrawing.size()!=expectedSizeAfterRemove) {
			System.out.println("*****TEST FAILED: incorrect drawing size after removal of winner.");
			allTestsPassed = false;
		}
		if(beforeSize>0 && winner==null) {
			System.out.println("*****TEST FAILED: winner is null.");
			allTestsPassed = false;
		}
		
		beforeSize = randomDrawing.size();
		winner = randomDrawing.selectWinner(DO_NOT_REMOVE_WINNER);
		System.out.println("Drawing, without removal. Winner = " + winner);			
		System.out.println("   Expected size after draw = " + expectedSizeAfterRemove);
		System.out.println("     Actual size after draw = " + randomDrawing.size());
		if(randomDrawing.size()!=expectedSizeAfterRemove) {
			System.out.println("*****TEST FAILED: incorrect drawing size after drawing of winner without removal.");
			allTestsPassed = false;
		}
		if(beforeSize>0 && winner==null) {
			System.out.println("*****TEST FAILED: winner is null.");
			allTestsPassed = false;
		}
	}
	

}

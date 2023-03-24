import java.util.*;

public class ExtraCreditDriver {

	public static final boolean REMOVE_WINNER = true;
	public static final boolean DO_NOT_REMOVE_WINNER = !REMOVE_WINNER;
	public static final boolean ALLOW_DUPLICATES = true;
	public static final boolean NO_DUPLICATES = !ALLOW_DUPLICATES;
	private static boolean allTestsPassed = true;
	
	public static void main(String[] args) {

		System.out.println("\n---------------TEST DESCRIPTION: Small capped drawing with repeats---------------");
		int drawingCapSize = 10;
		RandomDrawingInterface<Integer> smallCappedDrawing = new RandomDrawingCapped<Integer>(ALLOW_DUPLICATES, drawingCapSize);
		System.out.println("Adding 10 random (possibly duplicate) values to a drawing capped at 10. All values added?");

		Random generator = new Random();
		boolean allValuesAdded = true;
		for(int i=0; i<drawingCapSize; i++) {
			int entryNumber = generator.nextInt(drawingCapSize);
			boolean valueAdded = smallCappedDrawing.addEntry(entryNumber);
			if(!valueAdded) {
				allValuesAdded = false;
			}
		}
		System.out.println("Expected = true");
		System.out.println("  Actual = " + allValuesAdded);
		System.out.println("Expected size of drawing = " + drawingCapSize);
		System.out.println("  Actual size of drawing = " + smallCappedDrawing.size());
		if(!allValuesAdded || smallCappedDrawing.size()!=drawingCapSize) {
			System.out.println("*****TEST FAILED: " + drawingCapSize + " values should have been added.");
			allTestsPassed = false;
		}
		System.out.print("\nDrawing is now filled to capacity.");
		testDrawMethod(smallCappedDrawing); 
		
		System.out.println("\nRe-adding another entry so drawing is again filled to capacity.");
		smallCappedDrawing.addEntry(generator.nextInt(drawingCapSize));
		
		boolean addedToFullDrawing = false;
		for(int i=0; i<drawingCapSize; i++) {
			int entryNumber = generator.nextInt(drawingCapSize);
			boolean valueAdded = smallCappedDrawing.addEntry(entryNumber);
			if(valueAdded) {
				addedToFullDrawing = true;
			}
		}
		System.out.println("\nTrying to add more random (possibly duplicate) values to a full drawing capped at 10. Any values added?");
		System.out.println("Expected = false");
		System.out.println("  Actual = " + addedToFullDrawing);
		System.out.println("Expected size of drawing = " + drawingCapSize);
		System.out.println("  Actual size of drawing = " + smallCappedDrawing.size());
		if(addedToFullDrawing || smallCappedDrawing.size()!=drawingCapSize) {
			System.out.println("*****TEST FAILED: more than " + drawingCapSize + " values were added to the drawing that was capped at 10.");
			allTestsPassed = false;
		}
		System.out.println("\nDrawing is now filled to capacity.");
		testDrawMethod(smallCappedDrawing);


		
		drawingCapSize = 100;
		RandomDrawingInterface<Integer> largeCappedDrawing = new RandomDrawingCapped<Integer>(NO_DUPLICATES, drawingCapSize);
		fillDrawingWithEntries(largeCappedDrawing, 1000, "Large capped Integer drawing without repeats");
		System.out.println("Expected size of drawing = " + drawingCapSize);
		System.out.println("  Actual size of drawing = " + largeCappedDrawing.size());
		if(largeCappedDrawing.size()!=drawingCapSize) {
			System.out.println("*****TEST FAILED: size should be capped at " + drawingCapSize);
			allTestsPassed = false;
		}
		System.out.println("\nDrawing is now filled to capacity.");
		testDrawMethod(largeCappedDrawing);

		
		System.out.println("\n\n-----------------------------TESTING COMPLETE-----------------------------");
		if(allTestsPassed ) {
			System.out.println("----------Summary---------- \nAll Extra Credit automated tests have passed.\nBe sure to manually review your code for style and efficiency.");
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

	public static void fillDrawingWithEntries(RandomDrawingInterface<?> box, int number, String testDescription) {
		PartOneRandomDrawingDriver.fillDrawingWithEntries(box, number, testDescription, false);	
	}
	
	public static <T> void testDrawMethod(RandomDrawingInterface<T> randomDrawing) {
		PartOneRandomDrawingDriver.testDrawMethod(randomDrawing);
	}



}

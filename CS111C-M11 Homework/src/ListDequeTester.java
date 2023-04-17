import java.util.Arrays;

public class ListDequeTester {

	public static void main(String[] args) {
	
		ListDeque<String> wordDeque = new ListDeque<String>();
		System.out.println("Checking with an empty deque:");
		testRemoveFrontOnEmpty(wordDeque);
		testRemoveBackOnEmpty(wordDeque);
		testGetFrontOnEmpty(wordDeque);
		testGetBackOnEmpty(wordDeque);

		System.out.println("\n----------IMPORTANT NOTE: There are no automated tests from this point on. You muse compare the output manually.");
		// parameter 1: the deque
		// parameter 2: the word to add
		// parameter 3: the expected contents of the deque after the add
		testAddToFront(wordDeque, 	"amps", 	"[amps]");
		testAddToFront(wordDeque, 	"bank", 	"[bank, amps]");
		testAddToBack(wordDeque, 	"card", 	"[bank, amps, card]");
		testAddToFront(wordDeque, 	"door", 	"[door, bank, amps, card]");
		testAddToBack(wordDeque, 	"ever",	 	"[door, bank, amps, card, ever]");

		// parameter 1: the deque
		// parameter 2: the expected result of the remove/get
		// parameter 3: the expected contents of the queue after the remove/get
		testGetFront(wordDeque, 		"door",		"[door, bank, amps, card, ever]");
		testGetBack(wordDeque, 			"ever",		"[door, bank, amps, card, ever]");
		testRemoveFromBack(wordDeque, 	"ever", 	"[door, bank, amps, card]");
		testRemoveFromBack(wordDeque, 	"card", 	"[door, bank, amps]");
		testRemoveFromBack(wordDeque, 	"amps",	 	"[door, bank]");		
		testRemoveFromFront(wordDeque, 	"door", 	"[bank]");		

		testAddToBack(wordDeque, 		"frog", 	"[bank, frog]");
		testAddToFront(wordDeque, 		"golf", 	"[golf, bank, frog]");
		testGetFront(wordDeque, 		"golf",		"[golf, bank, frog]");
		testGetBack(wordDeque, 			"frog",		"[golf, bank, frog]");
		
		testRemoveFromBack(wordDeque, 	"frog", 	"[golf, bank]");
		testAddToFront(wordDeque,	 	"hats",	 	"[hats, golf, bank]");
		testAddToBack(wordDeque, 		"joke", 	"[hats, golf, bank, joke]");
		testAddToBack(wordDeque, 		"laugh", 	"[hats, golf, bank, joke, laugh]");
		testAddToFront(wordDeque, 		"iris", 	"[iris, hats, golf, bank, joke, laugh]");
		
		testRemoveFromFront(wordDeque, 	"iris", 	"[hats, golf, bank, joke, laugh]");		
		testRemoveFromFront(wordDeque, 	"hats", 	"[golf, bank, joke, laugh]");
		testRemoveFromFront(wordDeque, 	"golf", 	"[bank, joke, laugh]");
		testRemoveFromFront(wordDeque, 	"bank", 	"[joke, laugh]");
		testRemoveFromBack(wordDeque, 	"laugh", 	"[joke]");
		testGetFront(wordDeque, 		"joke",		"[joke]");
		testGetBack(wordDeque, 			"joke",		"[joke]");
		testRemoveFromBack(wordDeque, 	"joke", 	"[]");
		
	}
	
	/*----------------------------------------------------------------------------------------------------------*/
	/* TESTER METHODS */
	/*----------------------------------------------------------------------------------------------------------*/
	/*
	 * The methods below are designed to help support the tests cases run from main. You don't
	 * need to use, modify, or understand these methods. You can safely ignore them. :) 
	 * 
	 * Also, you can ignore the use of generics in the tester methods. These methods use
	 * generics at a level beyond which we use in our class. I only use them here to make this a robust 
	 * and useful testing file. You are NOT required to understand the use of generics in this way.
	 */

	
	private static void testAddToFront(ListDeque<String> wordDeque, String wordToAdd, String expectedResult) {
		System.out.println("\nAdding to front: " + wordToAdd);
		wordDeque.addToFront(wordToAdd);
		System.out.println("Expected deque contents: " + expectedResult);
		System.out.println("  Actual deque contents: " + wordDeque.list);
	}
	
	private static void testAddToBack(ListDeque<String> wordDeque, String wordToAdd, String expectedResult) {
		System.out.println("\nAdding to back: " + wordToAdd);
		wordDeque.addToBack(wordToAdd);
		System.out.println("Expected deque contents: " + expectedResult);
		System.out.println("  Actual deque contents: " + wordDeque.list);	
	}
	
	private static void testRemoveFromFront(ListDeque<String> wordDeque, String expectedRemovedWord, String expectedResult) {
		testRemoveGetFromNonEmpty(wordDeque, expectedRemovedWord, expectedResult, "Removing from front:", true, true);
	}
	private static void testRemoveFromBack(ListDeque<String> wordDeque, String expectedRemovedWord, String expectedResult) {
		testRemoveGetFromNonEmpty(wordDeque, expectedRemovedWord, expectedResult, "Removing from back:", false, true);
	}
	private static void testGetFront(ListDeque<String> wordDeque, String expectedWord, String expectedResult) {
		testRemoveGetFromNonEmpty(wordDeque, expectedWord, expectedResult, "Getting front:", true, false);
	}

	private static void testGetBack(ListDeque<String> wordDeque, String expectedWord, String expectedResult) {
		testRemoveGetFromNonEmpty(wordDeque, expectedWord, expectedResult, "Getting back:", false, false);
	}

	private static void testRemoveGetFromNonEmpty(ListDeque<String> wordDeque, String expectedWord, String expectedResult, 
			String message, boolean front, boolean remove) {
		
		System.out.println("\n" + message);
		String actualWord;
		if(front && remove) {
			actualWord = wordDeque.removeFront();
		} else if(!front && remove) {
			actualWord = wordDeque.removeBack();
		} else if(front && !remove) {
			actualWord = wordDeque.getFront();
		} else { // !front && !remove
			actualWord = wordDeque.getBack();
		}
		System.out.println("Expected returned result: " + expectedWord);
		System.out.println("  Actual returned result: " + actualWord);
		System.out.println("Expected deque contents: " + expectedResult);
		System.out.println("  Actual deque contents: " + wordDeque.list);

	}

	private static void testRemoveFrontOnEmpty(ListDeque<String> wordDeque) {
		testRemoveOnEmpty(wordDeque, "removeFront", true, true);
	}
	private static void testRemoveBackOnEmpty(ListDeque<String> wordDeque) {
		testRemoveOnEmpty(wordDeque, "removeBack", false, true);
	}
	private static void testGetFrontOnEmpty(ListDeque<String> wordDeque) {
		testRemoveOnEmpty(wordDeque, "getFront", true, false);
	}
	private static void testGetBackOnEmpty(ListDeque<String> wordDeque) {
		testRemoveOnEmpty(wordDeque, "getBack", false, false);
	}
	private static void testRemoveOnEmpty(ListDeque<String> wordDeque, String type, boolean front, boolean remove) {
		try {
			String removed;
			if(front && remove) {
				removed = wordDeque.removeFront();
			} else if(!front && remove) {
				removed = wordDeque.removeBack();
			} else if(!front && remove) {
				removed = wordDeque.getFront();
			} else { // !front && !remove
				removed = wordDeque.getBack();
			}
			
			if(removed==null) {
				System.out.println("\n*****WARNING****");
				System.out.println(type + " does find the deque empty, but check the interface to see what should happen when removing from an empty queue.");
				System.out.println("the method returned null");
			} else {
				System.out.println("\n*****TEST FAILED****");
				System.out.println(type + " incorrectly finds deque not empty");
			}
		} catch(EmptyQueueException ex) {
			System.out.println("\n" + type + " correctly finds deque empty");
		} catch(Exception ex) {
			System.out.println("\n*****WARNING****");
			System.out.println(type + " does find the deque empty, but check the interface to see what type of exception should be thrown.");
			System.out.println("the method threw: " + ex.getClass().getName());
		}
	}

}

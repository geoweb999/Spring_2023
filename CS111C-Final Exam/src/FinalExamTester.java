import java.util.*;

public class FinalExamTester {

	private static boolean allTestsPassed = true; 
	
	public static void main(String[] args) {
		System.out.println("----------------------------TESTING LINKED QUEUE CONSECUTIVE DUPLICATES----------------------------");
		// parameter 1: the contents of the queue, listed front to back 
		//		        for example, [1, 2, 3] means 1 is the front of the queue and 3 is the back
		// parameter 2: the expected result: true if there are consecutive duplicates, false otherwise
		// parameter 3: a description of the test
		testQueueDuplicates(new Integer[] {}, 				false, 	"empty queue");
		testQueueDuplicates(new Integer[] {3}, 				false, 	"singleton queue");
		testQueueDuplicates(new Integer[] {1, 2}, 			false, 	"even number of unique elements");
		testQueueDuplicates(new Integer[] {4, 5, 6}, 		false, 	"odd number of unique elements");
		testQueueDuplicates(new Integer[] {2, 5, 2}, 		false, 	"duplicates that are not consecutive");
		testQueueDuplicates(new Integer[] {7, 8, 7, 8}, 	false, 	"duplicates that are not consecutive");
		testQueueDuplicates(new Integer[] {1, 2, 1, 4, 4}, 	true,	"consecutive duplicates at the back");
		testQueueDuplicates(new Integer[] {2, 5, 3, 3}, 	true,	"consecutive duplicates at the back");
		testQueueDuplicates(new Integer[] {6, 6, 2}, 		true, 	"consecutive duplicates at the front");
		testQueueDuplicates(new Integer[] {3, 3, 5, 1}, 	true, 	"consecutive duplicates at the front");
		testQueueDuplicates(new Integer[] {6, 6, 2, 6}, 	true, 	"consecutive duplicates at the front");
		testQueueDuplicates(new Integer[] {1, 3, 5, 5, 2}, 	true, 	"consecutive duplicates in the middle");
		testQueueDuplicates(new Integer[] {1, 2, 5, 5, 2, 1}, true, "consecutive duplicates in the middle");
		testQueueDuplicates(new Integer[] {6, 6}, 			true,	"consecutive duplicates in a 2-element queue");
		testQueueDuplicates(new String[] {new String("hi"), new String("hi")}, true, "testing with Strings");
		
		
		System.out.println("\n\n----------------------------TESTING ARRAY STACK CONSECUTIVE DUPLICATES----------------------------");
		// parameter 1: the contents of the stack, listed bottom to top 
		//		        for example, [1, 2, 3] means 1 is on the bottom of the stack and 3 is at the top
		// parameter 2: the expected result: true if there are consecutive duplicates, false otherwise
		// parameter 3: a description of the test
		testStackDuplicates(new Integer[] {},				false, "empty stack");
		testStackDuplicates(new Integer[] {3},				false, "singleton stack");
		testStackDuplicates(new Integer[] {1, 2}, 			false, 	"even number of unique elements");
		testStackDuplicates(new Integer[] {4, 5, 6}, 		false, 	"odd number of unique elements");
		testStackDuplicates(new Integer[] {2, 5, 2}, 		false, 	"duplicates that are not consecutive");
		testStackDuplicates(new Integer[] {7, 8, 7, 8}, 	false, 	"duplicates that are not consecutive");
		testStackDuplicates(new Integer[] {1, 2, 5, 2}, 	false, 	"having a full behind-the-scenes \"stack\" array");
		testStackDuplicates(new Integer[] {6, 3, 5, 5}, 	true, 	"having a full behind-the-scenes \"stack\" array with duplicates in the last places of the array");
		testStackDuplicates(new Integer[] {1, 2, 1, 4, 4}, 	true,	"consecutive duplicates at the top");
		testStackDuplicates(new Integer[] {2, 5, 3, 3}, 	true,	"consecutive duplicates at the top");
		testStackDuplicates(new Integer[] {6, 6, 2}, 		true, 	"consecutive duplicates at the bottom");
		testStackDuplicates(new Integer[] {3, 3, 5, 1}, 	true, 	"consecutive duplicates at the bottom");
		testStackDuplicates(new Integer[] {6, 6, 2, 6}, 	true, 	"consecutive duplicates at the bottom");
		testStackDuplicates(new Integer[] {1, 3, 5, 5, 2}, 	true, 	"consecutive duplicates in the middle");
		testStackDuplicates(new Integer[] {1, 2, 5, 5, 2, 1}, true, "consecutive duplicates in the middle");
		testStackDuplicates(new Integer[] {6, 6}, 			true,	"consecutive duplicates in a 2-element stack");
		testStackDuplicates(new String[] {new String("hi"), new String("hi")}, true, "testing with Strings");
		
		System.out.println("\n\n-----------------------------TESTING COMPLETE-----------------------------");
		if(allTestsPassed) {
			System.out.println("----------Summary---------- \nAll automated tests have passed. \nBe sure to manually look at the output.\nBe sure to manually review your code for style and efficiency.");
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
	 * Also, you can ignore the use of generics in the tester methods. These methods use
	 * generics at a level beyond which we use in our class. I only use them here to make this a robust 
	 * and useful testing file. You are NOT required to understand the use of generics in this way.
	 */	
	public static <T> void testQueueDuplicates(T[] contents, boolean expectedResult, String testDescription) {
		LinkedQueue<T> queue = new LinkedQueue<T>();
		for(T element : contents) {
			queue.enqueue(element);
		}
		System.out.println("\nTesting queue:   FRONT*" + Arrays.toString(contents) + "*BACK");
		boolean actualResult = queue.hasConsecutiveDuplicates();
		System.out.println("Expected result: " + expectedResult);
		System.out.println("  Actual result: " + actualResult);
		if(actualResult!=expectedResult) {
			allTestsPassed = false;
			System.out.println("*****TEST FAILED for test: " + testDescription);
		}
		boolean queueUnchanged = true;
		if(queue.isEmpty() && contents.length>0) {
			queueUnchanged = false;
		}
		String afterQueueContents = "FRONT*[";
		int i=0;
		for(; i<contents.length && !queue.isEmpty() && queueUnchanged; i++) {
			T dequeuedElement = queue.dequeue();
			afterQueueContents += dequeuedElement.toString() + ", ";
			if(!contents[i].equals(dequeuedElement) ) {
				queueUnchanged = false;
			}
		}
		if(i<contents.length && queue.isEmpty()) {
			queueUnchanged = false;
		}
		if(!queueUnchanged || !queue.isEmpty()) {
			while(!queue.isEmpty()) {
				afterQueueContents +=  queue.dequeue().toString() + ", ";
			}
			if(afterQueueContents.length()>7) {
				afterQueueContents = afterQueueContents.substring(0, afterQueueContents.length()-2);
			}
			afterQueueContents += "]*BACK";
			allTestsPassed = false;
			System.out.println("*****TEST FAILED: queue was changed during method call. Queue after method call: ");
			System.out.println("                 " + afterQueueContents);
		}
	}
	
	public static <T> void testStackDuplicates(T[] contents, boolean expectedResult, String testDescription) {
		ArrayStack<T> stack;
		if(testDescription.indexOf("having a full behind-the-scenes")>=0) {
			stack = new ArrayStack<T>(4);
		} else {
			stack = new ArrayStack<T>();
		}
		for(T element : contents) {
			stack.push(element);
		}
		System.out.println("\nTesting stack:   BOTTOM*" + Arrays.toString(contents) + "*TOP");
		boolean actualResult = stack.hasConsecutiveDuplicates();
		System.out.println("Expected result: " + expectedResult);
		System.out.println("  Actual result: " + actualResult);
		if(actualResult!=expectedResult) {
			allTestsPassed = false;
			System.out.println("*****TEST FAILED for test: " + testDescription);
		}
		boolean stackUnchanged = true;
		if(stack.isEmpty() && contents.length>0) {
			stackUnchanged = false;
		} 
		String afterStackContents = "BOTTOM*[";
		int i=contents.length-1;
		for( ; i>=0 && !stack.isEmpty() && stackUnchanged; i--) {
			T poppedElement = stack.pop();
			afterStackContents += poppedElement.toString() + ", ";
			if(!contents[i].equals(poppedElement) ) {
				stackUnchanged = false;
			}
		}
		if(i>=0 && stack.isEmpty()) {
			stackUnchanged = false;
		}
		if(!stackUnchanged || !stack.isEmpty()) {
			while(!stack.isEmpty()) {
				afterStackContents +=  stack.pop().toString() + ", ";
			}
			if(afterStackContents.length()>8) {
				afterStackContents = afterStackContents.substring(0, afterStackContents.length()-2);
			}
			afterStackContents += "]*TOP";
			allTestsPassed = false;
			System.out.println("*****TEST FAILED: stack was changed during method call. Stack after method call: ");
			System.out.println("                 " + afterStackContents);
		}
	}
}

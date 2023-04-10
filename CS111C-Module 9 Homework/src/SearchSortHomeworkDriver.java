import java.text.NumberFormat;
import java.util.Arrays;

@SuppressWarnings({"rawtypes", "unchecked"})
public class SearchSortHomeworkDriver {

	public static double sortedness(Comparable[] array) {
		// check empty and singleton cases
		if (array.length <= 1) {
			return 1;
		}
		double numPairs = array.length - 1;
		double countSorted = 0;
		for (int i = 0; i < numPairs; i++) {
			countSorted += (array[i].compareTo(array[i+1]) <= 0) ? 1 : 0;
		}
		return countSorted / numPairs; 
	}
	
	public static double sortedness(Node<Comparable> node) {
		if (node == null || node.next == null) {
			return 1;
		}
		double numPairs = 0;
		Node<Comparable> current = node;
		// count number of pairs
		while (current.next != null) {
			numPairs++;
			current = current.next;
		}
		return sortednessHelper(node) / numPairs;
	}
	
	public static double sortednessHelper(Node<Comparable> node) {
		if (node == null || node.next == null) {
			return 0;
		}
		if (node.data.compareTo(node.next.data) <= 0) {
			return 1 + sortednessHelper(node.next);
		} else {
			return 0 + sortednessHelper(node.next);
		}
	}

	private static boolean allTestsPassed = true;
	
	public static void main(String[] args) {

		
		System.out.println("-----------------------------Array Sortedness Tester-----------------------------");
		// parameter 1: the array
		// parameter 2: the expected sortedness, as a double (e.g., 1 means 100% sorted; 0.5 means 50% sorted)
		// parameter 3: a description of the test
		testArraySortedness(new Integer[]{1, 2, 3, 3, 5}, 		1,   "sorted odd length of integers");
		testArraySortedness(new Integer[]{1, 2, 3, 4, 5, 5}, 	1,   "sorted even length of integers");
		testArraySortedness(new Integer[]{10, 8, 5, 3, 1}, 		0,   "unsorted odd length of integers");
		testArraySortedness(new Integer[]{12, 11, 9, 3, 2, 1}, 	0,   "unsorted even length of integers");
		testArraySortedness(new Integer[]{2, 8, 3, 9, 6}, 		0.5, "partially sorted odd length of integers");
		testArraySortedness(new Integer[]{7, 1, 6, 8, 5, 2}, 	0.4, "partially sorted even length of integers");
		testArraySortedness(new String[]{"abc", "def", "ghi"}, 	1,   "sorted odd length of Strings");
		testArraySortedness(new String[]{"cat", "ant", "dog", "egg"}, 0.66667, "partially sorted even length of Strings");
		testArraySortedness(new String[]{"blue"}, 				1,   "singleton array");
		testArraySortedness(new String[]{}, 					1,   "empty array");

		System.out.println("\n-----------------------------Node Sortedness Tester-----------------------------");
		// parameter 1: the contents of the chain
		//              note: these are the same values as the arrays above
		//              note: the array {1, 2, 3} represents the chain 1->2->3
		// parameter 2: the expected sortedness, as a double (e.g., 1 means 100% sorted; 0.5 means 50% sorted)
		// parameter 3: a description of the test
		testLinkedNodeSortedness(new Integer[]{1, 2, 3, 3, 5}, 			1,   "sorted odd length of integers");
		testLinkedNodeSortedness(new Integer[]{1, 2, 3, 4, 5, 5}, 		1,   "sorted even length of integers");
		testLinkedNodeSortedness(new Integer[]{10, 8, 5, 3, 1}, 		0,   "unsorted odd length of integers");
		testLinkedNodeSortedness(new Integer[]{12, 11, 9, 3, 2, 1}, 	0,   "unsorted even length of integers");
		testLinkedNodeSortedness(new Integer[]{2, 8, 3, 9, 6}, 			0.5, "partially sorted odd length of integers");
		testLinkedNodeSortedness(new Integer[]{7, 1, 6, 8, 5, 2}, 		0.4, "partially sorted even length of integers");
		testLinkedNodeSortedness(new String[]{"abc", "def", "ghi"}, 	1,   "sorted odd length of Strings");
		testLinkedNodeSortedness(new String[]{"cat", "ant", "dog", "egg"}, 0.66667, "partially sorted even length of Strings");
		testLinkedNodeSortedness(new String[]{"blue"}, 					1,   "singleton array");
		testLinkedNodeSortedness(new String[]{}, 						1,   "empty array");
		
		System.out.println("\n\n-----------------------------TESTING COMPLETE-----------------------------");
		if(allTestsPassed) {
			System.out.println("----------Summary---------- \nAll automated tests have passed.\nBe sure to manually review your code for style and efficiency.");
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

	public static <T extends Comparable<? super T>> void testArraySortedness(T[] array, double expectedSortedness, String testDescription) {
		NumberFormat formatter = NumberFormat.getPercentInstance();
		System.out.println("\nSortedness of " + Arrays.toString(array));
		double actualSortedness = sortedness(array);
		System.out.println("Expected sortedness = " + formatter.format(expectedSortedness));
		System.out.println("  Actual sortedness = " + formatter.format(actualSortedness));
		if(Math.abs(expectedSortedness - actualSortedness) > 0.00001) {
			allTestsPassed = false;
			System.out.println("*****TEST FAILED: " + testDescription);
		}
	}

	public static <T extends Comparable<? super T>> void testLinkedNodeSortedness(T[] array, double expectedSortedness, String testDescription) {
		NumberFormat formatter = NumberFormat.getPercentInstance();
		Node<Comparable> chain = null;
		Node<Comparable> previous = null;
		for(int i=array.length-1; i>=0; i--) {
			Node<Comparable> node;
			if(i==array.length-1) {
				node = new Node<Comparable>(array[i]);
			} else {
				node = new Node<Comparable>(array[i], previous);
			}
			previous = node;
		}
		chain = previous;
		
		System.out.print("\nSortedness of ");
		for(int i=0; i<array.length; i++) {
			T value = array[i];
			if(i < array.length-1) {
				System.out.print(value + "->");
			} else {
				System.out.println(value);
			}
		}
		if(array.length==0) {
			System.out.println("<empty chain>");
		}
		double actualSortedness = sortedness(chain);
		System.out.println("Expected sortedness = " + formatter.format(expectedSortedness));
		System.out.println("  Actual sortedness = " + formatter.format(actualSortedness));
		if(Math.abs(expectedSortedness - actualSortedness) > 0.00001) {
			allTestsPassed = false;
			System.out.println("*****TEST FAILED: " + testDescription);
		}
	}
	
	
}
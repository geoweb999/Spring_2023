import java.util.*;

public class MidtermExamTester {

	public static void addDuplicateNeighbors(ListFromOne<String> list) {
		int index = 1;
		while (index <= list.size()) {
			// note: size will increase by 1 each time we add a dupe element
			list.add(index + 1, list.get(index));
			index += 2; // jump past newly added element
		}
	}

	private static boolean allTestsPassed = true;
	
	public static void main(String[] args) {
		System.out.println("-----------------------------TESTING LINKEDMULTISET DOUBLE METHOD-----------------------------");
		// parameter 1: the contents of the set
		// parameter 2: a description of the test
		testDuplicateTheSet(new Integer[] {1, 2, 3}, 			"odd length set");
		testDuplicateTheSet(new Integer[] {4, 5, 6, 7}, 		"even length set");
		testDuplicateTheSet(new Integer[] {4, 4, 4}, 			"odd length set with duplicates");
		testDuplicateTheSet(new Integer[] {3, 4, 5, 4, 3}, 		"odd length set with duplicates");
		testDuplicateTheSet(new Integer[] {1, 1, 2, 2, 3, 3}, 	"even length set with duplicates");
		testDuplicateTheSet(new Integer[] {1, 2, 3, 1, 2, 3}, 	"even length set with duplicates");
		testDuplicateTheSet(new Integer[] {}, 					"empty set");
		testDuplicateTheSet(new Integer[] {4}, 					"singleton set");
		testDuplicateTheSet(new String[]  {"a", "b", "c"}, 		"test with Strings");

		
		System.out.println("\n\n-----------------------------TESTING LISTFROMONE CLIENT DUPLICATE NEIGHBOR METHOD-----------------------------");
		// parameter 1: the contents of the list
		// parameter 2: a description of the test
		testAddDuplicateNeighbors(new String[] {"a", "b", "c"}, 	 "odd length list");
		testAddDuplicateNeighbors(new String[] {"a", "b", "c", "d"}, "even length list");
		testAddDuplicateNeighbors(new String[] {"a", "b", "a", "b"}, "list with repeats");
		testAddDuplicateNeighbors(new String[] {"a", "b", "b", "a", "a"}, "list with neighbor repeats");
		testAddDuplicateNeighbors(new String[] {"a"}, 				 "singleton list");
		testAddDuplicateNeighbors(new String[] {}, 					 "empty list");

		
		System.out.println("\n\n-----------------------------TESTING ARRAYLISTFROMONE ADDTOFRONT METHOD-----------------------------");
		// parameter 1: the contents of the list
		// parameter 2: the chain contents to add to the front (e.g., {1, 2, 3} is the chain 1->2->3
		// parameter 3: a description of the test
		testAddToFront(new Integer[] {1, 2, 3, 4, 5}, 	new Integer[] {10, 11, 12}, 	"odd length chain being added to front of odd length list");
		testAddToFront(new Integer[] {1, 2, 3, 4}, 		new Integer[] {10, 11, 12, 13}, "even length chain being added to front of even length list");
		testAddToFront(new Integer[] {1, 2, 4, 2}, 		new Integer[] {2, 4, 2, 1}, 	"chain with duplicates being added to front of list with duplicates");
		testAddToFront(new Integer[] {1}, 				new Integer[] {8}, 				"singleton chain being added to front of singleton list");
		testAddToFront(new Integer[] {}, 				new Integer[] {5, 6, 7, 8}, 	"even length chain being added to front of empty list");		
		testAddToFront(new Integer[] {}, 				new Integer[] {9, 10, 11}, 		"odd length chain being added to front of empty list");
		testAddToFront(new Integer[] {1, 2, 3, 4}, 		new Integer[] {}, 				"empty chain being added to front of even length list");
		testAddToFront(new Integer[] {1, 2, 3, 4, 5}, 	new Integer[] {}, 				"empty chain being added to front of odd length list");
		testAddToFront(new Integer[] {}, 				new Integer[] {}, 				"empty chain being added to front of empty list");
		testAddToFront(new String[] {"w", "o", "r", "k"}, new String[] {"n", "i", "c", "e"}, "test with Strings");
		testAddToFront(new Integer[] {2, 2, 2, 2, 2, 2},  new Integer[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, },
																						"long chain that will require the list array to expand");							
		System.out.println("\n\n-----------------------------TESTING COMPLETE-----------------------------");
		if(allTestsPassed) {
			System.out.println("----------Summary---------- \nAll automated tests have passed. \nManually look at the output to confirm results.\nManually review your code for style and efficiency.");
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

	public static <T> LinkedMultiset<T> createCopySet(LinkedMultiset<T> set) {
		LinkedMultiset<T> copy = new LinkedMultiset<T>();
		LinkedMultiset<T> tmp = new LinkedMultiset<T>();
		
		while(!set.isEmpty()) {
			T element = set.remove();
			copy.add(element);
			tmp.add(element);
		}
		while(!tmp.isEmpty()) {
			set.add(tmp.remove());
		}
		return copy;
	}
	public static <T> T[] setToArray(LinkedMultiset<T> set) {
		LinkedMultiset<T> copy = createCopySet(set);
		T[] array = (T[]) new Comparable[set.size()];
		int i = 0;
		while(!copy.isEmpty()) {
			array[i] = copy.remove();
			i++;
		}
		return array;
	}
	public static <T> void testDuplicateTheSet(T[] originalMultisetContents, String testDescription) {
		LinkedMultiset<T> actualMultiset = new LinkedMultiset<T>();
		LinkedMultiset<T> duplicatedMultiset = new LinkedMultiset<T>();
		int originalSize = originalMultisetContents.length;
		int expectedDoubleSize = originalSize*2;
		for(T element : originalMultisetContents) {
			actualMultiset.add(element);
			duplicatedMultiset.add(element);
			duplicatedMultiset.add(element);
		}
		actualMultiset.duplicateContents();
		int actualDuplicatedSize = actualMultiset.size();
		T[] duplicatedMultisetArray = setToArray(duplicatedMultiset);
		T[] actualMultisetArray = null;
		try {
			actualMultisetArray = setToArray(actualMultiset);
		} catch(ArrayIndexOutOfBoundsException ex) {
			allTestsPassed = false;
			System.out.println("**********TEST FAILED. Test crashed with an exception. The number of nodes in the chain does not match numberOfEntries.");
			return;

		}
		boolean match = true;
		while(!duplicatedMultiset.isEmpty()) {
			T element = duplicatedMultiset.remove();
			if(!actualMultiset.remove(element)) {
				match = false;
			}
		}
		System.out.println("\n\tBefore set=" + Arrays.toString(originalMultisetContents) + " \t\t  (before size=" + originalSize + ")");
		System.out.println("Expected after set=" + Arrays.toString(duplicatedMultisetArray)+ " \t(expected size=" + expectedDoubleSize + ")");
		System.out.println("  Actual after set=" +  Arrays.toString(actualMultisetArray) + " \t  (actual size=" + actualDuplicatedSize + ")");
		if(actualDuplicatedSize!=expectedDoubleSize) {
			allTestsPassed = false;
			System.out.println("**********TEST FAILED. Size is not correct for test: " + testDescription);
		} else if(!match || !actualMultiset.isEmpty()) {
			allTestsPassed = false;
			System.out.println("**********TEST FAILED. Contents not correct for test: " + testDescription);
		}

	}
		
	public static <T extends Comparable<? super T>> void testAddToFront(T[] listContents, T[] nodeContents, String testDescription) {
		ArrayListFromOne<T> list = new ArrayListFromOne<T>();
		ArrayListFromOne<T> expectedList = new ArrayListFromOne<T>();
		for(T element : nodeContents) {
			expectedList.add(element);
		}
		for(T element : listContents) {
			list.add(element);
			expectedList.add(element);
		}
		ANode<T> chain = createANodeChain(nodeContents);
		list.addToFront(chain);
		
		boolean matchingContents = true;
		int listPos = 1;
		for(int i=0; i<nodeContents.length && matchingContents; i++, listPos++) {
			if(listPos>list.size() || !list.get(listPos).equals(nodeContents[i])) {
				matchingContents = false;
			}
		}
		for(int i=0; i<listContents.length && matchingContents; i++, listPos++) {
			if(!list.get(listPos).equals(listContents[i])) {
				matchingContents = false;
			}
		}
		System.out.println("\n\t  Before list=" + Arrays.toString(listContents) + " \t\t(before size=" + listContents.length + ")");
		System.out.println("Chain to add to front=" +  Arrays.toString(nodeContents) + "\t\t(chain size=" + nodeContents.length + ")");
		System.out.println("  Expected list after=" + Arrays.toString(listToArray(expectedList)) +  "\t(expected size=" + expectedList.size() + ")");
		System.out.println("    Actual list after=" + Arrays.toString(listToArray(list)) +  "\t  (actual size=" + list.size() + ")");

		if(!matchingContents) {
			allTestsPassed = false;
			System.out.println("**********TEST FAILED. Contents not correct for test: " + testDescription);
		}
		if(expectedList.size()!=list.size()) {
			allTestsPassed = false;
			System.out.println("**********TEST FAILED. Size not correct for test: " + testDescription);
		}		
	}
	
	private static <T> ANode<T> createANodeChain(T[] array) {
		ANode<T> firstNode = null;
		if(array.length>0) {
			ANode<T> node = new ANode<T>(array[0]);
			firstNode = node;
			for(int i=1; i<array.length; i++) {
				ANode<T> newNode = new ANode<T>(array[i]);
				node.next = newNode;
				node = node.next;
			}
		}
		return firstNode;
	}
	
	public static <T extends Comparable<? super T>> T[] listToArray(ListFromOne<T> list) {
		T[] array = (T[]) new Comparable[list.size()];
		for(int i=1; i<=list.size(); i++) {
			array[i-1] = list.get(i);
		}
		return array;
	}
	public static void testAddDuplicateNeighbors(String[] contents, String testDescription) {
		ListFromOne<String> list;
		ListFromOne<String> expectedList;
		Random generator = new Random();
		if(generator.nextBoolean()) {
			list = new ArrayListFromOne<String>();
			expectedList = new ArrayListFromOne<String>();
		} else {
			list = new LinkedListFromOne<String>();
			expectedList = new LinkedListFromOne<String>();
		}
		for(String element : contents) {
			list.add(element);
			expectedList.add(element);
			expectedList.add(element);
		}
		addDuplicateNeighbors(list);
		
		System.out.println("\n\tBefore list=" + Arrays.toString(contents)); 
		System.out.println("Expected list after=" + Arrays.toString(listToArray(expectedList)));
		System.out.println("  Actual list after=" +  Arrays.toString(listToArray(list)));
		
		boolean lengthMatch = true;
		if(list.size()!=expectedList.size()) {
			lengthMatch = false;
			allTestsPassed = false;
			System.out.println("**********TEST FAILED. Size is not correct for test: " + testDescription);
		}
		boolean contentsMatch = true;
		int failureIndex = -1;
		for(int i=1; lengthMatch && i<=list.size() && contentsMatch; i++) {
			if(!list.get(i).equals(expectedList.get(i)) ) {
				contentsMatch = false;
				failureIndex = i;
				allTestsPassed = false;
				System.out.println("**********TEST FAILED. Contents are not correct starting at index " + failureIndex + " for test: " + testDescription);

			}
		}

	
	}

	
	


}

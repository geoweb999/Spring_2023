import java.util.*;

public class HomeworkM4Driver {

	private static boolean allTestsPassed = true, setEfficiencyTestPassed = true; 
	
	public static void main(String[] args) {

		System.out.println("-----------------------------TESTING removeAll(T) in ArrayMultiset-----------------------------");
		System.out.println("Note: the expected and actual set contents do not have to have the same order. The sets only need to have the same contents.");
		// parameter 1: the contents to add to an originally empty set
		// parameter 2: the element to remove
		// parameter 3: a description of the test
		testArrayMultisetRemoveAll(new Integer[] {}, 						 1, 	"removing from empty set");
		testArrayMultisetRemoveAll(new Integer[] {1, 7, 2, 1, 3, 1, 1, 7, 1}, 1,  	"remove value appears multiple times");
		testArrayMultisetRemoveAll(new Integer[] {7, 2, 4, 3, 7}, 			 7, 	"remove value appears multiple times, first and last values added");
		testArrayMultisetRemoveAll(new Integer[] {2, 4, 3, 1, 5, 6}, 		 8, 	"remove value is not in the set");
		testArrayMultisetRemoveAll(new Integer[] {3, 3, 3, 3}, 				 3,		"remove value is the only value in the set");
		testArrayMultisetRemoveAll(new String[] {"a", "b", "a"}, 			 new String("a"),"test with Strings");

		System.out.println("\nTesting efficiency: The elapsed time should most likely < 100.");
		System.out.println("If the elapsed time is much higher than that, you might revisit your code to see if you have a nested loop.");	
		int bigSize = 10000;
		ArrayMultiset<Integer> bigSet = new ArrayMultiset<Integer>(bigSize);
		for(int i=0; i<bigSize/2; i++) {
			bigSet.add(99);
		}
		for(int i=0; i<bigSize/2; i++) {
			bigSet.add(43);
		}	
		long startTimeSet = System.currentTimeMillis();
		bigSet.removeAll(99);
		long stopTimeSet = System.currentTimeMillis();
		long elapsedTime = stopTimeSet - startTimeSet;
		System.out.println("Elapsed time = " + elapsedTime);
		if(elapsedTime>=100) {
			setEfficiencyTestPassed = false;
		}

		
		System.out.println("\n-----------------------------TESTING trimToSize() in ExpandableArrayMultiset-----------------------------");
		ExpandableArrayMultiset<Integer> resizableSet = new ExpandableArrayMultiset<Integer>(20);
		ExpandableArrayMultiset<Integer> copySet = new ExpandableArrayMultiset<Integer>(20);
		for(int i=0; i<100; i++) {
			resizableSet.add(1);
			copySet.add(1);
		}
		// parameter 1: the resizableArrayMultiset
		// parameter 2: a cope of the set that *hasn't* been trimmed
		// parameter 3: the expected size of the set (resizableSet.size())
		// parameter 4: the expected length of the set array (resizableSet.set.length)
		// parameter 5: a description of the test
		testTrimToSizeResults(resizableSet, copySet, 100, 160, "set with 100 elements; initial array length of 20 doubled three times during the additions for a length of 160");
		
		System.out.println("\nInvoking trimToSize...");
		resizableSet.trimToSize();
		testTrimToSizeResults(resizableSet, copySet, 100, 100, "set with 100 elements, trimmed to size");
	
		System.out.println("\nAdding an element...");
		resizableSet.add(43);
		copySet.add(43);
		testTrimToSizeResults(resizableSet, copySet, 101, 200, "set with 101 elements; previous array length of 100 doubled during the addition of one item");
		
		System.out.println("\nInvoking trimToSize...");
		resizableSet.trimToSize();
		testTrimToSizeResults(resizableSet, copySet, 101, 101, "set with 101 elements, trimmed to size");
		
		System.out.println("\nAdding an element...");
		resizableSet.add(99);
		copySet.add(99);
		testTrimToSizeResults(resizableSet, copySet, 102, 202, "set with 102 elements; previous array length of 101 doubled during the addition of one item");
		
		System.out.println("\nInvoking trimToSize...");
		resizableSet.trimToSize();
		testTrimToSizeResults(resizableSet, copySet, 102, 102, "set with 102 elements, trimmed to size");
		
		
		ExpandableArrayMultiset<Integer> resizableSetForEmptyTest = new ExpandableArrayMultiset<Integer>(10);
		System.out.println("\nInvoking trimToSize on an empty set and then using the set: make sure the code doesn't crash!");
		resizableSetForEmptyTest.trimToSize();
		resizableSetForEmptyTest.add(1);
		resizableSetForEmptyTest.add(2);
		resizableSetForEmptyTest.add(3);
		System.out.println("trimToSize method testing complete.");

		
		System.out.println("\n-----------------------------TESTING removeAll(T) in ArrayListFromOne-----------------------------");
		// parameter 1: the contents to add to an originally empty list
		// parameter 2: the element to remove
		// parameter 3: a description of the test
		testArrayListFromOneRemoveAll(new Integer[] {}, 						1, "removing from empty list");
		testArrayListFromOneRemoveAll(new Integer[] {1, 2, 1, 1, 4, 3, 7, 1}, 	1, "remove value appears multiple times");
		testArrayListFromOneRemoveAll(new Integer[] {7, 2, 4, 3, 7}, 			7, "remove value appears multiple times, first and last values added");
		testArrayListFromOneRemoveAll(new Integer[] {7, 2, 4, 3, 7}, 			8, "remove value is not in the list");
		testArrayListFromOneRemoveAll(new Integer[] {3, 3, 3, 3}, 				3, "remove value is the only value in the list");
		testArrayListFromOneRemoveAll(new String[] {"a", "b", "a"}, 			new String("a"), "test with Strings");


		System.out.println("\n-----------------------------TESTING equals method in ArrayListFromOne-----------------------------");
		// parameter 1: the list that will invoke the equals method
		// parameter 2: the list passed in as a parameter to the equals method
		// parameter 3: the expected result (true if the lists are equivalent, false otherwise)
		// parameter 4: a description of the test
		testEquivalentLists(new String[] {}, 				  		new String[] {}, 				   true, "two empty lists");
		testEquivalentLists(new String[] {"a", "b", "c", "d"}, 		new String[] {"a", "b", "c", "d"}, true, "equivalent lists");
		testEquivalentLists(new String[] {"a", "b", "c", "a"}, 		new String[] {"a", "b", "c", new String("a")}, true, "equivalent lists");
		testEquivalentLists(new String[] {"a", "b", "c", "a"}, 		new String[] {"a", "b", "c", "a"}, true, "equivalent lists 2");

		testEquivalentLists(new Integer[] {1, 2, 3}, 				new Integer[] {1, 2, 3}, 		   true, "equivalent lists");

		testEquivalentLists(new String[] {"a", "b", "c", "d"}, new String[] {}, 				  false, "empty parameter, non-empty invoking object");
		testEquivalentLists(new String[] {}, 				   new String[] {"a", "b", "c", "d"}, false, "empty invoking object, non-empty parameter");
		testEquivalentLists(new String[] {"a", "b", "c"}, 	   new String[] {"a", "b", "c", "d"}, false, "different lengths, invoking shorter");
		testEquivalentLists(new String[] {"a", "b", "c", "d"}, new String[] {"a", "b", "c"}, 	  false, "different lengths, invoking longer");
		testEquivalentLists(new String[] {"a", "b", "c", "d"}, new String[] {"e", "f", "g", "h"}, false, "same lengths, different elements");
		testEquivalentLists(new String[] {"a", "b", "c", "d"}, new String[] {"a", "b", "c", "e"}, false, "same lengths, different last element");
		testEquivalentLists(new String[] {"a", "b", "c", "d"}, new String[] {"e", "b", "c", "d"}, false, "same lengths, different first element");
		testEquivalentLists(new String[] {"a", "b", "c", "d"}, new String[] {"b", "d", "a", "c"}, false, "same lengths, same elements, different order of elements");

		// this method creates several ArrayListFromOne objects and puts them in a list (ArrayList<ArrayListFromOne>>)
		// the method then calls ArrayList.contains to test if an ArrayListFromOne is equal to one on the list
		// if equals works correctly, the call to contains will return true
		testEqualsMethod();

		
		System.out.println("\n-----------------------------TESTING EXTRA CREDIT ArrayListFromOne implementing Comparable-----------------------------");
		// parameter 1: the list that will invoke the compareTo method (List A)
		// parameter 2: the list passed in as a parameter to the compareTo method (List B)
		// parameter 3: the expected result (negative if list A < list B, positive if list A > list B, zero otherwise)
		// parameter 4: a description of the test
		testComparable(new Integer[] {}, 			 new Integer[] {}, 			   ResultRange.ZERO, "List A and B are empty");
		testComparable(new Integer[] {1}, 			 new Integer[] {}, 			   ResultRange.POSITIVE, "List A has more elements");
		testComparable(new Integer[] {}, 			 new Integer[] {1}, 		   ResultRange.NEGATIVE, "List A has fewer elements");
		testComparable(new Integer[] {1}, 	 		 new Integer[] {1, 2}, 		   ResultRange.NEGATIVE, "List A has fewer elements");
		testComparable(new Integer[] {1, 2}, 	 	 new Integer[] {1, 2}, 		   ResultRange.ZERO, "List A and List B are the same size and have the same elements");
		testComparable(new Integer[] {1, 2, 3}, 	 new Integer[] {1, 2, 4}, 	   ResultRange.NEGATIVE, "the lists are the same size; for the first non-matching element, the List A element is smaller)");
		testComparable(new Integer[] {1, 2, 3}, 	 new Integer[] {1, 2, 3}, 	   ResultRange.ZERO, "List A and List B are the same size and have the same elements");
		testComparable(new Integer[] {1, 2, 3, 4}, 	 new Integer[] {1, 2, 3, 2},   ResultRange.POSITIVE, "the lists are the same size; for the first non-matching element, the List A element is bigger");
		testComparable(new String[] {"a", "b", "c"}, new String[] {"a", "d", "a"}, ResultRange.NEGATIVE, "the lists are the same size; for the first non-matching element, the List A element is smaller");
		
		
		System.out.println("\n\n-----------------------------TESTING COMPLETE-----------------------------");
		if(allTestsPassed) {
			System.out.println("----------Summary---------- All automated tests have passed. Be sure to manually review your code for style and efficiency.");
			if(!setEfficiencyTestPassed) {
				System.out.flush();
				System.err.println("******Note: it is possible that your ArrayMultiset removeAll method is not efficient. Manually review the code. Be careful with nested loops that result from method calls inside of loops!");
			}
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
	public static <T> T[]  listToArray(ListFromOne<T> list) {
		return (T[]) Arrays.copyOfRange(( (ArrayListFromOne)list).listArray, 1, list.size()+1);
	}
	public static <T> T[]  setToArray(Multiset<T> set) {
		return (T[]) Arrays.copyOf(( (ArrayMultiset)set).setArray, set.size());
	}
	public static <T extends Comparable<? super T>> void testArrayMultisetRemoveAll(T[] valuesToAdd, T removeValue, String testDescription) {
		int numRemovedExpected = 0;
		ArrayMultiset<T> set = new ArrayMultiset<T>();
		ArrayMultiset<T> correctSet = new ArrayMultiset<T>();
		for(T value : valuesToAdd) {
			set.add(value);
			if(!value.equals(removeValue)) {
				correctSet.add(value);
			} else {
				numRemovedExpected++;
			}
		}
		int originalSize = set.size();
		int numRemovedActual = set.removeAll(removeValue);
		Object[] setContentsAfter = setToArray(set);
		Object[] correctSetContentsAfter = setToArray(correctSet);

		System.out.println("\nSet before removing " + removeValue + ":\t" + Arrays.toString(valuesToAdd) + "\tSize=" + originalSize);
		System.out.println("Expected set after:\t" + Arrays.toString(correctSetContentsAfter) + "\tSize=" + correctSet.size());
		System.out.println("Actual set after:\t" + Arrays.toString(setContentsAfter) + "\tSize=" + set.size());
		System.out.println("Expected return value = " + numRemovedExpected);
		System.out.println("Actual return value   = " + numRemovedActual);

		if(set.size()!=correctSet.size()) {
			allTestsPassed = false;
			System.out.println("**********TEST FAILED for: " + testDescription);
			System.out.println("\t  Error: Updated set is the incorrect size.");
		}
		if(numRemovedExpected!=numRemovedActual) {
			allTestsPassed = false;
			System.out.println("**********TEST FAILED for: " + testDescription);
			System.out.println("\t  Error: Incorrect return value.");
		}
		boolean match = true;
		while(!correctSet.isEmpty()) {
			boolean removed = set.remove(correctSet.remove());
			if(!removed) {
				match = false;
			}
		}
		if(!set.isEmpty()) {
			match = false;
		}
		if(!match) {
			allTestsPassed = false;
			System.out.println("**********TEST FAILED for: " + testDescription);
			System.out.println("\t  Error: Updated set does not have the expected contents.");		
		}
	}
	public static void testTrimToSizeResults(ExpandableArrayMultiset<Integer> set, ExpandableArrayMultiset<Integer> copySet, int expectedSetSize, int expectedArrayLength, String testDescription) {
		int actualSetSize = set.size();
		int actualArrayLength = ( (Object[]) set.setArray).length;
		

		System.out.println("Expected set size:\t" + expectedSetSize);
		System.out.println("Actual set size:  \t" + actualSetSize);
		System.out.println("Expected set array length: " + expectedArrayLength);
		System.out.println("  Actual set array length: " + actualArrayLength);
		
		if(actualSetSize!=expectedSetSize) {
			allTestsPassed = false;
			System.out.println("**********TEST FAILED with incorrect set size for test: " + testDescription);
		}

		if(actualArrayLength!=expectedArrayLength) {
			allTestsPassed = false;
			System.out.println("**********TEST FAILED with incorrect array length for test: " + testDescription);
		}
		try {
			if(!set.equals(copySet)) {
				allTestsPassed = false;
				System.out.println("**********TEST FAILED for: " + testDescription);
				System.out.println("\t  Error: the contents of the set were changed.");
				System.out.println("\t  Expected set contents: " + Arrays.toString(setToArray(copySet)));
				System.out.println("\t    Actual set contents: " + Arrays.toString(setToArray(set)));
			}
		}catch(NullPointerException ex) {
			System.out.println("**********TEST FAILED for: " + testDescription);
			System.out.println("\t  Error: the contents of the set were changed.");
			System.out.println("\t  Expected set contents: " + Arrays.toString(setToArray(copySet)));
			System.out.println("\t    Actual set contents: " + Arrays.toString(setToArray(set)));
		}
	}
	public static  <T extends Comparable<? super T>> void testArrayListFromOneRemoveAll(T[] valuesToAdd, T removeValue, String testDescription) {
		ArrayListFromOne<T> list = new ArrayListFromOne<T>();
		ArrayListFromOne<T> correctList = new ArrayListFromOne<T>();
		int numRemovedExpected = 0;
		for(T value : valuesToAdd) {
			list.add(value);
			if(!value.equals(removeValue)) {
				correctList.add(value);
			} else {
				numRemovedExpected++;
			}
		}
		int originalSize = list.size();
		int numRemovedActual = list.removeAll(removeValue);
		
		Object[] listContentsAfter = listToArray(list);
		Object[] correctListContentsAfter = listToArray(correctList);

		System.out.println("\nList before removing " + removeValue + ":\t" + Arrays.toString(valuesToAdd) + "\tSize=" + originalSize);
		System.out.println("Expected list after:\t" + Arrays.toString(correctListContentsAfter) + "\tSize=" + correctList.size());
		System.out.println("Actual list after:\t" + Arrays.toString(listContentsAfter) + "\tSize=" + list.size());
		System.out.println("Expected return value = " + numRemovedExpected);
		System.out.println("Actual return value   = " + numRemovedActual);

		if(list.size()!=correctList.size()) {
			allTestsPassed = false;
			System.out.println("**********TEST FAILED for: " + testDescription);
			System.out.println("\t  Error: Updated list is the incorrect size.");
		}
		if(numRemovedExpected!=numRemovedActual) {
			allTestsPassed = false;
			System.out.println("**********TEST FAILED for: " + testDescription);
			System.out.println("\t  Error: Incorrect return value.");
		}
		if(!Arrays.equals(listContentsAfter, correctListContentsAfter)) {
			allTestsPassed = false;
			System.out.println("**********TEST FAILED for: " + testDescription);
			System.out.println("\t  Error: Updated list does not have the expected contents.");		
		}
	}
	public static <T extends Comparable<? super T>> void testEquivalentLists(T[] listAContents, T[] listBContents, boolean expectedResult, String testDescription) {
		ArrayListFromOne<T> listA = new ArrayListFromOne<T>(100);
		for(T element : listAContents) {
			listA.add(element);
		}
		ArrayListFromOne<T> listB = new ArrayListFromOne<T>();
		for(T element : listBContents) {
			listB.add(element);
		}
		boolean actualResult = listA.equals(listB);
			
		System.out.println("\nInvoking list:  " + Arrays.toString(listToArray(listA)) + "\nParameter list: " + Arrays.toString(listToArray(listB)));
		System.out.println("Expected result = " + expectedResult);
		System.out.println("Actual result =   " + actualResult);
		
		if(actualResult!=expectedResult) {
			allTestsPassed = false;
			System.out.println("**********TEST FAILED for: " + testDescription);
		}
	}
	public static void testEqualsMethod() {
		System.out.println("\nTesting equality using the contains method...");
		List<ArrayListFromOne<Integer>> listOfArrayListFromOnes = new ArrayList<ArrayListFromOne<Integer>>();
		List<ArrayListFromOne<Integer>> listOfCopyLists = new ArrayList<ArrayListFromOne<Integer>>();
		
		Random generator = new Random();
		for(int i=0; i<100; i++) {
			ArrayListFromOne<Integer> list = new ArrayListFromOne<Integer>();
			ArrayListFromOne<Integer> copyList = new ArrayListFromOne<Integer>();
			int sizeOfList = generator.nextInt(100)+5;
			for(int j=0; j<sizeOfList; j++) {
				int value = generator.nextInt();
				list.add(value);
				copyList.add(value);
			}
			listOfArrayListFromOnes.add(list);
			listOfCopyLists.add(copyList);
		}

		boolean passContainsTests = true;
		for(ArrayListFromOne<Integer> list : listOfCopyLists) {
			boolean contains = listOfArrayListFromOnes.contains(list);
			if(contains!=true) {
				allTestsPassed = false;
				System.out.println("**********TEST FAILED: equals method failed when testing using the contains method. Check that you are overriding the equals method correctly.");
				passContainsTests = false;
				return;
			}
		}
		if(passContainsTests) {
			System.out.println("Test passed.");
		}
	}	
	public static enum ResultRange {
		POSITIVE("invoking List A > parameter List B"), NEGATIVE("invoking List A < parameter List B"), ZERO("the two lists are \"equal\"");
		
		private String text;
		private ResultRange(String text) {
			this.text = text;
		}
		public String toString() {
			return this.text;
		}
	}
	public static <T extends Comparable<? super T>> void testComparable(T[] listAContents, T[] listBContents, ResultRange expectedResultRange, String testDescription) {
		ArrayListFromOne<T> listA = new ArrayListFromOne<T>(100);
		for(T element : listAContents) {
			listA.add(element);
		}
		ArrayListFromOne<T> listB = new ArrayListFromOne<T>();
		for(T element : listBContents) {
			listB.add(element);
		}
		int result = listA.compareTo(listB);
		ResultRange resultRange;
		if(result<0) {
			resultRange = ResultRange.NEGATIVE;
		} else if(result>0) {
			resultRange = ResultRange.POSITIVE;
		} else {
			resultRange = ResultRange.ZERO;
		}
		System.out.println("\nInvoking List A:  " + Arrays.toString(listToArray(listA)) + "\nParameter List B: " + Arrays.toString(listToArray(listB)));
		System.out.println("Expected result = " + expectedResultRange);
		System.out.println("Actual result =   " + resultRange);
		
		if(resultRange!=expectedResultRange) {
			allTestsPassed = false;
			System.out.println("**********TEST FAILED for: " + testDescription);
		}
	}

}

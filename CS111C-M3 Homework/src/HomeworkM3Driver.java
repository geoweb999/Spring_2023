import java.util.*;

public class HomeworkM3Driver {

	public static void prioritizeMaximumValue(List<Integer> numberList) {
		// if List is size 0 or , do nothing otherwise find maxValue (and index)
		// and move to 0th position
		if (numberList.size() > 1) {
			int maxIndex = 0;
			int maxValue = numberList.get(0);
			for (int i = 0; i < numberList.size(); i++) {
				if (numberList.get(i) > maxValue) {
					maxIndex = i;
					maxValue = numberList.get(i);
				}
			}
			// remove first (or only) max value
			numberList.add(0, numberList.remove(maxIndex));
		}
		
	}

	public static boolean containsDuplicates(Multiset<String> wordSet) {
		// pull item from bag, then check to see if another instance is in bag
		//System.out.println("New test ***");
		Multiset<String> tempWordSet = new ArrayMultiset<>();
		String tempWord;
		Boolean dupeExists = false;
		if (!wordSet.isEmpty()) {
			while (!wordSet.isEmpty() && !(dupeExists)) {  // check each word and stop on empty or dupe found
				tempWord = wordSet.remove(); 
				tempWordSet.add(tempWord); // will use this to restore wordSet
				dupeExists = wordSet.contains(tempWord);
			}
		} else {
			//System.out.println("Empty bag");
			return false; // empty bag
		}
		// either we had a dupe or we went through the whole bag
		while (!tempWordSet.isEmpty()) {
			tempWord = tempWordSet.remove();
			wordSet.add(tempWord);
		}
		//System.out.println("Returning: " + dupeExists);
		//System.out.println(wordSet);
		return dupeExists;
		
	}

	public static ListFromOne<String> createListFromOneContainingChar(ListFromOne<String> wordList, char targetChar) {
		ListFromOne<String> returnList = new ArrayListFromOne<String>(); 		
		if (wordList.size() == 0) {
			return returnList;
		} else {
			for (int i=1; i <=wordList.size(); i++) {
				if (wordList.get(i).indexOf(targetChar) >= 0) {
					returnList.add(wordList.get(i));
				}
			}
			return returnList;	
		}
	}

	public static int lastPosition(List<String> wordList, String targetWord) {
		// walk backwards though wordList and if targetword found, return immediately
		int i;
		for (i = wordList.size() - 1; i >= 0; --i) {
			if (wordList.get(i).equals(targetWord)) {
				return i;
			}
		}
		// if we got here, we did not find a match so return -1
		return -1;
	}

	public static boolean equivalentLists(ListFromOne<Integer> numberListFromOne, List<Integer> numberList) {
		if (numberListFromOne.size() != numberList.size() ) {
			// lists must be the same size
			return false;
		}
		// lists have the same size and if zero, this for loop won't execute
		for (int i = 0; i < numberList.size(); i++) {
			if (numberListFromOne.get(i+1) != numberList.get(i)) {
				return false;
			}
		}
		// we got here so both lists have the same content
		return true;
		
	}


	public static void main(String[] args) {

		System.out.println("-----------------------------TESTING Comparable AND compareTo-----------------------------");
		Person p1 = new Person("Chip", "Munk", 123);			Person p2 = new Person("Trina", "Woods", 234);
		Person p3 = new Person("Trina", "Forest", 345);			Person p4 = new Person("holly", "wood", 456);
		Person p5 = new Person("Holly", "McRel", 567);			Person p6 = new Person("chip", "munk", 678);
		Person p7 = new Person("Holly", "Wood", 789);			Person p8 = new Person("anne", "teak", 890);
		Person p9 = new Person("Terry", "Bull", 901); 			Person p10 = new Person("Eddy", "Bull", 902);
		Person p11 = new Person("anne", "teak", 111);
		Person[] people =  {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11};
		Person[] sortedPeople = {p10, p9, p3, p5, p1, p6, p11, p8, p4, p7, p2};
		System.out.println("Before sorting, array order is:");
		System.out.println(Arrays.toString(people));
		Arrays.sort(people);
		System.out.println("\nArray is now sorted.");
		System.out.println("Expected array: " + Arrays.toString(sortedPeople));
		System.out.println("  Actual array: " + Arrays.toString(people));
		if(!Arrays.equals(people,  sortedPeople)) {
			allTestsPassed = false;
			System.out.println("***Test failed: array not sorted correctly.");
		}

		System.out.println("\n\n-----------------------------TESTING containsDuplicates(Multiset) METHOD-----------------------------");
		// parameter 1: the contents being added to the set
		// parameter 2: the expected result (true if the set contains duplicates, false otherwise)
		// parameter 3: a description of the test
		testMultisetContainsDuplicates(new String[] {}, false, "empty set");
		testMultisetContainsDuplicates(new String[] { "hi" }, false, "singleton set");
		testMultisetContainsDuplicates(new String[] { "hi", "HI" }, false, "set with no duplicates");
		testMultisetContainsDuplicates(new String[] { "a", "b", "c", "d", "e", "f" }, false, "set with no duplicates");
		testMultisetContainsDuplicates(new String[] { "a", "b", "d", "e", "c", "c" }, true,  "set with duplicates added last");
		testMultisetContainsDuplicates(new String[] { "a", "c", "b", "d", "a" }, true, "set with duplicates added first and last");
		testMultisetContainsDuplicates(new String[] { "a", "b", "b", "c" }, true, "set with duplicates added in the middle");
		testMultisetContainsDuplicates(new String[] { "a", "a", "b", "c", "e", "f" }, true, "set with duplicates added first");

		System.out.println("\n\n-----------------------------TESTING createListContainingChar METHOD-----------------------------");
		// parameter 1: the contents of the ListFromOne
		// parameter 2: the target character
		// parameter 3: the contents of the expected answer ListFromOne
		// parameter 4: a description of the test
		testCreateList(new String[] {}, 'z', new String[] {}, "empty list");
		testCreateList(new String[] { "gab" }, 'a', new String[] { "gab" },	
				"singleton list with a word that has the target char");
		testCreateList(new String[] { "zip" }, 'e', new String[] {}, 
				"singleton list with a word that does not have the target char");
		testCreateList(new String[] { "bid", "sing", "ennui", "i", "icky" }, 'i', new String[] { "bid", "sing", "ennui", "i", "icky" },
				"list with all elements containing the target character");
		testCreateList(new String[] { "bid", "sing", "ennui", "i", "icky" }, 'z', new String[] {},
				"list with no elements containing the target character");
		testCreateList(new String[] { "apple", "anna", "bob", "ANIMAL", "canal", "frog", "cat" }, 'a', new String[] { "apple", "anna", "canal", "cat" },
				"list with elements that contain the target at beginning, middle, and end of the list");

		System.out.println("\n\n-----------------------------TESTING lastPosition METHOD-----------------------------");
		// parameter 1: the contents of the List
		// parameter 2: the target word
		// parameter 3: a description of the test
		testLastPosition(new String[] {}, "frank", "empty list");
		testLastPosition(new String[] { "hyde" }, "frank", "singleton list that doens't contain the target");
		testLastPosition(new String[] { "square" }, "square", "singleton list that contains the target");
		testLastPosition(new String[] { "apple", "teal", "carrot" }, "ball", "target not on the list");
		testLastPosition(new String[] { "dog", "cat", "bird" }, new String("bird"),
				"target on the list once at the end of the list");
		testLastPosition(new String[] { "bell", "gourd", "sound", "age", "friend" }, "age",
				"target on the list once in the middle of the list");
		testLastPosition(new String[] { "x", "a", "d", "x", "e", "c", "b", "x" }, "x",
				"target on the list more than once with last occurrence being in last position on the list");
		testLastPosition(new String[] { "x", "b", "d", "x", "b", "c", "b", "x" }, "b",
				"target on the list more than once with last occurrence in the middle");
		testLastPosition(new String[] { "f", "b", "g", "b", "v", "e", "c", "s" }, "f",
				"target on the list once at the beginning of the list");

		System.out.println("\n\n-----------------------------TESTING equivalentLists METHOD-----------------------------");
		// parameter 1: the contents of the ListFromOne
		// parameter 2: the contents of the List
		// parameter 3: the expected result (true means equivalent, false means not)
		// parameter 4: a description of the test
		testEquivalentLists(new Integer[] {}, new Integer[] {}, true, "two empty lists");
		testEquivalentLists(new Integer[] { 4 }, new Integer[] { 4 }, true,
				"two singleton lists with the same element");
		testEquivalentLists(new Integer[] { 1, 3, 1, 5, 7 }, new Integer[] { 1, 3, 1, 5, 7 }, true,
				"equivalent lists (odd length)");
		testEquivalentLists(new Integer[] { 1, 3, 1, 5, 7, 6 }, new Integer[] { 1, 3, 1, 5, 7, 6 }, true,
				"equivalent lists (even length)");
		testEquivalentLists(new Integer[] { 4 }, new Integer[] { 7 }, false,
				"two singleton lists with different elements");
		testEquivalentLists(new Integer[] {}, new Integer[] { 1, 3, 1, 5, 7 }, false,
				"ListFromOne is empty, List is not");
		testEquivalentLists(new Integer[] { 1, 3, 1, 5, 7 }, new Integer[] {}, false,
				"List is empty, ListFromOne is not");
		testEquivalentLists(new Integer[] { 1 }, new Integer[] { 1, 3, 1, 5, 7 }, false,
				"difference sizes; the first elements match");
		testEquivalentLists(new Integer[] { 1, 3, 1, 5 }, new Integer[] { 1, 3, 1, 5, 7 }, false,
				"ListFromOne has one fewer element than List");
		testEquivalentLists(new Integer[] { 2, 4, 7, 6, 3 }, new Integer[] { 2, 4, 7, 6 }, false,
				"List has one fewer element than ListFromOne");
		testEquivalentLists(new Integer[] { 1, 2, 3 }, new Integer[] { 4, 2, 3 }, false,
				"only the first elements are different");
		testEquivalentLists(new Integer[] { 1, 2, 3 }, new Integer[] { 1, 6, 7 }, false,
				"only the first elements are the same");
		testEquivalentLists(new Integer[] { 1, 2, 3 }, new Integer[] { 1, 3, 2 }, false,
				"same elements but different order");
		testEquivalentLists(new Integer[] { 2, 3, 5, 4 }, new Integer[] { 3, 2, 5, 4 }, false,
				"last two elements are the same");

		System.out.println("\n\n-----------------------------TESTING EXTRA CREDIT prioritizeMaximumValue METHOD-----------------------------");
		// parameter 1: the contents of the List
		// parameter 2: the expected contents of the list after the max is prioritized
		// parameter 3: a description of the test
		testPrioritizeMax(new Integer[] {}, new Integer[] {}, "empty list");
		testPrioritizeMax(new Integer[] { 4 }, new Integer[] { 4 }, "singleton list");
		testPrioritizeMax(new Integer[] { 4, 3, 2, 5, 7 }, new Integer[] { 7, 4, 3, 2, 5 },
				"maximum at the end of the list");
		testPrioritizeMax(new Integer[] { 1, 3, 9, 5, 7 }, new Integer[] { 9, 1, 3, 5, 7 },
				"maximum in the middle of the list");
		testPrioritizeMax(new Integer[] { 8, 3, 4, 5, 2 }, new Integer[] { 8, 3, 4, 5, 2 },
				"maximum at the beginning of the list");
		testPrioritizeMax(new Integer[] { -4, -3, -2, -5 }, new Integer[] { -2, -4, -3, -5 }, "maximum is negative");

		System.out.println("\n\n-----------------------------TESTING COMPLETE-----------------------------");
		if (allTestsPassed) {
			System.out.println(
					"\n----------Summary---------- All automated tests have passed. Be sure to manually review your code for style.");
		} else {
			System.out.flush();
			System.err.println(
					"**********Summary********** ERROR: There is failure in at least one automated test. Review the output above for details.");
		}
	}


	/*----------------------------------------------------------------------------------------------------------*/
	/* TESTER METHODS */
	/*----------------------------------------------------------------------------------------------------------*/
	/*
	 * The methods below are designed to help support the tests cases run from main.
	 * You don't need to use, modify, or understand these methods. You can safely
	 * ignore them. :)
	 */
	
	private static boolean allTestsPassed = true;

	public static <T> T[]  listFromOneToArray(ListFromOne<T> list) {
		return (T[]) Arrays.copyOfRange(( (ArrayListFromOne)list).listArray, 1, list.size()+1);
	}
	public static void testMultisetContainsDuplicates(String[] wordArray, boolean expectedResult, String testDescription) {
		Multiset<String> wordSet = new ArrayMultiset<String>();
		for (String word : wordArray) {
			wordSet.add(word);
		}
		Object[] beforeSetArray = Arrays.copyOf(( (ArrayMultiset)wordSet).setArray, wordSet.size());
		Arrays.sort(beforeSetArray);

		boolean actualResult = containsDuplicates(wordSet);

		Object[] afterSetArray = Arrays.copyOf(( (ArrayMultiset)wordSet).setArray, wordSet.size());
		Arrays.sort(afterSetArray);

		if (actualResult != expectedResult) {
			System.out.println("\nDuplicates? Test of: " + Arrays.toString(beforeSetArray));
			System.out.println("Expected = " + expectedResult + "\n  Actual = " + actualResult);
			allTestsPassed = false;
			System.out.println("**********TEST FAILED for: " + testDescription);
			System.out.println("Error: Method did not return expected result.");
		}
		if (!Arrays.equals(beforeSetArray, afterSetArray)) {
			System.out.println("\nDuplicates? Test of: " + Arrays.toString(beforeSetArray));
			System.out.println("Expected = " + expectedResult + "\n  Actual = " + actualResult);
			allTestsPassed = false;
			System.out.println("**********TEST FAILED for: " + testDescription);
			System.out.println("Error: The set contents were changed.");
			System.out.println("Set contents before containsDuplicates invoked: " + Arrays.toString(beforeSetArray));
			System.out.println("Set contents after  containsDuplicates invoked: " + Arrays.toString(afterSetArray));
		}
	}

	public static void testCreateList(String[] wordArray, char c, String[] answerArray, String testDescription) {
		ListFromOne<String> wordList = new ArrayListFromOne<String>();
		for (String word : wordArray) {
			wordList.add(word);
		}
		ListFromOne<String> answerList = new ArrayListFromOne<String>();
		for (String word : answerArray) {
			answerList.add(word);
		}

		Object[] beforeListArray = listFromOneToArray(wordList);
		Object[] answerListArray = listFromOneToArray(answerList);

		ListFromOne<String> resultList = createListFromOneContainingChar(wordList, c);
		Object[] resultListArray = listFromOneToArray(resultList);

		if (!Arrays.equals(answerListArray, resultListArray)) {
			System.out.println("\nParameter list:\t\t\t " + Arrays.toString(beforeListArray));
			System.out.println("Expected list of words with '" + c + "': " + Arrays.toString(answerListArray));
			System.out.println("  Actual list of words with '" + c + "': " + Arrays.toString(resultListArray));
			allTestsPassed = false;
			System.out.println("**********TEST FAILED for: " + testDescription);
		}
		if (!Arrays.equals(beforeListArray, listFromOneToArray(wordList))) {
			System.out.println("\nParameter list:\t\t\t " + Arrays.toString(beforeListArray));
			System.out.println("Expected list of words with '" + c + "': " + Arrays.toString(answerListArray));
			System.out.println("  Actual list of words with '" + c + "': " + Arrays.toString(resultListArray));
			allTestsPassed = false;
			System.out.println("**********TEST FAILED: The parameter list was changed.");
			System.out.println("Parameter list after method:     " + Arrays.toString(listFromOneToArray(wordList)));
		}
	}

	public static void testLastPosition(String[] wordArray, String target, String testDescription) {
		List<String> originalList = new ArrayList<String>(Arrays.asList(wordArray));
		int resultPosition = lastPosition(originalList, target);
		int correctPosition = originalList.lastIndexOf(target);

		if (resultPosition != correctPosition) {
			System.out.println("\nParameter list:\t\t" + originalList);
			System.out.println(" Expected last index of \"" + target + "\": " + correctPosition);
			System.out.println("   Actual last index of \"" + target + "\": " + resultPosition);
			allTestsPassed = false;
			System.out.println("**********TEST FAILED for: " + testDescription);
		}
	}

	public static void testEquivalentLists(Integer[] listInterfaceContents, Integer[] listArrayContents,
			boolean expectedResult, String testDescription) {
		ListFromOne<Integer> listInterface = new ArrayListFromOne<Integer>();
		List<Integer> list = new ArrayList<Integer>();
		for (int num : listInterfaceContents) {
			listInterface.add(num);
		}
		for (int num : listArrayContents) {
			list.add(num);
		}

		Object[] listInterfaceArrayBefore = listFromOneToArray(listInterface);

		boolean actualResult = equivalentLists(listInterface, list);

		Object[] listInterfaceArrayAfter = listFromOneToArray(listInterface);
		List<Integer> listAfter = new ArrayList<Integer>(list);

		if (actualResult != expectedResult) {
			System.out.println("\nListFromOne:\t " + Arrays.toString(listInterfaceArrayBefore));
			System.out.println("List:         \t " + list);
			System.out.println("Expected result: " + expectedResult);
			System.out.println("Actual result:   " + actualResult);
			allTestsPassed = false;
			System.out.println("**********TEST FAILED for: " + testDescription);
		}
		if (!Arrays.equals(listInterfaceArrayBefore, listInterfaceArrayAfter)) {
			System.out.println("\nListFromOne:\t " + Arrays.toString(listInterfaceArrayBefore));
			System.out.println("List:         \t " + list);
			System.out.println("Expected result: " + expectedResult);
			System.out.println("Actual result:   " + actualResult);
			allTestsPassed = false;
			System.out.println("**********TEST FAILED: ListFromOne contents changed. Now contains: "
					+ Arrays.toString(listInterfaceArrayAfter));
		}
		if (!list.equals(listAfter)) {
			System.out.println("\nListFromOne:\t " + Arrays.toString(listInterfaceArrayBefore));
			System.out.println("List:         \t " + list);
			System.out.println("Expected result: " + expectedResult);
			System.out.println("Actual result:   " + actualResult);
			allTestsPassed = false;
			System.out.println("**********TEST FAILED: List contents changed. Now contains: " + listAfter);
		}

	}

	public static void testPrioritizeMax(Integer[] array, Integer[] result, String testDescription) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(array));
		List<Integer> originalList = new ArrayList<Integer>(list);
		prioritizeMaximumValue(list);
		List<Integer> resultList = new ArrayList<Integer>(Arrays.asList(result));

		if (!resultList.equals(list)) {
			System.out.println("\nOriginal list: \t " + originalList);
			System.out.println("Expected result: " + resultList);
			System.out.println("Actual result:   " + list);
			allTestsPassed = false;
			System.out.println("**********TEST FAILED for: " + testDescription);
		}
	}
}

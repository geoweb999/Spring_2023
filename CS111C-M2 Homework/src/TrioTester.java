import java.util.*;

/*
 * This tester file automates some tests to help you confirm that your code works and track down errors.
 * Note that some tests cannot be tested automatically, but rely on you reviewing the results. For example,
 * when testing toString output, you must visually confirm the results.
 *  
 */

public class TrioTester {

	private static boolean passGetterSetter = true, passReplaceAll = true, 
			passHasDuplicates = true, passCount = true, passEquals = true;
	
	public static void main(String[] args) {

		// ************TESTING GENERIC TYPE AND CONSTRUCTORS************
		Trio<Integer> numberTrio = new Trio<Integer>(3, 4, 5);
	
		Trio<String> wordTrio = new Trio<String>("hello");
		wordTrio.setItem2("goodbye");
		wordTrio.setItem3("farewell");

		/* un-comment each line of code below one at a time */
		/* each line should cause a compiler error because the generic types do not match*/
		//Trio<Integer> badTrio1 = new Trio<Integer>("a", "b", "c");
		//Trio<String> badTrio2 = new Trio<String>(1, 2, 3);
		
		System.out.println("------------------------------TESTING TOSTRING METHOD AND ONE-PARAMETER CONSTRUCTOR------------------------------");
		System.out.println("Check that the print of the Trio contains the elements (3, 4, 5):");
		System.out.println(numberTrio);
		System.out.println("\nCheck that the print of the Trio contains the elements (hello, goodbye, farewell):");
		System.out.println(wordTrio);
		numberTrio = new Trio<Integer>(99);
		System.out.println("\nCheck that the print of the Trio contains the elements (99, 99, 99):");
		System.out.println(numberTrio);
		
		
		System.out.println("\n------------------------------TESTING GETTERS AND SETTERS METHOD------------------------------");
		numberTrio = new Trio<Integer>(3, 4, 5);
		// parameter 1: the Trio object to test
		// parameter 2: which Trio item to get (first, second, or third)
		// parameter 3: the expected result
		testGetterSetter(numberTrio, 1, 3); // testing getItem1 method
		testGetterSetter(numberTrio, 2, 4); // testing getItem2 method
		testGetterSetter(numberTrio, 3, 5); // testing getItem3 method
		numberTrio.setItem1(6);
		numberTrio.setItem2(7);
		numberTrio.setItem3(8);
		testGetterSetter(numberTrio, 1, 6); // testing getItem1 method
		testGetterSetter(numberTrio, 2, 7); // testing getItem2 method
		testGetterSetter(numberTrio, 3, 8); // testing getItem3 method
		wordTrio = new Trio<String>("hello");
		testGetterSetter(wordTrio, 1, "hello"); // testing getItem1 method
		testGetterSetter(wordTrio, 2, "hello"); // testing getItem2 method
		testGetterSetter(wordTrio, 3, "hello"); // testing getItem3 method

		/* un-comment each line of code below one at a time */
		/* each line should cause a compiler error because the generic types do not match*/
		//numberTrio.setItem1("a");
		//numberTrio.setItem2("b");
		//numberTrio.setItem3("c");
		//Integer num = wordTrio.getItem1();
		//Integer num = wordTrio.getItem2();
		//Integer num = wordTrio.getItem3();

		
		System.out.println("\n-----Getter/Setter tests: " + (passGetterSetter ? "Passed" : "*****AT LEAST ONE TEST FAILED*****"));

		
		System.out.println("\n------------------------------TESTING REPLACE ALL METHOD------------------------------");
		numberTrio = new Trio<Integer>(3, 4, 5);
		numberTrio.replaceAll(43);
		System.out.println("\nCheck that the print of the Trio contains the elements (43, 43, 43):");
		System.out.println(numberTrio);
		if(numberTrio.getItem1().intValue()!=43 || numberTrio.getItem2().intValue()!=43 || numberTrio.getItem3().intValue()!=43) {
			System.out.println("**********TEST FAILED**********");
			passReplaceAll = false;
		} else {
			System.out.println("\n-----replaceAll test: Passed.");
		}		
		/* un-comment the line of code below and it should cause a compiler error because the generic types do not match*/
		//numberTrio.replaceAll("a");
	
		System.out.println("\n------------------------------TESTING HASDUPLICATES METHOD------------------------------");		
		// parameter 1: the Trio object to test for duplicates
		// parameter 2: the expected result
		// parameter 3: a description of the test
		testHasDuplicates(new Trio<Integer>(3, 4, 5), false, "no duplicates");
		testHasDuplicates(new Trio<Integer>(3, 3, 5), true,  "items 1 and 2 are duplicates");
		testHasDuplicates(new Trio<Integer>(3, 4, 4), true,  "items 2 and 3 are duplicates");
		testHasDuplicates(new Trio<Integer>(3, 4, 3), true,  "items 1 and 3 are duplicates");
		testHasDuplicates(new Trio<Integer>(4, 4, 4), true,  "all three items are duplicates");
		testHasDuplicates(new Trio<String>(new String("a"), new String("a"), new String("a")), true, "all three items are duplicates and items are Objects (Strings)");

		System.out.println("\n-----hasDuplicates tests: " + (passHasDuplicates ? "Passed" : "*****AT LEAST ONE TEST FAILED*****"));

				
		System.out.println("\n------------------------------TESTING COUNT METHOD------------------------------");
		// parameter 1: the Trio object 
		// parameter 2: the item to count
		// parameter 3: the expected count
		testCount(new Trio<Integer>(3, 4, 5), 7, 0);
		testCount(new Trio<Integer>(7, 4, 5), 7, 1);
		testCount(new Trio<Integer>(3, 7, 5), 7, 1);
		testCount(new Trio<Integer>(3, 4, 7), 7, 1);
		testCount(new Trio<Integer>(7, 7, 2), 7, 2);
		testCount(new Trio<Integer>(7, 2, 7), 7, 2);
		testCount(new Trio<Integer>(2, 7, 7), 7, 2);
		testCount(new Trio<Integer>(7, 7, 7), 7, 3);
		wordTrio = new Trio<String>(new String("a"), new String("a"), new String("a"));
		testCount(wordTrio, "a", 3);
		testCount(wordTrio, new String("a"), 3);
		testCount(wordTrio, "A", 0);

		System.out.println("\n-----count tests: " + (passCount ? "Passed" : "*****AT LEAST ONE TEST FAILED*****"));
		
				
		System.out.println("\n------------------------------TESTING EQUALS METHOD------------------------------");	
		// parameter 1: the Trio object to invoke equals on
		// parameter 2: the Trio object to pass in to the equals method as a parameter
		// parameter 3: the expected result (true if the Trios are equivalent, false otherwise)
		// parameter 4: a description of the test
		testEquals(new Trio<String>(new String("aa"), new String("bb"), new String("cc")), 
				   new Trio<String>(new String("aa"), new String("bb"), new String("cc")), 
				   true, "no duplicates, same items in same order; items are objects (type String)");

		testEquals(new Trio<Character>('a', 'b', 'c'), 
				   new Trio<Character>('b', 'c', 'a'), 
				   true, "no duplicates, same items in different order");
		
		testEquals(new Trio<Integer>(5, 6, 8), 
				   new Trio<Integer>(8, 5, 6), 
				   true, "no duplicates, same items in different order");
		
		testEquals(new Trio<Integer>(500, 600, 700), 
				   new Trio<Integer>(500, 600, 700), 
				   true, "no duplicates, same items in same order; items are objects (type Integer)");
		
		testEquals(new Trio<Integer>(1, 2, 1), 
				   new Trio<Integer>(1, 1, 2), 
				   true, "duplicates, same items in different order");
		
		testEquals(new Trio<Integer>(2, 1, 1), 
				   new Trio<Integer>(1, 1, 2), 
				   true, "duplicates, same items in different order");
		
		testEquals(new Trio<Integer>(4, 7, 8), 
				  new Trio<Integer>(7, 4, 8), 
				  true, "no duplicates, same items in different order");
		
		testEquals(new Trio<Integer>(4, 4, 4), 
				   new Trio<Integer>(4, 4, 4), 
				   true, "duplicates, same items in same order");
		
		testEquals(new Trio<Integer>(1, 2, 3), 
				   new Trio<Integer>(3, 2, 1), 
				   true, "no duplicates, same items in different order");
		
		testEquals(new Trio<Integer>(1, 2, 3), 
				   new Trio<Integer>(4, 5, 6), 
				   false, "different items");
		
		testEquals(new Trio<Integer>(1, 2, 3), 
				   new Trio<Integer>(1, 3, 2), 
				   true, "no duplicates, same items in different order");
		
		testEquals(new Trio<Integer>(5, 5, 8), 
				   new Trio<Integer>(8, 5, 6), 
				   false, "different items");
		
		testEquals(new Trio<Integer>(1, 3, 3), 
			  	   new Trio<Integer>(1, 3, 4), 
			  	   false, "different items, one Trio with a duplicate, the other not");

		testEquals(new Trio<Integer>(1, 3, 4), 
			  	   new Trio<Integer>(1, 3, 3), 
			  	   false, "different items, one Trio with a duplicate, the other not");
		
		testEquals(new Trio<Integer>(2, 1, 3), 
				   new Trio<Integer>(2, 2, 2), 
				   false, "different items, one Trio with a duplicate, the other not");
		
		testEquals(new Trio<Integer>(2, 2, 1), 
				   new Trio<Integer>(2, 2, 3), 
				   false, "same duplicate items, different unique item");
		
		 testEquals(new Trio<Integer>(2, 1, 1), 
                  new Trio<Integer>(1, 1, 3), 
                  false, "same duplicate items, different unique item");
		
		testEquals(new Trio<Integer>(1, 1, 2), 
			  	   new Trio<Integer>(1, 2, 2), 
			  	   false, "same items, but different items are duplicated");
		
		testEquals(new Trio<Integer>(2, 1, 2), 
				   new Trio<Integer>(1, 2, 1), 
				   false, "same items, but different items are duplicated");
		
		testEquals(new Trio<String>(new String("aa"), new String("bb"), new String("cc")), 
				   new Trio<Integer>(2, 1, 2), 
				   false, "different items and types");
		

		
		// this method runs a few extra tests of the equals method
		// if there are errors, a description will be given in the output
		extraEqualsTests();
		
		System.out.println("\n-----equals tests: " + (passEquals ? "Passed" : "*****AT LEAST ONE TEST FAILED*****"));

		
		System.out.println("\n------------------------------TESTING COMPLETE------------------------------");	
		if(passGetterSetter && passReplaceAll && passHasDuplicates && passCount && passEquals) {
			System.out.println("----------Summary---------- All automated tests have passed. Be sure to manually check the output for the results of other tests.");
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
	 */
	public static <T> void testGetterSetter(Trio<T> trio, int position, T expectedValue) {
		System.out.println("\nGetting item " + position + " from Trio: " + trio);
		T actualResult;
		if(position==1) {
			actualResult = trio.getItem1();
		} else if(position==2) {
			actualResult = trio.getItem2();
		} else { // position==3 
			actualResult = trio.getItem3();
		}
		System.out.println("Expected=" + expectedValue);
		System.out.println("  Actual=" + actualResult);
		if(!expectedValue.equals(actualResult)) {
			passGetterSetter = false;
			System.out.println("**********TEST FAILED");
		}
	}
	public static <T> void testHasDuplicates(Trio<T> trio, boolean expectedResult, String testDescription) {
		String trioDescription = "(" + trio.getItem1() + ", " + trio.getItem2() + ", " + trio.getItem3() + ")";
		System.out.println("\nDuplicates? Trio: " + trioDescription);
		boolean actualResult = trio.hasDuplicates();
		System.out.println("Expected=" + expectedResult);
		System.out.println("  Actual=" + actualResult);	

		if(expectedResult!=actualResult) {
			passHasDuplicates = false;
			System.out.println("**********TEST FAILED:" + testDescription);
		}
	}
	public static <T> void testCount(Trio<T> trio, T value, int expectedCount) {
		String trioDescription = "(" + trio.getItem1() + ", " + trio.getItem2() + ", " + trio.getItem3() + ")";
		System.out.println("\nTrio: " + trioDescription);
		System.out.println("Item to count=" + value);
		int actualCount = trio.count(value);
		System.out.println("Expected=" + expectedCount);
		System.out.println("  Actual=" + actualCount);	

		if(expectedCount!=actualCount) {
			passCount = false;
			System.out.println("**********TEST FAILED");
		}
	}	
	public static <T, S> void testEquals(Trio<T> trio1, Trio<S> trio2, boolean expectedResult, String testDescription) {
		String trio1Description = "(" + trio1.getItem1() + ", " + trio1.getItem2() + ", " + trio1.getItem3() + ")";
		String trio2Description = "(" + trio2.getItem1() + ", " + trio2.getItem2() + ", " + trio2.getItem3() + ")";
		System.out.println("\nTrio1 (invoking object):  "  + trio1Description);
		System.out.println(   "Trio2 (parameter object): " + trio2Description);
		boolean actualResult = trio1.equals(trio2);
		System.out.println("Expected equals result=" + expectedResult);
		System.out.println("  Actual equals result=" + actualResult);		
		if(expectedResult!=actualResult) {
			passEquals = false;
			System.out.println("**********TEST FAILED: " + testDescription);
		}
		System.out.println("Check that the Trios have not been changed.");
		trio1Description = "(" + trio1.getItem1() + ", " + trio1.getItem2() + ", " + trio1.getItem3() + ")";
		trio2Description = "(" + trio2.getItem1() + ", " + trio2.getItem2() + ", " + trio2.getItem3() + ")";
		System.out.println("Trio1 (invoking object):  "  + trio1Description);
		System.out.println("Trio2 (parameter object): " + trio2Description);
	}
	public static void extraEqualsTests() {
		Trio<Integer> numberTrio1 = new Trio<Integer>(1, 2, 3);
		Trio<Integer> numberTrio2 = new Trio<Integer>(3, 2, 1);
		numberTrio1.equals(numberTrio2);
		if(!(numberTrio1.getItem1()==1 && numberTrio1.getItem2()==2 && numberTrio1.getItem3()==3)) {
			passEquals = false;
			System.out.println("\n**********TEST FAILED IN EXTRA EQUALS TEST: invoking object changed");
		}
		if(!(numberTrio2.getItem1()==3 && numberTrio2.getItem2()==2 && numberTrio2.getItem3()==1)) {
			passEquals = false;
			System.out.println("\n**********TEST FAILED IN EXTRA EQUALS TEST: parameter object changed");

		}
		numberTrio1 = new Trio<Integer>(1, 2, 3);
		boolean result = numberTrio1.equals(4); 
		if(result==true) {
			passEquals = false;
			System.out.println("\n**********TEST FAILED IN EXTRA EQUALS TEST: returned true when a non-Trio object passed in");
		}

		List<Trio<Integer>> trioList = new ArrayList<>();
		trioList.add(new Trio<Integer>(4, 5, 2));
		result = trioList.contains(new Trio<Integer>(2, 5, 4));
		if(result==false) {
			passEquals = false;
			System.out.println("\n**********TEST FAILED IN EXTRA EQUALS TEST: equals method failed when invoked by a method such as contains; check your method header!");

		}
	}
}

import java.util.*;

public class HomeworkM6Driver {
	
	public static List<Integer> findDuplicatesLinear(List<Integer> numberList) {
		// use a hash map to detect duplicates
		// the trick is because the possible integer values range from -5*N to +5*N
		// what we map has to be skewed to the correct index
		// e.g. numberList[i] = -50 where N = 10
		// then Hashmap[-50] makes no sense, so skew index to Hashmap[0] by adding +5*N to index value
		int len = numberList.size();
		int index, map;
		int[] dupeMap = new int[10*len + 1];  // simple array to track count of each value in numbersList
		ArrayList<Integer> dupeList = new ArrayList<Integer>(len); // array we return which contains duplicate values
		Arrays.fill(dupeMap,0); // init map to zeroes
		for (int val : numberList) {
			index = val + (5 * len);  // skew the index for our hash map
     		map = dupeMap[index] + 1; // add 1 to duplicate count
			dupeMap[index] = map;     // update hashmap
			if (dupeMap[index] > 1) {
				dupeList.add(val);    // found a duplicate so add to dupeList which also can contain dupes
			}
		}
		return dupeList;
		
	}
	
	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList<>();
		int numberOfTimesToTest = 5; // consider starting with 1 test until you are certain it is working correctly
		boolean passedAllTests = true;
		
		for(int i=0; i<numberOfTimesToTest && passedAllTests; i++) {
			System.out.println("\n-----------------------------TESTING findDuplicatesLinear METHOD TEST #" + (i+1) + "-----------------------------");
		
			/* consider making these values smaller until you have a working method.
			 * it might be easier to debug with a smaller list.
			 * you can then increase the numbers again for more robust testing.
			 */
			int listSize = 100; 
			int minimumSingleSuplicates = 10; // minimum number of numbers on the list that will appear twice (have a "single duplicate")
			int minimumDoubleDuplicates = 5;  // minimum number of numbers on the list that will appear three times (have a "double duplicate")
			fillNumberList(numbers, listSize, minimumSingleSuplicates, minimumDoubleDuplicates);
			
			// print the list sorted (might help with testing)
			Collections.sort(numbers);
			System.out.println("\nThe original list (sorted for display purposes only):");
			System.out.println(numbers);
	
			// reshuffle the list
			Collections.shuffle(numbers);
	
			// the results should be the same for both methods
			System.out.println("\nThe duplicate lists from both methods- these should match! (Note: methods are sorted for display purposes only.)");
			List<Integer> duplicateListFromBad = findDuplicatesBad(new ArrayList<Integer>(numbers));
			Collections.sort(duplicateListFromBad);
			System.out.println("Duplicate list from bad method:  \t" + duplicateListFromBad);
	
			List<Integer> duplicateListFromLinear = findDuplicatesLinear(new ArrayList<Integer>(numbers));
			Collections.sort(duplicateListFromLinear);
			System.out.println("Duplicate list from linear method:\t" + duplicateListFromLinear);
	
			boolean match = duplicateListFromBad.equals(duplicateListFromLinear);
			if(!match) {
				passedAllTests = false;
				System.out.flush();
				System.err.println("\n*****TEST FAILED: The duplicate lists do not match.");
			} else {
				System.out.println("\nTest #" + (i+1) + ": The duplicate lists match. Test passed!");
			}
		}
		if (passedAllTests) {
			System.out.println("\n-----------------------------TESTING findDuplicatesLinear METHOD FOR EFFICIENCY -----------------------------");
			System.out.println("\nAll tests of functionality passed. Now trying to examine efficiency... ");
			System.out.println("Note: If this takes more than a minute, you might not have a linear solution. If that happens, you should force the program to quit and double check all method calls and loops!");
			efficiencyTest();
			System.out.println("Efficiency test complete.");
		} 
	}
	
	public static List<Integer> findDuplicatesBad(List<Integer> numberList) {
		List<Integer> duplicateList = new ArrayList<Integer>();
		
		// loop a: this loop is O(n)- it iterates over the whole list
		for(int i=0; i<numberList.size(); i++) {
			int numberEvaluating = numberList.get(i);
			boolean duplicateFound = false;
			
			// loop b: this loop starts at i+1 and goes to the end of the list OR until a duplicate is found
			for(int j=i+1; j<numberList.size() && !duplicateFound; j++) {
				int numberChecking = numberList.get(j);
				
				// we have found a duplicate that hasn't yet been put on the duplicateList
				if(numberEvaluating==numberChecking && !duplicateList.contains(numberEvaluating)) {
					duplicateFound = true; 
					
					// loop c: after a duplicate is found, we won't return to loop b
					// instead, loop c finishes checking the rest of the list and puts all copies of 
					// of the current duplicate on the duplicateList
					for(int k=j; k<numberList.size(); k++) {
						if(numberChecking==Integer.valueOf(numberList.get(k))) {
							duplicateList.add(numberChecking);
						}
					}
				}
			}
		}
		return duplicateList;
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

	public static void fillNumberList(List<Integer> numbers, int listSize, int minimumSingleSuplicates, int minimumDoubleDuplicates) {
		Random generator = new Random();
		int max = 5 * listSize;
		int min = -1 * max;
		int randomRange = max - min + 1;

		// add the first round of numbers
		int numberOfNumbersToAdd = listSize - minimumSingleSuplicates - minimumDoubleDuplicates;
		numbers.add(min); // adding the min and max number to help test for array out of bounds errors
		numbers.add(min);
		numbers.add(max);
		numbers.add(max);
		numbers.add(0);
		numbers.add(0); // adding zero as a special test case
		numberOfNumbersToAdd -= 6;
		for (int i = 0; i < numberOfNumbersToAdd; i++) {
			numbers.add(generator.nextInt(randomRange) + min);
		}

		// add duplicate numbers
		Collections.shuffle(numbers);
		for (int i = 0; i < minimumSingleSuplicates; i++) {
			numbers.add(numbers.get(i));
		}
		for (int i = 0; i < minimumDoubleDuplicates; i++) {
			numbers.add(numbers.get(i));
		}		
	}

	public static void efficiencyTest() {
		System.out.println("Testing...");
		List<Integer> numbers = new ArrayList<Integer>();
		int listSize = 1000000;
		int minimumSingleSuplicates = 100; 
		int minimumDoubleDuplicates = 50;  
		fillNumberList(numbers, listSize, minimumSingleSuplicates, minimumDoubleDuplicates);
	
		long startTime = System.currentTimeMillis();
		List<Integer> duplicateListFromLinear = findDuplicatesLinear(new ArrayList<Integer>(numbers));
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		//System.out.println("Elapsed time = " + (elapsedTime));
		
		if (elapsedTime>1100) {
			System.out.flush();
			System.err.println("**********WARNING! Your solution might not be linear. Double check all method calls and loops!");
		} else {
			System.out.println("Your solution appears to be linear. This test cannot know for sure, but it does appear to be!");
		}

	}


}

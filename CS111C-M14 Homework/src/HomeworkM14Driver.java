import java.util.*;

public class HomeworkM14Driver {

	private static boolean allTestsPassed = true; 
	
	public static void main(String[] args) {
	
		System.out.println("-------------------------------TESTING EQUALS AND HASHCODE METHOD IN STUDENT CLASS -------------------------------");
		Student testStudent = new Student(123, "Sam", "Studier", true);
		Student equivalentStudent = new Student(123, "Sam", "Studier", true);
		// parameter 1: studentA
		// parameter 2: studentB
		// parameter 3: expected result of studentA.equals(studentB)
		// parameter 4: expected result of studentA.hashCode()==studentB.hashCode()
		// parameter 5: test description
		testEqualsHashCode(testStudent, equivalentStudent, true, true, "equivalent students");
		
		testEqualsHashCode(testStudent, testStudent, true, true, "equivalent students- aliases");
		
		equivalentStudent = new Student(Integer.valueOf(123), new String("Sam"), new String("Studier"), Boolean.valueOf(true));
		testEqualsHashCode(testStudent, equivalentStudent, true, true, "equivalent students");
		
		Student differentStudent = new Student(123, "Sam", "Studier", false);
		testEqualsHashCode(testStudent, differentStudent, false, false, "non-equivalent students: different tuition status");
		
		differentStudent = new Student(123, "Sam", "StudierMore", true);
		testEqualsHashCode(testStudent, differentStudent, false, false, "non-equivalent students: different last name");
		
		differentStudent = new Student(123, "Sammy", "Studier", true);
		testEqualsHashCode(testStudent, differentStudent, false, false, "non-equivalent students: different first name");
		
		differentStudent = new Student(1123, "Sam", "Studier", true);
		testEqualsHashCode(testStudent, differentStudent, false, false, "non-equivalent students: different id");
		
		differentStudent = new Student(456, "Sam", "Studier", true);
		testEqualsHashCode(testStudent, differentStudent, false, false, "non-equivalent students: different id");

		differentStudent = new Student(123, "Pat", "Procrastinator", false);
		testEqualsHashCode(testStudent, differentStudent, false, false, "non-equivalent students: same id, different names");


		System.out.println("-------------------------------TESTING VOTER CLASS -------------------------------");

		int[] tableSizes = {5, 2, 10};
		for (int tableSize : tableSizes) {
			System.out.println("\n--------------------TESTING WITH TABLE SIZE " + tableSize+"--------------------");
			System.out.println("\n-----TESTING ADD TO VOTER HASHTABLE");

			Voter[] theVoters = { new Voter(912, "Delta Demm"), new Voter(670, "Riley Repp"), new Voter(381, "Ivan Indie"),
					new Voter(497, "Greta Green"), new Voter(301, "Liz Libert"), new Voter(541, "Ursula Undec")	};

			VoterHashTableSeparateChaining voterTable = new VoterHashTableSeparateChaining(tableSize);
			System.out.println("Each voter should be added to the table- each should print true.");
			for (Voter voter : theVoters) {
				boolean added = voterTable.addVoter(voter);
				System.out.println("Added " + voter.getName() + "\tadded?\t" + added);
				if(!added) {
					allTestsPassed = false;
					System.out.println("*****TEST FAILED. Voter not added.");
				}
			}
			System.out.println("\nCompare your table to the location of where each voter should be. The locations should match.");
			System.out.println("\nHere is your table that shows the actual locations.");
			voterTable.printTable();
			System.out.println();

			System.out.println("Here is the expected location where each voter *should* be.\nThe locations below should match the locations in the table above.");
			for (Voter v : theVoters) {
				System.out.println(v.getName() + "\tLocation " + voterTable.calculateArrayLocation(v.getId()));
			}

			
			System.out.println("\n-----TESTING RETRIEVE FROM-----");
			System.out.println("Get these voters based on their ID. The voter retrieved should match what is asked for.");
			for (Voter voter : theVoters) {
				Voter retrievedVoter = voterTable.getVoter(voter.getId());
				System.out.println("Retreiving " + voter.getId() + " (" + voter.getName() + ") \t: " + retrievedVoter);
				if(retrievedVoter==null || retrievedVoter.getId()!=(voter.getId()) || !retrievedVoter.getName().equals((voter.getName()))) {
					allTestsPassed = false;
					System.out.println("*****TEST FAILED. Retrieved voter does not match.");
				}
			}
			Voter nonExistentVoter = new Voter(412, "Nancy NotAVoter");
			Voter retrievedVoter = voterTable.getVoter(nonExistentVoter.getId());
			System.out.println("Retrieved Nancy NotAVoter? \tshould be null: " + retrievedVoter);
			if(retrievedVoter!=null) {
				allTestsPassed = false;
				System.out.println("*****TEST FAILED. null should be returned when attempting to retreive a voter not in the table.");
			}
			
			// COMMENT THIS SECTION OUT IF NOT COMPLETING THE EXTRA CREDIT
			System.out.println("\n-----TESTING EXTRA CREDIT- NO ADDING DUPLICATES-----");
			System.out.println("These voters are duplicates and should not be added- each should print false.");
			for (Voter voter : theVoters) {
				boolean added = voterTable.addVoter(voter);
				System.out.println(voter.getName() + "\tadded again?\t" + added);
				if(added) {
					allTestsPassed = false;
					System.out.println("*****TEST FAILED for extra credit. Duplicate voter should not be added.");
				}
			}		
			Voter[] duplicateVoters = {
					new Voter(912, "Delta Demm"), new Voter(Integer.valueOf(912), new String("Delta Demm")),
					new Voter(541, "Ursula Undec"), new Voter(Integer.valueOf(541), new String("Ursula Undec"))};
			for(Voter duplicateVoter : duplicateVoters) {
				boolean added = voterTable.addVoter(duplicateVoter);
				System.out.println(duplicateVoter.getName() + "\tadded again?:\t" + added);
				if(added) {
					allTestsPassed = false;
					System.out.println("*****TEST FAILED for extra credit. Duplicate voter should not be added.");
				}
			
			}
		}
		
		System.out.println("\n\n-----------------------------TESTING COMPLETE-----------------------------");
		if(allTestsPassed) {
			System.out.println("----------Summary---------- \nAll automated tests have passed. \nBe sure to manually look at the output.\nBe sure to manually review your code for style and efficiency.");
		} else {
			System.out.flush();
			System.err.println("**********Summary********** ERROR: There is failure in at least one automated test. Review the output above for details.");
		}
	}	
	
	
	/*
	 * The methods below are designed to help support the tests cases run from main. You don't
	 * need to use, modify, or understand these methods. You can safely ignore them. :) 
	 * 
	 */
	public static void testEqualsHashCode(Student studentA, Student studentB, boolean expectedEqualsResult, boolean expectedHashResult, String testDescription) {
		System.out.println("\nTesting with: \n   StudentA [" + studentA + "] and StudentB [" + studentB +"]");
		boolean aliases = studentA==studentB;
		if(expectedEqualsResult==true) {
			System.out.println("   Students " + ( (aliases) ? "are" : "are NOT") + " aliases.");
		} 
		boolean actualEqualsResult = studentA.equals(studentB);
		boolean actualHashResult = studentA.hashCode()==studentB.hashCode();
		System.out.println("Expected result of equals: " + expectedEqualsResult);
		System.out.println("  Actual result of equals: " + actualEqualsResult);
		if(expectedEqualsResult!=actualEqualsResult) {
			allTestsPassed = false;
			System.out.println("*****TEST FAILED: equals method for test: " + testDescription);
		}
		if(expectedEqualsResult==true) {
			System.out.println("Expected result of comparing hash codes: " + expectedHashResult);
			System.out.println("  Actual result of comparing hash codes: " + actualHashResult);
			if(expectedHashResult!=actualHashResult) {
				allTestsPassed = false;
				System.out.println("*****TEST FAILED: test of hash code failed: " + testDescription);
			}		
		} else { // expectedResult==false
			if(expectedHashResult!=actualHashResult) {
				System.out.println("***Note: these non-equivalent objects have the same hash code. This doesn't violate the contract, but you might review your hashCode method to see if it can be improved!");
			}	
			if(actualEqualsResult==true && actualHashResult==false) {
				allTestsPassed = false;
				System.out.println("*****TEST FAILED: hash code test. Your equals method says these students are equal (which is incorrect).");
				System.out.println("     Since your equals method returned true, your hash code method should have also returned true.");
			}
		}
		
		Set<Student> studentSet = new HashSet<>();
		studentSet.add(studentA);
		boolean expectedSetResult = !expectedEqualsResult;
		System.out.println("StudentA added to a Set<Student>");
		System.out.println("Expected result of adding StudentB to the set: " + expectedSetResult);
		boolean actualSetResult = studentSet.add(studentB);
		System.out.println("  Actual result of adding StudentB to the set: " + actualSetResult);
		if(expectedSetResult!=actualSetResult) {
			allTestsPassed = false;
			System.out.println("*****TEST FAILED: test of adding to a set failed fpr test: " + testDescription);
		}

	}

}

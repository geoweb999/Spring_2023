import java.util.*;

public class HomeworkM11Driver {

	// note: don't worry about generics here; i've omitted them and 
	// suppressed the warnings so you can focus on the queue logic
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void splice(QueueInterface firstQueue, QueueInterface secondQueue) {
     	QueueInterface tempQueue = new ArrayQueue<>();
     	
     	while (!secondQueue.isEmpty()) {
     		Object element = secondQueue.dequeue();
     		tempQueue.enqueue(element);
     		firstQueue.enqueue(element);
     	}
     	
     	while (!tempQueue.isEmpty()) {
     		Object element = tempQueue.dequeue();
     		secondQueue.enqueue(element);
     	}
	}
	
	
	public static void main(String[] args) {

		System.out.println("---------------------------------------TESTING CLIENT VIEW SPLICE---------------------------------------");
		// parameter 1: the contents of Q1 (listed front to back)
		// parameter 2: the contents of Q2 (listed front to back)
		// parameter 3: a description of the test 
		testClientSplice(new Integer[]{},            new Integer[]{},                 "splice empty queue to empty queue");
		testClientSplice(new Integer[]{4, 8, 9, 2},  new Integer[]{},                 "splice empty queue to non-empty queue");
		testClientSplice(new Integer[]{},            new Integer[]{3, 7, 1, 2, 5},    "splice non-empty queue to empty queue");
		testClientSplice(new Integer[]{},            new Integer[]{1, 3, 7, 6, 3, 1}, "splice non-empty queue to empty queue");
		testClientSplice(new Integer[]{4, 2, 3},     new Integer[]{1},                "splice singleton queue to non-empty queue");
		testClientSplice(new Integer[]{7},    	 	 new Integer[]{4, 9, 1, 3},       "splice non-empty queue to singleton queue");
		testClientSplice(new Integer[]{1, 3, 2, 4},  new Integer[]{6, 9, 3},          "splice non-empty queue to non-empty queue");
		testClientSplice(new String[]{"a", "b", "c", "d"}, new String[]{"d", "e", "d"},  "splice Strings"); 
	
		
		System.out.println("\n\n---------------------------------------TESTING ARRAYQUEUE SPLICE---------------------------------------");
		testArraySplice(new Integer[]{},            new Integer[]{},                 "splice empty queue to empty queue");
		testArraySplice(new Integer[]{4, 8, 9, 2},  new Integer[]{},                 "splice empty queue to non-empty queue");
		testArraySplice(new Integer[]{4, 2, 3},     new Integer[]{1},                "splice singleton queue to non-empty queue");
		testArraySplice(new Integer[]{7},    	 	new Integer[]{4, 9, 1, 3},       "splice non-empty queue to singleton queue");
		testArraySplice(new Integer[]{},            new Integer[]{3, 7, 1, 2, 5},    "splice non-empty queue to empty queue");
		testArraySplice(new Integer[]{},            new Integer[]{1, 3, 7, 6, 3, 1}, "splice non-empty queue to empty queue");
		testArraySplice(new Integer[]{1, 3, 2, 4},  new Integer[]{6, 9, 3},          "splice non-empty queue to non-empty queue");
		testArraySplice(new String[]{"a", "b", "c", "d"}, new String[]{"d", "e", "d"},  "splice Strings");

		// test the wrap-around nature of ArrayQueue
		// for these tests, extra elements are enqueued and dequeued to force the contents to wrap around the array
		testArraySpliceWrap(new Integer[]{4, 8, 9, 2},  new Integer[]{1, 3, 7, 6},   "test of wrap around behavior");
		

		System.out.println("\n\n---------------------------------------TESTING LINKEDQUEUE SPLICE---------------------------------------");
		testLinkedSplice(new Integer[]{},            new Integer[]{},                 "splice empty queue to empty queue");
		testLinkedSplice(new Integer[]{4, 8, 9, 2},  new Integer[]{},                 "splice empty queue to non-empty queue");
		testLinkedSplice(new Integer[]{4, 2, 3},     new Integer[]{1},                "splice singleton queue to non-empty queue");
		testLinkedSplice(new Integer[]{7},     		 new Integer[]{4, 9, 1, 3},       "splice non-empty queue to singleton queue");
		testLinkedSplice(new Integer[]{},            new Integer[]{3, 7, 1, 2, 5},    "splice non-empty queue to empty queue");
		testLinkedSplice(new Integer[]{},            new Integer[]{1, 3, 7, 6, 3, 1}, "splice non-empty queue to empty queue");
		testLinkedSplice(new Integer[]{1, 3, 2, 4},  new Integer[]{6, 9, 3},          "splice non-empty queue to non-empty queue");
		testLinkedSplice(new String[]{"a", "b", "c", "d"}, new String[]{"d", "e", "d"},  "splice Strings");
		testLinkedSplice(new Integer[]{4, 2, 3},     new Integer[]{1},                "splice singleton queue to non-empty queue");
		
		// test to make sure that the chains aren't actually "hooked together" but that the actual elements are spliced to q1
		testLinkedSpliceConnected();

		
		// UN-COMMENT THIS SECTION TO TEST EXTRA CREDIT	   
		
		System.out.println("\n\n---------------------------------------TESTING GETSECOND IN LINKEDQUEUE---------------------------------------");
		// parameter 1: the contents of Q1, listed front to back
		// parameter 2: if included, this is the expected result of invoking getSecond;
		//              if there is no value included as the second parameter, the expected 
		//              result is to return null or throw an exception
		// parameter 3: a description of the test
		testGetSecondLinked(new Integer[]{},                        "empty queue");
		testGetSecondLinked(new Integer[]{1},                       "singleton queue");
		testGetSecondLinked(new Integer[]{1, 2},          2,        "even length queue");
		testGetSecondLinked(new Integer[]{4, 5, 6},       5,        "odd length queue");
		testGetSecondLinked(new Integer[]{3, 8, 7, 2},    8,        "odd length queue");
		testGetSecondLinked(new String[]{"a", "b", "c", "d"},  "b", "test with Strings");
   

		System.out.println("\n\n---------------------------------------TESTING GETSECOND IN ARRAYQUEUE---------------------------------------");
		testGetSecondArray(new Integer[]{},                        "empty queue");
		testGetSecondArray(new Integer[]{1},                       "singleton queue");
		testGetSecondArray(new Integer[]{1, 2},          2,        "even length queue");
		testGetSecondArray(new Integer[]{4, 5, 6},       5,        "odd length queue");
		testGetSecondArray(new Integer[]{3, 8, 7, 2},    8,        "odd length queue");
		testGetSecondArray(new String[]{"a", "b", "c", "d"},  "b", "test with Strings");   
		
		// tests three special cases that might come up with arrays:
		//   front index not in position 0
		//   front index > back index
		//   front index == array.length-1
		testArrayGetSecondSpecialCases();
		
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
	private static boolean allTestsPassed = true; 

	private static enum SpliceType {CLIENT, ARRAY, LINKED };
	private static <T> T[] createExpectedResultsContents(T[] contents1, T[] contents2) {
		int resultLength = contents1.length + contents2.length;
		T[] result = (T[]) new Object[resultLength];
		int resultIndex=0;
		for(T content : contents1) {
			result[resultIndex] = content;
			resultIndex++;
		}
		for(T content : contents2) {
			result[resultIndex] = content;
			resultIndex++;
		}
		return result;
	}
	public static <T> void testClientSplice(T[] contents1, T[] contents2, String testDescription) {
		QueueInterface<T> q1 = new LinkedQueue<T>();
		QueueInterface<T> q2 = new ArrayQueue<T>();

		testSplice(contents1, q1, contents2, q2, true, testDescription, SpliceType.CLIENT);
	}	
	public static <T> void testArraySplice(T[] contents1, T[] contents2,  String testDescription) {
		QueueInterface<T> q1 = new ArrayQueue<T>();
		QueueInterface<T> q2 = new ArrayQueue<T>();

		testSplice(contents1, q1, contents2, q2, true, testDescription, SpliceType.ARRAY);
	}
	public static void testArraySpliceWrap(Integer[] contents1, Integer[] contents2, String testDescription) {
		Integer[] expectedResultContents = new Integer[contents1.length+contents2.length];
		Object[] objectResults = createExpectedResultsContents(contents1, contents2);
		System.arraycopy(objectResults, 0, expectedResultContents, 0, expectedResultContents.length);
		QueueInterface<Integer> q1 = new ArrayQueue<Integer>(4);
		QueueInterface<Integer> q2 = new ArrayQueue<Integer>(4);
		
		q1.enqueue(99); q1.enqueue(99); q1.enqueue(99);
		q1.enqueue(contents1[0]);
		q1.dequeue(); q1.dequeue(); q1.dequeue(); // forcing the contents to wrap around the array
		q1.enqueue(contents1[1]); 	q1.enqueue(contents1[2]); q1.enqueue(contents1[3]);

		q2.enqueue(99); q2.enqueue(99); q2.enqueue(99);
		q2.enqueue(contents2[0]);
		q2.dequeue(); q2.dequeue(); q2.dequeue(); // forcing the contents to wrap around the array
		q2.enqueue(contents2[1]); 	q2.enqueue(contents2[2]); q2.enqueue(contents2[3]);

		testSplice(contents1, q1, contents2, q2, false, testDescription, SpliceType.ARRAY);
	}
	public static void testLinkedSpliceConnected() {
		LinkedQueue<Integer> q1 = new LinkedQueue<Integer>();
		LinkedQueue<Integer> q2 = new LinkedQueue<Integer>();
	
		q1.enqueue(1);
		q1.enqueue(2);
		q2.enqueue(3);
		q2.enqueue(4);
		q1.splice(q2);
		q2.enqueue(5);
		q1.enqueue(6);
		q2.dequeue();
		q2.dequeue();
		System.out.println("\nTesting chains being linked together...");
		System.out.println("Q1 holds: [1, 2] and  Q2 holds: [3, 4].");
		System.out.println("Splice. \nNow Q1 holds [1, 2, 3, 4]. Q2 should still just hold [3, 4].");
		System.out.println("Next: q2.enqueue(5) and by q1.enqueue(6). Q1 should hold [1, 2, 3, 4, 6] and Q2 should hold [3, 4, 5]");
		System.out.println("Next: q2.dequeue() and then q2.dequeue() again. Q2 should now hold [5].");
		System.out.println("Expected front of q2: " + q2.getFront());
		System.out.println("  Actual front of q2: " + q2.getFront());
		if(q2.getFront()!=5) {
			allTestsPassed = false;
			System.out.println("*****TEST FAILED: Splice should add elements to q1. It should not link the chains together."); 
			System.out.println("   As written, changes to q1 can affect q2 after the splice is complete and vice versa."); 
		} else {
			System.out.println("Test passed!");
		}
	}
	public static <T> void testLinkedSplice(T[] contents1, T[] contents2, String testDescription) {
		QueueInterface<T> q1 = new LinkedQueue<T>();
		QueueInterface<T> q2 = new LinkedQueue<T>();
		testSplice(contents1, q1, contents2, q2, true, testDescription, SpliceType.LINKED);
	}	
	public static <T> void testSplice(T[] contents1, QueueInterface<T> q1, T[] contents2, QueueInterface<T> q2, boolean build, String testDescription, SpliceType type) {
		T[] expectedResultContents = createExpectedResultsContents(contents1, contents2);
		if(build) {
			q1.clear();
			q2.clear();
			for(T element : contents1) {
				q1.enqueue(element);
			}
			for(T element : contents2) {
				q2.enqueue(element);
			}
		}

		if(type==SpliceType.CLIENT) { 	
			splice(q1, q2);
		} else if(type==SpliceType.ARRAY) {
			( (ArrayQueue<T>)q1).splice(( (ArrayQueue<T>)q2));
		} else if(type==SpliceType.LINKED) {
			( (LinkedQueue<T>)q1).splice(( (LinkedQueue<T>)q2));
		}
		T[] actualArrayQ1 = getArray(q1);
		T[] actualArrayQ2 = getArray(q2);
		
		int actualSizeQ1 = getQueueSize(q1);
		int expectedSizeQ1 = expectedResultContents.length;
		
		int actualSizeQ2 = getQueueSize(q2);
		int expectedSizeQ2 = contents2.length;

		System.out.println("\nQ1 before splice: " + Arrays.toString(contents1));
		System.out.println("Q2 before splice: " + Arrays.toString(contents2));

		if(type!=SpliceType.LINKED) {
			System.out.println("Expected Q1 after splice: " + Arrays.toString(expectedResultContents));
			System.out.println("  Actual Q1 after splice: " + Arrays.toString(actualArrayQ1));
			System.out.println("Expected Q2 after splice: " + Arrays.toString(contents2));
			System.out.println("  Actual Q2 after splice: " + Arrays.toString(actualArrayQ2) );
		
		} else if(type==SpliceType.LINKED) {	
			T expectedFirstQ1 = expectedResultContents.length==0 ? null : expectedResultContents[0];
			T expectedLastQ1 = expectedResultContents.length==0 ? null : expectedResultContents[expectedResultContents.length-1];
			T expectedFirstQ2 = contents2.length==0 ? null : contents2[0];
			T expectedLastQ2 = contents2.length==0 ? null : contents2[contents2.length-1];
			
			T actualFirstQ1 = q1.isEmpty() ? null : ((LinkedQueue<T>)q1).firstNode.data;
			T actualLastQ1 = q1.isEmpty() ? null : ((LinkedQueue<T>)q1).lastNode.data;
			T actualFirstQ2 = q2.isEmpty() ? null : ((LinkedQueue<T>)q2).firstNode.data;
			T actualLastQ2 = q2.isEmpty() ? null : ((LinkedQueue<T>)q2).lastNode.data;
			System.out.println("Expected Q1 after splice: " + Arrays.toString(expectedResultContents));
			System.out.println("  Actual Q1 after splice: " + Arrays.toString(actualArrayQ1));
			System.out.println("         Q1 expected firstNode: " + expectedFirstQ1 + " \tlastNode: " + expectedLastQ1);
			System.out.println("         Q1   actual firstNode: " + actualFirstQ1 + " \tlastNode: " + actualLastQ1);

			System.out.println("Expected Q2 after splice: " + Arrays.toString(contents2));
			System.out.println("  Actual Q2 after splice: " + Arrays.toString(actualArrayQ2) );
			System.out.println("         Q2 expected firstNode: " + expectedFirstQ2 + " \tlastNode: " + expectedLastQ2);
			System.out.println("         Q2   actual firstNode: " + actualFirstQ2 + " \tlastNode: " + actualLastQ2);
							
			boolean q1FirstMatches = dataMatches(expectedFirstQ1, actualFirstQ1);
			boolean q1LastMatches = dataMatches(expectedLastQ1, actualLastQ1);
			boolean q2FirstMatches = dataMatches(expectedFirstQ2, actualFirstQ2);
			boolean q2LastMatches = dataMatches(expectedLastQ2, actualLastQ2);
			
			if(!q1FirstMatches || !q1LastMatches || !q2FirstMatches || !q2LastMatches) {
				allTestsPassed = false;
				System.out.println("*****TEST FAILED. firstNode or lastNode doesn't match for at least one queue after the splice for test: " + testDescription);
			}
		} 	
		boolean correctContents = true;
		for(int i=0; !q1.isEmpty() && correctContents && i<expectedResultContents.length; i++) {
			T qElement = q1.dequeue();
			if(!qElement.equals(expectedResultContents[i])) {
				correctContents = false;
			}
		}
		if(!correctContents || !q1.isEmpty()) {
			allTestsPassed = false;
			System.out.println("*****TEST FAILED. Q1 incorrect contents after splice for test: " + testDescription);
		}		
		if(actualSizeQ1!=expectedSizeQ1 || actualSizeQ2!=expectedSizeQ2) {
			allTestsPassed = false;
			System.out.println("*****TEST FAILED. Actual size of Q1 or Q2 after the splice is incorrect for test: " + testDescription);
			System.out.println("   Expected size of Q1 after slice=" + expectedSizeQ1 + " and expected size of Q2 after slice=" + expectedSizeQ2);
		}

	}
	private static <T> boolean dataMatches(T expected, T actual) {
		if(expected==null || actual==null) {
			if(expected!=actual) {
				return false;
			}
		} else { // neither is null
			if(!expected.equals(actual)) {
				return false;
			}
		}
		return true;
	}
	private static <T> T[] getArray(QueueInterface<T> queue) {
		int queueSize = getQueueSize(queue);
		T[] array = (T[]) new Object[queueSize];
		QueueInterface<T> copyQueue = new LinkedQueue<T>();
		while(!queue.isEmpty()) {
			copyQueue.enqueue(queue.dequeue());
		}
		for(int i=0; !copyQueue.isEmpty(); i++) {
			T element = copyQueue.dequeue();
			queue.enqueue(element);
			array[i] = element;
		}
		return array;	
	}
	private static <T> int getQueueSize(QueueInterface<T> queue) {
		int size = 0;
		QueueInterface<T> copyQueue = new LinkedQueue<T>();
		while(!queue.isEmpty()) {
			size++;
			copyQueue.enqueue(queue.dequeue());
		}
		while(!copyQueue.isEmpty()) {
			queue.enqueue(copyQueue.dequeue());
		}
		return size;
	}	
	public static <T> void testGetSecondLinked(T[] contents, String testDescription) {
		testGetSecondLinked(contents, null, testDescription);
	}
	public static <T> void testGetSecondLinked(T[] contents, T expectedResult, String testDescription) {
		LinkedQueue<T> queue = new LinkedQueue<T>();
		LinkedQueue<T> printQueue = new LinkedQueue<T>();
		for(T element : contents) {
			queue.enqueue(element);
			printQueue.enqueue(element);
		}
		testGetSecond(queue, printQueue, expectedResult, testDescription);
	}
	public static <T> void testGetSecondArray(T[] contents, String testDescription) {
		testGetSecondArray(contents, null, testDescription);
	}
	public static <T> void testGetSecondArray(T[] contents, T expectedResult, String testDescription) {
		ArrayQueue<T> queue = new ArrayQueue<T>();
		ArrayQueue<T> printQueue = new ArrayQueue<T>();
		for(T element : contents) {
			queue.enqueue(element);
			printQueue.enqueue(element);
		}
		testGetSecond(queue, printQueue, expectedResult, testDescription);
	}
	private static <T> void testGetSecond(QueueInterface<T> queue, QueueInterface<T> printQueue, T expectedResult, String testDescription) {
		List<T> printList = createDisplayListFromQueue(printQueue);
		boolean exceptionThrown = false;
		T actualResult;
		Exception exception = null;
		try {
			actualResult = queue.getSecond();
		} catch(Exception ex) {
			// Normally this is bad practice to catch a general exception object. Don't do this!! 
			// I had to do this here to make this general since you can choose different kinds of exceptions for this question.
			exceptionThrown = true;
			actualResult = null;
			exception = ex;
		}
		
		int actualAfterSize = getQueueSize(queue);
		int expectedAfterSize = printList.size();
		
		System.out.println("\nContents of queue: Front*" + printList + "*Back");
		if(expectedResult==null) {
			System.out.println("Expected getSecond behavior: exception thrown of an appropriate type");
		} else {
			System.out.println("Expected getSecond = " + expectedResult);
		}
		if(exceptionThrown) {
			System.out.println("  Actual getSecond behavior: " + "exception thrown of type " + exception.getClass().getName());
		} else {
			System.out.println("  Actual getSecond = " + actualResult);
		}
		
		if(expectedResult==null) {
			if(! (actualResult==null || exceptionThrown)) {
				allTestsPassed = false;
				System.out.println("*****TEST FAILED for: " + testDescription);
				System.out.println("     Expected getSecond contents are either null or an exception should be thrown.");
			}
		} else { 
			if(!expectedResult.equals(actualResult)) {
				allTestsPassed = false;
				System.out.println("*****TEST FAILED for: " + testDescription);
			}
		}
		if(actualAfterSize!=expectedAfterSize) {
			allTestsPassed = false;
			System.out.println("*****TEST FAILED. Size of queue should not change. Test: " + testDescription);
			System.out.println("     Expected size = " + expectedAfterSize);
			System.out.println("       Actual size = " + actualAfterSize);		
		}		
	}
	public static void testArrayGetSecondSpecialCases() {
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>(10);
		
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.dequeue();
		queue.dequeue();
		
		Integer[] contents = new Integer[] {3,4};
		
		ArrayQueue<Integer> printQueue = new ArrayQueue<Integer>();
		for(Integer element : contents) {
			printQueue.enqueue(element);
		}
		testGetSecond(queue, printQueue, 4, "front index not in position 0");
		
		queue = new ArrayQueue<Integer>(4);
		contents = new Integer[] {1, 2, 3, 4};
		printQueue = new ArrayQueue<Integer>();
		for(Integer element : contents) {
			printQueue.enqueue(element);
		}
		queue.enqueue(99); queue.enqueue(99); queue.enqueue(99);
		queue.enqueue(1);
		queue.dequeue(); queue.dequeue(); queue.dequeue(); // forcing the contents to wrap around the array
		queue.enqueue(2); queue.enqueue(3); queue.enqueue(4);

		testGetSecond(queue, printQueue, 2, "front index > back index");
		
		queue = new ArrayQueue<Integer>(3);
		contents = new Integer[] {4, 5};
		printQueue = new ArrayQueue<Integer>();
		for(Integer element : contents) {
			printQueue.enqueue(element);
		}
		queue.enqueue(1); queue.enqueue(2); queue.enqueue(3);
		queue.dequeue(); queue.dequeue();
		queue.enqueue(4); queue.enqueue(5); 
		queue.dequeue(); 

		testGetSecond(queue, printQueue, 5, "front index == array.length-1");
	}
	private static <T> List<T> createDisplayListFromQueue(QueueInterface<T> queue) {
		List<T> printList = new LinkedList<T>();
		while(!queue.isEmpty()) {
			printList.add(queue.dequeue());
		}
		return printList;
	}
}
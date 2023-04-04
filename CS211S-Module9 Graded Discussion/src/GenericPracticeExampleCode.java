import java.util.*;

public class GenericPracticeExampleCode {

	public static void main(String[] args) {
		Integer[] numberArray = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		System.out.println("Array:\t" + Arrays.toString(numberArray));
		System.out.print("Every other:\t");
		printEveryOther(numberArray);
		System.out.println("Values greater than 3: " + countGreaterThan(numberArray, 3));
		
		String[] stringArray = new String[] {"a", "b", "c", "d", "e"};
		System.out.println("\nArray:\t" + Arrays.toString(stringArray));
		System.out.print("Every other:\t");
		printEveryOther(stringArray);
		System.out.println("Values greater than a: " + countGreaterThan(stringArray, "a"));
		System.out.println("Values greater than g: " + countGreaterThan(stringArray, "g"));
		
		System.out.println();
		Pair<Integer> numberPair = new Pair<>(4,3);
		numberPair.order();
		Pair<String> stringPair = new Pair<>("hi", "hello");
		stringPair.order();
		Quad<Integer,String> numberStringQuad = new Quad<>(numberPair, stringPair);
		System.out.println(numberStringQuad);
		
	}
	
	public static <T> void printEveryOther(T[] array) {
		for(int i=0; i<array.length; i+=2) {
			System.out.print(array[i] + "  ");
		}
		System.out.println();
	}
	
	public static <T extends Comparable<? super T>> int countGreaterThan(T[] array, T target) {
		int count = 0;
		for(T element : array) {
			if(element.compareTo(target)>0) {
				count++;
			}
		}
		return count;
	}
	
}

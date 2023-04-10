
public class GenericGreaterThan {
	
	public static <T extends Comparable<? super T>> int countGreaterThan(T[] array, T target) {
	
		int count = 0;
		for (T item : array) {
			if (item.compareTo(target) > 0) {
				count++;
			}
		}
		return count;
	}

    public static void main(String[] args) {
        Integer[] intArray = {1, 5, 2, 3, 1, 4};
        int intCount = countGreaterThan(intArray, 2);
        System.out.println("Number of elements greater than 2 (expect 3): " + intCount);

        String[] strArray = {"a","b","c","d","e"};
        int strCount = countGreaterThan(strArray, "b");
        System.out.println("Number of elements greater than 'b' (expect 3): " + strCount);
    }
}

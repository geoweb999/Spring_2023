import java.util.ArrayList;

public class RecursiveIndexFinder {

    public static ArrayList<Integer> findIndicesRecursiveSorted(Comparable[] array, Comparable target) {
        return recuriveIndexFinderHelper(array, target, 0, array.length - 1);
    }

    private static ArrayList<Integer> recuriveIndexFinderHelper(Comparable[] array, Comparable target, int start, int end) {
        ArrayList<Integer> indices = new ArrayList<>();

        if (start > end) {
            return indices;
        }

        int mid = start + (end - start) / 2;

        if (target.compareTo(array[mid]) == 0) {
            // target found, add current index to list
            indices.add(mid);

            // check for dupes: search left and right sub-arrays
            indices.addAll(recuriveIndexFinderHelper(array, target, start, mid - 1));
            indices.addAll(recuriveIndexFinderHelper(array, target, mid + 1, end));
        } else if (target.compareTo(array[mid]) > 0) {
            // target is to the right: search right sub-array
            indices.addAll(recuriveIndexFinderHelper(array, target, mid + 1, end));
        } else {
            // target is to the left: search left sub-array
            indices.addAll(recuriveIndexFinderHelper(array, target, start, mid - 1));
        }

        return indices;
    }
    
    public static void main(String[] args) {
        // Create a sorted array with duplicate elements
        Integer[] array = {1, 2, 3, 3, 4, 4, 4, 5, 5};

        // Search for the number 4 and print the resulting list of indices
        ArrayList<Integer> indices = RecursiveIndexFinder.findIndicesRecursiveSorted(array, 4);
        System.out.println("Indices where 4 appears: " + indices);
    }
}



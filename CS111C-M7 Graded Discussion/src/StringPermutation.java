import java.util.ArrayList;
import java.util.Scanner;

public class StringPermutation {
	
    public static ArrayList<String> permute(String str) {
        ArrayList<String> result = new ArrayList<>();
        permuteHelper(str.toCharArray(), 0, result);
        return result;
    }
        
    private static void permuteHelper(char[] arr, int index, ArrayList<String> result) {
    	System.out.print(arr);
    	System.out.println(" " + index);

        if (index == arr.length - 1) {
            result.add(new String(arr));
        } else {
            for (int i = index; i < arr.length; i++) {
                swap(arr, index, i);
                permuteHelper(arr, index + 1, result);
                swap(arr, index, i);
            }
        }
    }
    
    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static void main(String[] args) {
    	Scanner scnr = new Scanner(System.in);
    	System.out.print("Enter a string to permute: ");
    	String input = scnr.nextLine();
        ArrayList<String> permutations = permute(input);
        System.out.println(input + " has " + permutations.size() + " permutations.");
        System.out.println(permutations);
        scnr.close();
    }
        

}

import java.util.ArrayDeque;
import java.util.Deque;

public class Palindrome {
	
	public static boolean isPalindrome(String text) {
		Deque<String> deque = new ArrayDeque<>();
		// add all chars as strings to deque
		for (int i=0; i<text.length(); i++) {
			deque.addFirst(String.valueOf(text.charAt(i)));
		}
		// compare first and last until deque is consumed
		// if size = 1, it's a palindrome (also size = 0)
		while (deque.size() > 1) {
			String first = deque.pollFirst();
			String last = deque.pollLast();

			if (!first.equals(last)) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		String[] palindromes = {"kayak", "sagas", "redder", "noon"};  // even and odd sized
		String[] notPalindromes = {"notpalindromne", "nine", "palin", "moms"};
		for (int i=0; i<4; i++) {
			System.out.println("Palindrome '" + palindromes[i] + "' expected true / is " + isPalindrome(palindromes[i]));
			System.out.println("Not Palindrome '" + notPalindromes[i] + "' expected false / is " + isPalindrome(notPalindromes[i]));

		}
	}

}

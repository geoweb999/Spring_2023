import java.util.Stack;

public class PalindromeTester {
	public static boolean isPalindrome(String text) {
		// reverse text by push each char onto stack
		// and then reading them off
		Stack<String> stringStack = new Stack<>();
		for (int i=0; i<text.length(); i++) {
			stringStack.push(String.valueOf(text.charAt(i)));
		}
		String backwardsText = "";
		for (int i=0; i<text.length(); i++) {
			backwardsText += stringStack.pop();
		}
		return text.equals(backwardsText);
	}
	
	public static void main(String[] args) {
		String text = "he's a fine boy";
		System.out.println(isPalindrome(text));
	
		text = "rotator";
		System.out.println(isPalindrome(text));
	
	}
}

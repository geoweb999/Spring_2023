import java.util.Stack;

public class Homework {
	
	public static void PrintStack(Stack<Integer> s)
	{
	     
	    // If stack is empty
	    if (s.empty())
	        return;
	   
	    // Extract top of the stack
	    int x = s.peek();
	   
	    // Pop the top element
	    s.pop();
	   
	    // Print the current top
	    // of the stack i.e., x
	    System.out.print(x + " ");
	   
	    // Proceed to print
	    // remaining stack
	    PrintStack(s);
	   
	    // Push the element back
	    s.push(x);
	}
	
	public static void main(String args[]) {
		Stack<Integer> stack = new Stack<>();
		
		stack.push(23);
		stack.push(9);
		stack.pop();
		stack.push(14);
		stack.push(3); 
		stack.push(stack.pop()); 
		stack.push(stack.peek());
		stack.push(17);
		stack.push(8);
		stack.pop();
		stack.peek();
		
		System.out.println("===================== *TOP* -> *BOTTOM*");
		PrintStack(stack);
		
	}
}

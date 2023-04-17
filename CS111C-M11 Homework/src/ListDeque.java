import java.util.*;

public class ListDeque <T> implements DequeInterface<T> {
	
	protected List<T> list; // note: this is List from the Java standard library java.util package

	// ANY OTHER VARIABLES AND CONSTRUCTORS GO HERE
	
	public void addToBack(T newEntry) {
		// YOUR CODE HERE
	}
	public void addToFront(T newEntry) {
		// YOUR CODE HERE
	}
	public T removeFront() {
		return null; // PLACEHOLDER: YOUR CODE GOES HERE
	}
	public T removeBack() {
		return null; // PLACEHOLDER: YOUR CODE GOES HERE
	}

	public T getFront() {
		return null; // PLACEHOLDER: YOUR CODE GOES HERE
	}
	public T getBack() {
		return null; // PLACEHOLDER: YOUR CODE GOES HERE
	}

	public boolean isEmpty() {
		return false; // PLACEHOLDER: YOUR CODE GOES HERE
	}

	public void clear() {
		// YOUR CODE HERE
	}
	
	/* 
	 * these methods have either been implemented for you or you are not required to implement them
	 * for the homework
	 */
	public T dequeue() {
		return removeFront();
	}
	public void enqueue(T element) {
		addToBack(element);
	}
	public T getSecond() {
		return null;
	}

}

import java.util.*;

public class ListDeque <T> implements DequeInterface<T> {
	
	protected List<T> list; // note: this is List from the Java standard library java.util package

	public ListDeque() {
		list = new ArrayList<T>();
	}
	
	public void addToBack(T newEntry) {
		list.add(newEntry);
	}
	public void addToFront(T newEntry) {
		list.add(0, newEntry);
	}
	public T removeFront() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return list.remove(0);
	}
	public T removeBack() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return list.remove(list.size() - 1);
	}

	public T getFront() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return list.get(0);
	}
	public T getBack() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return list.get(list.size() - 1);	}

	public boolean isEmpty() {
        return list.isEmpty();
	}

	public void clear() {
        list.clear();
	}
	
	@Override
	public String toString() {
		
		String out = "front ";
		for (T element :  this.list) {
			Integer el = (Integer) element;
			out += Integer.toString(el) + " ";
		}
		out += "back";
		return out;
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

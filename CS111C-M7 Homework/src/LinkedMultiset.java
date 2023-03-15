import java.util.Random;

public class LinkedMultiset<T> implements Multiset<T> {
	
	private Node head;
	private int size;
	
	public LinkedMultiset() {
		head = null; // head is null for an empty set
		size = 0;
	}

	@Override
	public boolean add(T element) {
		if(isEmpty()) {
			head = new Node(element);
		} else {
			Node newNode = new Node(element);
			newNode.next = head;
			head = newNode;
		}
		size++;
		return true;
	}

	@Override
	public T remove() {
		if(isEmpty()) {
			return null;
		} else {
			T removedData = head.data;
			head = head.next;
			size--;
			return removedData;
		}
	}

	@Override
	public boolean remove(T element) {
		Node current = head;
		while(current!=null) {
			if(current.data.equals(element)) {
				current.data = head.data;
				head = head.next;
				size--;
				return true;
			} else {
				current = current.next;
			}
		}
		return false;
	}

//	@Override
//	public int getOccurrencesOf(T element) {
//		int count = 0;
//		Node current = head;
//		while(current!=null) {
//			if(current.data.equals(element)) {
//				count++;
//			}
//			current = current.next;
//		}
//		return count;
//	}

	@Override
	public int getOccurrencesOf(T element) {
		Node current = head;
		return checkOccurence(element, current);
	}
	
	private int checkOccurence(T element, Node ptr) {
		if (ptr == null) {
			return 0;
		} 
		if (ptr.data.equals(element)) {
			return 1 + checkOccurence(element, ptr.next);
		} else {
			return 0 + checkOccurence(element, ptr.next);
		}
	}
	
	@Override
	public boolean contains(T element) {
		Node current = head;
		while(current!=null) {
			if(current.data.equals(element)) {
				return true;
			} else {
				current = current.next;
			}
		}
		return false;	
	}

	@Override
	public boolean isEmpty() {
		return size==0 && head==null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		while(head!=null) {
			head.data = null;
			head = head.next;
		}
		size = 0;
		
	}
	
	@Override
	public String toString() {
		String s = "[";
		Node currentNode = head;
		while(currentNode!=null) {
			s += currentNode.data + ", ";
			currentNode = currentNode.next;
		}
		if(!isEmpty()) {
			s = s.substring(0, s.length()-2);
		}
		s += "]";
		return s;
	}
	
	public T randomRemove( ) {
		if(isEmpty()) {
			return null;
		}
		Random generator = new Random();
		int randomIndex = generator.nextInt(size); // returns a random number from 0 (inclusive) to size (exclusive)
		
		Node current = head;
		for(int i=0; i<randomIndex; i++) {
			current = current.next;
		}
		T removedData = current.data;

		// removeNode(current); // invoke this instead of the line below if you want to use the practice version of removing the actual ndoe
		remove(removedData); // technically removes the first appearance of the randomly selected data
		
		return removedData;
		
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof LinkedMultiset<?>) {
			LinkedMultiset<T> otherSet = (LinkedMultiset<T>) obj;
			
			if(this.size!=otherSet.size) {
				return false;
			}
			
			// make a "copy" of the parameter set
			LinkedMultiset<T> copyOther = new LinkedMultiset<>();
			Node current = otherSet.head;
			while(current!=null) {
				copyOther.add(current.data);
				current = current.next;
			}
			
			current = this.head;
			while(current!=null) {
				T currentData = current.data;
				if(copyOther.remove(currentData)) {
					current = current.next;
				} else {
					return false;
				}
			}
			
			// other set is now empty because we first compared sizes
			
			// the sets have the same contents
			return true;
			
		} else {
			return false;
		}
	}
	
	
	
	private class Node {
		private T data;
		private Node next;
		
		public Node(T data) {
			this(data, null);
		}
		public Node(T data, Node next) {
			this.data = data;
			this.next = next;			
		}
		@Override
		public String toString() {
			return data.toString();
		}
	}
	
}

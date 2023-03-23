/*
 * An expandable, linked-node implementation of a list that is 1-indexed.
 */
public class LinkedListFromOne<T extends Comparable<? super T>> implements ListFromOne<T> {

	private Node head;
	private int size;
	
	public LinkedListFromOne() {
		head = null;
		size = 0;
	}
	
	@Override
	public boolean add(T element) {
		Node newNode = new Node(element);

		if (isEmpty()) {
			head = newNode;
		} else { 
			Node tail = getNodeAt(size);
			tail.next = newNode; 
		} 

		size++;
		return true;
		
		// alternative implementation:
		// return add(size+1, element);
	}

	@Override
	public boolean add(int position, T element) {
		if (isValidPosition(position) || position == size + 1) {
			
			Node newNode = new Node(element);
			
			if (position == 1) { // adding first in the chain; also works for an empty list
				newNode.next = head;
				head = newNode;
			} else { // position > 1 (and list is not empty)
				Node nodeBefore = getNodeAt(position - 1);
				Node nodeAfter = nodeBefore.next; 
				newNode.next = nodeAfter;
				nodeBefore.next = newNode;
			} 
			size++;
			return true;
		} else {
			throw new IndexOutOfBoundsException(position + " is invalid for adding from a list of size " + size + ".");
		}

	}
	
	@Override
	public T remove(int position) {
		T result = null; 

		if (isValidPosition(position)) {			
			if (position == 1) { // remove first entry
				result = head.data; 
				head = head.next; 
			} else { // remove some other entry from position > 1
				Node nodeBefore = getNodeAt(position - 1);
				Node nodeToRemove = nodeBefore.next;
				Node nodeAfter = nodeToRemove.next;
				result = nodeToRemove.data; 
				nodeBefore.next = nodeAfter; // remove entry
			} 
			size--;
			return result; 
		} else {
			throw new IndexOutOfBoundsException(position + " is invalid for removing from a list of size " + size + ".");
		}

	}

	@Override
	public boolean remove(T element) {
		int position = getPositionOf(element);
		if(position>=1) {
			remove(position);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean contains(T element) {
		Node currentNode = head;

		while (currentNode != null) {
			if (element.equals(currentNode.data)) {
				return true;
			} else {
				currentNode = currentNode.next;
			}
		} 
		return false;
	}

	

	@Override
	public T set(int position, T element) {
		if (isValidPosition(position)) {			
			Node node = getNodeAt(position);
			T originalData = node.data;
			node.data = element;
			return originalData;
		} else {
			throw new IndexOutOfBoundsException(position + " is invalid for setting in a list of size " + size + ".");
		}

	}

	@Override
	public T get(int position) {
		if (isValidPosition(position)) {			
			return getNodeAt(position).data;
		} else {
			throw new IndexOutOfBoundsException(position + " is an invalid index for a list of size " + size + ".");
		}

	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0 && head==null;
	}

	@Override
	public void clear() {
		while(!isEmpty()) {
			head.data = null;
			head = head.next;
			size--;
		}
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
	public int getPositionOf(T element) {
		int position = 1;
		Node currentNode = head;
		
		while(currentNode!=null) {
			if(currentNode.data.equals(element)) {
				return position;
			} else {
				currentNode = currentNode.next;
				position++;
			}
		}
		return -1;
	}
	
	public void swap(int firstPosition, int secondPosition) {
		if(isValidPosition(firstPosition) && isValidPosition(secondPosition)) {
			Node firstNode = getNodeAt(firstPosition);
			Node secondNode = getNodeAt(secondPosition);
			T temp;
			
			temp = secondNode.data;
			secondNode.data = firstNode.data;
			firstNode.data = temp;
			
			
		} else {
			throw new IndexOutOfBoundsException("At least one position is invalid for swapping in a list of size " + size + ": " + firstPosition + ", " +secondPosition);
		}
	}
	
	public ListFromOne<T> getAllLessThan(T target) {
		LinkedListFromOne<T> lessThanList = new LinkedListFromOne<T>();
		Node lessThanListCurrent = lessThanList.head;
		
		Node current = head; // use a local Node variable instead of invoking .get(i) for improved efficiency
		while(current!=null) {
			T currentElement = current.data;
			if(currentElement.compareTo(target) < 0) { // currentElement is < target
				lessThanList.add(currentElement); // works but is inefficient. how can we improve this?
			}
			current = current.next;
		}
		return lessThanList;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof LinkedListFromOne<?>) {
			LinkedListFromOne<T> otherList = (LinkedListFromOne<T>) obj;
			
			if(this.size!=otherList.size) {
				return false; // different sizes
			}

			Node thisCurrent = this.head;
			Node otherCurrent = otherList.head;
			
			while(thisCurrent!=null && otherCurrent!=null) {
				T thisElement = thisCurrent.data;
				T otherElement = otherCurrent.data;
				
				if(!thisElement.equals(otherElement)) {
					return false; // mismatched element
				}
				
				thisCurrent = thisCurrent.next;
				otherCurrent = otherCurrent.next;
			}
			
			// this works, but it is WILDLY inefficient!
//			for(int i=1; i<=size; i++) {
//				T thisElement = this.get(i);
//				T otherElement = otherList.get(i);
//				if(!thisElement.equals(otherElement)) {
//					return false; // mismatched element
//				}
//			}
			
			return true;
			
		} else {
			return false;
		}
	}
	

	public void addAllAtIntervalInefficient(LinkedListFromOne<T> otherList, int interval) {
		for(int i=1; i<=otherList.size; i++) {
			if(i%interval==0) {
				this.add(otherList.get(i));
			}
		}
	}
	
	public void addAllAtIntervalGood(LinkedListFromOne<T> otherList, int interval) {
		Node lastNode = getNodeAt(size);
		Node otherCurrent = otherList.head;
		
		int counter = 1;
		while(otherCurrent!=null) {
			if(counter%interval==0) {
				if(lastNode==null) { // current list is empty
					lastNode = new Node(otherCurrent.data);
				} else {
					lastNode.next = new Node(otherCurrent.data);
				}
				lastNode = lastNode.next;
				size++;
			}
			counter++;
			otherCurrent = otherCurrent.next;
		}
	}
	
	
	
	// assumes 1 <= position <= size
	private Node getNodeAt(int position) {
		Node currentNode = head;

		for (int i=1; i<position; i++) {
			currentNode = currentNode.next;
		}
		return currentNode;
	}
	
	private boolean isValidPosition(int position) {
		return 1 <= position && position <= size;
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

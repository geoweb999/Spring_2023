public class LinkedFrontBackCappedList<T extends Comparable<? super T>> 
   implements FrontBackCappedList<T>, Comparable<LinkedFrontBackCappedList<T>>{

	// head.data is null && head.next is first node in list or null
	// tail.data is null && tail.next is last node in list or null
	// size is the current size of the linked list not counting head/tail
	// capacity is the max size of the linked list not counting head/tail
	private Node head, tail;
	private int size, capacity;
   

	public LinkedFrontBackCappedList(int capacity) {
		this.size = 0;
		this.capacity = capacity;
		this.head = new Node(null, null);
		this.tail = new Node(null, null);
	} 

	@Override
	public boolean addFront(T newEntry) {
		if (this.isFull()) {
			return false;
		}
		if (this.isEmpty()) {
			Node newNode = new Node(newEntry, null);
			this.head.next = newNode;
			this.tail.next = newNode;
			this.size++;
			return true;
		}
		Node newNode = new Node(newEntry, head.next);
		head.next = newNode;
		this.size++;
		return true;
	}

	@Override
	public boolean addBack(T newEntry) {
		if (this.isFull()) {
			return false;
		}
		if (this.isEmpty()) {
			// same as adding to front so reuse code
			this.addFront(newEntry);
			return true;
		}
		Node newNode = new Node(newEntry, null);
		tail.next.next = newNode;
		tail.next = newNode;
		this.size++;
		return true;
	}

	@Override
	public T removeFront() {
		if (this.isEmpty()) {
			return null;
		}
		if (this.size() == 1) {
			T data = head.next.data;
			this.clear();
			return data;
		}
		T data = head.next.data;
		this.head.next = this.head.next.next;
		this.size--;
		return data;
	}

	@Override
	public T removeBack() {
		if (this.isEmpty()) {
			return null;
		}
		if (this.size() == 1) {
			T data = tail.next.data;
			this.clear();
			return data;
		}
		// find node previous to tail
		T data = tail.next.data;
		Node current = head;
		while (current.next.next != null) {
			current = current.next;
		}
		current.next = null;
		tail.next = current;
		this.size--;
		return data;
	}

	@Override
	public void clear() {
		this.tail = new Node(null,null);
		this.head = new Node(null,null);
		this.size = 0;
	}

	@Override
	public T getEntry(int givenPosition) {
		if (this.isEmpty()) {
			return null;
		}
		if (this.isValid(givenPosition)) {
			int count = 0;
			Node current = head.next;
			while (count < givenPosition) {
				current = current.next;
				count++;
			}
			return current.data;
		} else {
			return null;
		}
	}

	@Override
	public int indexOf(T anEntry) {
		if (this.isEmpty()) {
			return -1;
		}
		int count = 0;
		Node current = head.next;
		while (current != null) {
			if (current.data.equals(anEntry)) {
				return count;
			}
			current = current.next;
			count++;

		}
		return -1;
	}

	@Override
	public int lastIndexOf(T anEntry) {
		if (this.isEmpty()) {
			return -1;
		}
		int count = 0;
		int index = 0;
		boolean found = false;
		Node current = head.next;
		while (current != null) {
			if (current.data.equals(anEntry)) {
				found = true;
				index = count;
			}
			current = current.next;
			count++;

		}
		if (found) {
			return index;
		}
		return -1;
	}

	@Override
	public boolean contains(T anEntry) {
		return this.indexOf(anEntry) != -1;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public boolean isFull() {
		return this.size == this.capacity;
	} 
	
	@Override 
	public String toString() {
		if (this.isEmpty()) {
			return "[]" + "\tsize=" + this.size + "\tcapacity=" + this.capacity;
		}
		if (this.size() == 1){
			return "[" + this.head.next.data.toString() + "]" + "\tsize=" + this.size + 
					"\tcapacity=" + this.capacity + "\thead=" + head.next.data.toString() + " tail=" + tail.next.data.toString();
		}
		Node current = head.next;	
		String contents = "[";
		while (current.next != null) {
			contents += current.data.toString() + ", ";
			current = current.next;
		}
		contents += current.data.toString() + "]";
		contents += "\tsize=" + this.size + "\tcapacity=" + this.capacity + 
				"\thead=" + this.head.next.data.toString() + " tail=" + this.tail.next.data.toString(); 
		return contents;
		
	}
	
	private boolean isValid(int index) {
		return (index >=0 && index < this.size);
	}
		

	@Override
	public int compareTo(LinkedFrontBackCappedList<T> other) {
		int sizeCompare = Integer.valueOf(this.size()).compareTo(Integer.valueOf(other.size()));

		// check for variations of empty lists
		if ((this.isEmpty()  && !other.isEmpty()) ||
	       (!this.isEmpty()  && other.isEmpty())) {
			return sizeCompare;
		}

		if (this.isEmpty() && this.isEmpty()) {
			return 0;
		}
		
		// both lists have at least one element
		Node thisCurrent = this.head;
		Node otherCurrent = other.head;
		do  {
			thisCurrent = thisCurrent.next;
			otherCurrent = otherCurrent.next;
			int cmp = thisCurrent.data.compareTo(otherCurrent.data);
			if (cmp != 0) {
				return cmp;
			}
		} while (thisCurrent.next != null && otherCurrent.next != null);
		// if we made it here then all elements checked were the same (one or both is a subset of other)
		if (thisCurrent.next == null && otherCurrent.next == null) {
			// lists were same size and had same elements
			return 0;
		}
		// one list is a smaller subset of the other
		return sizeCompare;
	}
	
	
	public class Node {
		public T data; 
		public Node next; 

		private Node(T dataValue) {
			data = dataValue;
			next = null;
		}

		private Node(T dataValue, Node nextNode) {
			data = dataValue;
			next = nextNode;
		}

		private T getData() {
			return data;
		}

		private void setData(T newData) {
			data = newData;
		}

		private Node getNextNode() {
			return next;
		}

		private void setNextNode(Node nextNode) {
			next = nextNode;
		}


	}


}
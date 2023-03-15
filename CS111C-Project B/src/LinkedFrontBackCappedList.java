public class LinkedFrontBackCappedList<T> implements FrontBackCappedList<T> {

	private Node head, tail;
   
    // head.data is cast to int and stores current size of list
	// tail.data is cast to int and stores capacity of list

	public LinkedFrontBackCappedList(int size) {
		Object obj = (Object) size;
		this.tail = new Node((T) obj,null);
		obj = 0;
		this.head = new Node((T) obj,null);
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
			this.incrementSize(1);
			return true;
		}
		Node newNode = new Node(newEntry, head.next);
		head.next = newNode;
		this.incrementSize(1);
		return true;
	}

	@Override
	public boolean addBack(T newEntry) {
		if (this.isFull()) {
			return false;
		}
		if (this.isEmpty()) {
			this.addFront(newEntry);
			return true;
		}
		Node newNode = new Node(newEntry, null);
		tail.next.next = newNode;
		tail.next = newNode;
		this.incrementSize(1);
		return true;
	}

	@Override
	public T removeFront() {
		if (this.isEmpty()) {
			return null;
		}
		T data = head.next.data;
		this.head.next = this.head.next.next;
		this.incrementSize(-1);
		return data;
	}

	@Override
	public T removeBack() {
		if (this.isEmpty()) {
			return null;
		}
		if (this.size() == 1) {
			T data = tail.next.data;
			this.head.next = null;
			this.tail.next = null;
			this.incrementSize(-1);
			return data;
		}
		T data = tail.next.data;
		Node current = head;
		while (current.next.next != null) {
			current = current.next;
		}
		current.next = null;
		tail.next = current;
		this.incrementSize(-1);
		return data;
	}

	@Override
	public void clear() {
		Object size = (Object) tail.data;
		this.tail = new Node((T) size,null);
		size = 0;
		this.head = new Node((T) size,null);
	}

	@Override
	public T getEntry(int givenPosition) {
		// TODO Auto-generated method stub
		return null;
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
		if (this.isEmpty()) {
			return false;
		}
		int count = 0;
		Node current = head.next;
		while (current != null) {
			if (current.data.equals(anEntry)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	@Override
	public int size() {
		return (int) head.data;
	}

	@Override
	public boolean isEmpty() {
		return head.data.equals(0);
	}

	@Override
	public boolean isFull() {
		return head.data.equals(tail.data);
	} 
	
	@Override 
	public String toString() {
		if (this.isEmpty()) {
			return "[]" + "\tsize=" + head.data.toString() + "\tcapacity=" + tail.data.toString() + "\thead= tail =";
		}
		if (this.size() == 1){
			return "[" + this.head.next.data.toString() + "]" + "\tsize=" + head.data.toString() + 
					"\tcapacity=" + tail.data.toString() + "\thead=" + head.next.data.toString() + " tail=" + tail.next.data.toString();
		}
		Node current = head.next;	
		String contents = "[";
		while (current.next != null) {
			contents += current.data.toString() + ", ";
			current = current.next;
		}
		contents += current.data.toString() + "]";
		contents += "\tsize=" + head.data.toString() + "\tcapacity=" + tail.data.toString() + 
				"\thead=" + this.head.next.data.toString() + " tail=" + this.tail.next.data.toString(); 
		return contents;
		
	}
	
	private void incrementSize(int change) {
		int size = (int) this.head.data;
		size += change;
		Object obj = (Object)size;
		this.head.data = (T)obj;
		
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

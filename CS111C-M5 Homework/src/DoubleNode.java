public class DoubleNode<T> {
	public T data; // entry 
	public DoubleNode<T> next; // link to next node
	public DoubleNode<T> previous; // link to the previous node

	public DoubleNode(T dataPortion) {
		this(dataPortion, null, null);
	}

	public DoubleNode(T dataPortion, DoubleNode<T> nextNode, DoubleNode<T> previousNode) {
		data = dataPortion;
		next = nextNode;
		previous = previousNode;
	}
}
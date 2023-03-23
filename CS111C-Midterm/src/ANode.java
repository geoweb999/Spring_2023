public class ANode<T> {
	
	public T data;
	public ANode<T> next;

	public ANode(T data) {
		this.data = data;
		next = null;
	}

	public ANode(T data, ANode<T> next) {
		this.data = data;
		this.next = next;
	}
}

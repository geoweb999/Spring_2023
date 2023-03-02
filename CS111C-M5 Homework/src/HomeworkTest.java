
public class HomeworkTest {
	
	public static void method(Node firstNode) {
		   while(firstNode != null) {
		      System.out.println(firstNode.data);
		      firstNode = firstNode.next;
		   }
		}   


	public static void main(String[] args) {
		Node a = new Node(5);
//		Node b = new Node(3, a);
//		Node c = new Node(2, b);
		Node firstNode = a;
		Node currentNode = firstNode;
		method(null);
//		currentNode = currentNode.next;
//		currentNode = currentNode.next;
//		System.out.println(currentNode.next.data);
//		System.out.println(firstNode.data);
//		System.out.println(firstNode.next.data);
//		System.out.println(firstNode.next.next.data);


	}
	
}

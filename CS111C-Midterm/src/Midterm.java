import java.util.ArrayList;
import java.util.List;



public class Midterm {
	
	public static void mystery(Node nodeA, Node nodeB) {
		   nodeB.next = nodeA.next; 
		   nodeA.next = nodeB;
		}
	
	public static void main(String[] args) {
//		List<String> myList = new ArrayList<>();
//		myList.add("delaware");
//		myList.add("california");
//		myList.add("iowa");
//		myList.add("montana");
//
//		myList.add(2, "maine");	
//	System.out.println(myList);
		
		Node n1 = new Node(48);
		Node n2 = new Node(35);
		n1.next = n2;
		Node n3 = new Node(16);
		n2.next = n3;
		Node n4 = new Node(23);
		n3.next = n4;
		
		Node currentNode = n1.next;
		currentNode.next = currentNode.next.next;
		currentNode = currentNode.next;
		System.out.println(currentNode.data);
		Node current = n1;
		while (current != null) {
			System.out.println(current.data);
			current = current.next;
		}

	}
}

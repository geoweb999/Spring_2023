
public class LinkedMultisetTester {

	public static <T> T findMiddleElement(Node<T> firstNode) {
		// return the "middle" node in a linked list
		// middle = linkedList.size (integer divide by 2) + 1
		// if list size = 7, middle = 4
		// if list size = 8, middle = 5
		// if list size = 1, middle = 1
		// if list size = 0, return null
		Node<T> currentNode = firstNode; // iterates through the list
		Node<T> middleNode = firstNode;  // lagging pointer, iterates at half the pace
		int nodeCount = 0;				 // counts number of nodes
		if (firstNode == null) {
			return null;
		}
		if (firstNode.next == null) {
			return firstNode.data;
		}
		// eliminated special cases
		while (currentNode != null) {
			currentNode = currentNode.next;
			nodeCount++;
			if (nodeCount % 2 == 0) {
				middleNode = middleNode.next;
			}
		}
		return middleNode.data;
	}
	
	public static void main(String[] args) {
		
		Student[] students = new Student[8];
		students[0] = new Student("April", "B245");
		students[1] = new Student("Barb", "B691");
		students[2] = new Student("Carl", "C208");	
		students[3] = new Student("Dannie", "D810");
		students[4] = new Student("Earl", "E890");
		students[5] = new Student("Fred", "E891");
		students[6] = new Student("Geoffrey", "E892");
		students[7] = new Student("Heshim", "E893");
		
		Node<Student> head = new Node<Student>(students[0]);
		Node<Student> currNode = head;
		Node<Student> nextNode = head;
		for (int i = 1; i < 7; i++) {
			nextNode = new Node<Student>(students[i]);
			currNode.next = nextNode;
		    currNode = currNode.next;
		}
		currNode = head;
		while (currNode != null) {
			System.out.println(currNode.data);
			currNode = currNode.next;
		}

		Student middleStudent = findMiddleElement(head);
		System.out.println("Middle Student = " + middleStudent);
	}

}
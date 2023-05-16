
public class VoterHashTableSeparateChaining {

	public Node<Voter>[] hashTable; // made public for testing purposes only!
	public int tableSize; 		     // made public for testing purposes only!

	public VoterHashTableSeparateChaining(int size) {
		hashTable = (Node<Voter>[]) new Node[size];
		tableSize = size;
	}
	
	public int calculateArrayLocation(int voterID) {
		// YOUR CODE HERE
		return 0;
	}

	public boolean addVoter(Voter voterToAdd) {
		// YOUR CODE HERE
		return false;
	}

	public Voter getVoter(int voterID) {
		// YOUR CODE HERE
		return null;
	}

	public void printTable() {
		for (int i = 0; i < tableSize; i++) {
			if (hashTable[i] != null) {
				System.out.print("Location " + i + ": ");
				Node<Voter> current = hashTable[i];
				while (current != null && current.next != null) {
					System.out.print(current.data.getName() + " -> ");
					current = current.next;
				}
				System.out.println(current.data.getName());
			}
		}
	}

}

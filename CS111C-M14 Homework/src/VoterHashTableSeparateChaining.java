
public class VoterHashTableSeparateChaining {

	public Node<Voter>[] hashTable; // made public for testing purposes only!
	public int tableSize; 		     // made public for testing purposes only!

	public VoterHashTableSeparateChaining(int size) {
		hashTable = (Node<Voter>[]) new Node[size];
		tableSize = size;
	}
	
	public int calculateArrayLocation(int voterID) {
		return voterID % tableSize;
	}

	public boolean addVoter(Voter voterToAdd) {
		// COMPLETING EXTRA CREDIT
		int index = calculateArrayLocation(voterToAdd.getId());
		Node<Voter> newNode = new Node<Voter>(voterToAdd, null);
		if (hashTable[index] == null) {
			// no collision, just add node
			hashTable[index] = newNode;
			return true;
		} else {
			Node<Voter> current = hashTable[index];
			if (newNode.data.getId() == current.data.getId()) {
				return false;
			}
			while (current.next != null) {
				current = current.next;
				if (newNode.data.getId() == current.data.getId()) {
					return false;
				}
			}
			current.next = newNode;
			return true;
		}
	}

	public Voter getVoter(int voterID) {
		int index = calculateArrayLocation(voterID);
		Node<Voter> current = hashTable[index];
		while (current != null) {
			if (current.data.getId() == voterID) {
				return current.data;
			}
			current = current.next;
		}
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

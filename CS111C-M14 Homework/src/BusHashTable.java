public class BusHashTable {
	
	private Bus[] hashTable;
	
	private static final Bus AVAILABLE = new Bus(-1, "");

	public BusHashTable(int size) {
		hashTable = new Bus[size];
	}
	
	private boolean canAddAtIndex(int index) {
		return hashTable[index] == null || hashTable[index] == AVAILABLE;
	}
	
	public boolean addBus(Bus bus) {
		System.out.println("Adding " + bus);
		int hashCode = getHashCode(bus);
		int location = hashCode;
		int locationsChecked = 0;
		boolean checkedAllLocations = false;
		
		while(!canAddAtIndex(location) && !checkedAllLocations) {
			System.out.print("\tCollision at position " + location);
			location++;
			location = location % hashTable.length;
			System.out.println("...\tTrying position " + location);
			locationsChecked++;
			if(locationsChecked >= hashTable.length) {
				checkedAllLocations = true;
			}
		}
		if(!checkedAllLocations) {
			hashTable[location] = bus;
			System.out.println("\tAdded at position " + location);
			printTable();
			return true;
		} else {
			System.out.println("\tCannot add: table full.");
			printTable();
			return false;
		}
		
		
		
	}
	
	// gets the numeric hash code, which is the position in the hash table
	// for this example, I am hashing students by their id
	private int getHashCode(Bus bus) {
		return getHashCode(bus.getNum());
	}
	
	private int getHashCode(int num) {
		return num % hashTable.length; 
	}
	
	private int findIndexOfBus(int num) {
		System.out.println("Looking for bus with ID " + num + "...");
		int location = getHashCode(num);
		int locationsChecked = 0;
		boolean checkedAllLocations = false;
		
		while(hashTable[location] != null && !checkedAllLocations) {
			Bus currentBus = hashTable[location];
			if(currentBus.getNum()==num) {
				System.out.println("\tStudent found at position " + location);
				return location;
			} else {
				System.out.println("\tStudent at position " + location + " is not a match. Trying next position...");
				location++;
				location = location % hashTable.length;
				locationsChecked++;
				if(locationsChecked >= hashTable.length) {
					checkedAllLocations = true;	
				}
			}
		}
		return -1;
	}
	
	public Bus getBus(int num) {
		int busIndex = findIndexOfBus(num);
		if(busIndex >= 0) {
			return hashTable[busIndex];
		} else {
			System.out.println("\tBus not found.");
			return null; // the student is not in the table
		}
		
	}
	
	public boolean removeBus(int num) {
		int busIndex = findIndexOfBus(num);
		if(busIndex >= 0) {
			hashTable[busIndex] = AVAILABLE;
			System.out.println("\tBus removed.");
			printTable();
			return true;
		} else {
			System.out.println("\tCould not remove- bus not in the table.");
			printTable();
			return false;
		}
	}
	
	public boolean removeStudent(Bus s) {
		return removeBus(s.getNum());
	}
	
	public void printTable() {
		System.out.println();
		for(int i=0; i<hashTable.length; i++) {
			System.out.println("\t" + i + "\t\t" + hashTable[i]);
		}
		System.out.println();
	}
}

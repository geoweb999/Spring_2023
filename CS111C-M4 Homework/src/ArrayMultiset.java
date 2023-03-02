/*
 * A fixed-size, array-based implementation of a multiset.
 */
public class ArrayMultiset<T> implements Multiset<T> {
	
	public T[] setArray;
	private int size;
	
	public static final int DEFAULT_CAPACITY = 100;
	
	public ArrayMultiset(int capacity) {
		setArray = (T[]) new Object[capacity];
		size = 0;
	}
	
	public ArrayMultiset() {
		this(DEFAULT_CAPACITY);
	}
	
	@Override
	public boolean add(T element) {
		if(!isFull()) {
			setArray[size] = element;
			size++;
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public T remove() {
		if(isEmpty()) {
			return null;
		} else {
			T elementToRemove = setArray[lastPosition()];
			removeElement(lastPosition());
			return elementToRemove;
		}
	}

	@Override
	public boolean remove(T element) {
		int position = getPosition(element);
		if(position>=0) {
			removeElement(position);
			return true;
		} else {
			return false;
		}
	}
	

	@Override
	public int getOccurrencesOf(T element) {
		int count = 0;
		for(int i=0; i<size; i++) {
			if(setArray[i].equals(element)) {
				count++;
			}
		}
		return count;
	}

	@Override
	public boolean contains(T element) {
		return getPosition(element) >= 0;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}
	
	public boolean isFull() {
		return size==setArray.length;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		for(int i=0; i<size; i++) {
			setArray[i] = null;
		}
		size = 0;
	}
	
	@Override
	public String toString() {
		String s = "[";
		for(int i=0; i<size; i++) {
			s += setArray[i] + ", ";
		}
		if(!isEmpty()) {
			s = s.substring(0, s.length()-2);
		}
		s += "]";
		return s;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ArrayMultiset<?>) {
			ArrayMultiset<T> otherSet = (ArrayMultiset<T>) obj;
			
			ArrayMultiset<T> copySet = new ArrayMultiset<T>(otherSet.size);
			for(int i=0; i<otherSet.size; i++) {
				copySet.add(otherSet.setArray[i]);
			}

			for(int i=0; i<size; i++) {
				T currentElement = setArray[i];
				if(!copySet.remove(currentElement)) {
					return false;
				}
			}
			
			return copySet.isEmpty();

		} else {
			return false;
		}
		
	}
	
	public int removeAll(T anEntry) {
		int count = 0;
		int i = 0;
		// if size = 0 the while loop won't run and we return 0 already
		while (i < size) {
			if(setArray[i].equals(anEntry)) {
				removeElement(i);
				count++;
				// do not increment i on this loop as element i has been swapped with last 
				// so we need to check element i again
				// removeElement decrements size so we're not at risk for infinite loop
			} else {
				i++;
			}
		}
		return count;
	}

		
	private int lastPosition() {
		return size - 1;
	}
	
	// assumes that 0 <= position < size
	private void removeElement(int position) {
		setArray[position] = setArray[lastPosition()];
		setArray[lastPosition()] = null;
		size--;
	}
	
	private int getPosition(T element) {
		for(int i=0; i<size; i++) {
			if(setArray[i].equals(element)) {
				return i;
			}
		}
		return -1;
	}
	
	

}

import java.util.Arrays;

/*
 * An expandable, array-based implementation of a list that is 1-indexed.
 */
public class ArrayListFromOne<T extends Comparable<? super T>> implements ListFromOne<T>, Comparable<ArrayListFromOne<T>> {
	
	public T[] listArray; // will store elements starting at index 1
	private int size;
	
	public static final int DEFAULT_CAPACITY = 100;
	
	public ArrayListFromOne(int capacity) {
		listArray = (T[]) new Comparable[capacity+1]; // listArray[0] will always store null
		size = 0;
	}
	
	public ArrayListFromOne() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public boolean add(T element) {
		return add(size+1, element);
	}

	@Override
	public boolean add(int position, T element) {
		if (isValidPosition(position) || position == size + 1) {
			if (isArrayFull()) {
				expandArray();
			}
			makeASpace(position);
			listArray[position] = element;
			size++;
			return true;
		} else {
			throw new IndexOutOfBoundsException(position + " is invalid for adding from a list of size " + size + ".");
		}
	}

	@Override
	public boolean contains(T element) {
		return getIndexOf(element)>=1;
	}

	@Override
	public T remove(int position) {
		if(isValidPosition(position)) {
			T elementToRemove = listArray[position];
			shiftToCoverSpace(position);
			listArray[size] = null;
			size--;
			return elementToRemove;
		} else {
			throw new IndexOutOfBoundsException(position + " is invalid for removing from a list of size " + size + ".");
		}
	}

	@Override
	public boolean remove(T element) {
		int removeIndex = getIndexOf(element);
		if(removeIndex < 1) {
			return false;
		} else {
			remove(removeIndex);
			return true;
		}
	}

	@Override
	public T set(int position, T element) {
		if(isValidPosition(position)) {
			T originalElement = listArray[position];
			listArray[position] = element;
			return originalElement;
		} else {
			throw new IndexOutOfBoundsException(position + " is invalid for setting in a list of size " + size + ".");
		}
	}

	@Override
	public T get(int position) {
		if(isValidPosition(position)) {
			return listArray[position];
		} else {
			throw new IndexOutOfBoundsException(position + " is an invalid index for a list of size " + size + ".");
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public void clear() {
		for(int i=1; i<=size; i++) {
			listArray[i] = null;
		}
		size = 0;		
	}
	
	@Override
	public String toString() {
		String s = "[";
		for(int i=1; i<=size; i++) {
			s += listArray[i] + ", ";
		}
		if(!isEmpty()) {
			s = s.substring(0, s.length()-2);
		}
		s += "]";
		return s;
	}
	
	public void moveToEnd() {
		if(!isEmpty()) {
			T firstElement = remove(1);
			add(firstElement);
		} else {
			throw new IllegalStateException("Cannot moveToEnd when list is empty.");
		}
	}
	
	public void swap(int firstPosition, int secondPosition) {
		if(isValidPosition(firstPosition) && isValidPosition(secondPosition)) {
			T temp = listArray[firstPosition];
			listArray[firstPosition] = listArray[secondPosition];
			listArray[secondPosition] = temp;

			
		} else {
			throw new IndexOutOfBoundsException("At least one position is invalid for swapping in a list of size " + size + ": " + firstPosition + ", " +secondPosition);
		}
	}
	
	public ListFromOne<T> getAllLessThan(T element) {
		return ArrayListFromOne.getAllLessThan(this, element);
		
	}
	
	public static <T extends Comparable<? super T>> ListFromOne<T> getAllLessThan(ArrayListFromOne<T> list, T element) {
		ArrayListFromOne<T> lessThanList = new ArrayListFromOne<T>();
		for(int i=1; i<=list.size; i++) {
			if(list.listArray[i].compareTo(element) < 0){ // list.listArray[i] is SMALLER than element
				lessThanList.add(list.listArray[i]);
			}
		}
		return lessThanList;
		
	}
	
	
	public int removeAll(T element) {
		int count = 0;
		int i = 1;
		// empty List will cause loop not to run and return 0 correctly
		while (i <= this.size) {
			if(listArray[i].equals(element)) {
				this.remove(i);
				count++;
				// do not increment i on this loop as element i is the next shifted down element 
				// so we need to check element i again
				// removeElement decrements size so we're not at risk for infinite loop
			} else {
				i++;
			}
		}
		return count;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof ArrayListFromOne<?>) {
			ArrayListFromOne<T> otherList = (ArrayListFromOne<T>) other;
			
			// sizes must also keeps us out of trouble for index out of bounds
			if (otherList.size != this.size) {
				return false;
			}
			
			for(int i = 1; i <= this.size; i++) {
				if(!this.listArray[i].equals(otherList.listArray[i]) ) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	} 
	
	@Override
	public int compareTo(ArrayListFromOne<T> otherList) {
		if (this.size != otherList.size) {
			Integer sizeThis = this.size;
			Integer sizeOther = otherList.size;
			return sizeThis.compareTo(sizeOther);
		} else {
			for (int i = 1; i <= this.size; i++) {
				if (!this.listArray[i].equals(otherList.listArray[i])) {
					return listArray[i].compareTo(otherList.listArray[i]);
				}
			}
			return 0;
		}
	}

		
	private boolean isArrayFull() {
		return (size+1)==listArray.length;
	}
	
	private void expandArray() {
		T[] tempArray = (T[]) new Comparable[listArray.length * 2];
		for(int i=1; i<=size; i++) {
			tempArray[i] = listArray[i];
		}
		listArray = tempArray;
			
	}
	
	// assumes that 1 <= position <= size
	private void makeASpace(int position) {
		if(size==listArray.length) {
			expandArray();
		}
		for(int i=size; i>=position; i--) {
			listArray[i+1] = listArray[i];
		}
	}
	
	// assumes that 1 <= position < size
	private void shiftToCoverSpace(int position) {
		if(position<=listArray.length) {
			for(int i=position; i<size; i++) {
				listArray[i] = listArray[i+1];
			}
		}
	}
	
	private boolean isValidPosition(int position) {
		return 1 <= position && position <= size;
	}
	
	private int getIndexOf(T element) {
		for(int i=1; i<=size; i++) {
			if(listArray[i].equals(element)) {
				return i;
			}
		}
		return -1;
	}

}


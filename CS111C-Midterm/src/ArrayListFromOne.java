import java.util.Arrays;

/*
 * An expandable, array-based implementation of a list that is 1-indexed.
 */
public class ArrayListFromOne<T extends Comparable<? super T>> implements ListFromOne<T> {

	
	public void addToFront(ANode<T> first) {
		// find the size (count) of the passed linked Nodes
		// create a new list of size count + this.size + 1
		// copy linked nodes into new list
		// copy listArray into new list

		ANode<T> current = first;
		int count = 0;
		// count size of linked nodes
		while (current != null) {
			count++;
			current = current.next;
		}
		// create a new list which we'll concatenate both lists into
		int newSize = count + this.size;
		T[] newList = (T[]) new Comparable[newSize + 1];
		
		// add linked nodes to front
		current = first;
		for (int i = 1; i <= count; i++) {
			newList[i] = current.data;
			current = current.next;
		}
		// add this.listArray to back of newList
		for (int i = count+1; i <= newSize; i++) {
			newList[i] = this.get(i - count);
		}
		this.listArray = newList;
		this.size = newSize;
		
	}
	
	
	
	private T[] listArray; // will store elements starting at index 1
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

//			alternative equally efficient approach:
//			T tmp = get(firstPosition);
//			set(firstPosition, get(secondPosition));
//			set(secondPosition, tmp);
			
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
	
	public void addAllAtIntervalInefficient(ArrayListFromOne<T> otherList, int interval) {
		for(int i=1; i<=otherList.size(); i++) {
			if(i%interval==0) {
				add(otherList.get(i));
			}
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
		
		// could also use:
		// listArray = Arrays.copyOf(listArray, listArray.length * 2);
		
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

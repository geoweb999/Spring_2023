
public class ArrayFrontBackCappedList<T> implements FrontBackCappedList<T>  {
	
	private T[] list;
	private int numberOfElements;
	
	public ArrayFrontBackCappedList(int size) {
		list = (T[]) new Object[size];
		numberOfElements = 0;
//        Object[] objs = {2, 4, 6, 8, 9, null, null, null, null, null};
//        this.list = (T[]) objs;
//        this.numberOfElements = 5;
	}

	@Override
	public boolean addFront(Object newEntry) {
		if (!this.isFull()) {
			for (int i = numberOfElements; i > 0; i--) {
				list[i] = list[i - 1];
			}
			this.numberOfElements++;
			list[0] = (T) newEntry;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean addBack(Object newEntry) {
		if (!this.isFull()) {
			list[numberOfElements] =  (T) newEntry;
			numberOfElements++;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public T removeFront() {
		if (this.isEmpty()) {
			return null;
		} else {
			T item = list[0];
			for (int i = 1; i < numberOfElements; i++) {
				list[i - 1] = list[i];
			}
			numberOfElements--;
			return item;

		}
	}

	@Override
	public T removeBack() {
		if (this.isEmpty()) {
			return null;
		} else {
			numberOfElements--;
			return list[numberOfElements];
		}
	}

	@Override
	public void clear() {
		numberOfElements = 0;
	}

	@Override
	public T getEntry(int givenPosition) {
		if (this.isValid(givenPosition)) {
			return list[givenPosition];
		} else {
			return null;
		}
	}

	@Override
	public int indexOf(Object anEntry) {
		if (this.isEmpty()) {
			return -1;
		}
		for (int i=0; i < numberOfElements; i++) {
			if (list[i].equals(anEntry)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object anEntry) {
		if (this.isEmpty()) {
			return -1;
		}
		for (int i=numberOfElements - 1; i >= 0; i--) {
			if (list[i].equals(anEntry)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean contains(Object anEntry) {
		return this.indexOf(anEntry) != -1;
	}

	@Override
	public int size() {
		return this.numberOfElements;
	}

	@Override
	public boolean isEmpty() {
		return (this.numberOfElements == 0);
	}

	@Override
	public boolean isFull() {
		return (this.list.length == this.numberOfElements);
	}

	@Override
	public String toString() {
		String output = "size=" + this.numberOfElements + "; capacity=" + this.list.length + ";\t[";
		if (this.numberOfElements >= 1) {
			for (int i = 0; i < numberOfElements - 1; i++) {
				output += list[i].toString() + ", ";
			}
			return output + list[numberOfElements - 1] + "]";
		} else {
			return output + "]";
		}
	}
	
	private boolean isValid(int index) {
		return ( (index >= 0) && (index < this.numberOfElements) );
	}
	
}

import java.util.ArrayList;
import java.util.List;

public class ListFrontBackCappedList<T> implements FrontBackCappedList<T> {
	
	private List<T> list; // initialize to type ArrayList<T> in the ListFrontBackCappedList constructor
	
	public ListFrontBackCappedList(int size) {
		list = new ArrayList<T>(size);
		Object newSize = (Object) size;
		list.add((T) newSize);

	}

	@Override
	public boolean addFront(Object newEntry) {
		if (this.isFull()) {
			return false;
		} else {
			list.add(1, (T) newEntry);
			return true;
		}
	}

	@Override
	public boolean addBack(Object newEntry) {
		if (this.isFull()) {
			return false;
		} else {
			return list.add((T) newEntry);
		}
		
	}

	@Override
	public T removeFront() {
		if (this.isEmpty()) {
			return null;
		} else {
			return list.remove(1);
		}
	}

	@Override
	public T removeBack() {
		if (this.isEmpty()) {
			return null;
		} else {
			return list.remove(list.size() - 1);
		}
	}

	@Override
	public void clear() {
		int size = this.getCapacity();
		list = new ArrayList<T>(size);
		Object newSize = (Object) size;
		list.add((T) newSize);
	}

	@Override
	public T getEntry(int givenPosition) {
		if (this.isValid(givenPosition + 1)) {
			return list.subList(1,list.size()).get(givenPosition);
		} else {
			return null;
		}
	}

	@Override
	public int indexOf(Object anEntry) {
		if (this.isEmpty()) {
			return -1;
		} else {
			return list.subList(1, list.size()).indexOf(anEntry);
		}
	}

	@Override
	public int lastIndexOf(Object anEntry) {
		if (this.isEmpty()) {
			return -1;
		} else {
			return list.subList(1, list.size()).lastIndexOf(anEntry);
		}
	}

	@Override
	public boolean contains(Object anEntry) {
		if (this.isEmpty()) {
			return false;
		} else {
			return list.subList(1, list.size()).contains(anEntry);
		}
	}

	@Override
	public int size() {
		return list.size() - 1;
	}

	@Override
	public boolean isEmpty() {
		return list.size() == 1;
	}

	@Override
	public boolean isFull() {
		return list.size() == this.getCapacity() + 1; // list.size includes list(0)
	}
	
	public int getCapacity() {
		return (int) list.get(0);
	}

	@Override
	public String toString() {
		String output = "size=" + this.size() + "; capacity=" + this.getCapacity() + ";\t[";
		if (list.size() >= 2) {
			for (int i = 1; i < list.size() - 1; i++) {
				output += list.get(i) + ", ";
			}
			return output + list.get(list.size() - 1) + "]";
		} else {
			return output + "]";
		}
	}
	
	private boolean isValid(int index) {
		return ( (index >= 1) && (index < list.size()) );
	}
	
	private int reIndex(int index) {
		return index + 1;
	}
	
}

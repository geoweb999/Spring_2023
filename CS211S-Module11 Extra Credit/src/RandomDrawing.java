import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class RandomDrawing<T> implements RandomDrawingInterface<T> { 	

	private List<T> drawingList;
	public boolean allowsDuplicates;
	private int size;
	private Predicate<T> filter; 
	
	public RandomDrawing(boolean allowsDuplicates) {
		List<T> newList = new ArrayList<>();
		this.drawingList = newList;
		size = 0;
		this.allowsDuplicates = allowsDuplicates;
		Predicate<T> filter = (s-> { return true;});
		this.filter = filter;
	}
	
	public RandomDrawing(boolean allowsDuplicates, Predicate<T> filter) {
		this(allowsDuplicates);
		this.filter = filter;
	}
	
	@Override
	public boolean addEntry(T entry) {
		if (!filter.test(entry)) {
				return false;
		}
		if (this.allowsDuplicates) {
			if (drawingList.add(entry)) {
				size++;
				return true;
			}
		} else {
			if (!drawingList.contains(entry)) {
				if (drawingList.add(entry)) {
					size++;
					return true;
				}
			} else {
				return false;
			}
		}
		return false;
	}

	public T selectWinner(boolean removeWinner) {
		if (this.size == 0) {
			return null;
		}
		
		Random random = new Random();
		int index = random.nextInt(this.size);
		Object item = this.drawingList.get(index);
		if (removeWinner) {
			this.drawingList.remove(index);
			size--;
		}
		return (T) item;
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean allowsDuplicates() {
		return this.allowsDuplicates;
	}
	
	public void displayEntries() {
		System.out.println("Drawing List allows duplicates=" + this.allowsDuplicates + ":");
		for (T item : this.drawingList) {
			System.out.println("\t"+item);
		}
	}
	

	

}

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomDrawing<T> implements RandomDrawingInterface<T> {
	

	private List<T> drawingList;
	public boolean allowsDuplicates;
	private int size;
	
	
	public RandomDrawing(boolean allowsDuplicates) {
		List<T> newList = new ArrayList<>();
		this.drawingList = newList;
		size = 0;
		this.allowsDuplicates = allowsDuplicates;
	}
	
	public boolean addEntry(T entry) {
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
		System.out.println("Drawing List allows duplictes=" + this.allowsDuplicates + ":");
		for (T item : this.drawingList) {
			System.out.println("\t"+item);
		}
	}
	

	

}

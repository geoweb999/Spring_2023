
public class RandomDrawingCapped<T> extends RandomDrawing<T> implements RandomDrawingInterface<T> {

	private static final int MAX_CAPACITY = 100;
	private int capacity;
	
	public RandomDrawingCapped(boolean allowDuplicates) {
		super(allowDuplicates);
		this.capacity = MAX_CAPACITY;
	}
	
	public RandomDrawingCapped(boolean allowDuplicates, int capacity) {
		super(allowDuplicates);
		this.capacity = capacity;
	}
	
	@Override
	public boolean addEntry(T entry) {
		if (this.size() == this.capacity) {
			return false;
		} else {
			return super.addEntry(entry);
		}

	}
}

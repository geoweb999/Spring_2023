import java.util.ArrayList;

public class BoxH<T> {
	
	private T thing;
	private int thingCount;
	private ArrayList<T> thingHistory;
	
	public BoxH(T firstThing) {
		this.thing = firstThing;
		this.thingCount = 1;
		this.thingHistory = new ArrayList<T>();
		thingHistory.add(firstThing);
	}
	
	public T getContents() {
		return thing;
	}
	
	public int getCount() {
		return thingCount;
	}
	
	public ArrayList<T> getThingHistory() {
		return thingHistory;
	}
	
	public void replaceContents(T newThing) {
		this.thing = newThing;
		thingCount++;
		this.thingHistory.add(newThing);
	}
	
	public boolean hasDuplicates() {
		for (int i = 0; i < this.thingHistory.size(); i++) {
			for (int j = i + 1; j < this.thingHistory.size(); j++) {
				if (this.thingHistory.get(i) == this.thingHistory.get(j)) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		String output = thing.toString() + " (item " + thingCount + ")";
		for (int i = 0; i <  this.thingHistory.size(); i++) {
			output += "\nHistory[" + i + "] = " + this.thingHistory.get(i);
		}
		return output;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof BoxH<?>) {
			BoxH<?> otherBoxR = (BoxH<?>) other;
			boolean sameThing = this.thing.equals(otherBoxR.thing);
			boolean sameCount = this.thingCount==otherBoxR.thingCount;
			if (!sameCount) {
				// history cannot be the same so return false
				return false;
			}
			boolean sameHistory = true;  // used in loop to see if thingHistory is the same
			if (this.thingHistory.size() != otherBoxR.thingHistory.size()) {
				return false;
			} else {
				// no worries about index out of bounds now
				for (int i = 0; i < this.thingHistory.size(); i++) {
					sameHistory = sameHistory && this.thingHistory.get(i).equals(otherBoxR.thingHistory.get(i));
				}
			}
			return sameThing && sameCount && sameHistory;
		} else {
			return false;
		}
	}
}
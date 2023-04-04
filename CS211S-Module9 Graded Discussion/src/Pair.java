
public class Pair <T extends Comparable<? super T>>{
	
	private T first;
	private T second;
		
	public Pair(T first, T second) {
		this.first = first;
		this.second = second;
	}
	
	public T getFirst() {
		return first;
	}
	public void setFirst(T first) {
		this.first = first;
	}
	public T getSecond() {
		return second;
	}
	public void setSecond(T second) {
		this.second = second;
	}
	
	@Override
	public String toString() {
	    return "First: " + first + "\tSecond: " + second;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Pair<?>) {
			Pair<?> otherPair = (Pair<?>) obj;
			return (this.first.equals(otherPair.first) && this.second.equals(otherPair.second))
					||
					(this.first.equals(otherPair.second) && this.second.equals(otherPair.first));
		} else {
			return false;
		}
	}
	
	public void order() {
		if(this.first.compareTo(this.second)>0) { // first is bigger than second
			T temp = this.first;
			this.first = this.second;
			this.second = temp;
		}
	}
	

}

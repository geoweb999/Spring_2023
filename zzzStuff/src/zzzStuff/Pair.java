package zzzStuff;

public class Pair <T extends Comparable<? super T>, U extends Comparable<? super U>>{
	
	private T first;
	private U second;
		
	public Pair(T first, U second) {
		this.first = first;
		this.second = second;
	}
	
	public T getFirst() {
		return first;
	}
	public void setFirst(T first) {
		this.first = first;
	}
	public U getSecond() {
		return second;
	}
	public void setSecond(U second) {
		this.second = second;
	}
	
	@Override
	public String toString() {
	    return "First: " + first + "\tSecond: " + second;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Pair<?, ?>) {
			Pair<?, ?> otherPair = (Pair<?, ?>) obj;
			return (this.first.equals(otherPair.first) && this.second.equals(otherPair.second))
					||
					(this.first.equals(otherPair.second) && this.second.equals(otherPair.first));
		} else {
			return false;
		}
	}
	

	public int compareTo(Pair<T, U> other) {
		int cmp = first.compareTo(other.getFirst());
        if (cmp != 0) {
            return cmp;
        }
        return second.compareTo(other.getSecond());
	}

}

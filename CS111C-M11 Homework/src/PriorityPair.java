

public class PriorityPair <T extends Comparable<? super T>, U extends Comparable<? super U>>{
	
	private T priority;
	private U task;
		
	public PriorityPair(T first, U second) {
		this.priority = first;
		this.task = second;
	}
	
	public T getFirst() {
		return priority;
	}
	public void setFirst(T first) {
		this.priority = first;
	}
	public U getSecond() {
		return task;
	}
	public void setSecond(U second) {
		this.task = second;
	}
	
	@Override
	public String toString() {
	    return "Priority: " + priority + " Task: " + task;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof PriorityPair<?, ?> otherPair) {
//			Pair<?, ?> otherPair = (Pair<?, ?>) obj;
			return (this.priority.equals(otherPair.priority) && this.task.equals(otherPair.task));
		} else {
			return false;
		}
	}
	

	public int compareTo(PriorityPair<T, U> other) {
		int cmp = priority.compareTo(other.getFirst());
        if (cmp != 0) {
            return cmp;
        }
        return task.compareTo(other.getSecond());
	}

}

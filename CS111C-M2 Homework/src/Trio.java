
public class Trio<T> {
	
	private T item1;
	private T item2;
	private T item3;
	
	public Trio(T item1, T item2, T item3) {
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
	}
	
	public Trio(T item) {
		this(item, item, item);
	}

	public T getItem1() {
		return item1;
	}

	public void setItem1(T item1) {
		this.item1 = item1;
	}

	public T getItem2() {
		return item2;
	}

	public void setItem2(T item2) {
		this.item2 = item2;
	}

	public T getItem3() {
		return item3;
	}

	public void setItem3(T item3) {
		this.item3 = item3;
	}
	
	public void replaceAll(T item) {
		this.item1 = item;
		this.item2 = item;
		this.item3 = item;

	}
	
	public boolean hasDuplicates() {
		return ( this.item1.equals(item2) || this.item1.equals(item3) || this.item2.equals(item3) );
	}
	
	public int count(T item) {
		int counter = 0;
		if (item.equals(item1)) {
			counter++;
		}
		if (item.equals(item2)) {
			counter++;
		}
		if (item.equals(item3)) {
			counter++;
		}
		return counter;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof Trio<?> ) {
			Trio<T> otherTrio = (Trio<T>) other;
			boolean case1, case2, case3;
			case1 = this.count(item1) == otherTrio.count(item1); 
			case2 = this.count(item2) == otherTrio.count(item2);
			case3 = this.count(item3) == otherTrio.count(item3);
			return case1 && case2 && case3;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "\nTrio: \nItem1: " + this.item1 + "\nItem2: " + this.item2 + "\nItem3: " + this.item3;
	}
}

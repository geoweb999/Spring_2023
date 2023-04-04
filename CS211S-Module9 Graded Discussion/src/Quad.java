// I updated the class header because of the modifications made to Pair so that it
// only holds Comparable objects. If Pair was not modified, then the Quad class
// header would be: public class Quad<T, S>
public class Quad<T extends Comparable<? super T>, S extends Comparable<? super S>> {
	
	private Pair<T> pair1;
	private Pair<S> pair2;
	
	public Quad(Pair<T> pair1, Pair<S> pair2) {
		this.pair1 = pair1;
		this.pair2 = pair2;
	}
	
	@Override
	public String toString() {
		return "Quad Pair 1: " + pair1.toString() + "\n" +
		       "Quad Pair 2: " + pair2.toString();
	}

}

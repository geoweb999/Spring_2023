
public class Cat extends Animal {

	private boolean allowedOutside;

	public Cat(String name, int age, AnimalStatus status, boolean allowedOutside) {
		super(name, age, status);
		this.allowedOutside = allowedOutside;
	}

	public boolean isAllowedOutside() {
		return allowedOutside;
	}

	public void setAllowedOutside(boolean allowedOutside) {
		this.allowedOutside = allowedOutside;
	}
	
	
}

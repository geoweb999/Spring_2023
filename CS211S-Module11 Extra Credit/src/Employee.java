
public class Employee {
	
	private String name;
	private boolean eligible;

	public Employee(String name, boolean eligible) {
		this.name = name;
		this.eligible = eligible;
	}
	public String getName() {
		return name;
	}
	public boolean isEligible() {
		return eligible;
	}

	@Override
	public String toString() {
		return name + (eligible ? "---eligible" : "---not eligible");
	}
	
	
	

}

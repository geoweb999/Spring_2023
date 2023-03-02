
public class Person implements Comparable<Person>{
	
	private String firstName, lastName;
	private int id;
	
	public Person(String firstName, String lastName, int id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName + " (" + id + ")";
	}
	
	public int compareTo(Person p1) {
		if (this.lastName.compareToIgnoreCase(p1.lastName) == 0) {
			if (this.firstName.compareToIgnoreCase(p1.firstName) == 0) {
				return this.id - p1.id;
			} else {
				return this.firstName.compareToIgnoreCase(p1.firstName);
			}
		} else {
			return this.lastName.compareToIgnoreCase(p1.lastName);
		}
	}
	
	
}

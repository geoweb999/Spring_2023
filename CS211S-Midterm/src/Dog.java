
public class Dog extends Animal {
	
	private String breed;

	public Dog(String name, int age, AnimalStatus status, String breed) {
		super(name, age, status);
		this.breed = breed;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

}

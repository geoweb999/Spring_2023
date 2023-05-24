
public class Animal {
	private String name;
    private int age;
    
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public void eat() {
        System.out.println("The animal is eating.");
    }
    
    public void sleep() {
        System.out.println("The animal is sleeping.");
    }
    
    public void makeSound() {
        System.out.println("The animal is making a sound.");
    }
}

import java.util.Comparator;


public abstract class Animal implements Comparable<Animal> {

    private String name;
    private int age;
    private AnimalStatus status;

    public Animal(String name, int age, AnimalStatus status) {
        this.name = name;
        this.age = age;
        this.status = status;
    }
    
	public static final Comparator<Animal> AGE_COMPARATOR = new AnimalAgeComparator();
	
	public static class AnimalAgeComparator implements Comparator<Animal>{
		
		public int compare(Animal a1, Animal a2 ) {
			return Integer.valueOf(a1.age).compareTo(a2.age);
		}
	}

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public AnimalStatus getStatus() {
        return status;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        }
    }
    public void setStatus(AnimalStatus status) {
    	this.status = status;
    }
    @Override
    public String toString() {
        return name + " (Age: " + age + ")";
    }
    
    
    public void feed() {
    	System.out.println("Feeding " + name);
    }

    public void adopt() {
    	System.out.println(name + " is being adopted.");
    }

    @Override
    public boolean equals(Object obj) {
		if(obj instanceof Animal other) {
			return this.name.equalsIgnoreCase(other.name) && this.age == other.age;
		} else {
			return false;
		}
		
	}
    	
    @Override
    public int compareTo(Animal other) {
    	// implement as case SENSITIVE
    	if (this.name.equals(other.name)) {
    		return Integer.valueOf(age).compareTo(other.age);
    	} else {
    		return this.name.compareTo(other.name);
    	}
    }
}
   
   


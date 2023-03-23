import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	
	public static void printOlderAnimalsToFile(List<Animal> animals, int minAge, String fileName) {
		
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileOutputStream(fileName));

			for (Animal animal  : animals) {
				if (animal.getAge() >= minAge) {
					out.println(animal.getName());
				}
			}
		} catch (IOException ex) {
		      System.out.println("An I/O error occurred opening file: " + fileName);
		      ex.printStackTrace();
		} finally {
			out.close();
			System.out.println("In finally");
		}
		System.out.println("At end");
	}
	
	public static int countAdoptableAnimals(List<Animal> animals) {
		int count = 0;
	    for (Animal a : animals) {
	    	if (a.getStatus().isAdoptable()) {
	    		count++;
	    	}
	    }
		return count;
	}
	
	public static void adoptionUpdates(Map<String, Animal> animalMap, List<String> animalNameList) {
	    for (String name : animalNameList) {
	    	if (animalMap.containsKey(name)) {
	    		animalMap.get(name).adopt();
	    	} else {
	    		System.out.println("Animal " + name + " not found in animalMap");
	    	}
	    }
	}
	
	public static void methodA() {
	    try {
	        System.out.println("Statement 1");

		    try {
		        System.out.println("Statement 6");
		    } catch (ExceptionS ex) {
		        System.out.println("Statement 7");
		    } catch (ExceptionT ex) {
		        System.out.println("Statement 8");
		    } finally {
		        System.out.println("Statement 9");
		    }
	        System.out.println("Statement 10");
	        
	        System.out.println("Statement 2");
	    } catch (ExceptionQ ex) {
	        System.out.println("Statement 3");
	    } finally {
	        System.out.println("Statement 4");
	    }
        System.out.println("Statement 5");
	}


;
	}  
	
	
	public static void main(String[] args) {
		
//		Animal a1 = new Dog("Fido", 2, AnimalStatus.SHORT_TERM_RESIDENT, "Collie");
//		Animal a2 = new Dog("Fido", 3, AnimalStatus.SHORT_TERM_RESIDENT, "Shepard");
//		Animal a3 = new Dog("Fido", 2, AnimalStatus.SHORT_TERM_RESIDENT, "Pit Bull");
//		 
//		System.out.println("a1 < a2 " + (a1.compareTo(a2) < 0));
//		System.out.println("a1 = a3 " + a1.equals(a3));
		
		List<Animal> animalList = new ArrayList<>();
		animalList.add(new Dog("Droolius Caesar", 5, AnimalStatus.NEW, "Mutt"));
		animalList.add(new Dog("Salvador Dogi",   2, AnimalStatus.SHORT_TERM_RESIDENT, "Lab"));
		animalList.add(new Cat("Hairy Pawter",    8, AnimalStatus.NEW, false));
		animalList.add(new Bird("Meryl Cheep",    1, AnimalStatus.SHORT_TERM_RESIDENT));
		
		for(Animal animal : animalList) {
		     animal.feed();
		}
		
		printOlderAnimalsToFile(animalList, 5, "animals.txt");
//		System.out.println(countAdoptableAnimals(animalList));
		Map <String, Animal> animalMap = new HashMap<>();
		for (Animal a : animalList) {
			animalMap.put(a.getName(), a);
		}
		List<String> animals = new ArrayList<>();
		animals.add("Salvador Dogi");
		animals.add("Fido");
		adoptionUpdates(animalMap, animals);
		
		
	}

}

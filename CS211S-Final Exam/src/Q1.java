import java.util.ArrayList;
import java.util.List;

public class Q1 {

	public static void methodA(List<Animal> list) { return; }

	public static void methodB(List<? extends Animal> list) { return; }

	public static void methodC(List<? super Dog> list) { return; }

	public static void methodD(List<? extends Dog> list) { return; }

	public static void methodE(List<?> list) { return; }
	
	public static void Main(String[] args) {
		List<Animal> animalList = new ArrayList<>();
		List<Dog> dogList = new ArrayList<>();
		List<Bird> birdList = new ArrayList<>();
		
		methodE ( birdList ); 

	}
}

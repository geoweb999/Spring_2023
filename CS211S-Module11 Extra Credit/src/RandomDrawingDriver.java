import java.util.*;
import java.util.function.Predicate;

public class RandomDrawingDriver {

	public static final boolean ALLOW_DUPLICATES = true;
	
	public static void main(String[] args) {
		
		System.out.println("\n**************************TESTING STRING DRAWING: LETTERS AND SPACES ONLY**************************");
		Predicate<String> filter = RandomDrawingDriver::containsOnlyLettersSpaces;
		RandomDrawingInterface<String> stringDrawingLettersSpacesOnly = new RandomDrawing(true, filter);
		fillRandomStringBox(stringDrawingLettersSpacesOnly);
		System.out.print("Eligible entries added: ");
		stringDrawingLettersSpacesOnly.displayEntries();

		System.out.println("\n**************************TESTING INTEGER DRAWING: EVEN NUMBERS ONLY**************************");
		Predicate<Integer> filter1 = (s -> s%2 == 0);
		RandomDrawingInterface<Integer> numberDrawingEvensOnly = new RandomDrawing(true, filter1); 
		fillRandomIntegerBox(numberDrawingEvensOnly, 20);
		System.out.print("Eligible entries added: ");
		numberDrawingEvensOnly.displayEntries();
		
		System.out.println("\n**************************TESTING EMPLOYEE DRAWING: ELIGIBLE EMPLOYEES ONLY**************************");
		Predicate<Employee> filter2 = Employee::isEligible;
		RandomDrawingInterface<Employee> employeeDrawingEligibleOnly = new RandomDrawing(true, filter2);
		fillRandomEmployeeBox(employeeDrawingEligibleOnly, 10);
		System.out.print("Eligible entries added: ");
		employeeDrawingEligibleOnly.displayEntries();
		
		// test using constructor with default filter
		System.out.println("\n**************************TESTING EMPLOYEE DRAWING: ALL EMPLOYEES **************************");
		RandomDrawingInterface<Employee> employeeDrawingEligible = new RandomDrawing(true);
		fillRandomEmployeeBox(employeeDrawingEligible, 10);
		System.out.print(" Entries added: ");
		employeeDrawingEligible.displayEntries();
		
	}
	

	// YOUR BOOLEAN METHOD HERE
	public static boolean containsOnlyLettersSpaces(String word) {
		    if (word == null || word.isEmpty()) {
		        return false;
		    }
		    for (char c : word.toCharArray()) {
		        if (!Character.isLetter(c) && c != ' ') {
		            return false;
		        }
		    }
		    return true;
	}
	
	private static List<String> names() {
		List<String> nameList = new ArrayList<String>();
		nameList.add("Adam Zapel"); nameList.add("Al Dente"); nameList.add("Al Fresco"); nameList.add("Al K. Seltzer"); nameList.add("Alf A. Romeo"); nameList.add("Amanda Lynn"); nameList.add("Anita Job"); nameList.add("Anna Conda"); nameList.add("Anna Graham"); nameList.add("Anna Prentice "); nameList.add("Anna Sasin"); nameList.add("Anne Teak"); nameList.add("B.A. Ware"); nameList.add("Barb Dwyer"); nameList.add("Barb E. Dahl"); nameList.add("Barbara Seville"); nameList.add("Barry Cade"); nameList.add("Bea Minor"); nameList.add("Dee Major"); nameList.add("Beau Tye"); nameList.add("Bill Board"); nameList.add("Cara Van"); nameList.add("Chris P. Bacon"); nameList.add("Constance Noring"); nameList.add("Crystal Ball"); nameList.add("Crystal Glass"); nameList.add("Earl Lee Riser"); nameList.add("Easton West "); nameList.add("Ferris Wheeler"); nameList.add("Flint Sparks"); nameList.add("Franklin Stein "); nameList.add("Gene Poole"); nameList.add("Heidi Clare"); nameList.add("Helen Back"); nameList.add("Helen Wiells"); nameList.add("Holly McRell"); nameList.add("Holly Wood"); nameList.add("Jack Pott"); nameList.add("Joe Kerr"); nameList.add("Joy Rider"); nameList.add("Justin Case"); nameList.add("Justin Time"); nameList.add("Kandi Apple"); nameList.add("Laura Norder"); nameList.add("Leigh King "); nameList.add("Luke Warm"); nameList.add("Marsha Mellow"); nameList.add("Marshall Law"); nameList.add("Marty Graw"); nameList.add("Olive Branch"); nameList.add("Paige Turner"); nameList.add("Pepe Roni"); nameList.add("Price Wright"); nameList.add("Rocky Beach"); nameList.add("Sandy Beach"); nameList.add("Sal A. Mander"); nameList.add("Stanley Cupp"); nameList.add("Tom Morrow"); nameList.add("Warren Peace"); nameList.add("Will Power"); nameList.add("X. Benedict");
		nameList.add("Pete Repeaty"); nameList.add("Pete Repeaty"); nameList.add("Pete Repeaty"); nameList.add("Pete Repeaty");
		return nameList;
	}
	public static void fillRandomEmployeeBox(RandomDrawingInterface<Employee> employeeBox, int size) {
		Random generator = new Random();
		List<String> names = names();
		for(int i=0; i<size; i++) {
			String name = names.get(generator.nextInt(names.size()));
			boolean eligible = generator.nextBoolean();
			Employee employee = new Employee(name, eligible); 
			boolean success = employeeBox.addEntry(employee);
			if(!success) {
				System.out.println("Ineligible entry: " + employee);
			}
		}
	}
	public static void fillRandomStringBox(RandomDrawingInterface<String> wordBox) {
		List<String> names = names();
		for(String name : names) {
			boolean success = wordBox.addEntry(name);
			if(!success) {
				System.out.println("Ineligible entry: " + name);
			}
		}
	}
	public static void fillRandomIntegerBox(RandomDrawingInterface<Integer> numbersBox, int numberOfNumbers) {
		Random generator = new Random();
		int maxNumber = numberOfNumbers * 10;
		for(int i=0; i<numberOfNumbers; i++) {
			int entry = generator.nextInt(maxNumber);
			boolean success = numbersBox.addEntry(entry);
			if(!success) {
				System.out.println("Ineligible entry: " + entry);
			}
		}
	}
	
}

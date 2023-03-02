import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

public class M3Driver {
	public static final int TWIN_ENGINE = 2;

	public static int  getMenuChoice() {
		Scanner scnr = new Scanner(System.in);
		int choice = 5;
		boolean validChoice = false;
		while (!validChoice) {
			System.out.println("\n");
			System.out.println("1. Add new airplane");
			System.out.println("2. Execute Takeoff");
			System.out.println("3. Change Altitude");
			System.out.println("4. List all aircraft by Registraion");
			System.out.println("5. List all aircraft by Altitude");
			System.out.println("6. Exit");
			choice = scnr.nextInt();
			validChoice = (choice >= 1 && choice <= 6);
		}
		return choice;
	}
	
	public static Aircraft newAircraft(Location[] locations) {
		Scanner scnr = new Scanner(System.in);
		System.out.print("Enter registration number: ");
		String regNumber = scnr.nextLine();
		boolean validChoice = false;
		int choice = 0;
		while (!validChoice) {
			for (int i = 0; i < locations.length; i++) {
				System.out.println(i + ". Location: " + locations[i]);
			}
			System.out.print("Enter location #: ");
			choice = scnr.nextInt();
			validChoice = (choice >= 0 && choice < locations.length);
		}
		// intial version only creates FixedWing, 1 soul, default engines
		return new FixedWing(regNumber, locations[choice], 1);
		
	}
	
	public static void changeAltitude(ArrayList<Aircraft> planes) {
		Scanner scnr = new Scanner(System.in);
		int choice = getPlaneChoice(planes);
		System.out.print("Enter new altitude: ");
		int altitude = scnr.nextInt();
		planes.get(choice).setAltitude(altitude);
	}
	
	public static void listPlanes(ArrayList<Aircraft> planes) {
		for (int i = 0; i < planes.size(); i++) {
			System.out.println(i + ". Plane: " + planes.get(i));
		}
	}
	
	public static int getPlaneChoice(ArrayList<Aircraft> planes) {
		boolean validChoice = false;
		int choice = 0;
		Scanner scnr = new Scanner(System.in);
		while (!validChoice) {
			listPlanes(planes);
			System.out.print("Enter plane #: ");
			choice = scnr.nextInt();
			validChoice = (choice >= 0 && choice < planes.size());
		}
		return choice;
	}
	
	public static void performTakeoff(ArrayList<Aircraft> planes) {
		int choice = getPlaneChoice(planes);
		planes.get(choice).executeTakeoff();
	}
	
	public static void main(String[] args) {
		// Array of test locations with different terrain types
		Location[] locations = new Location[5];
		locations[0] = new Location(37.0, 37.0, 0, TerrainType.LAND);
		locations[1] = new Location(21.0, 98.0, 0, TerrainType.LAND);
		locations[2] = new Location(71.0, 67.0, 0, TerrainType.LAND);
		locations[3] = new Location(60.0, 15.0, 0, TerrainType.SEA);
		locations[4] = new Location(80.0, 17.0, 0, TerrainType.MOUNTAIN);
		
		boolean quitMenu = false;
		int menuChoice = 0;
		ArrayList<Aircraft> planes = new ArrayList<Aircraft>();
		while (!quitMenu) {
			menuChoice = getMenuChoice();
			switch (menuChoice) {
			case 1:
				planes.add(newAircraft(locations));
				break;
			case 2:
				performTakeoff(planes);
				break;
			case 3:
				changeAltitude(planes);
				break;
			case 4:
				// M3 USING COMPARATOR
				Collections.sort(planes, Aircraft.REGID_COMPARATOR);
				listPlanes(planes);
				break;
			case 5:
				// M3 USING COMPARATOR
				Collections.sort(planes, Aircraft.ALTITUDE_COMPARATOR);
				listPlanes(planes);
				break;
			case 6:
				quitMenu = true;
				System.out.println("Exiting system.");
				break;
			}			
		}
		
	}
}

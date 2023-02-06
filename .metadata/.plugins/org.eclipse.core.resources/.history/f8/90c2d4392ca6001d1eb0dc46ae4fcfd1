import java.util.Arrays;

public class M2Driver {
	
	public static final String LAND_TYPE = "LAND";
	public static final String SEA_TYPE = "SEA";
	public static final String MOUNTAIN_TYPE = "MNT";
	public static final int TWIN_ENGINE = 2;

	
	public static void main(String[] args) {
		// Array of test locations with different terrain types
		Location[] locations = new Location[5];
		locations[0] = new Location(37.0, 37.0, 0, LAND_TYPE);
		locations[1] = new Location(21.0, 98.0, 0, LAND_TYPE);
		locations[2] = new Location(71.0, 67.0, 0, LAND_TYPE);
		locations[3] = new Location(60.0, 15.0, 0, SEA_TYPE);
		locations[4] = new Location(80.0, 17.0, 0, MOUNTAIN_TYPE);
		
		// Array of differing classes of Aircraft
		Aircraft[] aircraft = new Aircraft[5];
		// M2 HOMEWORK STATIC 
		System.out.println("Current number of aircraft: " + Aircraft.getNumberOfAircraft());
		aircraft[0] = new FixedWing("N1257X", locations[0],1);
		aircraft[1] = new FixedWing("N7526J", locations[1],2);
		aircraft[2] = new FixedWing("N999OT", locations[2],89, TWIN_ENGINE);
		aircraft[3] = new Seaplane("N11wtf", locations[3],12);
		aircraft[4] = new Rotary("N11WTF", locations[4],6);

		for (int i = 0; i < 5; i++) {
			System.out.println(aircraft[i]);
		}
		// M2 HOMEWORK STATIC 
		System.out.println("Current number of aircraft: " + Aircraft.getNumberOfAircraft());

		// M2 USING COMPARABLE/SORT
		System.out.println("************* Sort Test *************");
		Arrays.sort(aircraft);
		for (int i = 0; i < 5; i++) {
			System.out.println(aircraft[i]);
		}
		System.out.println("*************           *************");
	
		// M2 USING FACTORY
		Aircraft[] aircraftF = new Aircraft[3];
		aircraftF[0] = AircraftFactory.newAircraft("FIXEDWING", "N11111", locations[2], 1); // DEFAULT NUM ENGINES = 1
		aircraftF[1] = AircraftFactory.newAircraft("ROTARY", "N22222", locations[2], 2, 2); // numEngines = 2
		aircraftF[2] = AircraftFactory.newAircraft("SEAPLANE", "N33333", locations[2], 1); // DEFAULT NUM ENGINES = 1
		
		System.out.println("************* Factory Test *************");
		Arrays.sort(aircraftF);
		for (int i = 0; i < 3; i++) {
			System.out.println(aircraftF[i]);
		}
		System.out.println("*************           *************");
		System.out.println("Current number of aircraft: " + Aircraft.getNumberOfAircraft());
	}

}

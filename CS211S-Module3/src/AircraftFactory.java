// M2 USING FACTORY
public class AircraftFactory {
	
	public static final int DEFAULT_NUMBER_ENGINES = 1;
	
	// "constructor" with param for numEnginers
	public static Aircraft newAircraft(String type, String registrationID, Location location, int soulsOnBoard, int numEngines) {
		if (type.equalsIgnoreCase("FIXEDWING")) {
			return new FixedWing(registrationID, location, soulsOnBoard, numEngines);
		} else if (type.equalsIgnoreCase("SEAPLANE")) {
			return new Seaplane(registrationID, location, soulsOnBoard, numEngines);
		} else if (type.equalsIgnoreCase("ROTARY")) {
			return new Rotary(registrationID, location, soulsOnBoard, numEngines);
		} else {
			throw new IllegalArgumentException(type + " is an invalid Product type.");
		}
	}
	// "constructor" for default number of engines
	public static Aircraft newAircraft(String type, String registrationID, Location location, int soulsOnBoard) {
		if (type.equalsIgnoreCase("FIXEDWING")) {
			return new FixedWing(registrationID, location, soulsOnBoard, DEFAULT_NUMBER_ENGINES);
		} else if (type.equalsIgnoreCase("SEAPLANE")) {
			return new Seaplane(registrationID, location, soulsOnBoard, DEFAULT_NUMBER_ENGINES);
		} else if (type.equalsIgnoreCase("ROTARY")) {
			return new Rotary(registrationID, location, soulsOnBoard, DEFAULT_NUMBER_ENGINES);
		} else {
			throw new IllegalArgumentException(type + " is an invalid Product type.");
		}
	}
}

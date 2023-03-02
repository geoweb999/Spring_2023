import java.util.Comparator;

public class Aircraft implements Comparable<Aircraft>  {

	private String registrationID;	// a unique identifier
	private AircraftType type;
	private int soulsOnBoard;
	private int numEngines;
	private boolean airBorne;
	private boolean isFueled;
	
	
	public Aircraft(String registrationID, int soulsOnBoard, int numEngines, AircraftType type, boolean isFueled) {
		this.registrationID = registrationID;
		this.type = type;
		this.soulsOnBoard = soulsOnBoard;
		this.numEngines = numEngines;
		this.isFueled = isFueled;
		this.airBorne = false;

	}

	public static final Comparator<Aircraft> REGID_COMPARATOR = new AircraftComparator();
	
	private static class AircraftComparator implements Comparator<Aircraft>{
		
		public int compare(Aircraft a1, Aircraft a2 ) {
			return a1.registrationID.compareToIgnoreCase(a2.registrationID);
		}

		
	}
	
	public int compareTo(Aircraft obj)  {
		// Order alphanumerically by Registrtion ID, case insensitive
		return this.registrationID.toUpperCase().compareTo(obj.registrationID.toUpperCase());
	}
	
	public int getSoulsOnBoard() {
		return soulsOnBoard;
	}

	
	public void setSoulsOnBoard(int soulsOnBoard) {
		soulsOnBoard = (soulsOnBoard < 0) ? 0 : soulsOnBoard;
		this.soulsOnBoard = soulsOnBoard;
	}

		
	public int getNumEngines() {
		return numEngines;
	}


	public void setNumEngines(int numEngines) {
		this.numEngines = numEngines;
	}


	public String getRegistrationID() {
		return registrationID;
	}

	
	public void setRegistrationID(String registrationID) {
		this.registrationID = registrationID;
	}
	
	
	public String getType() {
		return type.getAbbreviation();
	}


	public void setType(AircraftType type) {
		this.type = type;
	}


	public void setAirborne(boolean airborne) {
		this.airBorne = airborne;
	}
	
	
	public boolean isAirborne() {
		return this.airBorne;
	}

	public String takeOff() {
		this.airBorne = true;
		return this.registrationID + " is airborne!";
	}
	
	public String land() {
		this.airBorne = false;
		return this.registrationID + " has landed!";
	}
		
	@Override
	public String toString() {
		//TODO apply better formatting for readability
		String airborne = (this.airBorne) ? "is airborne" : "is not airborne";
		return String.format("Registration: %6s: %12s, %1d engine(s), %3d soul(s) on board %15s has fuel: %5s", 
				this.registrationID, this.type.getAbbreviation(),this.numEngines, this.soulsOnBoard, airborne, this.isFueled);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		// this reflects reality that Registration number is unique by type
		if(obj instanceof Aircraft other) {
			return this.registrationID.equalsIgnoreCase(other.registrationID);
		} else {
			return false;
		}
		
	}
	
}
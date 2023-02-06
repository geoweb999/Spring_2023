
public abstract class Aircraft {

	private Location location;
	private float airSpeed;
	private int soulsOnBoard;
	private String registrationID;	// a unique identifier
	private boolean airBorne;
	private int verticalSpeed;   	// -1 = descent, +1 = climb
	
	
	public Aircraft(String registrationID, Location location, int soulsOnBoard) {
		//TODO ensure that registration ID conforms to format: /^[A-Z]-[A-Z]{4}|[A-Z]{2}-[A-Z]{3}|N[0-9]{1,5}[A-Z]{0,2}$/ 
		this.registrationID = registrationID;
		this.location = location;
		this.soulsOnBoard = soulsOnBoard;
		this.airBorne = false;
		this.airSpeed = 0;
		this.verticalSpeed = 0;
	}

	
	public Location getLocation() {
		return location;
	}
	
	
	public String getTerrainType() {
		return this.location.getTerrainType();
	}

	
	public void setLocation(Location location) {
		this.location = location;
	}

	
	public float getAirSpeed() {
		return airSpeed;
	}

	
	public void setAirSpeed(float airSpeed) {
		//TODO airSpeed for rotary probably should allow for negative airspeed
		airSpeed = (airSpeed < 0) ? 0 : airSpeed;
		this.airSpeed = airSpeed;
	}

	
	public int getSoulsOnBoard() {
		return soulsOnBoard;
	}

	
	public void setSoulsOnBoard(int soulsOnBoard) {
		soulsOnBoard = (soulsOnBoard < 0) ? 0 : soulsOnBoard;
		this.soulsOnBoard = soulsOnBoard;
	}

	
	public String getRegistrationID() {
		return registrationID;
	}

	
	public void setRegistrationID(String registrationID) {
		//TODO ensure that registration ID conforms to format: /^[A-Z]-[A-Z]{4}|[A-Z]{2}-[A-Z]{3}|N[0-9]{1,5}[A-Z]{0,2}$/ 
		this.registrationID = registrationID;
	}
	
	
	public void setAirborne(boolean airborne) {
		this.airBorne = airborne;
	}
	
	
	public boolean isAirborne() {
		return this.airBorne;
	}

	
	public int getVerticalSpeed() {
		return verticalSpeed;
	}
	
	
	public void setAltitude(int altitude) {
		this.location.setAltitudeAGL(altitude);
	}

	
	public void setVerticalSpeed(int verticalSpeed) {
		if (verticalSpeed > 0) {
			this.verticalSpeed = 1;
		} else if (verticalSpeed < 0) {
			this.verticalSpeed = -1;
		} else {
			this.verticalSpeed = 0;
		}
	}

	
	@Override
	public String toString() {
		//TODO apply better formatting for readability
		String airborne = (this.airBorne) ? "is airborne" : "is not airborne";
		String verticalAirspeed = "";
		if (this.verticalSpeed > 0) {
			verticalAirspeed = "climbing";
		} else if (this.verticalSpeed < 0) {
			verticalAirspeed = "descending";
		}
		return "Reg#: " + this.registrationID + " Location: " + this.location + "\t souls: " + 
				this.soulsOnBoard + " " + airborne + " " + verticalAirspeed;
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
	public abstract boolean requestTakeoff();
	public abstract boolean requestLanding();
	public abstract boolean executeTakeoff();
	public abstract boolean executeLanding();
}
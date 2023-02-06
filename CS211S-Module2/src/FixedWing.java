
public class FixedWing extends Aircraft {
	// TODO create a class for un-powered aircraft like gliders
	
	public static final int DEFAULT_NUMBER_ENGINES = 1;

	private int numEngines;
	private int throttlePercent;

	
	public FixedWing(String registrationID, Location location, int soulsOnBoard, int numEngines) {
		super(registrationID, location,soulsOnBoard);
		this.numEngines = numEngines;
		this.throttlePercent = 0;
	}
	
	
	public FixedWing (String registrationID, Location location, int soulsOnBoard) {
		this(registrationID, location, soulsOnBoard, DEFAULT_NUMBER_ENGINES);
	}
	
	
	public int getNumEngines() {
		return numEngines;
	}

	
	public void setNumEngines(int numEngines) {
		this.numEngines = numEngines;
	}

	
	public int getThrottlePercent() {
		return throttlePercent;
	}

	
	public void setThrottlePercent(int throttlePercent) {
		this.throttlePercent = throttlePercent;
	}
	
	
	public String getTerrainType() {
		return super.getTerrainType();
	}
	
	
	public boolean requestLanding() {
		// TODO elaborate to reflect conditions required to approve a landing clearance
		return (this.getTerrainType() == Location.LAND_TYPE);
	}
	
	
	public boolean requestTakeoff() {
		// TODO elaborate to reflect conditions required to approve a takeoff clearance
		return (this.getTerrainType() == Location.LAND_TYPE);
	}
	
	
	public boolean executeLanding() {
		// check for landing permission and execute landing procedure
		if (this.requestLanding()) {
			this.setAirSpeed(0);
			this.setThrottlePercent(0);
			this.setVerticalSpeed(0);
			this.setAltitude(0);
			this.setAirborne(false);
			return true;
		} else {
			System.out.println("Landing request denied.");
			return false;
		}
	}
	
	
	public boolean executeTakeoff() {
		// check for landing permission and execute takeoff procedure
		if (this.requestTakeoff()) {
			this.setAirSpeed(100);
			this.setThrottlePercent(100);
			this.setVerticalSpeed(1);
			this.setAirborne(true);
			return true;
		} else {
			System.out.println("Takeoff request denied.");
			return false;
		}
	}
	
	
	@Override
	public String toString() {
		
		return String.format(super.toString() + "Power: %2d engine(s), throttle(s)  at: %3d", this.numEngines, this.throttlePercent);
		
	}
	
	
	@Override
	public boolean equals(Object obj) {
		// TODO: this implementation reflects reality but could add location check here
		if(obj instanceof FixedWing) {
			return super.equals(obj);
			
		} else {
			return false;
		}
		
	}
}

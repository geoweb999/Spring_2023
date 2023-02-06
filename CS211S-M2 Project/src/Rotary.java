public class Rotary extends FixedWing {

	public static final int DEFAULT_NUMBER_ENGINES = 1;
	private boolean isHovering;
	
	public Rotary(String registrationID, Location location, int soulsOnBoard, int numEngines) {
		super(registrationID, location, soulsOnBoard, numEngines);
	}

	public Rotary(String registrationID, Location location, int soulsOnBoard) {
		super(registrationID, location, soulsOnBoard, DEFAULT_NUMBER_ENGINES);
	}
	
	public boolean isHovering() {
		return isHovering;
	}
	
	public void setIsHovering(boolean isHovering) {
		this.isHovering = isHovering;
	}
	
	
	//@Override
	public boolean executeHover() {
		if (this.isAirborne()) {
			this.setIsHovering(true);
			this.setVerticalSpeed(0);
			return true;
		} else {
			System.out.println("Hover request denied.");
			return false;
		}
	}
	
	@Override
	public boolean requestLanding() {
		// this is a neat trick as this method gets called from executeLanding below
		// but the code below is calling super.executeLanding
		// this is pretty cool
		return (this.getTerrainType() == Location.LAND_TYPE ||
				this.getTerrainType() == Location.MOUNTAIN_TYPE);
	}
	
	
	@Override
	public boolean requestTakeoff() {
		// this is a neat trick as this method gets called from executeLanding below
		// but the code below is calling super.executeLanding
		// this is pretty cool
		return (this.getTerrainType() == Location.LAND_TYPE ||
				this.getTerrainType() == Location.MOUNTAIN_TYPE);
	}

	
//	@Override
//	public boolean executeLanding() {
//		//TODO: additional takeoff checklist for helicopters
//		return super.executeLanding();
//	}
//	
//	
//	@Override
//	public boolean executeTakeoff() {
//		//TODO: additional takeoff checklist for helicopters
//		return super.executeTakeoff();
//	}
	
	
	@Override
	public boolean equals(Object obj) {
		// TODO: this implementation reflects reality but could add location check here
		if(obj instanceof Rotary) {
			return super.equals(obj);
			
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		String hovering = (this.isHovering) ? "is hovering" : "";
		return super.toString() + " " + hovering; 
	}
}

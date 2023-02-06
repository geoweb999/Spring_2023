public class Seaplane extends FixedWing {

	public static final int DEFAULT_NUMBER_ENGINES = 1;
	
	public Seaplane(String registrationID, Location location, int soulsOnBoard, int numEngines) {
		super(registrationID, location, soulsOnBoard, numEngines);
	}

	public Seaplane(String registrationID, Location location, int soulsOnBoard) {
		super(registrationID, location, soulsOnBoard, DEFAULT_NUMBER_ENGINES);
	}
	
	
	@Override
	public boolean requestLanding() {
		// TODO more functionality to represent a real request for landing 
		
		// this is a neat trick as this method gets called from executeLanding below
		// but the code below is calling super.executeLanding
		// this is pretty cool
		return (this.getTerrainType() == Location.SEA_TYPE);
	}
	
	
	@Override
	public boolean requestTakeoff() {
		// TODO more functionality to represent a real request for takeoff 
		
		// this is a neat trick as this method gets called from executeLanding below
		// but the code below is calling super.executeLanding
		// this is pretty cool
		return (this.getTerrainType() == Location.SEA_TYPE);
	}

//  rely on inheritance to call correct method
//  TODO: add these methods back iff there are additional checklist items
//	@Override
//	public boolean executeLanding() {
//		// TODO: additional landing checklist items for seaplanes
//		return super.executeLanding();
//	}
//	
//	@Override
//	public boolean executeTakeoff() {
//		//TODO: additional takeoff checklist for seaplanes
//		return super.executeTakeoff();
//	}
	
	
	@Override
	public boolean equals(Object obj) {
		// TODO: this implementation reflects reality but could add location check here
		if(obj instanceof Seaplane) {
			return super.equals(obj);
			
		} else {
			return false;
		}
		
	}
}

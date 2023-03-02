public enum AircraftType {
	FIXED_WING("Fixed Wing"),  SEA_PLANE("Seaplane"), ROTARY("Rotary");
	
	private String abbreviation;
	
	private AircraftType(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	
	public String getAbbreviation() {
		return this.abbreviation;
	}
	
	@Override
	public String toString() {
		return "Type = " + this.getAbbreviation() + " ";
	}
}
// M3 HOMEWORK ENUM

public enum TerrainType {
	LAND("LND"),  SEA("SEA"), MOUNTAIN("MTN"), GRASS("GRS");
	
	private String abbreviation;
	
	private TerrainType(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	
	public String getAbbreviation() {
		return this.abbreviation;
	}
	
	@Override
	public String toString() {
		return "Terrain = " + this.getAbbreviation() + " ";
	}
}
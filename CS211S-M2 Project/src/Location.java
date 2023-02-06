
public class Location {
	
	public static final String LAND_TYPE = "LAND";
	public static final String SEA_TYPE = "SEA";
	public static final String MOUNTAIN_TYPE = "MTN";

	private double latitude;
	private double longitude;
	private int altitudeAGL;	// note AGL is standard for Above Ground Level in feet
	private String terrainType;
	
	public Location() {
		this.latitude = 0.0;
		this.longitude = 0.0;
		this.altitudeAGL = 0;			
		this.terrainType = LAND_TYPE;
	}
	public Location(double latitude, double longitude, int altitudeAGL, String terrainType) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitudeAGL = 0;
		this.terrainType = terrainType;
	}
	
	
	public double getLatitude() {
		return latitude;
	}
	
	
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	
	
	public double getLongitude() {
		return longitude;
	}
	
	
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
	
	public float getAltitudeAGL() {
		return altitudeAGL;
	}
	
	
	public void setAltitudeAGL(int altitudeAGL) {
		altitudeAGL = (altitudeAGL < 0) ? 0 : altitudeAGL;
		this.altitudeAGL = altitudeAGL;
	}
	
	
	public String getTerrainType() {
		return terrainType;
	}
	
	public void setTerrainType(String terrainType) {
		if (terrainType == LAND_TYPE || terrainType == SEA_TYPE || terrainType == MOUNTAIN_TYPE) {
			this.terrainType = terrainType;
		} else {
			System.out.println("Invalid terrain type.");
		}
	}
	
	
	public String toString() {
		//TODO apply better formatting for readability
		return String.format("%.1f/%.1f %d' AGL Terrain: %5s",this.latitude, this.longitude, this.altitudeAGL, this.terrainType);
		//return this.latitude + "/" + this.longitude + " " + this.altitudeAGL + "' AGL" + "\t terrain " + this.terrainType;
	}

}

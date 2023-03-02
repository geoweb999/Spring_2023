// M3 HOMEWORK ENUM
public class Location {


	private double latitude;
	private double longitude;
	private int altitudeAGL;	// note AGL is standard for Above Ground Level in feet
	private TerrainType terrainType;
	
	public Location() {
		this.latitude = 0.0;
		this.longitude = 0.0;
		this.altitudeAGL = 0;			
		this.terrainType = TerrainType.LAND;
	}
	public Location(double latitude, double longitude, int altitudeAGL, TerrainType terrainType) {
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
	
	
	public int getAltitudeAGL() {
		return altitudeAGL;
	}
	
	
	public void setAltitudeAGL(int altitudeAGL) {
		altitudeAGL = (altitudeAGL < 0) ? 0 : altitudeAGL;
		this.altitudeAGL = altitudeAGL;
	}
	
	
	public TerrainType getTerrainType() {
		return terrainType;
	}
	
	public void setTerrainType(TerrainType terrainType) {
		this.terrainType = terrainType;
	}
	
	
	public String toString() {
		//TODO apply better formatting for readability
		return String.format("%.1f/%.1f %d' AGL Terrain: %5s",this.latitude, this.longitude, this.altitudeAGL, this.terrainType);
		//return this.latitude + "/" + this.longitude + " " + this.altitudeAGL + "' AGL" + "\t terrain " + this.terrainType;
	}

}

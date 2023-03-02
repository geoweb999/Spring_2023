import java.util.ArrayList;
import java.util.Collections;

public class Fleet {
	private ArrayList<Aircraft> aircraftList;
		
	public Fleet() {
		aircraftList = new ArrayList<>();
	}
	
	public void addAircraft(Aircraft a) {
		aircraftList.add(a);
	}
		
	public ArrayList<Aircraft> getAircraftList() {
		Collections.sort(aircraftList);
			return aircraftList;
	}
	
}

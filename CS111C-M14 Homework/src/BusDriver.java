import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BusDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		BusHashTable hash = new BusHashTable(11);
//		
//		Bus bus1 = new Bus(22,"Irving");
//		Bus bus2 = new Bus(89, "California");
//		Bus bus3 = new Bus(135, "Great");
//		Bus bus4 = new Bus(15, "Serra");
//		Bus bus5 = new Bus(940, "Main");
//		Bus bus6 = new Bus(700, "Parkway");
//		Bus bus7 = new Bus(85, "Capistrano");
//		Bus bus8 = new Bus(778, "Kirkham");
//		Bus bus9 = new Bus(32, "Brannan");
//		Bus bus10 = new Bus(350, "Powell");
//
//		
//		hash.addBus(bus1);
//		hash.addBus(bus2);
//		hash.addBus(bus3);
//		hash.addBus(bus4);
//		hash.addBus(bus5);
//		hash.addBus(bus6);
//		hash.addBus(bus7);
//		hash.addBus(bus8);
//		hash.addBus(bus9);
//
//		hash.removeBus(bus1.getNum());
//		hash.removeBus(bus9.getNum());
//		
//		hash.printTable();
//
//		hash.getBus(bus10.getNum());
		Map<String, Integer> numberMap = new HashMap<String, Integer>();
		numberMap.put("A", 1);
		numberMap.put("B", 2);
		numberMap.put("C", 3);
		System.out.println(numberMap.keySet());
	}
}

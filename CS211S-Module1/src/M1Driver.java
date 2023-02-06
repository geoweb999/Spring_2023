public class M1Driver {
	
	public static final String LAND_TYPE = "LAND";
	public static final String SEA_TYPE = "SEA";
	public static final String MOUNTAIN_TYPE = "MNT";
	public static final int TWIN_ENGINE = 2;

	
	public static void main(String[] args) {
		// Array of test locations with different terrain types
		Location[] locations = new Location[5];
		locations[0] = new Location(37.0, 37.0, 0, LAND_TYPE);
		locations[1] = new Location(21.0, 98.0, 0, LAND_TYPE);
		locations[2] = new Location(71.0, 67.0, 0, LAND_TYPE);
		locations[3] = new Location(60.0, 15.0, 0, SEA_TYPE);
		locations[4] = new Location(80.0, 17.0, 0, MOUNTAIN_TYPE);
		
		// Array of differing classes of Aircraft
		Aircraft[] aircraft = new Aircraft[5];
		aircraft[0] = new FixedWing("N1257X", locations[0],1);
		aircraft[1] = new FixedWing("N7526J", locations[1],2);
		aircraft[2] = new FixedWing("N999OT", locations[2],89, TWIN_ENGINE);
		aircraft[3] = new Seaplane("N2762S", locations[3],12);
		aircraft[4] = new Rotary("N11WTF", locations[4],6);
		
		// test toString
		for (int i= 0; i < 5; i++) {
			System.out.println(aircraft[i]);
		}
		System.out.println();
		
		// test request and execute Takeoff method
		// each aircraft is at a location that allows takeoff
		for (int i= 0; i < 5; i++) {
			aircraft[i].executeTakeoff();
			aircraft[i].setAltitude(1000);
			System.out.println(aircraft[i]);
		}
		System.out.println("*************");
		
		// test all aircraft at all locations for landing/takeoff
		// fixedwing will fail at locations[3-4]
		// seaplace will fail at locations[0-2,4]
		// rotary will fail at location[3]
		// added test for hover for type rotary
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				aircraft[i].setLocation(locations[j]);
				aircraft[i].setAltitude(1000);
				if (aircraft[i].executeLanding()) {
					System.out.println(aircraft[i]);
					aircraft[i].executeTakeoff();
					aircraft[i].setAltitude(1000);
				} else {
					System.out.println(aircraft[i]);
				}
				if(aircraft[i] instanceof Rotary rotary) {
					if (rotary.executeHover()) {
						System.out.println(aircraft[i]);
					} else {
						System.out.println(aircraft[i]);
					}
				}
			}
			System.out.println("*************");
			System.out.println("*************");
		}
		System.out.println();
		
		// test equals method
		System.out.println("Aircraft 1 = Aircraft 1, should be true: " + aircraft[1].equals(aircraft[1]));
		System.out.println();

		Aircraft fixedwing1 = new FixedWing("N12345", locations[0],1);
		Aircraft fixedwing2 = new FixedWing("N12345", locations[0],1);
		Aircraft rotary3 = new Rotary("N12345", locations[0],1);
		
		// test symmetry of equals
		System.out.println("Fixedwing 1 = FixedWing 2, should be true: " + fixedwing1.equals(fixedwing2));
		System.out.println("Fixedwing 2 = FixedWing 1, should be true: " + fixedwing2.equals(fixedwing1));
		
		//test failure
		System.out.println("Fixedwing 1 != Rotary 3, should be false: " + rotary3.equals(fixedwing1));
		System.out.println();
		
		Aircraft seaplane1 = new Seaplane("N12345", locations[0],1);
		Aircraft seaplane2 = new Seaplane("N12345", locations[0],1);
		Aircraft fixedwing3 = new FixedWing("N12345", locations[0],1);
		
		// test symmetry of equals
		System.out.println("seaplane 1 = seaplane 2, should be true: " + seaplane1.equals(seaplane2));
		System.out.println("seaplane 2 = seaplane 1, should be true: " + seaplane2.equals(seaplane1));
		
		//test failure
		System.out.println("seaplane 1 != FixedWing 3, should be false: " + fixedwing3.equals(seaplane1));
		System.out.println();
		
		Aircraft rotary1 = new Rotary("N12345", locations[0],1);
		Aircraft rotary2 = new Rotary("N12345", locations[0],1);
		Aircraft seaplane3 = new Seaplane("N12345", locations[0],1);
		
		// test symmetry of equals
		System.out.println("rotary 1 = rotary 2, should be true: " + rotary1.equals(rotary2));
		System.out.println("rotary 2 = rotary 1, should be true: " + rotary2.equals(rotary1));
		
		//test failure
		System.out.println("rotary 1 != seaplane 3, should be false: " + seaplane3.equals(rotary1));
		System.out.println();
	}

}

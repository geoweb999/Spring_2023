import java.util.ArrayList;
import java.util.Collections;

public class M3Driver {
	public static void main(String[] args) {

		ArrayList<Computer> computers = new ArrayList<Computer>();
		computers.add(new Computer("PC 2", 128));
		computers.add(new Computer("PC 1", 128));
		computers.add(new Computer("PC 9", 128));
		computers.add(new Computer("PC 4", 128));
		computers.add(new Computer("PC 2", 64));
		computers.add(new Computer("PC 2", 640));
		
		System.out.println("**** PRIOR TO SORT ****");
		for (Computer pc : computers) {
			System.out.println(pc);
		}
		
		System.out.println("**** SORTED ****");
		Collections.sort(computers);
		for (Computer pc : computers) {
			System.out.println(pc);
		}
		
	}
}

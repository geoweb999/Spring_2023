
public class Computer implements Comparable<Computer> {
	
	private String name;		// computer name
	private int memoryInKB;		// memory in kilobytes

	public Computer(String name, int memory) {
		this.name = name;
		this.memoryInKB = memory;
	}
	
	@Override
	public String toString() {
		return "Name: " + name + " " + memoryInKB + "Kb";
	}
	
	public int compareTo(Computer obj)  {
		// Order alphanumerically by name,then Kb
		if (this.name.compareToIgnoreCase(obj.name) == 0) {
			return this.memoryInKB - obj.memoryInKB;
		} else {
			return this.name.compareToIgnoreCase(obj.name);
		}
	}
}

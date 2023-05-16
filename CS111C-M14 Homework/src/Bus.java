
public class Bus {
	private int num;
	private String line;
	
	public Bus(int num, String line) {
		this.num = num;
		this.line = line;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}
	
	@Override
	public String toString() {
		return num + " " + line ;
	}
}

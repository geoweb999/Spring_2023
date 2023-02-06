
public class Perishable extends Product {
	
	public static final int DEFAULT_SHELF_LIFE = 0;
	
	private int shelfLife;
	  
	public Perishable(int id, String name, double price, int stock, String description, int shelfLife) {
		super(id, name, price, stock, description);
	    this.shelfLife = shelfLife;
	}
	
	public Perishable(int id, String name, double price, int stock, String description) {
		this(id, name, price, stock, description, DEFAULT_SHELF_LIFE);
	}
	  
	public int getShelfLife() {
	    return shelfLife;
	}
	
	public void setShelfLife(int time) {
		this.shelfLife = time;
	}
	  
	public String toString() {
		return super.toString() + "\nShelf Life: " + shelfLife + " days";
	}
}

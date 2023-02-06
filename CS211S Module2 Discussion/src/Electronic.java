
public class Electronic extends Product {
	
	public static final String DEFAULT_BRAND = "DEFAULT";


	private String brand;
	  
	public Electronic(int id, String name, double price, int stock, String description, String brand) {
	    super(id, name, price, stock, description);
	    this.brand = brand;
	}
	
	public Electronic(int id, String name, double price, int stock, String description) {
		this(id, name, price, stock, description, DEFAULT_BRAND);
	}
	  
	public String getBrand() {
	    return brand;
	}
	  
	public String toString() {
	    return super.toString() + "\nBrand: " + brand;
	}
}

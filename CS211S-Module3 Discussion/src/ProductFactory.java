
public class ProductFactory {
	public static Product newProduct(String type, int id, String name, 
						double price, int stock, String description) {
		if (type.equalsIgnoreCase("PRODUCT")) {
			return new Product(id, name, price, stock, description);
		} else if (type.equalsIgnoreCase("PERISHABLE")) {
			return new Perishable(id, name, price, stock, description);
		} else if (type.equalsIgnoreCase("ELECTRONIC")) {
			return new Electronic(id, name, price, stock, description);
		} else {
			throw new IllegalArgumentException(type + " is an invalid Product type.");
		}
	}
	public static Product newProduct(String type, int id, String name, 
			double price, int stock, String description, int shelfLife) {
		
			if (type.equalsIgnoreCase("PRODUCT")) {
				return new Product(id, name, price, stock, description);
			} else if (type.equalsIgnoreCase("PERISHABLE")) {
				return new Perishable(id, name, price, stock, description, shelfLife);
			} else if (type.equalsIgnoreCase("ELECTRONIC")) {
				return new Electronic(id, name, price, stock, description);
			} else {
				throw new IllegalArgumentException(type + " is an invalid Product type.");
			}
	}
	
	public static Product newProduct(String type, int id, String name, 
			double price, int stock, String description, String brand) {
		
			if (type.equalsIgnoreCase("PRODUCT")) {
				return new Product(id, name, price, stock, description);
			} else if (type.equalsIgnoreCase("PERISHABLE")) {
				return new Perishable(id, name, price, stock, description);
			} else if (type.equalsIgnoreCase("ELECTRONIC")) {
				return new Electronic(id, name, price, stock, description, brand);
			} else {
				throw new IllegalArgumentException(type + " is an invalid Product type.");
			}
	}
	
}

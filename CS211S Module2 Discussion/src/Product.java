
class Product implements Comparable<Product> {
	
	private static int numberOfProducts = 0;
	
	private int productID;
	private String productName;
	private double productPrice;
	private int numberInStock;
	private String productDescription;
  
 
	public Product(int id, String name, double price, int stock, String description) {
		this.productID = id;
		this.productName = name;
		this.productPrice = price;
		this.numberInStock = stock;
		this.productDescription = description;
		Product.numberOfProducts++;
  }
	
	public static int getNumberOfProducts() {
		return Product.numberOfProducts;
	}
	
	public static void setNumberOfProducts(int number) {
		if (number < 0) {
			System.out.println("Number of products cannot be less than zero.");
		} else {
			Product.numberOfProducts = number;
		}
	}
  
	public int getId() {
		return productID;
	}
  
	public String getName() {
		return productName;
	}
  
	public double getPrice() {
		return productPrice;
	}
  
	public int getStock() {
		return numberInStock;
	}
  
	public void setStock(int stock) {
		this.numberInStock = stock;
	}
  
	public String getDescription() {
		return productDescription;
	}
  
	public String toString() {
		return "ID: " + productID + "\nName: " + productName + "\nPrice: $" + productPrice + "\nStock: " + numberInStock + "\nDescription: " + productDescription;
	}
	
	public int compareTo(Product prod) {
		return this.productName.toUpperCase().compareTo(prod.productName.toUpperCase()); 
	}
}

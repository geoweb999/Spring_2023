
class Product implements Comparable<Product> {
	
	private static int numberOfProducts = 0;
	
	private int productID;
	private String productName;
	private double productPrice;
	private int numberInStock;
	private String productDescription;
	private SaleType saleType;
  
 
	public Product(int id, String name, double price, int stock, String description) {
		this.productID = id;
		this.productName = name;
		this.productPrice = price;
		this.numberInStock = stock;
		this.productDescription = description;
		this.saleType = SaleType.NORMAL;
		Product.numberOfProducts++;
   }
	public Product(int id, String name, double price, int stock, String description, SaleType saleType) {
		this(id, name, price, stock, description);
		this.saleType = saleType;
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
	
	public SaleType getSaleType() {
		return this.saleType;
	}
	
	public void setSaleType(SaleType saleType) {
		this.saleType = saleType;
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
		return "ID: " + productID + "\nName: " + productName + "\nPrice: $" + productPrice + 
				"\nStock: " + numberInStock + "\nDescription: " + productDescription + " " + saleType;
	}
	
	public int compareTo(Product prod) {
		if (this.productName.toUpperCase().compareTo(prod.productName.toUpperCase()) < 0) {
			return -1;
		} else if (this.productName.toUpperCase().compareTo(prod.productName.toUpperCase()) > 0) {
			return 1;
		} else {
			return 0;
		}
	}
}

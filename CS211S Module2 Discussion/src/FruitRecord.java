
public record FruitRecord(int productID, String productName, 
		double ProductPrice, int NumberInStock, String productDescription,
		int shelfLife) {

	
	public FruitRecord {
		int num = Product.getNumberOfProducts() + 1;
		Product.setNumberOfProducts(num);
	}
  
}

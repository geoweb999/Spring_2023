import java.util.Arrays;

public class Driver {
	public static void main(String[] args) {	
		Product[] products = new Product[3];
		
		System.out.println("Number of Products in Inventory: " + Product.getNumberOfProducts());
		
		products[0] = new Perishable(1, "Milk", 2.99, 10, "1 gallon of whole milk", 7);
		products[1] = new Electronic(2, "Laptop", 999.99, 5, "Dell Inspiron 15", "Dell");
		products[2] = new Product(3,"Box", 0.99, 100, "A box");
		
		
		System.out.println("Perishable Product: ");
		System.out.println(products[0]);
		    
		System.out.println("\nElectronic Product: ");
		System.out.println(products[1]);
		
		System.out.println("\nProduct: ");
		System.out.println(products[2]);
		
		FruitRecord fruit = new FruitRecord(4, "Grape", 1.99, 5, "Bunch of Grapes", 1);
		System.out.println("\n" + fruit);
		
		System.out.println("\nNumber of Products in Inventory: " + Product.getNumberOfProducts());
		
		Product.setNumberOfProducts(-1);

		Arrays.sort(products);
		for (int i = 0; i < 3; i++) {
			System.out.println(products[i]);
		}
		
		Product[] productF = new Product[3];
		
		productF[0] = ProductFactory.newProduct("PERISHABLE",1, "Milk", 2.99, 10, "1 gallon of whole milk", 7);
		productF[1] = ProductFactory.newProduct("ELECTRONIC",2, "Laptop", 999.99, 5, "Dell Inspiron 15", "Dell");
		productF[2] = ProductFactory.newProduct("PRODUCT", 3,"Box", 0.99, 100, "A box");
		for (int i = 0; i < 3; i++) {
			System.out.println(productF[i]);
		}
		
	}
}

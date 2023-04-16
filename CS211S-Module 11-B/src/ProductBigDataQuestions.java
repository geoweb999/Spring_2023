import java.util.*;
import java.util.function.Function;
import java.util.stream.*;
import java.io.*;
import java.time.*;

public class ProductBigDataQuestions {

	public static List<Product> productList;
	public static Map<String, List<Product>> chemicalProductMap;
	
    public static void main(String[] args) {
    	productList = createList();
 
        int numberOfProducts = productList.size();
        System.out.println("Number of products\nExpected: 73547\n  Actual: " + numberOfProducts);
        
        // QUESTION 1: How many total chemicals appear across all products? 
        // Example: Product1 contains Chemical1, Chemical2 and Product2 contains Chemical2 and Chemical3
        //          count would be 4 total chemicals
        int numberChemicalsInAllProducts = productList.stream()
        		.mapToInt(Product::getNumberOfChemicals)
        		.sum();
        		
        System.out.println("\nQ1. Number of chemicals in all products.\nExpected: 81789\n  Actual: " + numberChemicalsInAllProducts);
     
        // QUESTION 2: How many different companies are in the dataset?
        int numberCompanies = (int) productList.stream()
        		.map(Product::getCompany)
        		.distinct()
        		.count();
        
        System.out.println("\nQ2. Number of companies.\nExpected: 549\n  Actual: " + numberCompanies);
        
        // QUESTION 3: How many products have 4 or more chemicals?
        long numberProductsFourOrMore = productList.stream()
        		.filter( (product) -> product.getNumberOfChemicals() >= 4)
        		.count();
        		
        System.out.println("\nQ3. Number of products with 4 or more chemicals.\nExpected: 193\n  Actual: " + numberProductsFourOrMore);

        //  QUESTION 4: Create a Map with key = company name and value = list of products for that company.
        // Hint: use Collectors.groupingBy!
        Map<String, List<Product>> companyProductMap = productList.stream()
        		.collect(Collectors.groupingBy(Product::getCompany));
        		
        // checks that the map is correct; consider adding additional checks!
        System.out.println("\nQ4: Spot checking the map (key=company name, value = list of products for that company).");
        System.out.println("\nNumber of companies (keys).\nExpected: 549\n  Actual: " + companyProductMap.size());
        System.out.println("\nNumber of products for Aloette Cosmetics Inc.\nExpected: 77\n  Actual: " + 
        		( (companyProductMap.get("Aloette Cosmetics Inc.")!=null ? companyProductMap.get("Aloette Cosmetics Inc.").size() : "0 <it is not on the map>")));
        System.out.println("\nNumber of products for Yves Rocher Inc.\nExpected: 416\n  Actual: " + 
        		( (companyProductMap.get("Yves Rocher Inc.")!=null ? companyProductMap.get("Yves Rocher Inc.").size() : "0 <it is not on the map>")));
   	    System.out.println("\nNumber of products for label.m USA INC\nExpected: 4\n  Actual: " + 
        		( (companyProductMap.get("label.m USA INC")!=null ? companyProductMap.get("label.m USA INC").size() : "0 <it is not on the map>")));
       		                 

        // QUESTION 5: Use the map you created above. Which company has the most products?
        // Hint: Use .max(Comparator) and define the Comparator to compare companies based on size of their lists.
   	    
        String companyMostProducts = companyProductMap.keySet().stream()
        		.max(Comparator.comparingInt(company -> companyProductMap.get(company).size()))
        		.orElse("Nothing found");

        System.out.println("\nQ5. Company with most products.\nExpected: LOreal USA\n  Actual: " + companyMostProducts);
        System.out.println("\nHow many products?\nExpected: 5662\n  Actual: " + 
        		( (companyProductMap.get(companyMostProducts)!=null ? companyProductMap.get(companyMostProducts).size() : "0 <it is not on the map>")));
        
       // Use this map for the next questions. 
       // This is a map with key = chemical name and value = list of products that contain that chemical.
       // This code creates a map with all chemicals added as keys with empty lists as the values.
       chemicalProductMap = new HashMap<String, List<Product>>();
       
//       Map<Product, List<String>> productNames = productList.stream()
//				.collect(Collectors.toMap(product -> product, product -> product.getChemicals()));
//       
//   
//       Set<String> chemList = new HashSet<>();
//       productList.stream().forEach(
//             product -> { 
//                 product.getChemicals().stream().
//                     forEach(chemicalName -> 
//                         chemList.add(chemicalName));
//             });
//       System.out.println(LocalDateTime.now());
//       productList.stream().forEach(
//               product -> { 
//                   product.getChemicals().stream().
//                       forEach(chemicalName -> 
//                           chemicalProductMap.putIfAbsent(chemicalName,  
//                        		   productList.stream()
//                        		   .filter(prod -> prod.getChemicals().contains(chemicalName))
//                        		   .toList()
//                        		   
//                        		   ));
//               }      
//               );

       // QUESTION 6: Fill the lists (the value) of the map above.
       // Hint: Use a nested stream (one stream of productList and then a separate stream for each list of each product).
//       productList.stream().forEach(
//               product -> { 
//                   product.getChemicals().stream().
//                       forEach(chemicalName -> 
//                           chemicalProductMap.putIfAbsent(chemicalName,  new ArrayList<Product>()
//               ));
//               }      
//               );
//       
//       chemicalProductMap.keySet().stream().forEach(
//               chemical -> chemicalProductMap.replace(chemical, 
//                   productList.stream()
//                   		.filter(product -> product.getChemicals().contains(chemical)) 
//                        .toList()    
//               )
//               );
       System.out.println(LocalDateTime.now());
       productList.stream().forEach(
               product -> { 
                   product.getChemicals().parallelStream().
                       forEach(chemicalName -> 
                           chemicalProductMap.putIfAbsent(chemicalName, productList.parallelStream().filter(product2 -> product2.getChemicals().contains(chemicalName)).toList()));
                       
               }
               );

       System.out.println(LocalDateTime.now());

       // checks that the map is correct; consider adding additional checks!
       System.out.println("\nQ6: Spot checking the map (key=chemical name, value = list of products that contain that chemical).");
       System.out.println("\nNumber of products that contain Formaldehyde (gas) \nExpected: 121\n  Actual: " + (chemicalProductMap.get("Formaldehyde (gas)")).size());
       System.out.println("\nNumber of products that contain Lauramide DEA \nExpected: 20\n  Actual: " + (chemicalProductMap.get("Lauramide DEA")).size());
       System.out.println("\nNumber of products that contain Arsenic (inorganic oxides) \nExpected: 1\n  Actual: " + (chemicalProductMap.get("Arsenic (inorganic oxides)")).size());

       // QUESTION 7: Which chemical appears in the most products?
       // Hint: use max(Comparator) again. Define your comparator to compare chemical names based on the size of the list of products.
       String mostOccurringChemical = chemicalProductMap.keySet().stream()
    		   .max(Comparator.comparing(chemical -> chemicalProductMap.get(chemical).size()))
    		   .orElse("No chemical found");
    		 
    		   
       System.out.println("\nQ7. Most common chemical \nExpected: Titanium dioxide\n  Actual: " + mostOccurringChemical);
       System.out.println("\nHow many products does it appear in?\nExpected: 67896\n  Actual: " + (chemicalProductMap.get("Titanium dioxide")).size());

    }

    public static int q1() {
        // QUESTION 1: How many total chemicals appear across all products? 
        // Example: Product1 contains Chemical1, Chemical2 and 
    	//          Product2 contains Chemical2 and Chemical3.
        //          The count would be 4 total chemicals.
        return 0; // placeholder so the code will compile; replace with your own code
    }
    public static int q2() {
        // QUESTION 2: How many different companies are in the dataset?
    	 return 0; // placeholder so the code will compile; replace with your own code
    }
    public static long q3() {
        // QUESTION 3: How many products have 4 or more chemicals?
    	 return 0L; // placeholder so the code will compile; replace with your own code
    }
    public static Map<String, List<Product>> q4() {
        // QUESTION 4: Create a Map with key = company name and 
    	// value = list of products for that company.
        // Hint: use Collectors.groupingBy!
    	 return new HashMap<>(); // placeholder so the code will compile; replace with your own code
    }
    public static String q5(Map<String, List<Product>> companyProductMap) {
    	// QUESTION 5: Use the map you created above. Which company has the most products?
        // Hint: Use .max(Comparator) and define the Comparator to compare companies 
    	// based on size of their lists.
    	 return ""; // placeholder so the code will compile; replace with your own code
    }
    public static void q6( Map<String, List<Product>> chemicalProductMap) {
    	// QUESTION 6: Fill the lists (the value) of the map above.
        // Hint: Use a nested stream (one stream of productList 
    	// and then a separate stream for each list of each product).
      
    }
    public static String q7() {
        // QUESTION 7: Which chemical appears in the most products?
        // Hint: use max(Comparator) again. Define your comparator 
    	// to compare chemical names based on the size of the list of products.
    	 return ""; // placeholder so the code will compile; replace with your own code
    }
    
    public static List<Product> createList() {
        String line = "";
        String fileName = "ChemicalData.csv";
        Map<String, Product> productMapByHash = new HashMap<String, Product>();
        try (Scanner fileScan = new Scanner(
                new FileReader(new File(fileName)))) {

        	line = fileScan.nextLine(); // column headers        
            while(fileScan.hasNext() ) {
                line = fileScan.nextLine();
                Scanner lineScan = new Scanner(line);
                lineScan.useDelimiter(",");

                String name = lineScan.next();
                
                String colorScentFlavor = lineScan.next();
                String company  = lineScan.next();
                String brand = lineScan.next();
                String categoryString = lineScan.next();

                String chemicalName = lineScan.next();

                Category category = null;
                Category[] categories = Category.values();
                for(Category categoryOption : categories) {
                    if(categoryString.trim().equalsIgnoreCase(categoryOption.getDescription())) {
                        category = categoryOption;
                    }
                }
                Product newProduct = new Product(name, company, brand, colorScentFlavor, category);
                String key = newProduct.hashCode() + newProduct.toString();
                if(productMapByHash.containsKey(key)) {
                	Product existingProduct = productMapByHash.get(key);
                	if(!newProduct.equals(existingProduct)) {
                		System.err.println("newProduct=" + newProduct);
                		System.err.println("existingProduct=" + existingProduct);
                	}
                    existingProduct.addChemical(chemicalName);
                } else {
                   	newProduct.addChemical(chemicalName);
                   	productMapByHash.put(key, newProduct);
                }            
            }
        } catch (IOException ex){
            System.out.println(line);
            ex.printStackTrace();
        }
        List<Product> list = new ArrayList<Product>(productMapByHash.values());
        return list;
    }

}

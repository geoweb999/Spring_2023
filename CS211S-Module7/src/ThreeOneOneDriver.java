import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class ThreeOneOneDriver {
	
	public static TreeMap<String, Integer> ticketsByCategory(List<ThreeOneOne> recordsList, String hood) {
		// Builds a map of sum of tickets by ticket category for a passed neighborhood
		TreeMap<String, Integer> categoryMap = new TreeMap<>();
		int count = 0;
		for (ThreeOneOne record : recordsList) {
			if (record.getNeighborhood().equals(hood)) {
				if (categoryMap.containsKey(record.getCategory())) {
					count = categoryMap.get(record.getCategory()) + 1;
					categoryMap.put(record.getCategory(), count);
				} else {
					categoryMap.put(record.getCategory(), 1);
				}
			}
		}
		
		return categoryMap;
	}
	
	public static TreeMap<String, TreeMap<Integer, Integer>> analyzeDaysOfWeek(List<ThreeOneOne> recordsList) {
		// builds a Map of Neighborhoods mapped to a map of Days of Weeks and number of tickets
		// Uses the numeric (integer) version of Day of Week (dow) for sorting purposes
		TreeMap<String, TreeMap<Integer, Integer>> hoodByDayOfWeek = new TreeMap<>();
		int count = 0;
		for (ThreeOneOne record : recordsList) {
			Integer dow = record.getDOW().getValue();
			if (hoodByDayOfWeek.containsKey(record.getNeighborhood())) {
				TreeMap<Integer, Integer> days = hoodByDayOfWeek.get(record.getNeighborhood());
				if (days.containsKey(dow)) {
					count = days.get(dow) + 1;
					days.put(dow, count);
				} else {
					days.put(dow,1);
				}
			} else {
				TreeMap<Integer, Integer> days = new TreeMap<>();
				days.put(dow, 1);
				hoodByDayOfWeek.put(record.getNeighborhood(), days);
			}
		}
		return hoodByDayOfWeek;
	}
	

	public static TreeMap<String, Integer> sumTicketsByHood(List<ThreeOneOne> results) {
		// takes a list of 311 tickets and calculates a map of neighborhood and number of tickets
		TreeMap<String, Integer> hoodMap = new TreeMap<>();
		int count = 0;
		for (ThreeOneOne record : results) {
			if (hoodMap.containsKey(record.getNeighborhood())) {
				count = hoodMap.get(record.getNeighborhood()) + 1;
				hoodMap.put(record.getNeighborhood(), count);
			
			} else {
				hoodMap.put(record.getNeighborhood(), 1);
			}
		}
		// get neighborhood with most tickets and output
		int maxTickets = 0;
		String maxHood = "";
		for (String hood : hoodMap.keySet()) {
			if (hoodMap.get(hood) > maxTickets) {
				maxTickets = hoodMap.get(hood);
				maxHood = hood;
			}
		}
		System.out.println("The neighboord with the most 311 tickets is " + maxHood + " with " + maxTickets + " tickets.");
		return hoodMap;
	}
	
	public static TreeMap<String, List<ThreeOneOne>> sumTicketsByStreet (List<ThreeOneOne> results, String hood) {
		// performs a detailed neighborhood analysis from a list of 311 tickets
		// outputs tickets for a passed neighborhood by street name with total # tickets per street
		TreeMap<String, List<ThreeOneOne>> streetMap = new TreeMap<>();
		for (ThreeOneOne record : results) {
			if (record.getNeighborhood().equals(hood)) {
				if (streetMap.containsKey(record.getStreet())) {
					List<ThreeOneOne> tickets = streetMap.get(record.getStreet());
					tickets.add(record);
				} else {
					List<ThreeOneOne> tickets = new ArrayList<ThreeOneOne>();
					tickets.add(record);
					streetMap.put(record.getStreet(), tickets);
				}
			}
		}
		return streetMap;
	}
	
	public static String promptForHood(String prompt, Set<String> hoodsList) {
		// prompt user to enter a neighborhood name and validate against passed in neighborhood list
		// returns valid neighborhood name, or quit
		// "?" returns a list of valid neighborhoods
		boolean goodInput = false;
		String choice = "";
		Scanner scnr = new Scanner(System.in);
		while (!goodInput) {
			System.out.println(prompt);
			System.out.print("Enter a neighborhood, quit to quit, ? for list of neighbohoods: ");
			choice = scnr.nextLine();
			if (hoodsList.contains(choice) || choice.equals("quit")) {
				return choice;
			} else if (choice.equals("?")) {
				System.out.println("List of neighborhoods:");
				for (String hood : hoodsList) {
					System.out.println(hood);
				}
			} else {
				System.out.println("Invalid neighborhood, try again.");
			}
		}
		scnr.close();
		return choice;
	}
		
	public static List<ThreeOneOne> parseFile(String csvFile) {
		// parse a passed CSV file of 311 tickets, return valid tickets in a list
		LocalDateTime start = LocalDateTime.now();
        BufferedReader reader = null;
        String line = "";
        String csvSplitByQuote = "\"";
        String csvSplitByComma = ",";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a", Locale.ENGLISH);
        List<ThreeOneOne> recordsList = new ArrayList<ThreeOneOne>();
        int errors = 0;
        int count = 0;
        try {
            // Read CSV file
        	int stop=0;
            reader = new BufferedReader(new FileReader(csvFile));
            System.out.println("Processing input file...");
            while ((line = reader.readLine()) != null && stop < 10000000) {
            	stop++;
                // Parse CSV line, need to work around quotes that contain commas
            	// Split first by quote then parse again by comma
                String[] quoteValues = line.split(csvSplitByQuote);
                // pull out columns we want
                if (quoteValues.length > 4) {
                	String[] values = quoteValues[0].split(csvSplitByComma);
                	if (values.length >= 7) {
	                	try {
	                		Integer caseID = Integer.parseInt(values[0]);
	                		try {
		                		LocalDate dateTime = LocalDate.parse(values[1], formatter);
				                String responsible = values[6];
				                String category = values[7];
				                
				                // parse another chunk of data that was split by quote
				                values = quoteValues[2].split(csvSplitByComma);         
				                if (values.length >= 3) {
					                String street = values[1];
					                String neighborhood = values[3];
					                ThreeOneOne record = new ThreeOneOne(caseID, dateTime, responsible, category, street, neighborhood);
					                recordsList.add(record);
					                count++;
				                } else {
				                	errors++;
				                }
	                		} catch (DateTimeParseException ex) {
	                			errors++;
	                		}
	                	} catch (NumberFormatException ex) {
	                		errors++;
						}
	                } else {
	                	errors++;
	                }
                } else {
                	errors++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        LocalDateTime end = LocalDateTime.now();
        System.out.println("Processed " + count + " records, rejected " + errors + " records with errors in "+ Duration.between(start, end).toMillis() + " msecs.");
        return recordsList;
		
	}
	
    public static void main(String[] args) {
    
    	System.out.println("************************************");
    	System.out.println("*                                  *");
    	System.out.println("*       311 Tracking Reports       *");
    	System.out.println("*                                  *");
    	System.out.println("************************************");
    	System.out.println();
    	
        String csvFile = "";

        // Prompt user for file name
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter CSV file name (press Enter for 311_Cases.csv): ");
        csvFile = scanner.nextLine();
        csvFile = (csvFile == "") ? "311_Cases.csv" : csvFile;
        
        // parse file
        List<ThreeOneOne> recordsList = parseFile(csvFile);
        
        // HOMEWORK M7 QUERY 1:
        // print tickets per Neighborhood for all neighborhoods
        TreeMap<String, Integer> hoodsMap = sumTicketsByHood(recordsList);
        System.out.println("\nOverview of 311 tickets by San Francisco Neighborhood:");
        for (String hood : hoodsMap.keySet()) {
			System.out.printf("%-25s had %,7d tickets.\n", hood, hoodsMap.get(hood));
        }
        
        // HOMEWORK M7 QUERY 2:
        // create tickets by neighborhood by day of week
        // TreeMap<Neighborhood, TreeMap<Day of Week, Count of tickets>>
        TreeMap<String, TreeMap<Integer, Integer>> hoodByDayOfWeek = analyzeDaysOfWeek(recordsList);
        
        // loop for detailed neighborhood analysis
        boolean done = false;
        while (!done) {
        	String hood = promptForHood("311 Tickets for Neighborhood", hoodsMap.keySet());
        	if (hood.equals("quit")) {
        		done = true;
        	} else {
        		// HOMEWORK M7 QUERY 3:
        		// prints tickets by Street Name for neighborhood
                TreeMap<String, List<ThreeOneOne>> streetMap = sumTicketsByStreet(recordsList, hood);
                String maxStreet = "";
                int maxTickets = 0;
                System.out.println("Analysis for " + hood + ":");
        		for (String street : streetMap.keySet()) {
        			if (streetMap.get(street).size() > maxTickets) {
        				maxStreet = street;
        				maxTickets = streetMap.get(street).size();
        			}
        			System.out.printf("%-20s had %,6d tickets.\n", street, streetMap.get(street).size());
        		}
        		System.out.println("The street with the most tickets (" + maxTickets + ") is " + maxStreet);
                
                // print tickets by day of week from QUERY 2
                TreeMap<Integer, Integer> weekMap = hoodByDayOfWeek.get(hood);
                String maxDOW = "";
                maxTickets = 0;
                System.out.println("\nTickets by Day Of Week:");
        		for (Integer dow : weekMap.keySet()) {
        			if (weekMap.get(dow) > maxTickets) {
        				maxTickets = weekMap.get(dow);
        				maxDOW = DayOfWeek.of(dow).toString();
        			}
        			System.out.printf("In %-12s on %-10s there were %,d tickets.\n", hood, DayOfWeek.of(dow).toString()+"s", weekMap.get(dow));
        		}
        		System.out.println("The Day of Week with the most tickets (" + maxTickets + ") is " + maxDOW);

        		// HOMEWORK M7 QUERY 4:
        		// tickets by neighborhood by category
        		// TreeMap<Category, Number of Tickets>
        		String maxCat = "";
        		maxTickets = 0;
        		TreeMap<String, Integer> categoryMap = ticketsByCategory(recordsList, hood);
        		System.out.println("\nTickets by Category:");
        		for (String category : categoryMap.keySet()) {
        			if (categoryMap.get(category) > maxTickets) {
        				maxTickets = categoryMap.get(category);
        				maxCat = category;
        			}
        			System.out.printf("Category: %-40s had %,7d tickets.\n", category, categoryMap.get(category));
        		}
        		System.out.println("The Category with the most tickets (" + maxTickets + ") is " + maxCat);
        	}
 	
        }
    }
}

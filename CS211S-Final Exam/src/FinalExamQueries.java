import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.time.*;

public class FinalExamQueries {

	public static void qASortByNameApgar() {
		// Write a single statement to sort the list using a comparator.
		// Sort by name and then apgar
		// Use a lambda.
		recordList.sort(Comparator.comparing(BirthRecord::getName)
			    .thenComparingInt(BirthRecord::getApgarScore));

	}
	public static void qBPrintUniqueNames() {
		// print the unique name of every baby 
		// (in other words, do not print duplicate names)
		recordList.stream()
			    .map(BirthRecord::getName)
			    .distinct()
			    .forEach(System.out::println);
	}
	public static double qCAverageWeightFemales() {
		// the average weight of all female babies
		
		double averageWeight = recordList.stream()
			    .mapToDouble(BirthRecord::getWeight)
			    .average()
			    .orElse(0.0);

		return averageWeight;


	}
	public static List<String> qDCreateListLongMaleNames() {
		// a list of all the names of all male babies with 
		// names longer than 7 characters (note that this is a 
		// list of *names*, not birth records)

		List<String> maleBabiesWithLongNames = recordList.stream()
			    .filter(record -> record.getSexAtBirth() == SexAtBirth.MALE)
			    .filter(record -> record.getName().length() > 7)
			    .map(BirthRecord::getName)
			    .distinct()
			    .collect(Collectors.toList());
		return maleBabiesWithLongNames;

	}
	public static long qENumberOfMultipleBirths() {
		// the number of birth records that were multiple births 
		// (as defined by the instance data variable "multiple")

		long numberOfMultipleBirths = recordList.stream()
			    .filter(BirthRecord::isMultiple)
			    .count();
		
		return numberOfMultipleBirths;

	}
	public static Map<Integer, List<BirthRecord>> qFCreateMapRecordsByApgar() {
		// a map of the birth records for each apgar score 
		// that includes all birth records with scores < 7 
		// (key = apgar score, value = list of records)

		Map<Integer, List<BirthRecord>> birthRecordsByApgarScore = recordList.stream()
			    .filter(record -> record.getApgarScore() < 7)
			    .collect(Collectors.groupingBy(BirthRecord::getApgarScore));

		return birthRecordsByApgarScore;
	}
	
	public static BirthRecord qGGetShortestBaby() {
		// the birth record of the shortest (smallest height) baby

		BirthRecord shortestBaby = recordList.stream()
			    .min(Comparator.comparingDouble(BirthRecord::getHeight))
			    .orElse(null);
		
		return shortestBaby;
	}
	public static boolean qHExistsAnyFemaleHalloweenBaby() {
		// whether there were any female babies that were born 
		// on October 31 (in any year)

		boolean anyFemaleBabiesOnOctober31 = recordList.stream()
			    .filter(record -> record.getSexAtBirth() == SexAtBirth.FEMALE)
			    .anyMatch(record -> record.getBirthDate().getMonth() == Month.OCTOBER && record.getBirthDate().getDayOfMonth() == 31);

		return anyFemaleBabiesOnOctober31;
	}

	
	public static List<BirthRecord> recordList = null;	
	
	public static void main(String[] args) {
		recordList = createList();
		
		System.out.println("\n-----Question A-----");
		System.out.println("---Before sorting:");
		recordList.stream().limit(10).forEach(System.out::println);
		qASortByNameApgar();
		System.out.println("\n");
		System.out.println("---After sorting:");
		recordList.stream().limit(10).forEach(System.out::println);
		
		System.out.println("\n-----Question B-----");
		qBPrintUniqueNames();
		
		System.out.println("\n-----Question C-----");
		double averageWeightFemales = qCAverageWeightFemales();
		System.out.println(averageWeightFemales);
		
		System.out.println("\n-----Question D-----");
		List<String> maleNameList = qDCreateListLongMaleNames();
		System.out.println(maleNameList);
		
		System.out.println("\n-----Question E-----");
		long numberOfMultipleBirths = qENumberOfMultipleBirths();
		System.out.println(numberOfMultipleBirths);

		System.out.println("\n-----Question F-----");
		Map<Integer, List<BirthRecord>> recordMapByDate = qFCreateMapRecordsByApgar();
		System.out.println(recordMapByDate);

		System.out.println("\n-----Question G-----");
		BirthRecord shortestBaby = qGGetShortestBaby();
		System.out.println(shortestBaby);
		
		System.out.println("\n-----Question H-----");
		boolean existsFemaleMultBirthOlivia = qHExistsAnyFemaleHalloweenBaby();
		System.out.println(existsFemaleMultBirthOlivia);
		
	}
	
    public static List<BirthRecord> createList() {
        String line = "";
        String fileName = "BirthRecordData.csv";
        List<BirthRecord> list = new ArrayList<BirthRecord>();

        try (Scanner fileScan = new Scanner(
                new FileReader(new File(fileName)))) {
            line = fileScan.nextLine(); // column headers
            
            while(fileScan.hasNext() ) {
                line = fileScan.nextLine();
                Scanner lineScan = new Scanner(line);
                lineScan.useDelimiter(",");
               
                String name = lineScan.next();
                String sexAtBirthString = lineScan.next();
                String weightString  = lineScan.next();
                String heightString = lineScan.next();
                String apgarString = lineScan.next();
                String year = lineScan.next();
                String month = lineScan.next();
                String day = lineScan.next();
                String multipleString = lineScan.next();

                SexAtBirth sexAtBirth = sexAtBirthString.equalsIgnoreCase("female") ? SexAtBirth.FEMALE : SexAtBirth.MALE;
                double weight = Double.parseDouble(weightString);
                double height = Double.parseDouble(heightString);
                int apgar = Integer.parseInt(apgarString);
                LocalDate birthDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
                boolean multiple = Boolean.parseBoolean(multipleString);
                
                BirthRecord record = new BirthRecord(name, birthDate, weight, height, apgar, multiple, sexAtBirth);
 
                list.add(record);
            }
        } catch (IOException ex){
            System.out.println(line);
            ex.printStackTrace();
        }
        return list;
    }

}

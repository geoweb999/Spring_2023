
public class HistoryDriver {
		
	public static void main(String[] args) {	
		// check basic history functionality
		BoxH boxH1 = new BoxH(Integer.valueOf(0));
		boxH1.replaceContents(Integer.valueOf(1));
		boxH1.replaceContents(Integer.valueOf(2));
		boxH1.replaceContents(Integer.valueOf(3));
		boxH1.replaceContents(Integer.valueOf(4));
		System.out.println(boxH1);
		
		// check duplicate detection
		System.out.println("Duplicate check expected false: " + boxH1.hasDuplicates());
		boxH1.replaceContents(Integer.valueOf(0));
		System.out.println(boxH1);
		System.out.println("Duplicate check expected true: " + boxH1.hasDuplicates());
		
		// check euqality
		BoxH boxH2 = boxH1;
		System.out.println("Equality test boxH1 = boxH2 expected true: " + boxH1.equals(boxH2));
		System.out.println("Equality test boxH2 = boxH1 expected true: " + boxH2.equals(boxH1));
		boxH2.replaceContents(Integer.valueOf(99));
		System.out.println("Equality test boxH1 = boxH2 expected false: " + boxH1.equals(boxH2));
		System.out.println("Equality test boxH2 = boxH1 expected false: " + boxH2.equals(boxH1));
		
		// check with different type
		BoxH boxH3 = new BoxH("A string");
		boxH3.replaceContents("A string");
		boxH3.replaceContents("Another string");
		System.out.println(boxH3);
		System.out.println("Duplicate check expected true: " + boxH3.hasDuplicates());
		System.out.println("Equality test boxH1 = boxH3 expected false: " + boxH1.equals(boxH3));
		System.out.println("Equality test boxH3 = boxH1 expected false: " + boxH3.equals(boxH1));


	}
}

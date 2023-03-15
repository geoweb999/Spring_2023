import java.io.*;
import java.util.Scanner;

public class M6GradedDiscussion {
	
	public static void main(String[] args) {

		PrintWriter out = null;
		Scanner scnr = new Scanner(System.in);
		System.out.print("Enter a filename: ");
		String outFile = scnr.nextLine();
		int[] intList = new int[10];
		
		try {
			out = new PrintWriter(new FileOutputStream(outFile));
			int i = 0;
			boolean done = false;
			while (!done) {
				System.out.print("Enter integer [" + i + "]: ");
				String inText = scnr.nextLine();
				try {
					intList[i] = Integer.parseInt(inText);
					i++;
				} catch (NumberFormatException ex) {
					System.out.println("Invalid integer, please try again.");
				} finally {
					done = (i == 10);
				}
			}
			for (int val : intList) {
				out.println(val);
			}

		} catch (IOException ex) {
		      System.out.println("An I/O error occurred opening file: " + outFile);
		      ex.printStackTrace();
		} finally {
			out.close();
		}
		
	}
}

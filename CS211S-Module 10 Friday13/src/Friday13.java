import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

public class Friday13 {
	
	public static int countFriday13s(int year) {
		int count = 0;
		for (int i = 1; i <=12; i++) {
			LocalDate date = LocalDate.of(year, i, 13);
			if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
				count++;
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		System.out.println("Program will calculate number of Friday the 13ths for an input year and the 9 years following.");
		System.out.print("Enter a year: ");
		int year = scnr.nextInt();
		for (int i = 0; i < 10; i++) {
			System.out.println(year+i + " has " + countFriday13s(year+i) + " Friday the 13ths.");
		}
	}
}

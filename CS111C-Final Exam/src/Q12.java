
public class Q12 {
	public static void mystery(char[] array, int index) {
	    if (index < array.length) {
	        System.out.print(array[index]);
	        mystery(array, index + 1);
	        System.out.print(array[index]);
	    }
	}
	
	public static void Main(String[] args) {
		char[] chars = {'a', 'b', 'c' };
		mystery(chars, 0);
	}

}


public class Q12A {
	public static int sumUp(int n1, int n2) {
	    int sum = n1;
	    if(n1 < n2) {
	       sumUp(n1+1, n2);
	    } 
	    return sum;
	}
	public static void main(String[] args) {
//		char[] chars = {'a', 'b', 'c' };
		int result = sumUp(10,12);
		System.out.println(result);
	}

}



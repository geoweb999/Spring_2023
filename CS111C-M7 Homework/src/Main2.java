public class Main2 {
	
	public static int methodA(int n) {
		   if(n==0) {
		      return 0;
		   } else if(n>0) {
		      return 1 + methodA(n-1);
		   } else {
		      return 1 + methodA(n+1);
		   }
		}
	
	public static void main(String[] args) {
		
		System.out.println(methodA(4));
		
	}
	
}

public class Main {
	
	public static int methodA(int n) {
		   if(n==0) {
		      return 0;
		   } else if(n>0) {
		      return 1 + methodA(n-1);
		   } else {
		      return 1 + methodA(n+1);
		   }
		}
	
	public static int methodB(String s, char c) {
		   if(s.length()==0) {
		      return 0;
		   } else {
		      return (s.charAt(0)==c ? 1 : 0) + methodB(s.substring(1), c);
		   }
		}
	
	public static int countEvens(int[] array) {
		   return countEvensHelper(array, 0, array.length);
		}

	private static int countEvensHelper(int[] array, int start, int stop) {
	   int count = 0;
	   if(start==stop) {
	      return count;
	   } else {
	      if(array[start] % 2 == 0) {
	         count = 1 + countEvensHelper(array, start+1, stop);
	      }
	      return count;
	   }
	}
	
	public static void printNodes(Node head) {
		System.out.print("[ ");
		Node curr = head;
		while (curr != null) {
			System.out.print(curr.data + " ");
			curr = curr.next;
		}
		System.out.println("] ");

	}
	
	public static void recMethod(Node firstNode) {
		   printNodes(firstNode);
		   
		   if(firstNode.next!=null) {
		      firstNode.data = (int) firstNode.data * 2;
		      recMethod(firstNode.next.next);
		   } else {
		      System.out.println("done");
		   }
		   printNodes(firstNode);
		   
	}
	
	public static void infiniteRecursion(int n) {
		   if (n > 0) { 
			      System.out.println("here"); 
			      infiniteRecursion(n-1);
			   } else if(n < 0){ 
			      System.out.println("here");
			      infiniteRecursion(n+1);
			   } else {   
			      System.out.println("here"); 
			   }
		}
	public static int method1(int x) {
	   if (x > 0) {
	      return method1(x - 1) + 1;
	   } else {
	      return 0; 
	   }
	}

	public static int method2(int x) {
	   if (x <= 0) {
	      return 0; 
	   } else {
	      return 1 + method2(x - 1);
	   }
	}

	   public static int countOdds(int[] array) {
		   return countOddsHelper(array, 0, array.length);
		}

		private static int countOddsHelper(int[] array, int start, int stop) {
		   int count = 0;
		   if(start < stop) {
		      if(array[start] % 2 == 1) {
		         count++;
		      }
		     countOddsHelper(array, start+1, stop);
		   }
		    return count;
		}	   
	   
		public static boolean evenPosZero(int x)  {
			   if(x<0) {
			      return false;
			   } else {
				   return(x==0);
			   }
			}
		
		public static int countZeros(int[] array) {
			 return countZerosHelper(array, 0, array.length, 0);
			}

			private static int countZerosHelper(int[] array, int start, int stop, int count) {
			 if(start < stop) {
			 if(array[start] == 0) {
			 count++;
			 }
			 countZerosHelper(array, start+1, stop, count);
			 }
			 return count;
			}
		
	public static void main(String[] args) {
		System.out.println(methodA(-3));
		
//		int[] a = {1, 4, 6, 3, 2, 7, 8};
//		recMethod(a, 3, 8);
//		Node a = new Node(2,null);
//		Node b = new Node(3, a);
//		Node c = new Node(5, b);
//		Node d = new Node(6, c);
//		Node e = new Node(4, d);
//		Node head = a;
//	
//		recMethod(null);
//		
//	infiniteRecursion(2);
//		System.out.println(method1(-55) + " " + method2(-55));
		
//		int[] a = {1,3,5,7};
//		System.out.println(countOdds(a));
//		System.out.println(evenPosZero(4));
//		int[] a = {0, 0};
//		System.out.println(countZeros(a));
	}
	
}

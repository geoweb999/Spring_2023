public class M6Discussion {
	
	public static int[] rearrange(int[] randomList) {
		int negIndex = 0;
		int posIndex = randomList.length - 1;
		int[] newList = new int[randomList.length];
		
		for (int i = 0; i < randomList.length; i++) {
			if (randomList[i] < 0) {
				newList[negIndex] = randomList[i];
				negIndex++;
			} else {
				newList[posIndex] = randomList[i];
				posIndex--;
			}
		}
		return newList;
	}

	public static void main(String[] args) {

		int[] ints = {1, 9, -4, -8, -2, -8, 1, 9, -2, -2};
	
		for (int i = 0; i<ints.length; i++) {
			System.out.print(" " + ints[i] + " ");
		}
		System.out.println();
		ints = rearrange(ints);
		for (int i = 0; i<ints.length; i++) {
			System.out.print(" " + ints[i] + " ");
		}
		System.out.println();
		
		int[] ints2= {1};
		
		for (int i = 0; i<ints2.length; i++) {
			System.out.print(" " + ints2[i] + " ");
		}
		System.out.println();
		ints = rearrange(ints2);
		for (int i = 0; i<ints2.length; i++) {
			System.out.print(" " + ints2[i] + " ");
		}
		
	}
	
}

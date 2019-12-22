package thread.old;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		int num[] = new int[] { 2, 7, 18, 25, 36, 63, 89 };
		int j = num[0];
		for (int i = 1; i < num.length; i++) {
			if (j > num[i]) {
				j = num[i];
			}

		}
		System.out.println(Arrays.toString(num));
		System.out.println("最小值" + j);
		int m = Arrays.binarySearch(num, j);
		System.out.println("下标" + m);

	}

}

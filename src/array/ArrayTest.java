package array;

import java.util.Arrays;
import java.util.List;

/**
 * 数组
 * 
 * @author wwn
 *
 */
public class ArrayTest {
	public static void main(String[] args) {
		String[] strs = new String[] { "csda", "adaf", "advd" };
		//////////////////// asList/////////
		List<String> list = Arrays.asList(strs);
		for (String str : list) {
			System.out.println(str);
		}
		System.out.println("==============================");
		//////////// copyOf/copyOfRange////
		System.out.println(strs.length);
		String[] copyArray = Arrays.copyOf(strs, 10);// copy strs数值，第二个参数表示长度，不足部分补null或初始值吧
		System.out.println(Arrays.toString(copyArray));
		int[] array = new int[] { 1, 2, 3, 4 };
		System.out.println(Arrays.binarySearch(array, 2));// 用二分法查找key，返回索引
		int[] copyRange = Arrays.copyOfRange(array, 1, 6);// 从0计数，第二个参数是索引1，第二个参数是索引6，不包含
		System.out.println(Arrays.toString(copyRange));
		Arrays.sort(strs);// 在原数组上排序

		System.out.println(Arrays.toString(strs));// 打印数组
		String[][] strList = new String[][] { { "wewer", "wrhr" }, { "45645", "rty", "rtyryrt" } };
		System.out.println(Arrays.deepToString(strList));// 多维数组打印
		// 类似还有Arrays.equals来比较1维数组是否相等，Arrays.deepEquals能够去判断多维数组是否相等。
		Arrays.fill(array, 1, 3, 5);// 到索引3，不包含3 会有越界问题
		System.out.println(Arrays.toString(array));

	}
}

package collection;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("one");
		list.add("three");
		list.add("two");
		// 将元素插入到索引1中，不会覆盖原元素
		list.add(2, "three");
		// list变成数组
		Object[] array = list.toArray();
		for (Object str : array) {
			System.out.print(str + "  ");
		}
		// 转换的list2是定长，
		//List<Object> list2 = Arrays.asList(array);
		// list2.add("five");// 不能加操作，会报 java.lang.UnsupportedOperationException

	}
}

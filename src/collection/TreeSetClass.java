package collection;

import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetClass {

	public static void main(String[] args) {

		TreeSet<MyObject> set = new TreeSet<>();
		MyObject obj1 = new MyObject();
		obj1.setName("eqwwe");
		MyObject obj2 = new MyObject();
		obj2.setName("eqwwe");
		MyObject obj3 = new MyObject();
		obj3.setName("eqwfdf");
		MyObject obj4 = new MyObject();
		obj4.setName("afsdffdf");
		// 对象1和对象2的name是一样的，比较后返回0，所以2添加不成功
		System.out.println(set.add(obj1));
		System.out.println(set.add(obj2));
		System.out.println(set.add(obj3));
		System.out.println(set.add(obj4));
		System.out.println("set 的第一个元素：" + set.first().getName() + " ，set 的最后1个元素：" + set.last().getName());
		System.out.println("set的容量：" + set.size() + "对象obj前一个元素是：" + set.lower(obj1).getName() + "对象obj后一个元素是："
				+ set.higher(obj3).getName());
		Iterator<MyObject> its = set.iterator();
		System.out.println("原set是：");
		while (its.hasNext()) {
			System.out.print(its.next().getName() + " ");
		}
		TreeSet<MyObject> newSet = (TreeSet<MyObject>) set.subSet(obj4, false, obj1, true);// 返回set的子集，从obj3，到obj1，第二，4个参数表示是否包含obj1，obj3
		System.out.println("子集是：");
		Iterator<MyObject> newIts = newSet.iterator();
		while (newIts.hasNext()) {
			System.out.print(newIts.next().getName() + " ");
		}
	}

}

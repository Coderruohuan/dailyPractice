package collection;

import java.util.HashSet;

public class SetClass {

	public static void main(String[] args) {
		HashSet<MyObject> set = new HashSet<>();
		MyObject object1 = new MyObject();
		object1.setName("name1");
		MyObject object2 = new MyObject();
		object2.setName("name2");
		set.add(object1);
		set.add(object2);
		object1.setName("name3");
		System.out.println(set.remove(object1));
		System.out.println(set.remove(object2));
	}

}

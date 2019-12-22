package collection;

import java.util.EnumSet;

public class EnumClass {
	enum Season {
		Spring, Summer, Fall, Winter;
	}

	public static void main(String[] args) {
		// 创建一个enumSet值为season值
		EnumSet<Season> set = EnumSet.allOf(Season.class);
		System.out.println(set);
		//

	}
}

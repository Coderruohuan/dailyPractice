package algorithm.util;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.junit.Test;

/**
 * 运行工具类
 * 
 * @author wwn
 * @date 2019年9月30日
 *
 */
public class RunUtil {
	public static void runMethod(Class<?> className) throws Exception {
		Method[] methods = className.getDeclaredMethods();
		for (Method method : methods) {
			// 标注了Solve,Test和不是public的方法不要调用
			if (method.getDeclaredAnnotation(Solve.class) != null || method.getDeclaredAnnotation(Test.class) != null
					|| method.getModifiers() != Modifier.PUBLIC) {
				continue;
			}
			Constructor<?> constructor = className.getConstructors()[0];
			Object object = method.invoke(constructor.newInstance());
			if (object != null) {
				print(object);
			}
		}
	}

	/**
	 * 各结果输出
	 * 
	 * @param object
	 */
	private static void print(Object object) {
		if (object instanceof String || object instanceof Number) {
			System.out.print(object);
		} else if (object instanceof Collections) {
			// 集合输出
			Collection<?> collect = (Collection<?>) object;
			Iterator<?> iterator = collect.iterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next() + " ");
			}
		} else if (object instanceof int[]) {
			int[] objects = (int[]) object;
			for (int o : objects) {
				System.out.print(o + " ");
			}
		}else if (object instanceof String[]) {
			String[] objects = (String[]) object;
			for (String o : objects) {
				System.out.print(o + " ");
			}
		}else if(object instanceof Array) {
			Object[] objects=(Object[])object;
			for(Object o:objects) {
				System.out.print(o+" ");
			}
		} else {
			System.out.print(object.toString());
		}
        System.out.println();
	}
	
}

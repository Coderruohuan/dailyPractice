package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 静态加载类 ：编译的时候就把使用到的类都加载了
 * <li>动态加载类：运行时刻加载类
 * 
 * 反射是在运行时通过class文件获取class对象（类信息）
 * 
 * question
 * <li>1、method 的isAccessible 怎么理解
 * <li>2、classLoader获取class对象
 * <li>3、动态代理
 * <li>4、spring怎么用反射的
 * 
 * @See Class
 * @See Method
 * @see Field
 * @see Constructor
 * 
 * @author wwn
 *
 */
public class ClassTest {

	public static void main(String[] args) throws Exception {
		new ClassTest().classTest1();
		new ClassTest().classField();
	}

	public void classTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		// 第一种根据类名加载class，类名可以放在配置文件里，动态改，不用重新编译
		Class<?> userClass = Class.forName("reflect.ClassTest.User");
		User user = (User) userClass.newInstance();
		user.setUsername("test");
		user.print();
	}

	/**
	 * 获取类对象
	 * 
	 * @throws Exception
	 */
	public void classTest1() throws Exception {

		User user = new User();
		user.setPassword("123");
		// 根据对象获取class
		Class<?> clazz = user.getClass();
		// 通过类信息获取构造器
		Constructor<?> constructor = clazz.getConstructor();
		// 构造器创建对象
		User newUser = (User) constructor.newInstance();
		newUser.print();
	}

	/**
	 * 获取类方法
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public void classMethod() throws NoSuchMethodException, SecurityException {
		Class<User> clazz = User.class;
		Method[] methods = clazz.getMethods();// 获取所有public的方法，包括父类
		for (Method method : methods) {
			Class<?> returnType = method.getReturnType();
			System.out.println(returnType.getName() + " " + method.getName());
		}
		System.out.println("==========================");
		Method[] methods1 = clazz.getDeclaredMethods();// 获取所有该类自己声明的方法(不包含继承的方法)，不管访问权限
		for (Method method : methods1) {
			Class<?> returnType = method.getReturnType();
			System.out.println(returnType.getName() + " " + method.getName());
		}
	}

	/*
	 * 获取类成员变量
	 */
	public void classField() {
		Class<User> clazz = User.class;
		Field[] fields = clazz.getFields();// 获取类的public 方法,包括父类
		for (Field field : fields) {
			Class<?> fieldType = field.getType();
			System.out.println(fieldType.getName() + " " + field.getName());
		}

		Field[] fields1 = clazz.getDeclaredFields();// 获取所有该类自己声明的方法(不包含继承的方法)，不管访问权限
		for (Field field : fields1) {
			System.out.println(field.isAccessible());
			Class<?> fieldType = field.getType();
			// field是字段访问修饰符 Modifiers
			System.out.println(fieldType.getName() + " " + field.getName() + " " + field.getModifiers());
		}
	}

	/**
	 * method 的invoke
	 * 
	 * @throws Exception
	 */
	public void methodInvoke() throws Exception {
		Class<?> userClass = Class.forName("reflect.ClassTest.User");
		Constructor<?> constructor = userClass.getConstructor();
		// 方法名称和参数类型，没参数的不写
		Method method = userClass.getMethod("run");
		Object object = constructor.newInstance();
		// 对象和参数，没参数则不写
		method.invoke(object);
	}

	/**
	 * class 方法类型
	 * 
	 * @throws Exception
	 */
	public void classTest3() throws Exception {
		Class<?> clazz = Class.forName("reflect.User");
		// 是否person是clazz的父类或接口或者是同一个类，接口，是返回true 不是返回false
		System.out.println(Person.class.isAssignableFrom(clazz));
		// 是否该类是jvm基本类型 就9类基本类型，包装的不算
		System.out.println(clazz.isPrimitive() + " " + int.class.isPrimitive() + " " + Integer.class.isPrimitive());
	}

}

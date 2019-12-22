package generics;

/**
 * 泛类
 * 
 * @author wwn
 *<li>泛型类或方法允许用户指定可以和这些类或方法一起工作的对象，能够在编译时检测出错误而不是在运行时
 *<li>泛类中通常用单个大写字母表示一个形式泛型类型常见的比如<br>
 *    E-element表示集合中的元素;T-Type表示Java类;K-key表示键 ;V-value表示值;N-number
 *                表示数值类型;?-表示不确定的Java类,无限制通配符类型
 * <li>不使用类型参数的泛型称为原始类型，比如ArrayList list=new ArrayList()是原始类型，ArrayList<String> list=new ArrayList<String>()是泛型类型
 * <li>当有多个泛型的时候，可以写在一个<>中，用逗号分隔
 * <li>E extends Collection 这种是受限制的泛型，说明E只能被Collection的子类所泛型化
 * <li>通配符类型有三种：?（不受限制通配符）, ? extends T（受限通配符，表示T或者T的子类）, ? Super T（下限通配符，代表T或T的父类）
 * <li>虽然Integer是Number的子类但是 List<Integer> 不是 List<Nmuber>的子类。因为List<Integer>没有继承List<Number>
 * <li>泛型使用一种称为类型消除的方法来实现的，泛型信息在运行中是不可用的，在运行时会转化为原始类型，比如ArrayList<Integer>和ArrayList<String>都会被转成ArrayList
 * <li>不能用泛型类型参数创建实例，数组，比如说E e=new E()是错的
 * <li>在静态环境下不允许类的参数是泛型类型，必须是静态方法也是泛型方法；如下面的GenericsClass的get，是静态方法，即使泛型类中声明了E1，但是还是要这个静态方法是泛型方法，但是普通方法get2可以
 * <li>异常类不能是泛型类，因为jvm必须检查这个从try中抛出的异常来确定是否是catch中的类型，但是泛型信息在类型中是不出现的？？？？？（不懂）
 * <li>泛类方法是在方法调用时指明泛型的具体类型；方法返回值前面的泛类型可以理解为声明此方法为泛型方法，如下面的get3是泛型方法，get2不是
 * <br>
 * Q:普通类的泛型方法有什么用
 * 
 */
public class GenericsTest {

	public static void main(String[] args) {

		Panel panel = new Panel();
		panel.put(new Apple());

		// ArrayList<Integer> list=new ArrayList<>();
		// new GenericsTest().test(list); 不能调用，因为不是父类与子类的关系,虽然在运行时是一个类型，但是编译期会不通过
	}

	// public void test(ArrayList<Number> list) {
	// }
}

class GenericsClass<E1, E3> {

	public static <E1> void get(E1 e) {

	}

	// 如get3这种返回值带<E2>的是泛型方法，get不是泛型方法
	public <E2> void get3(E2 e) {

	}

	// 参数的E1使用时需要声明，一种方法是类时泛型类，在泛型类中声明了E1；第二种类是普通类，方法是泛型方法如上面的E2在泛型类中没有声明，那么这个需要在泛型方法中声明
	public E1 get2(E1 e) {
		return e;

	}

}

class Panel {
	// 泛类方法是在方法调用时指明泛型的具体类型；方法返回值前面的泛类型可以理解为声明此方法为泛型方法
	public <T> Fruit put(T food) {
		return null;

	}

}

class Fruit {

}

class Vegetable {

}

class Apple extends Fruit {

}

class photo extends Vegetable {

}

class Orange extends Fruit {

}

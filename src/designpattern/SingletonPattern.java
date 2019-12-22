package designpattern;

/**
 * 单例模式 
 * 
 * <td>单例模式的要点是：
 * <li>1、构造器设置为private，防止外部创建对象
 * <li>2、单例默认获取对象要static，因为外部无法创建对象，只能通过类方法调用
 * <li>3、创建对象还有一种方式是clone，它是直接复制二进制字节，不是通过构造器创建的，所以单例类不要实现Cloneable
 * <li>4、单例模式的对象不会被被当作垃圾回收，因为方法区中的类静态属性引用的对象属于GC ROOT
 * <td> 使用场景
 * 
 * @author wwn
 * @date 2019年9月18日
 *
 */
public class SingletonPattern {
	public static void main(String[] args) {
		System.out.print(SingletonPatternOne.getInstall()+"----"+SingletonPatternOne.getInstall());
	}

}

//饿汉模式，在类初始化时创建对象，优点是线程安全，缺点是万一后面没有用到就白白创建了一个对象
class SingletonPatternOne {
	// 实例
	private static SingletonPatternOne install = new SingletonPatternOne();

	private SingletonPatternOne() {
	}

	public static SingletonPatternOne getInstall() {
		return install;
	}
}

//懒汉模式，在使用是才创建，要注意线程安全
class SingletonPatternTwo {

	private static SingletonPatternTwo install = null;

	private SingletonPatternTwo() {
	}

	public synchronized static SingletonPatternTwo getInstall() {
		if (install == null) {
			install = new SingletonPatternTwo();
		}
		return install;
	}
}

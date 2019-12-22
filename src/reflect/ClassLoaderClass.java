package reflect;
/**
 * 类加载机
 * @author wwn
 * @date 2019年10月15日
 * 
 * <li> 类加载器：引导类加载器，扩展类加载器，系统类加载器
 * <li> 类加载器有父子关系，除了引导类加载器，其他类加载器都有一个父类加载器
 * <li> 当一个子类加载器要加载类时，先会让父类进行加载，如果加载失败才会沦到子类加载器
 * <li>什么叫类加载器倒置？？
 * <li> 每个线程都有对一个类加载器的引用，称为上下文类加载器；可以设置thread.setContextClassLoader(loader)
 * <li>在同一个虚拟机中，用类的全名和类加载器确定类的唯一性，即同一个类文件被不同类加载器加载，他们不是同一个对象
 * <li>类加载器将Java类的字节码加载到jvm时会经过校验器，校验器负责检查无法执行明显有破坏性的操作
 * <li>当类通过第一层校验器后，就会执行下一步，下一个机制是安全管理器{@link SecurityManager}，它是一个负责控制具体操作是否允许执行的类
 * 
 * 
 *
 */
public class ClassLoaderClass {

}


//编写字节的类加载需要继承ClassLoader，然后覆写findClass 方法
class MyClassLoader extends ClassLoader{
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		return super.findClass(name);
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return super.loadClass(name);
	}
	
	
}
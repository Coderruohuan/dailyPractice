package designpattern;

/**
 * 工厂模式：定义一个用于创建对象的接口，让子类决定实例化哪个类，工厂方法是一个类的实例化延迟到其子类
 * 
 * @author wwn
 * @date 2019年9月18日 用于创建对象复杂的，如果对象只要new就好了就不用工厂模式
 * 
 */
public class FactoryPattern {
	public static void main(String[] args) {
		DBFactory factory = new MysqlFactory();
		DBOperate db=factory.createOperate();
		db.connection();
	}

}

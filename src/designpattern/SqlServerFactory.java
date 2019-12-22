package designpattern;

/**
 * 工厂实现类
 * {@link FactoryPattern}
 * @author wwn
 * @date 2019年9月18日
 *
 */
public class SqlServerFactory implements DBFactory {

	@Override
	public DBOperate createOperate() {
		DBOperate db=new SQLServerOperator();
		System.out.println("一大串操作");
		return db;
	}

}

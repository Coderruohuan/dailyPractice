package designpattern;
/**
 * 对象实现类
 * {@link FactoryPattern}
 * @author wwn
 * @date 2019年9月18日
 *
 */
public class SQLServerOperator implements DBOperate {

	@Override
	public void connection() {
		System.out.println("sql server连接操作...");
	}

}

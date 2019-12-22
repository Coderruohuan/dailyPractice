package designpattern;
/**
 * 对象实现类
 * {@link FactoryPattern}
 * @author wwn
 * @date 2019年9月18日
 *
 */
public class MysqlOperate implements DBOperate {

	@Override
	public void connection() {
		System.out.print("mysql 连接操作...");
	}

}

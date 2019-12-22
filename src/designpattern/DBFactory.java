package designpattern;
/**
 * 创建对象的工厂接口
 * {@link FactoryPattern}
 * @author wwn
 * @date 2019年9月18日
 *
 */
public interface DBFactory {
	
     DBOperate createOperate();
}

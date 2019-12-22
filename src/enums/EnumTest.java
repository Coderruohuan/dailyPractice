package enums;

/**
 * 
 * 1、为什么要用枚举
 * 有时候我们要用一组值来描述对象的一种属性，比如说交通灯有红绿黄三种颜色，如果我用常量的话要用3个常量，而且值只能固定一种，比如记到数据是red，green
 * ，yellow，但是展示的时候是红绿黄，那么就需要用条件语句进行翻译 这样代码可读性很差，扩展性也不好
 * 
 * @author wwn
 *
 */
public class EnumTest {

	public static void main(String[] args) {
		TrafficLightEnum.RED.on();
		TrafficLightEnum.RED.off();
	}

}

package enums;

/**
 * 交通灯枚举类 <br>
 * 命名规范:
 * <li>类名以Enum结尾
 * <li>枚举值都大写，单词之间用下划线分隔
 * 
 * 
 * javap -c TrafficLightEnum.class 反编译,得到以下几点<br>
 * 一、非抽象类 1、TrafficLightEnum继承了java.lang.Enum类，而且是final的，所以不能有子类（这只对非抽象类来说的）
 * 2、red，green，yellow都会被当作变量，变量类型是enum.TrafficLightEnum
 * 3、也就是说每个枚举值都是一个TrafficLightEnum对象，RED(0,
 * "红")相当于调用构造方法new了一个TrafficLightEnum对象，0和"红"都是构造方法的参数，所以下面必须要有对应参数的构造器
 * TrafficLightEnum RED =new TrafficLightEnum (0, "红"); <br>
 * question：RED怎么理解??相当于对象变量吗
 * 
 * 二、有抽象方法的
 * <li>1、有抽象方法的枚举就会变成抽象类
 * <li>2、{@code  TrafficLightEnum RED =new TrafficLightEnum (0,
 * "红"){@Override public void on() { System.out.println(this.getName() + "is
 * on");} } } 相当于实现了TrafficLightEnum的匿名子类
 * 
 * 
 * 枚举类可以实现多个接口
 * 
 * @author wwn
 *
 */
public enum TrafficLightEnum implements LightInterface {
	// 编译的时候就创建对象了，所以可以直接调用，final修饰
	RED(0, "红") {
		@Override
		public void on() {
			System.out.println(this.getName() + " is on");
		}

	},
	GREEN(1, "绿") {
		@Override
		public void on() {
			System.out.println(this.getName() + " is on");

		}

	},
	YELLOW(2, "黄") {
		@Override
		public void on() {
			System.out.println(this.getName() + " is on");

		}

	};

	private Integer code;
	private String name;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private TrafficLightEnum(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * 抽象方法
	 */
	public abstract void on();

	/**
	 * 这个也可以放在匿名子类里实现（即RED{}里）
	 */
	@Override
	public void off() {
		System.out.println(this.getName() + " is off");
	}

}

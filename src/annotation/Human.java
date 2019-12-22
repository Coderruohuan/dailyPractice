package annotation;

@Person(interests = { "xx" }, name = "xx")
public interface Human {
	// 不能设置默认返回值
	String run();
}

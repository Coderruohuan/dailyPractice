package annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 注解 怎么理解
 * 
 * 反编译后是一个接口，继承java.lang.annotation.Annotation 接口
 * 
 * {@link https://docs.oracle.com/javase/tutorial/java/annotations/basics.html}
 * <li>如果属性名是value且使用设置时只设置了value，则使用时可以省略
 * <li>如果注解不设置属性值，则圆括号可以省略
 * <li>能用在类，方法，成员变量等元素上
 * <li>通过反编译可以看到person是个接口,里面的就相当抽象方法，可以设定默认值
 * <li>设置多个注解
 * 
 * 一些常用的注解
 * 
 * 自定义注解的时候要加元注解 retention 不然无法使用
 * 
 * @author Administrator
 *
 */
// 定义一个可重复定义的注解，persons注解包含多个person，而且对应的方法名必须是value
// @Repeatable(value = Persons.class)
@Retention(value = RetentionPolicy.RUNTIME) // 这边有的persons也要有
@Inherited // 表示被该注解修饰的class的子类也会有被拥有该标签（只对class起作用，method什么都没用）
@SuperAnnotation // 这样就可以用superAnnotation来描述person
public @interface Person {

	String name();

	// 可以设置默认值，没默认的在使用时必填，这个是方法，"女"是默认返回值
	String sex() default "女";

	String[] interests();

}

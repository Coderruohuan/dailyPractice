package algorithm.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记标签-用来标记算法是否以完成
 * @author wwn
 *
 */
@Target(value = { ElementType.METHOD })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Solve {
	String title() default "";
    String content() default "";
	String date() default "";
	String keywords() default"";
}

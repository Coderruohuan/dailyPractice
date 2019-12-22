package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import enums.TrafficLightEnum;

@Retention(RetentionPolicy.RUNTIME) // 默认class，如果不加，它修饰的东西.getAnnotation就获取不到这个标签了
public @interface SuperAnnotation {
	TrafficLightEnum light() default TrafficLightEnum.GREEN;

}

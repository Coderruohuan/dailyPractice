package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

/**
 * 注解：
 * 
 * 1、注解产生的原因？ 注解是给jvm和编译器看的 xml和注解 注解是接口，他的方法可以设置default为什么普通接口不能
 * 
 * 
 * @author wwn
 *
 */

public class AnnotationTest {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		// 利用反射获取类信息，然后根据class,method,field 解析Annotation
		Class<Girl> clazz = Girl.class;
		Annotation[] annotations = clazz.getAnnotations();
		for (Annotation annotation : annotations) {
			System.out.println(annotation.annotationType());
			if (Person.class.equals(annotation.annotationType())) {
				// person其实是接口，这边newInstance出现问题
				// java.lang.InstantiationException: annotation.Person
				// Person person = (Person) annotation.annotationType().newInstance();
				Person person = (Person) annotation;
				System.out.println(person.name() + " " + person.sex() + person.interests());
				Annotation[] annotationlist = Person.class.getAnnotations();
				for (Annotation meteAnnotation : annotationlist) {
					if (SuperAnnotation.class.equals(meteAnnotation.annotationType())) {
						System.out.println(meteAnnotation.annotationType().getMethod("light").invoke(meteAnnotation));
					}

				}

			}
		}
	}
}

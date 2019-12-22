package basic;

import java.math.BigDecimal;
import java.math.RoundingMode;

import reflect.User;

public class BasicDataTest {
	public static void main(String[] args) {
		int a1=1;
		int b1=2;
		int c1=3;
		int d1=a1=c1=b1;
		System.out.println(a1+" "+b1+" "+d1+" "+c1);
		Long l1=34L;
		Long l2=34L;
		Long l5=new Long(34);
		Long l6=new Long(34);
		Long l3=128L;
		Long l4=128L;
		System.out.println(l1==l2);//true
		System.out.println(l1==l5);//false
		System.out.println(l5==l6);//false
		System.out.println(l3==l4);//false 
		//short s=32767;		
		BigDecimal b=new BigDecimal(23.44).setScale(2,RoundingMode.DOWN);//23.44以float传入，已经失去精度了
		System.out.println(b);
		BigDecimal c=new BigDecimal("23.44");
		System.out.println(c);
		BigDecimal d=new BigDecimal(23.5);
		System.out.println(d);
		//对象的equals比较和== 比较
		User user1=new User();
		user1.setUsername("123");
		User user2=new User();
		user2.setUsername("123");
		System.out.println("user1.equals(user2) ? "+(user1.equals(user2)));
		System.out.println("user1==user2 ? "+(user1==user2));
	}
	
}

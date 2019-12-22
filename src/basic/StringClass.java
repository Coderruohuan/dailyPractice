package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

/**
 * 字符{@link String}
 * @author wwn
 * @date 2019年10月9日
 * 
 * 1、String的常用方法
 * 2、String是否是同一个对象
 * 
 *
 */
public class StringClass {
	@Test
	public void StringMethods() {

		System.out.println("=============字符串拼接==========");
		String  str="12345678";
		System.out.println("原字符串："+str);
		//字符串截取方法
		System.out.println("截取index[2,5):"+str.substring(2, 5));
		System.out.println(str.subSequence(2, 5));//这个和上面那个功能是一样的，就是调用的this.subString(2,5),是为了实现CharSequence接口的方法
		System.out.println(String.join("-",str,"jsm"));//字符传连接，第一个参数是连接分隔符，后面是可变参数
		List<String> list=new ArrayList<>();
		for(int i=0;i<10;i++) {
			list.add(String.valueOf(i));
		}
		System.out.println(String.join(",", list));//也可以传Iterable<? extends CharSequence>
		System.out.print(str.concat("连接"));//也是字符串连接；join是StringBuilder.append()(底层也是操作数组);contact是直接操作数组
		System.out.println("=============大小写转换==========");
		String strCase="aBCdCe";
		System.out.println("原字符串: "+strCase);
		System.out.println("转大写: "+strCase.toUpperCase()+";转小写："+strCase.toLowerCase(Locale.FRANCE));//可以设置参数Locale
		System.out.println("============字符串中字符验证==========");
		System.out.println("字符串是否以ab开头？"+(strCase.startsWith("ab")?"是":"否"));
		System.out.println("字符串是否以aB开头？"+(strCase.startsWith("aB")?"是":"否"));//大小写敏感
		System.out.println("字符串偏移2位后是否以Cd开头？"+(strCase.startsWith("Cd",2)?"是":"否"));//大小写敏感
		System.out.println("字符串是否以Cde结尾？"+(strCase.endsWith("Cde")?"是":"否"));
	
		System.out.println("字符串是否含有字符串'c'？"+(strCase.contains("C")?"是":"否"));
		System.out.println("字符串'c'在字符串中strCase的位置（返回第一个匹配的）："+strCase.indexOf("C",0));
		//如果要在字符串中找出所有子串的位置，只要设置第二个参数就可以了,比如我要知道所有"abc"的位置
		String findStr="abcaddbcabccadcabc";
		int i=-1;
		while((i=findStr.indexOf("abc",i+1))!=-1) {
			System.out.print(i+" ");
		}
		System.out.println();
		System.out.println(findStr.lastIndexOf("abc", findStr.length()));//最后一个匹配的位置,第二个参数表示截至到9，即在（0,9）之间查找匹配的最后一个
		System.out.println("第4个元素是:"+findStr.charAt(4));
		
		System.out.println("两次字符串内容是否相等(还有一个是不区分大小写的)？"+findStr.contentEquals("abcaddbcabccadcabc"));//和equals功能一样
		System.out.println(" a b   cg ".trim());//去首尾空格
		
		String[] strs="1,2,3,4,5".split(",", 2);//按逗号分隔，数组长度不超过第二个参数设定的值，值要大于0才会起作用（此列被分成1   和2,3,4,5）
		Arrays.asList(strs).forEach(item->System.out.println(item));
		System.out.println("abcabcabc".replace("a","b"));//将字符串中所有的字符串a替换成b
		//这个可以用来统计某个字符串中某个字符的个数
		String original="abcabcabc";
		System.out.println("字符串中a的个数有："+(original.length()-original.replaceAll("a", "").length()));
		System.out.println(original.replaceFirst("a", "b"));//只替换第一个a
		System.out.println(String.format("%(,d", 20190230));//将某个对象化成String,格式要match  Formatter.formatSpecifier
	    original.intern();//从字符串池中获取original的引用		
		System.out.println("============代码点，代码单元==========");	//TODO 
		System.out.println(original.codePointAt(0));
	}
	
	@Test
	public void isSame() {
		String str="abc";
		String str1="ab"+"c";
		String str2=new String("abc");
		String str3=new String("abc").intern();//abc是从字符串常量池获取的
		System.out.println("str==str1?"+(str==str1));
		System.out.println("str==str2?"+(str==str2));
		System.out.println("str==str3?"+(str==str3));
		System.out.println("str3==str2?"+(str2==str3));
	}

}



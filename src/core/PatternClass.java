package core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternClass {
	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("want to");
		Matcher matcher = pattern.matcher("I'm very tired,I want to sleep");
		//全匹配
		System.out.println(matcher.matches());
		//是否有和正则式一样的子序列，可以重复调用，查找所有匹配的子序列，每次调用都从上一次离开的位置开始
		System.out.println(matcher.find());
		//包含最后一个匹配序列的字符串
		System.out.println(matcher.group());
		//序列中当前匹配的索引
		System.out.println(matcher.start());
		//当前匹配序列末尾之后下一个字符的索引
		System.out.println(matcher.end());
		//用新字符串替换所有匹配的序列 
		System.out.print(matcher.replaceAll("like to"));
	}

}

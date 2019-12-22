package io;

import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 正则表达式
 * @author wwn
 * @date 2019年9月16日
 * @see Pattern
 * 
 */
public class RegularExpressionClass {
	
	@Test
	public  void patternTest() {
		// String regex compile成pattern，如果regex是无效的，会报PatternSyntaxException错误
		Pattern pattern=Pattern.compile("*[a-z]",Pattern.LITERAL);
		System.out.println(pattern.flags());
		System.out.print(pattern.matcher("sz").matches());
	}

}

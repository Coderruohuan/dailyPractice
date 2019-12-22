package basic;

import java.text.Collator;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
/**
 * 国际化 Locale
 * @author wwn
 * @date 2019年12月13日
 *
 */
public class LocaleClass {
	
	public static void main(String[] args) {
		//Locale.setDefault(Locale.CANADA_FRENCH);
		System.out.println(Locale.getDefault().getCountry()+"-"+Locale.getDefault().getDisplayName());
		Locale[] locales=Locale.getAvailableLocales();
		//类似于刚开始选择语言
		for(Locale locale:locales) {
			//数字显示
		    String number= NumberFormat.getNumberInstance(locale).format(1000);
		    //货币显示
		    String currency=NumberFormat.getCurrencyInstance(locale).format(454334.54);
		    //百分比显示
		    String percent= NumberFormat.getPercentInstance(locale).format(234);    
		    System.out.println(locale.getDisplayName()+"-"+locale.getDisplayName(locale));
		    System.out.println("数字格式化------------>");
		    System.out.println(number+"-"+currency+"-"+percent);
		    System.out.println("时间格式化------------>");
		    //getDateInstance 和getTimeInstance
		    String dateFull= DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL,locale).format(new Date());
		    String dateShort= DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT,locale).format(new Date());
		    String dateMedium= DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM,locale).format(new Date());
		    String dateLong= DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG,locale).format(new Date());
		    System.out.println(dateFull);
		    System.out.println(dateShort);
		    System.out.println(dateMedium);
		    System.out.println(dateLong);
		    System.out.println("-----------------------------");    
		}
		sort();
		messageFormat();
	}
	
	/**
	 * 按字典排序的比较
	 */
	public static void sort() {
	    String[] strArray=new String[] {"Axsdd","Zxdgsr","ssrxx","Beze","bezes"};
	    //一般的排序
	    System.out.println("一般的排序------------>");
	    List<String> original=Arrays.asList(strArray);
	    Collections.sort(original);
	    System.out.println(original);
		List<String> list=Arrays.asList(strArray);
		//Collator.getInstance(Locale)
		Collator coll=Collator.getInstance();//这边可以传Locale类型的参数
		Collections.sort(list, coll);
		System.out.println(list);
	}
	
	/**
	 * 消息格式化
	 */
	public static void messageFormat() {
		//choice 的使用，choice是为了格式化选项而设计的，它由一个下限和格式字符串组成（就是ChoiceFormat中的key和value），格式为：下限#格式字符串|下限#格式字符串....('#'也可以用'<',#表示的是大于等于)
		//比如下面我对应{1}输入的参数是32，而1<32<34，那么输出的是one hourse，如果我输的是44，那么输出的是44 hourses（其中44替换格式字符串中的{1}），大于等于1小于34 输出34
		String pattern="on {2,date,long},{0} destroyed {1,choice,0<no hourses|1<one hourse|34#{1} hourses} and caused {3,number,currency} of damage";
		String msg=MessageFormat.format(pattern,"hurricane",1,new Date(), 10.0E8);
		System.out.println(msg);
		
	}

}

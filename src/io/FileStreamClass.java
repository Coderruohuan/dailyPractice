package io;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

/**
 * @see InputStream
 * @see OutputStream
 * 
 * @author wwn
 * TODO 资源关闭什么先不管
 *
 */
public class FileStreamClass {

	@Test
	public void inputStreamTest() {
		//Java 7 try-with-resource 可以帮助关闭资源
		try(FileInputStream fis=new FileInputStream("random.txt");
			//当参数传字符串的时候，如果这个路径找不到就会报FileNotFoundException，如果可以找到这个路径，但是路径下面没这个文件，则会创建相应的文件
			//比如这个h盘根本没有就会报错。另外参数可以是绝对路径和相对路径
			//注意，如果不加第二个参数，已存在的output.txt的内容会被覆盖掉（默认是false），传了true，原来内容还会存在
			FileOutputStream	fos=new FileOutputStream("output.txt",true)){
			System.out.println("available:"+fis.available());
			byte[] bytes=new byte[fis.available()];
			@SuppressWarnings("unused")
			int b;
			while((b=fis.read(bytes))!=-1) {
		        ByteArrayInputStream in =new ByteArrayInputStream(bytes);
		        System.out.println("完整内容为："+new String(bytes));
		        if(in.markSupported()) {
		        	in.mark(0);//对于ByteArrayInputStream的mark来说，这里的参数没有意义，注释有说明   	
		        	for(int i=0;i<4;i++) {
		        	  	if(i==2) {
		            		in.mark(0);
		            	}
		        	   	byte[] someBytes=new byte[10];
		            	in.read(someBytes);
		                if(i!=1) {
		            		in.reset();//重置
		            	}
		            	System.out.println("第"+i+"次"+new String(someBytes));
		        	}
		        }	
			} 	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	 
	}
}

package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;

import org.junit.Test;

/**
 * 过滤流
 * @author wwn
 *
 */
public class FilterStreamClass {
	
	@Test
	public void filterStreamTest()  {
		try(FilterOutputStream fos=new MyFilterOutputStream(new FileOutputStream("output.txt"));
			FilterInputStream fis=new MyFilterInputStream(new FileInputStream("output.txt"));){
			fos.write("secret".getBytes());
			byte[] result=new byte[200];
			while(fis.read(result)>0) {
				System.out.println(new String(result));
			}
		}catch (FileNotFoundException e) {
			
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}
//一般outputStream 和inputStream 重写最顶层的write和read，因为其他write和read都是调用这个的
class MyFilterOutputStream extends FilterOutputStream{
	  
	public MyFilterOutputStream(OutputStream out) {
		super(out);
	}
	@Override
	public void write(byte[] b) throws IOException {
		super.write(Base64.getEncoder().encode(b));
	}
}

class MyFilterInputStream extends FilterInputStream{

	protected MyFilterInputStream(InputStream in) {
		super(in);
	}
	
	@Override
		public int read(byte[] b) throws IOException {
			byte[] sourceByte=new byte[super.available()]; 
			int result=super.read(sourceByte);
		    byte[] temp=Base64.getDecoder().decode(sourceByte);
		    System.arraycopy(temp, 0,b, 0, temp.length);//把b指向的对象值都进行赋值，不能直接b=xxx，这个只是将b指向其他对象，并不改变原来状态的值
			return result;
		}
	
}

  

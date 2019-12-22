package io;

import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;
/**
 * 标准流
 * @author wwn
 *  setIn
 *  setOut 可以从键盘和文件读取流也可以输出到屏幕和文件
 */
public class StandardIOClass{
 
    @Test
	public  void StandardInput() throws IOException {
    	byte[] inBytes=new byte[1024];
    	System.in.read(inBytes);//从键盘输入读取
    	System.out.println(new String(inBytes)+"yes");//后面加的yes输不出来
    	System.setIn(new FileInputStream("output.txt"));//指定输入流
    	inBytes=new byte[1024];
    	System.in.read(inBytes);
    	System.out.println(new String(inBytes));
    }
}

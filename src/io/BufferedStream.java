package io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

/**
 * 缓存流
 * @author wwn
 *
 */
public class BufferedStream {
     
	 @Test
	 public  void  BufferedStreamTest() throws InterruptedException {
		 try(
			 BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream("output.txt"))){
			 bos.write("测试".getBytes());
			 File file=new File("output.txt");
			 System.out.println("执行flush前："+file.length());
			 //throw new RuntimeException(); 测试了报异常还是写入成功了，close调用成功了？= =
			 bos.flush();
	         System.out.println("执行flush后："+file.length());
		 Thread.sleep(10000);//让流迟一点关闭，因为程序执行结束，调用close会调用flush
		} catch (FileNotFoundException e) {
		} catch (IOException e1) {
		}
		 
	 }

}

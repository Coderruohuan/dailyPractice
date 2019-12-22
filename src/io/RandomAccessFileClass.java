package io;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.Test;

/**
 * 随机访问文件类
 * @author wwn
 *
 */
public class RandomAccessFileClass {
	
   @Test
   public  void randomAccessFileTest() throws IOException {
	   //第二个参数有4个选值范围
	   RandomAccessFile randomFile=new RandomAccessFile("random.txt", "rw");	   
	   System.out.println("写入前文件长度:"+randomFile.length());//文件字节数
	   //向文件编写内容,，会覆盖之前的内容，如果要接着写设置指针
	   randomFile.seek(randomFile.length());
	   randomFile.write(new String("After 我  graduated from university,I haven't learn  more.so be hard, then you can chooes what you want").getBytes());
	  
	   //操作文件的句柄
	   FileDescriptor fileDescriptor=randomFile.getFD();
	   fileDescriptor.sync();//write的时候，只是将内容传输到操作系统的缓存中，还没刷到硬盘中，等缓存满了或者文件被关闭了，或程序结束了，才会被刷到磁盘中，而执行sync就是将缓存中的内容刷到硬盘中，可以打断点，前后比较一下。
	   
	   // randomFile.writeChars("After 我  graduated from university,I haven't learn  more.so be hard, then you can chooes what you want");
	   //writeChars是以一个字符的形式写入的，最后会写成A F T E R ,中文会有乱码
	   System.out.println("写入后文件长度:"+randomFile.length());//文件字节数	      
	   System.out.println("filePointer:"+randomFile.getFilePointer());//当前文件指针所在的位置（基数是从0开始的）
	 
	   randomFile.setLength(randomFile.length()-10);//设置文件长度
	   byte[] chars=new byte[1024];
	   randomFile.seek(0);//写入后，指针已经到最后了，要读取的话，指针重置一下,指向起始位置
	   randomFile.read(chars);//读取一个字节，如果返回-1表示读到文件末尾了
	   System.out.println("文件长度缩短了10个字节后输出内容为:"+new String(chars));
	   //System.out.println("readChar:"+randomFile.readChar());//读取一个字符（即两个字节），另外还有其他的，比如读int的，8位，还有一行行读的
       randomFile.skipBytes(-10);//指针往后移动10个字节，如果当前指针位置+需要移动的字节>文件的长度，那实际移动的字节为=文件长度-当前指针位置;如果参数是负的，就不移动
       //System.out.print(randomFile.readChar());基于上面那个skipByte=-10，再执行这句指令会报错
        randomFile.close();
   }
}

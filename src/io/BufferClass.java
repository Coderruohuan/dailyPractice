package io;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;

import org.junit.Test;

/**
 * buffer
 * @author wwn
 * 1、不能知道mark值
 */
public class BufferClass {
	
	private ByteBuffer buffer=ByteBuffer.allocate(10);
	{
		buffer.put("flip".getBytes());
		}
	 @Test
     public void bufferMethodTest() {
	   ByteBuffer buffer=ByteBuffer.allocate(10);
	   System.out.println("初始后buffer的三个属性值"+buffer);//mark初始时未定义，position=0，limit=capacity 四者关系是0<=mark<=position<=limit<=capacity
//       buffer=buffer.asReadOnlyBuffer();//创建一个新的只读的buffer
//	   System.out.println(buffer.array());//返回buffer的底层array，当这个buffer只读或者底层不是一个可访问数组的时候，会报错
	   buffer.put("test".getBytes());
	   buffer.limit(8);
	   buffer.mark();
	   System.out.println(buffer);
	   buffer.clear();//进行clear
	   System.out.println(buffer);
	   byte[] bytes=new byte[buffer.capacity()];
	   buffer.get(bytes);
	   System.out.println(new String(bytes));//内容还可以打印，不会被clear掉
	 }
	 
	 /**
	  * flip的操作是将limit指向当前位置，position指向0;
	  * 作用一般将写模式转换成读模式
	  */
	 @Test
	public void flipTest() {
		
		System.out.println(buffer);
		buffer.flip();
		System.out.println(buffer);
	}
	 
	 @Test
	 public  void markTest() {
		 buffer.mark();//此时mark指向的flip的p
		 buffer.put(" mark".getBytes());//buffer溢出了，不会自动扩展，在创建时指定后就不会变了，因为底层是数组
		 System.out.println(buffer);
		 buffer.reset();//position指向mark处，可以重复一直读
		 System.out.println(buffer);
		 byte[] bytes=new byte[buffer.remaining()];
		 buffer.get(bytes);//如果bytes.length>reminding 报BufferUnderflowException
		 System.out.println(new String(bytes));
		 
		 
		  buffer= ByteBuffer.wrap(new byte[30],10,5);//position=10，limit=15，capacity=30
		  buffer.put("test2".getBytes());//超过了报错
		  System.out.println(buffer);
	 }
	 
	 @Test
	 public  void duplicateTest() {
		 byte[] bytes="testwerwrweurwiworowr".getBytes();
		 buffer= ByteBuffer.wrap(bytes,10,5);//position=10，limit=15，capacity=30
		  System.out.println(buffer);
		  ByteBuffer cloneBuffer=buffer.duplicate();//克隆buffer
		  System.out.println("克隆buffer为："+cloneBuffer);
		  System.out.println("设置之后=======================");
		  cloneBuffer.position(4);
		  System.out.println("cloneBuffer:"+cloneBuffer);
		  System.out.println("buffer："+buffer);
		  byte[] byte1=new byte[cloneBuffer.remaining()];
		  cloneBuffer.get(byte1);
		  
		  System.out.println("原始:"+new String(byte1));
		  
		  
		  
		  bytes[4]='A';
		  cloneBuffer.position(4);
		  byte1=new byte[cloneBuffer.remaining()];
		  cloneBuffer.get(byte1);
		  System.out.println("修改后："+new String(byte1));
		  
		  buffer.position(4);
		  byte[] byte2=new byte[buffer.remaining()];
		  buffer.get(byte2);
		  System.out.println("修改后："+new String(byte2));//和cloneBuffer输出一样，说明公用一个array
		  
		  LongBuffer  longBuffer=buffer.asLongBuffer();//可以将ByteBuffer转成LongBuffer
		  System.out.println(longBuffer.capacity());
	 }
	 
	 
	 
	 
	 
	 
}

package io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

/**
 * 管道流
 * @author wwn
 * 
 * 
 *
 */
//整个程序都发生阻塞，写的都停在了wait上；读的停在while上;
//https://techtavern.wordpress.com/2008/07/16/whats-this-ioexception-write-end-dead/
public class PipedStreamClass {

	/**
	 * @throws InterruptedException 
	 * @throws IOException 
	 * 
	 * 模拟两个线程用一个管道输出流向另一个输入流输入数据
	 * 
	 * 程序编写的时候遇到以下问题：
	 * 1、读数据的时候报Write end dead，这是因为PipedInputStream读数据的时候如果写的线程死了就报这个错；然后我用了pos.wait使线程到阻塞状态
	 * 2、一开始没调用pos.close（），导致整个线程都是阻塞状态。那是因为管道输入流只有在输出流close时才会读到-1，不然一直阻塞状态
	 * 3、管道输出流和输入流不要同一个线程，会死锁的
	 * 4、管道输出流和输入流是相互阻塞的
	 * 
	 */
	@Test
	public void pipedStreamTest() throws InterruptedException{
		CountDownLatch countDownLatch=new CountDownLatch(3);
		CountDownLatch countDownLatch1=new CountDownLatch(1);
		try(PipedInputStream  pis=new PipedInputStream();//读取流，参数是piped input buffer大小
		 PipedOutputStream pos=new PipedOutputStream(pis)){//传参了就两个管道流进行连接
			//pis.connect(pos);//也可以创建两个无参的管道流对象，然后用connect进行连接；无论如何不连接可能不能用;另外已经练了的就不要重复练了，也会报错
			for(int i=0;i<2;i++) {
				new Thread(new Runnable() {					
					@Override
					public  void run() {
						synchronized (pos) {
							try {
								pos.write(("线程名称："+Thread.currentThread().getName()).getBytes());
								synchronized (countDownLatch) {
									countDownLatch.countDown();
								}	
								if(countDownLatch.getCount()==1) {
						        	  pos.close();//设置closeByWriter=false,告诉输入流已经输出结束，不然输入流一直阻塞所以countDown不会在close之前变成0
						         }
								while(countDownLatch.getCount()!=0) {
								    try {  
									      pos.wait();//如果在输入流读取之前，输出流所依附的线程都死了，会报错，所以阻塞一下
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								//pos.notifyAll();不能写在这，因为线程一直阻塞了根本运行不到下面，在其他线程中唤醒
                                countDownLatch1.countDown();
							} catch (IOException e) {
								e.printStackTrace();
							}	
						}	
					}
				},("writer"+i)).start();
			}
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					byte[] outByte=new byte[1024];
					@SuppressWarnings("unused")
					int b=0;
					try {
						while((b=pis.read(outByte))>0) {
							System.out.println(new String(outByte));
						}
						countDownLatch.countDown();
						synchronized (pos) {
							pos.notifyAll();//这个唤醒线程，不然两个输出流一直是阻塞状态。
						}		
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			},"read").start();	
		   countDownLatch1.await();//主程序等线程跑完在结束
		}catch (Exception e) {
			
		}
	
	}
}

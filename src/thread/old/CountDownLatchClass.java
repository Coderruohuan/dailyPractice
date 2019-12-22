package thread.old;

import java.util.concurrent.CountDownLatch;

/**
 * @see CountDownLatch
 * @author wwn
 * <tbody>countDownLatch允许一个或者多个线程等待其他线程完成操作
  * 创建CountDownLatch对象时会初始化一个计数器Sync，每次调用countDown()，相应的计数器也会减1，
  * 当计数器减至0时，等待的线程就会执行了，不然就会一直阻塞。
 *  
 *
 */
public class CountDownLatchClass {
	public static void main(String[] args) throws InterruptedException {
		ThreadOrder threadOrder=new ThreadOrder();
		//先启动3
		new Thread(new Runnable() {		
			@Override
			public void run() {
				try {
					threadOrder.printThird();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();	
		
		new Thread(new Runnable() {		
			@Override
			public void run() {
				try {
					threadOrder.printSecode();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();		
		
		//最后启动1
		Thread.sleep(3000);
		new Thread(new Runnable() {
			@Override
			public void run() {
				threadOrder.printFirst();				
			}
		}).start();
	}	
}

class  ThreadOrder {	
	private CountDownLatch countDownLatch=new CountDownLatch(1);
	private CountDownLatch countDownLatch2=new CountDownLatch(1);
	
	public void printFirst() {
		System.out.println("first is  printed....");
		countDownLatch.countDown();
	}
	
	public void printSecode() throws InterruptedException {
		countDownLatch.await();//等待countDownLatch减至0就会被唤醒。
		System.out.println("second is printed......");
		countDownLatch2.countDown();
	}
	public  void printThird() throws InterruptedException {
		countDownLatch2.await();
		System.out.println("third is printed.....");
	}

}
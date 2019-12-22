package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

/**
 * @see Object#wait()
 * @see Object#notify()
 *<br>调用a.wait()造成当前线程B（此时线程B拥有对象a的锁）阻塞,释放对象a的对象锁，直到另一个线程C调用a的notify或notifyAll方法
 * 打断线程B阻塞的几种情况
 * <li> 其他线程调用对象a的notify和notifyAll对象
 * <li> 其他线程调用了interrupt
 * <li> 等待时间到了
 * 
 *  <summary>
 *   1、wait，notify必须在获取对象monitor才能执行
 *   2、执行完wait，当前线程释放锁，然后进入等待队列
 *   3、当判断某种条件不满足时，调用wait使线程进入阻塞态，当线程被唤醒时继续执行，条件判断要用while，不能用if，因为已经执行了if，就不会再去判断条件
 *  </summary>
 *
 * @author wwn
 *
 */
public class WaitNotifyClass {
	
	@Test
	public void waitTest() throws InterruptedException {
		Object obj=new Object();
		Lock lock=new ReentrantLock();
		Thread  thread1= new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("线程开始执行.....");
				try {
					synchronized (obj) {
						obj.wait();
					}	
//					lock.tryLock();
//					obj.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					lock.unlock();
					System.out.println("线程执行结束.....");
				}	
			}
		});
		thread1.start();
		Thread.sleep(3000);
		new Thread(new Runnable() {		
			@Override
			public void run() {
				thread1.interrupt();//方式1：用thread1.interrupt打断thread1的wait	
			}
		}).start();
		Thread.sleep(4000);		
		synchronized (obj) {
		    System.out.println("main 调用notify....");
			obj.notify(); //方式2：用notify或者notifyAll打断thread1的wait
			System.out.println("main 调用结束....");
		}		
	}
	
	
	 

}

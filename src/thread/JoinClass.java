package thread;
/**
 * @see Thread#join()
 * @author wwn
 * <tbody>join的实现原理是调用wait/notifyAll，wait是挂起当前正在执行的线程（注意不是挂起调用的线程对象，
 * 比如在线程main中调用线程t.join(),此时是线程main 获取t对象，阻塞的是线程main），在t线程执行完成后，底层会调用notifyAll
 * 多线程按序输出可以用join
 * </tbody>
 *  <li> 1、join底层是wait实现，可一下为什么不用notify就可以唤醒？
 *        object.c——>synchronizer.cpp
 *  <li> 2、由1可知为什么在线程执行时调用当前线程的join会死锁
 *  
 *  
 *  
 */
public class JoinClass {
     public static void main(String[] args) throws InterruptedException {
    	 Thread myThread=new Thread(new Runnable() {
			@Override
			public void run() {
			    System.out.println("myThread 开始执行....");
			  try {
				  Thread.sleep(9000);
				  System.out.println("myThread 执行完成......");
			    } catch (InterruptedException e) {
				 e.printStackTrace();
			   }	
			}
		});
    	 myThread.setName("myThread");
    	 myThread.start();
     	 new  Thread(new Runnable() {
 			@Override
 			public void run() {
 				System.out.println("otheThread 执行开始....");
 				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
 				System.out.println("otherThread 执行完成....");
 			}
 		}).start();
     	//不影响前面启动的线程，但是影响后面启动的线程，因为主线程被挂起，就不能调用线程的start方法了
    	myThread.join();
    	//Thread.currentThread().join();//调用死锁 
    	System.out.println("main 执行完成");		
	}
}

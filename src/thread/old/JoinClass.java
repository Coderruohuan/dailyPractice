package thread.old;
/**
 * @see Thread#join()
 * @author wwn
 * <P>join的实现原理是调用wait/notifyAll，wait是挂起当前正在执行的线程（注意不是挂起调用的线程对象，
 * 比如在线程main中调用线程t.join(),此时是线程main 获取t对象，阻塞的是线程main），在t线程执行完成后，底层会调用notifyAll
 * 多线程按序输出可以用join
 *
 */
public class JoinClass {
     public static void main(String[] args) throws InterruptedException {
    	 Thread myThread=new Thread(new Runnable() {
			@Override
			public void run() {
			    System.out.println("myThread is started....");
			  try {
				  Thread.sleep(9000);
				  System.out.println("myThread is finished......");
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
 				System.out.println("other thread is started....");
 				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
 				System.out.println("other thread is finished....");
 			}
 		}).start();
    	 myThread.join();//不影响前面启动的线程，但是影响后面启动的线程，因为主线程被挂起，就不能调用线程的start方法了
    	 //Thread.currentThread().join();
    	 System.out.println("main is finished......");
			
	}
}

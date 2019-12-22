package thread;
/**
 * 测试synchronized修饰方法和静态方法的区别
 * (1)线程1,2调用myThread.print1()=====顺序执行
 * (2)线程1调用myThread.print1()，线程2调用myThread2.print1();==========交替执行
 * (3)线程1调用myThread.print2(),线程2调用myThread2.print2();========顺序执行
 * (4)线程1调用myThread.print1()，线程2调用myThread.print2()====交替执行
 * 
 * 
 * 结论：假设现在有普通methodA和静态methodB被synchronized修饰
 *   <li>普通方法锁住的是对象，静态方法锁的是类对象（class对象）
 *   <li>分别调用不同对象methodA，由于锁的是对象，所以不同对象不是同一把锁，不存在同步问题
 *   <li>分别调用不同对象methodB，一个线程会执行，另一个线程会阻塞
 *   <li>分别调用同一个对象methodA和methodB，线程同步执行，说明静态synchronized和普通方法的synchronized互不影响
 * 
 * @author wwn
 *
 */
public class SynchronizedOne {
	public static void main(String[] args) {
		MyThread myThread=new MyThread();
		@SuppressWarnings("unused")
		MyThread myThread2=new MyThread();
		//线程1
		Thread thread=new Thread(new Runnable() {	
			@Override
			public void run() {
				try {
					myThread.print1();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread.setName("线程1");
		thread.start();
		
	    //线程2
		Thread thread1=new Thread(new Runnable() {	
			@Override
			public void run() {
				try {
					MyThread.print2();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread1.setName("线程2");
		thread1.start();
	}	
}


class   MyThread{
	
	public synchronized void print1() throws InterruptedException {
		for(int i=0; i<10;i++) {
			System.out.println(Thread.currentThread().getName()+"正在执行普通Synchronized方法................"+i);
			Thread.sleep(2000);
		}
	}
	
	public synchronized static void print2() throws InterruptedException {
		for(int i=0; i<10;i++) {
			System.out.println(Thread.currentThread().getName()+"正在执行静态Synchronized方法============="+i);
			Thread.sleep(2000);
		}
	}
}
package thread.old;

import java.util.concurrent.CountDownLatch;

public class MyTest {
	CountDownLatch l1=new CountDownLatch(1);
	CountDownLatch l2=new CountDownLatch(1);

	public MyTest() {
	        
	    }

	public  void first(Runnable printFirst) throws InterruptedException {
		printFirst.run();
	    l1.countDown();
	}

	public  void second(Runnable printSecond) throws InterruptedException {
		 l1.await();
		 printSecond.run();
		 l2.countDown();
	}

	public   void third(Runnable printThird) throws InterruptedException {
		l2.await();
		printThird.run();
	}
}

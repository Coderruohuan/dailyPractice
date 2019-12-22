package thread.old;

import java.util.Date;

public class SynchronizedTest {
	public static void main(String[] args) {

		System.out.println(new Date(new Date().getTime() - 24 * 60 * 60 * 1000));
		SynchronizedClass synchronizedClass = new SynchronizedClass();
		Thread5 thread = new Thread5(synchronizedClass);
		thread.setName("线程1");
		Thread5 thread1 = new Thread5(synchronizedClass);
		thread1.setName("线程2");
		thread.start();
		thread1.start();
	}

}

class Thread5 extends Thread {
	private SynchronizedClass synchronizedClass;

	public Thread5(SynchronizedClass synchronizedClass) {
		this.synchronizedClass = synchronizedClass;
	}

	@Override
	public void run() {
		synchronizedClass.print();
	}
}

class Thread6 extends Thread {
	private SynchronizedClass synchronizedClass;

	public Thread6(SynchronizedClass synchronizedClass) {
		this.synchronizedClass = synchronizedClass;
	}

	@Override
	public void run() {
		synchronizedClass.print();
	}
}

class SynchronizedClass {
	private Wang wang;

	public SynchronizedClass() {
		wang = new Wang();
	}

	public void print() {
		// Wang wang = new Wang(); 这样没用，因为创建了两个B,不存在同步问题
		// System.out.println(wang);
		synchronized (wang) {
			// synchronized（对象）也只有对 对象的synchronized修饰的对象或代码块起作用；下面的方法也是异步的
			System.out.println(Thread.currentThread().getName() + "已经进入....");
			wang.write();
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
			}
			System.out.println(Thread.currentThread().getName() + "print执行完");
		}

	}
}

class Wang {
	public void write() {
		System.out.println(Thread.currentThread().getName() + " nothing .......");
		if ("线程1".equals(Thread.currentThread().getName())) {
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
			}
		}

		System.out.println(Thread.currentThread().getName() + " write执行完毕....");
	}
}
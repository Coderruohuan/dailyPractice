package thread.old;

public class ThreadMethod {
	public static void main(String[] args) {
		SynchronizedObject object = new SynchronizedObject();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("线程A调用printString......");
				object.printString();
			}
		}, "A");
		thread.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("线程B调用printString......");
				object.printString();
			}
		}, "B");
		thread2.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("现在使线程A恢复");
		thread.resume();

	}
}

class SynchronizedObject {
	synchronized public void printString() {
		System.out.println("线程执行synchronize的printString方法.......");
		if ("A".equals(Thread.currentThread().getName())) {
			System.out.println("线程A暂停");// 暂停后不会释放锁
			Thread.currentThread().suspend();
			System.out.println("线程A恢复了");
		}
	}
}

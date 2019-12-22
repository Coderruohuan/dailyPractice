package thread.old;

public class ThreadClass1 {
	public static void main(String[] args) {
		MyThread thread = new MyThread();
		Thread thread1 = new Thread(thread);
		Thread thread2 = new Thread(thread);
		Thread thread3 = new Thread(thread);
		thread1.start();
		thread2.start();
		thread3.start();

	}

}

class MyThread implements Runnable {
	private int count = 6;

	@Override
	public void run() {
		for (int i = 0; i < 6; i++) {
			count--;
			System.out.println("the run thread is " + Thread.currentThread().getName() + ",now count is:" + count);
		}
	}

}

package thread.old;

/**
 * 制造死锁
 * 
 * @author Administrator
 *
 */
public class DeadThreadTest {
	public static void main(String[] args) {
		DeadThread thread = new DeadThread();
		thread.setUsername("a");
		Thread thread1 = new Thread(thread);
		thread1.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		thread.setUsername("b");
		Thread thread2 = new Thread(thread);
		thread2.start();

	}

}

class DeadThread implements Runnable {
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public void run() {
		if ("a".equals(this.username)) {
			synchronized (lock1) {
				System.out.println("a测试一下");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
				}
				synchronized (lock2) {
					System.out.println("a测试两下");
				}
			}

		}

		if ("b".equals(this.username)) {
			synchronized (lock2) {
				System.out.println("b测试一下");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
				}
				synchronized (lock1) {
					System.out.println("b测试两下");
				}
			}

		}

	}
}
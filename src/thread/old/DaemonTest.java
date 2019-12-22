package thread.old;

public class DaemonTest {

	public static void main(String[] args) {
		DaemonThread daemon = new DaemonThread();
		Thread thread = new Thread(daemon);
		// thread.setDaemon(true);
		thread.start();
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
		}
	}

}

class DaemonThread implements Runnable {
	@Override
	public void run() {
		try {
			System.out.println("这是执行的开始....");
			Thread.sleep(4000);
			System.out.println("睡眠结束....");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("这是finally....");
		}
	}
}

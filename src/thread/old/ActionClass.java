package thread.old;

public class ActionClass {
	private int i;

	public void print() throws InterruptedException {
		synchronized (this) {
			for (; i < 10; i++) {
				System.out.println(Thread.currentThread().getName() + ":" + i);
			}
		}
	}

}

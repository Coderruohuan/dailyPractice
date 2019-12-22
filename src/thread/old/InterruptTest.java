package thread.old;

public class InterruptTest {
	public static void main(String[] args) {
		Thread thread = new Thread(new InterruptClass());
		thread.start();
		thread.interrupt();
	}
}

class InterruptClass extends Thread {
	@Override
	public void run() {
		int i = 1000;
		for (; i > 0; i--) {
			if (Thread.currentThread().isInterrupted()) {
				System.out.println("当i为" + i + "时，线程已被标志为中断");
				System.out.println("currentThread:" + Thread.currentThread().getName() + "，该线程现在的状态是："
						+ Thread.currentThread().isInterrupted());
				System.out.println("this:" + this.getName() + " interrupt:" + this.isInterrupted());
			}
			if (Thread.interrupted()) {
				System.out.println("i:" + i + "  线程状态是：" + Thread.currentThread().isInterrupted());
			}

		}

	}

}

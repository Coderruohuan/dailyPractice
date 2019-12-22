package thread.old;

public class ThreadPriority {
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + "的优先级是：" + Thread.currentThread().getPriority());
		Thread1 thread = new Thread1();
		thread.setName("A");
		thread.start();

	}

}

class Thread1 extends Thread {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "的优先级是：" + Thread.currentThread().getPriority());
		Thread2 thread = new Thread2("B");
		thread.start();
		System.out.println("没设置之前" + thread.getName() + "的优先级是：" + thread.getPriority());
		Thread.currentThread().setPriority(8);
		System.out.println(Thread.currentThread().getName() + "的优先级是：" + Thread.currentThread().getPriority());
	}
}

class Thread2 extends Thread {
	public Thread2(String name) {
		super(name);
	}

	@Override
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("设置之后" + Thread.currentThread().getName() + "的优先级是：" + Thread.currentThread().getPriority());
	}
}

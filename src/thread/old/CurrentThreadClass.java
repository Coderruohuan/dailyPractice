package thread.old;

public class CurrentThreadClass {
	public static void main(String[] args) {

	}

}

class ThreadClass2 extends Thread {
	public ThreadClass2() {
		System.out.println(
				"this currentThread is:" + Thread.currentThread().getName() + Thread.currentThread().isAlive());
		System.out.println("this currentThread is:" + this.getName() + this.isAlive());
		this.interrupt();

	}

	@Override
	public void run() {
		System.out.println("this is a ...");

	}

}

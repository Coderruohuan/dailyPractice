package thread.old;

public class ThreadSafeClass {

	public static void main(String[] args) {
		MyTest12 myTest = new MyTest12();
		Thread4 thread1 = new Thread4(myTest);
		thread1.setName("a");
		Thread4 thread2 = new Thread4(myTest);
		thread2.setName("b");
		thread1.start();
		thread2.start();

	}

}

class Thread4 extends Thread {
	private MyTest12 myTest;

	public Thread4(MyTest12 myTest) {
		this.myTest = myTest;
	}

	@Override
	public void run() {
		myTest.doSomething(this.getName());
	}
}

class MyTest12 {
	// 静态变量
	private static int staticNum;
	// 成员变量
	private int fieldNum;

	public void doSomething(String name) {
		int localNum = 0;
		if ("a".equals(name)) {
			staticNum = 1;
			localNum = 1;
			fieldNum = 1;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			staticNum = 2;
			localNum = 2;
			fieldNum = 2;
		}
		System.out.println(
				"name:" + name + ",staticNum:" + staticNum + ",localNum:" + localNum + ",fieldNum:" + fieldNum);

	}

}

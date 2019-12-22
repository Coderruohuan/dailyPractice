package thread.old;

public class NotifyClassTest {

	public static void main(String[] args) {
		String str = new String("xewrwrw");
		try {
			synchronized (str) {
				// 不加这句会报Exception in thread "main"
				// java.lang.IllegalMonitorStateException
				str.wait();
				str.notify();
			}
			System.out.println("测试一下");
		} catch (InterruptedException e) {
		}

	}
}

package thread.old;

import java.util.concurrent.TimeUnit;

public class ThreadTest1 {

	public static void main(String[] args) {

		final MySignAll mySignAll = new MySignAll();

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("1--run" + System.currentTimeMillis());
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				mySignAll.setHasProcess(true);

				System.out.println("1---end" + mySignAll.hasProcess + System.currentTimeMillis());
				while (true && mySignAll.hasProcess) {
					try {
						TimeUnit.SECONDS.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				System.out.println("1--->" + mySignAll.hasProcess);

			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("2xxxxxx");
				while (true && !mySignAll.hasProcess) {
					// System.out.println("2--run" + System.currentTimeMillis());
					// try {
					// Thread.sleep(10);
					// } catch (Exception e) {
					// e.printStackTrace();
					// }

					if (mySignAll.hasProcess) {
						System.out.println(mySignAll.hasProcess);
						// System.out.println(mySignAll.hasProcess());
						//
						System.out.println("2---go" + System.currentTimeMillis());
						mySignAll.setHasProcess(false);
					}
					// System.out.println("2---end" + System.currentTimeMillis());
				}
			}
		}).start();

	}

	static class MySignAll {

		public boolean hasProcess = false;

		public synchronized boolean hasProcess() {
			return hasProcess;
		}

		public void setHasProcess(boolean hasProcess) {
			this.hasProcess = hasProcess;
		}

	}

}
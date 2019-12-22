package thread.old;

public class ThreadTest {
	public static void main(String[] args) throws InterruptedException {
		MyTest test=new MyTest();	
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					test.second(new Runnable() {
						
						@Override
						public void run() {
							System.out.print("second...");	
						}
					});
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					test.third(new Runnable() {
						@Override
						public void run() {
							System.out.print("third...");
							
						}
					});
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
		Thread.sleep(1000);
		new Thread(new Runnable() {
			@Override
			public void run() {
			   try {
				test.first(new Runnable() {
					@Override
					public void run() {
						System.out.print("first...");	
					}
				});
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   }
		}).start();
	}

}

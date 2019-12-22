package thread.old;

public class VolatileTest {
	public static void main(String[] args) {
		VolatileClass volatileClass = new VolatileClass();
		new Thread(volatileClass).start();
	}

}

class VolatileClass implements Runnable {
	private Boolean flag = true;

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		while (flag) {
			System.out.println("running..");
		}
	}

}
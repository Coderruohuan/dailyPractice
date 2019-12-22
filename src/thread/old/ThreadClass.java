package thread.old;

public class ThreadClass extends Thread {

	private ActionClass actionClass;

	public ThreadClass(ActionClass actionClass) {
		this.actionClass = actionClass;
	}

	@Override
	public void run() {
		try {
			actionClass.print();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

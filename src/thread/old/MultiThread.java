package thread.old;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class MultiThread {

	public static void main(String[] args) {
		ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
		ThreadInfo[] infos = mxBean.getThreadInfo(mxBean.getAllThreadIds());
		for (ThreadInfo info : infos) {
			System.out.println(info.getThreadName());
		}

	}

}

package thread;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

/**
 * @see synchronized
 * 
 * <li>synchronized 的锁存在于Java对象头里，叫对象监视器，
 * <li>synchronized是依赖于看jvm虚拟机实现同步，底层是依赖于操作系统Mutex Lock（互斥锁）来实现的
 * <li> 同步代码块相对应的字节码指令是monitorenter和monitorexit，同步方法是通过ACC_SYNCHRONIZED标识符来实现
 * <li> 静态方法加synchronized和synchronized(class) 都是锁的class类对象，同一个类不同的对象也会锁住
 * <li> 普通方法加synchronized和synchronized(this)都是锁的对象，如果创建的对象不同，不存在锁竞争问题
 * @author wwn
 *
 */
public class SynchronizedClass {	
	@Test
	public  void synchronizedTest() throws InterruptedException{
		int total=10000;
		CountDownLatch cl=new CountDownLatch(total);//等待减到0，才会从阻塞到准备
             NumCount numCount=new NumCount();
             for(int i=0;i<total;i++) {
            	 new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(1000);
							numCount.addCount();
							numCount.addSynCount();
							numCount.addVcount();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						cl.countDown();
					}		
				}).start();
             }
             cl.await();
             //输出（随机）：此时count值是：9985;synCount值是:10000;vCount值是：9999
             System.out.println("此时count值是："+numCount.getCount()+";synCount值是:"+numCount.getSynCount()+";vCount值是："+numCount.getvCount());
	}

}

class NumCount{
	private int synCount;
	private int count;
	private volatile int vCount;
	
	public int getSynCount() {
		return synCount;
	}

	public void setSynCount(int synCount) {
		this.synCount = synCount;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getvCount() {
		return vCount;
	}

	public synchronized void setvCount(int vCount) {
		this.vCount = vCount;
	}

	public  void addCount() {
		count++;
	}
	
	public  void addSynCount() {
		synchronized (this) {
			synCount++;		
		}		
	}
	
	public void addVcount() {
		vCount++;
	}
}


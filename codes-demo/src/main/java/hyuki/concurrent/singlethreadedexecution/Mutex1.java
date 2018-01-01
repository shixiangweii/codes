package hyuki.concurrent.singlethreadedexecution;

/**
 * 可重入 自己打开自己的锁，别人打不开，也打不开别人的
 * 
 * @author SXW
 *
 */
public class Mutex1 {
	private long locks = 0L; // 枷锁的层数
	private Thread owner = null;// 所有者

	/**
	 * 监视器模式 使用对象锁保护字段
	 */
	public synchronized void lock() {
		// 当前线程
		Thread me = Thread.currentThread();
		// 如果当前枷锁层数不为0 并且不是当前线程的锁
		while (locks > 0 && owner != me) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 层数为0 或者 当前线程是锁的持有者
		assert locks == 0 || owner == me;
		// 更新持有者
		owner = me;
		// 层数++
		locks++;
	}

	/**
	 * 监视器模式 使用对象锁保护字段
	 */
	public synchronized void unlock() {
		// 当前线程
		Thread me = Thread.currentThread();

		// 当前枷锁层数为0 或者 当前线程不是所有者
		if (locks == 0 || owner != me) {
			return;
		}

		// 层数不为0 并且 当前线程是锁的所有者
		assert locks > 0 && owner == me;
		locks--;
		// 解锁，层数解为0了
		if (locks == 0) {
			owner = null;
			notifyAll();
		}
	}
}

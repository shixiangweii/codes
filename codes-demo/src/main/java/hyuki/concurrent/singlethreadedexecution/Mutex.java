package hyuki.concurrent.singlethreadedexecution;

/**
 * 最初版本
 * 
 * @author SXW
 *
 */
public class Mutex {
	// 标记
	private boolean busy = false;

	/**
	 * 加锁 用监视器模式，对象锁，保护字段busy
	 */
	public synchronized void lock() {
		// *不可重入，光判断busy字段，第二次会进入wait
		// 自己把自己锁在外面
		while (busy) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		busy = true;
	}

	/**
	 * 对外public
	 * 即使没有调用lock，也可以调用unlock
	 * 不是自己上的锁，自己也可以打开
	 * 自己上的锁，别人也可以打开
	 */
	public synchronized void unlock() {
		busy = false;
		notifyAll();
	}
}

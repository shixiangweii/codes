package hyuki.concurrent.singlethreadedexecution;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 信号量使用
 * 
 * 总的来说，Dong Lea已经为我们提供了大量的可用的concurrent包
 * 大大简化的并发编程的编码设计
 * 所以自己要做的是学习其中的思想，首先学会使用它们！
 * 
 * @author SXW
 *
 */
public class Main {

	public static void main(String[] args) {
		// 3个资源
		BoundedResource resource = new BoundedResource(3);
		// 10个线程
		for (int i = 0; i < 10; i++) {
			new UserThread(resource).start();;
		}
	}
}

class Log {
	public static void println(String s) {
		System.out.println(Thread.currentThread().getName() + ":" + s);
	}
}

// 资源个数有限
class BoundedResource {
	// 其实放在这里，自己第一反应是吃惊的，自己可能会放在main，或者线程里面
	// 限制性的资源，可以把信号量当作属性字段，封装在一起体现资源的限制性
	
	private final Semaphore semaphore;// *重要
	private final int permits;
	private final static Random random = new Random(383820932);
	
	public BoundedResource(int permits) {
		semaphore = new Semaphore(permits);
		this.permits = permits;
	}
	
	public void use() throws InterruptedException {
		semaphore.acquire();
		try {
			doUse();
		} finally {
			semaphore.release();
		}
	}
	
	protected void doUse() throws InterruptedException {
		Log.println("BEGIN: used = " + (permits - semaphore.availablePermits()));
		Thread.sleep(random.nextInt(500));
		Log.println("END  : used = " + (permits - semaphore.availablePermits()));
	}
}

class UserThread extends Thread {
	private final static Random random = new Random(223);
	private final BoundedResource resource;

	public UserThread(BoundedResource resource) {
		this.resource = resource;
	}

	@Override
	public void run() {
		try {
			while (true) {
				resource.use();
				Thread.sleep(random.nextInt(3000));
			}
		} catch (InterruptedException e) {

		}
	}

}




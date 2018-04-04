package hyuki.concurrent.twophasetermination;

import hyuki.concurrent.Concurrent;
import hyuki.concurrent.Frozen;

public class CountupThread extends Thread {
	private long counter = 0L;
	// 一旦被设置为true后就不会再变为false的“闭锁”
	private volatile boolean shutdownRequested = false;

	@Concurrent
	public void shutdownRequest() {
		// 一旦被设置为true后就不会再变为false的“闭锁”
		shutdownRequested = true;
		
		// 中断，确保在sleep,wait时候也会被终止，提高响应性
		// 不进行中断，只修改标记：
		// 场景一：如果线程sleep，那么等sleep时间过，也会开始终止处理，只是响应性降低了
		// 场景二：如果线程wait，即使改变了标记，线程也不会从等待队列中出来，所以此时必须使用中断
		super.interrupt();
	}

	@Concurrent
	public boolean isShutdownRequested() {
		return shutdownRequested;
	}

	@Frozen
	@Override
	public final void run() {
		try {
			while (!isShutdownRequested()) {
				doWork();
			}
		} catch (InterruptedException e) {
		} finally {// 确保进行终止处理
			doShutdown();
		}
	}

	private void doWork() throws InterruptedException {
		counter++;
		System.out.println("doWork: counter = " + counter);
		Thread.sleep(500);
	}

	private void doShutdown() {
		System.out.println("doShutdown: counter = " + counter);
	}

}

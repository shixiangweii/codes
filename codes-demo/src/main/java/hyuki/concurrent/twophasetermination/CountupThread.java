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
		} finally {
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

package hyuki.concurrent.twophasetermination;

/**
 * 一、未捕获的异常的处理器 二、退出钩子
 * 
 * @author SXW
 *
 */
public class Main2 {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread());
		System.out.println("main:BEGIN");

		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("****");
				System.out.println("UncaughtExceptionHandler:BEGIN");
				System.out.println("CurrentThread = " + Thread.currentThread());
				System.out.println("thread = " + t);
				System.out.println("exception = " + e);
				System.out.println("UncaughtExceptionHandler:END");
			}
		});

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("****");
				System.out.println("shutdown hook:BEGIN");
				System.out.println("currentThread = " + Thread.currentThread());
				System.out.println("shutdown hook:END");
			}
		}));

		new Thread("MyThread") {
			public void run() {
				System.out.println("MyThread:BEGIN");
				System.out.println("MyThread:SLEEP...");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("MyThread:DIVIDE");
				int x = 1 / 0;
				System.out.println(x);
				System.out.println("MyThread:END");
			}
		}.start();

		System.out.println("main:END");
	}

}

package hyuki.concurrent.twophasetermination;

/**
 * 如何正确结束线程
 * Two-phase Termination
 * @author SXW
 *
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("main: BEGIN");
		try{
			CountupThread t = new CountupThread();
			t.start();
			Thread.sleep(10000);
			System.out.println("main: shutdownRequest");
			t.shutdownRequest();
			System.out.println("main: join");
			// 等待线程终止
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("main: END");
	}

}

package hyuki.concurrent.singlethreadedexecution;

public class Gate {
	private int counter = 0;
	private String name = "Nobody";
	private String address = "Nowhere";

	private final Mutex mutex = new Mutex();
//	private final Mutex1 mutex = new Mutex1();
//	private final Mutex2 mutex = new Mutex2();

	public void pass(String name, String address) {
		mutex.lock();
		try {
			this.counter++;
			this.name = name;

			// 使用yield，是线程不安全加速暴露
			Thread.yield();

			this.address = address;
			check();
		} finally {
			mutex.unlock();
		}
	}

	@Override
	public String toString() {
		String s = null;
		mutex.lock();
		try {
			s = "No." + counter + ":" + name + "," + address;
		} finally {
			mutex.unlock();
		}
		return s;
	}

	public void check() {
		if (name.charAt(0) != address.charAt(0)) {
			System.out.println("***** BROKEN *****" + toString());
		}
	}
}

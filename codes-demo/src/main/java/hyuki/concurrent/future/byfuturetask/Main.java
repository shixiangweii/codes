package hyuki.concurrent.future.byfuturetask;
/**
 * Future模式例子
 * @author SXW
 * Main，调用者，相当于“客户”
 */
public class Main {

	public static void main(String[] args) {

		// 也体现了OOP的优点，虽然底层用FutureTask重写了，但此处并不受影响
		// 不用改动
		
		Host host = new Host();

		Data data1 = host.request(1);

		Data data2 = host.request(2);

		System.out.println("do other things");

		String content1 = data1.getContent();
		String content2 = data2.getContent();

		System.out.println(content1);
		System.out.println(content2);
	}

}

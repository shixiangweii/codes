package hyuki.concurrent.future;
/**
 * Future模式例子
 * @author SXW
 * Main，调用者，相当于“客户”
 */
public class Main {

	public static void main(String[] args) {

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

package hyuki.concurrent.future;

import java.util.concurrent.ExecutionException;

/**
 * Future模式例子
 * @author SXW
 * Main，调用者，相当于“客户”
 */
public class Main {

	public static void main(String[] args) throws ExecutionException {

		Host host = new Host();

		Data data1 = host.request(1);

		Data data2 = host.request(2);

		System.out.println("do other things");

		String content1 = data1.getContent();// main抛异常的话data1执行完就退出程序了
		String content2 = data2.getContent();

		System.out.println(content1);
		System.out.println(content2);
	}

}

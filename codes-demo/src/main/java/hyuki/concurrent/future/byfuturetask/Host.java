package hyuki.concurrent.future.byfuturetask;

import java.util.concurrent.Callable;

/**
 * 客户请求的对象
 * 具体生成"提货单"&启动"考蛋糕"线程的对象
 * @author SXW
 *
 */
public class Host {
	
	/**
	 * 执行客户的请求
	 * @return
	 * 		注意，返回的实例实际是“提货单”，但用统一的数据接口Data
	 */
	public Data request(final int count){
		
		// "提货单"
		final FutureData future = new FutureData(new Callable<RealData>() {
			// “匿名类”
			// 封装具体的返回结果，和业务操作逻辑
			@Override
			public RealData call() throws Exception {
				RealData realdata = new RealData(count);
				return realdata;
			}
		});

		
		// 开启线程执行
		// 在新开的线程中callable中的逻辑就是普通的照着单线程的方式执行的
		new Thread(future).start();
		
		// 返回提货单
		return future;
	}
	
}

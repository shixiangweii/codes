package hyuki.concurrent.future;
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
		final FutureData future = new FutureData();

		// 启动“烤蛋糕线程”
		new Thread(new Runnable() {
			@Override
			public void run() {
				RealData realdata = new RealData(count);
				
				// 回填
				future.setRealData(realdata);
			}
		}).start();
		
		// 返回提货单
		return future;
	}
	
}

package hyuki.concurrent.future.byfuturetask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * “提货单”，“提货的票据”
 * @author SXW
 * 
 * 使用java.concurrent.FutureTask
 * 来作为模式中的"Future"角色
 */
public class FutureData extends FutureTask<RealData> implements Data {// *继承

	/**
	 * 构造方法
	 * 使用Callable，把具体的执行逻辑和返回值再做一层封装
	 * @param callable
	 */
	public FutureData(Callable<RealData> callable) {// *重要
		super(callable);
	}

	@Override
	public String getContent() {// *重要
		String string = null;
		
		try {
			
			// 获取结果
			RealData realData = super.get();
			string = realData.getContent();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		return string;
	}
		
}

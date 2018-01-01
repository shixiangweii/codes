package hyuki.concurrent.future.byfuturetask;

/**
 * 真实的数据，
 * “烤好的蛋糕”
 * @author SXW
 *
 */
public class RealData implements Data {
	
	private final String content;
	
	
	public RealData(int count) {
		
		// 把“耗时”的计算过程放在构造中，确保，对外的getContent调用时候
		// 结果数据已经是计算好了的
		
		content = "Business Data Content";
		// 模拟耗时的业务逻辑操作
		try {
			Thread.sleep(3 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public String getContent() {
		// XXX:此处不是有具体的计算逻辑，这个接口就是单纯的返回计算结果
		// 自己一开始，确实没有注意到这一点
		/*
		content = "Business Data Content";

		// 模拟耗时的业务逻辑操作
		try {
			Thread.sleep(3 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
		
		// 返回计算结果
		return content;
	}

}

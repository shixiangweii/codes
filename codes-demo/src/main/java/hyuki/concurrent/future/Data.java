package hyuki.concurrent.future;
/**
 * 业务逻辑的数据获取接口
 * 用来统一“提货单”future和"真实"数据realData的数据操作
 * @author SXW
 *
 */
public interface Data {
	/**
	 * 返回结果数据
	 * @return
	 */
	String getContent();
}

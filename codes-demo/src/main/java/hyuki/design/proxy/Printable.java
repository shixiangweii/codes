package hyuki.design.proxy;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-04-08
 * Time: 11:12
 *
 * Subject(主体)角色
 */
public interface Printable {
    /**
     * 设置打印机名字
     * @param name
     */
    void setPrinterName(String name);

    /**
     * 获取打印机名字
     * @return
     */
    String getPrinterName();

    /**
     * 打印
     * @param string
     */
    void print(String string);
}

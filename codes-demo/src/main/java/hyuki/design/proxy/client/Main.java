package hyuki.design.proxy.client;

import hyuki.design.proxy.Printable;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-04-08
 * Time: 10:55
 *
 * 代理模式
 */
public class Main {
    /**
     * Client角色，请求者
     * @param args
     */
    public static void main(String[] args) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException {

        System.out.println(args[0]);


        // 使用反射解耦，Client角色中就不用在依赖特定的，代理人，和实际的主体，而是
        // 依赖主体的接口
        Class<?> c=Class.forName(args[0]);
        Printable p = (Printable) c.newInstance();

        // Printable p = new PrinterProxy("Alice");

        System.out.println("现在的名字是" + p.getPrinterName() + "。");
        p.setPrinterName("Bob");
        System.out.println("现在的名字是" + p.getPrinterName() + "。");
        p.print("Hello, world.");
    }

}

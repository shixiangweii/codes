package hyuki.design.proxy;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-04-08
 * Time: 11:24
 * 角色： 代理人Proxy
 */
public class PrinterProxy implements Printable {

    private String name = "DefaultName";

    /**
     * 使用委托
     */
    private Printer real;

    public PrinterProxy(){}

    public PrinterProxy(String name){
        this.name = name;
    }

    @Override
    public synchronized void setPrinterName(String name) {
        if(real!=null){
            real.setPrinterName(name);
        }
        this.name=name;
    }

    @Override
    public String getPrinterName() {
        return this.name;
    }

    @Override
    public void print(String string) {
        realize();
        real.print(string);
    }

    private synchronized void realize(){
        if(real==null){
            real = new Printer(name);
        }
    }
}

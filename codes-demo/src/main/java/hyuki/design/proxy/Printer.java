package hyuki.design.proxy;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-04-08
 * Time: 11:16
 *
 * 角色： RealSubject(实际的主体)
 *
 */
public class Printer implements Printable {

    private String name = "DefaultName";

    public Printer(){
        heavyJob("正在生成Printer实例");
    }

    public Printer(String name){
        this.name=name;
        heavyJob("正在生成Printer实例,name="+name);
    }

    @Override
    public void setPrinterName(String name) {
        this.name = name;
    }

    @Override
    public String getPrinterName() {
        return name;
    }

    @Override
    public void print(String string) {
        System.out.println("==="+name+"===");
        System.out.println(string);
    }

    private void heavyJob(String msg){
        System.out.println(msg);
        for (int i=0;i<5;i++){
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {}
            System.out.print(".");
        }
        System.out.println("结束。");
    }
}

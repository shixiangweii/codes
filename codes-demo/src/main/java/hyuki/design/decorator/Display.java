package hyuki.design.decorator;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-04-10
 * Time: 15:06
 * 角色：被装饰者、Component
 */
public abstract class Display {
    public abstract int getColumns();
    public abstract int getRows();
    public abstract String getRowText(int row);

    /**
     * Template Method
     * 模板方法模式
     */
    public final void show() {
        for (int i = 0; i < getRows(); i++) {
            System.out.println(getRowText(i));
        }
    }
}

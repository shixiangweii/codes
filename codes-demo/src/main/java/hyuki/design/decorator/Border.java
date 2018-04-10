package hyuki.design.decorator;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-04-10
 * Time: 15:34
 * 角色：装饰者，Decorator
 *
 */
public abstract class Border extends Display {
    /**
     * 表示被装饰者
     */
    protected Display display;

    protected Border(Display display) {
        this.display = display;
    }
}

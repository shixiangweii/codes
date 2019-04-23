package interview.abstractdemo.abstract2;

/**
 * @author shixiangweii
 * <p>
 * 重载的方法，可以是抽象的
 */
public abstract class MyClass {
    public int constInt = 5;

    /**
     * 有参
     *
     * @param a param
     */
    public abstract void method(int a);

    /**
     * 无参
     */
    public void method() {

    }
}
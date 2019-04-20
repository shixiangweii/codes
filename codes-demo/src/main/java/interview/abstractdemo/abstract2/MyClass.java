package interview.abstractdemo.abstract2;

public abstract class MyClass {
    public int constInt = 5;

    /**
     * 重载的方法，可以是抽象的
     *
     * @param a
     */
    public abstract void method(int a);

    public void method() {

    }
}
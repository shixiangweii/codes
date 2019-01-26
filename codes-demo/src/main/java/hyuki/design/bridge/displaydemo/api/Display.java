package hyuki.design.bridge.displaydemo.api;

import hyuki.design.bridge.displaydemo.api.impler.api.DisplayImpl;

/**
 * Description: 设计模式，注重的是逻辑模型，API,不一定非要是 具体的 interface.java
 * 逻辑模型上的接口API，具体的表现形式可以是抽象类，普通类，接口
 * User: shixiangweii
 * Date: 2019-01-26
 * Time: 20:49
 *
 * @author shixiangweii
 */
public class Display {
    private DisplayImpl impl;

    public Display(DisplayImpl impl) {
        this.impl = impl;
    }

    public void open() {
        impl.rawOpen();
    }

    public void print() {
        impl.rawPrint();
    }

    public void close() {
        impl.rawClose();
    }

    public final void display() {
        open();
        print();
        close();
    }
}

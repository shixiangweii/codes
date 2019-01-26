package hyuki.design.bridge.displaydemo.api.extender;

import hyuki.design.bridge.displaydemo.api.Display;
import hyuki.design.bridge.displaydemo.api.impler.api.DisplayImpl;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-01-26
 * Time: 21:02
 *
 * @author shixiangweii
 */
public class CountDisplay extends Display {
    public CountDisplay(DisplayImpl impl) {
        super(impl);
    }

    public void multiDisplay(int times) {
        open();
        for (int i = 0; i < times; i++) {
            print();
        }
        close();
    }
}

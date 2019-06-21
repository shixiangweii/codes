package interview.innerclass.demo7.server;

import interview.innerclass.demo7.api.InnerInterface;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-06-21
 * Time: 8:26
 *
 * @author shixiangweii
 */
public class OuterClass2 implements InnerInterface {
    /**
     * 这种并未完全，完全隐藏实现，通过继承冲洗
     * 因为无法，实现接口的时候，把访问权限变成private
     */
    @Override
    public void innerMethod() {

    }
}

class OuterSub extends OuterClass2 {
    @Override
    public void innerMethod() {
        super.innerMethod();
    }
}

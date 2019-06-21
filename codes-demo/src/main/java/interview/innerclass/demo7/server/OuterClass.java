package interview.innerclass.demo7.server;

import interview.innerclass.demo7.api.InnerInterface;

/**
 * https://blog.csdn.net/u013728021/article/details/87358517
 * 实现信息隐藏
 *
 * @author shixiangweii
 */
public class OuterClass {

    /**
     * private修饰内部类，实现信息隐藏
     * 比起OuterClass直接实现接口，private语法上更进一步的信息隐藏
     */
    private class InnerClass implements InnerInterface {

        @Override
        public void innerMethod() {
            System.out.println("实现内部类隐藏");
        }

    }

    public InnerInterface getInner() {

        return new InnerClass();

    }

    /**
     * 虽然是私有的，但是可以被外部类public返回，不冲突，只是外面的类调用这个方法，
     * 会提示"has private access"
     *
     * @return inner class
     */
    public InnerClass getOriginInner() {

        return new InnerClass();

    }

}

class Sub extends OuterClass {
    @Override
    public InnerInterface getInner() {
        // 重写也是调用父类
        return super.getInner();
    }

/*    @Override
    public OuterClass.InnerClass getOriginInner() {
        // 直接语法报错
        return super.getOriginInner();
    }*/
}
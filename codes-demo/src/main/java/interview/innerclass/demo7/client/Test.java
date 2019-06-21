package interview.innerclass.demo7.client;

import interview.innerclass.demo7.api.InnerInterface;
import interview.innerclass.demo7.server.OuterClass;

/**
 * @author shixiangweii
 */
public class Test {

    public static void main(String[] args) {

        OuterClass outerClass = new OuterClass();

        InnerInterface inner = outerClass.getInner();

        inner.innerMethod();

        // 语法错误，"InnerClass"是私有的，无法在外部呗被访问到
        // OuterClass.InnerClass originInner = outerClass.getOriginInner();
    }

}

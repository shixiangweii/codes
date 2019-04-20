package interview.innerclass.demo1;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-03-02
 * Time: 19:15
 *
 * @author shixiangweii
 */
public class TestDemo1 {

    /**
     * https://www.nowcoder.com/questionTerminal/fdaecbf8358e41db9d087be7a2babce1
     * 把内部类理解成类的成员，成员有4种访问权限吧，内部类也是！分别为private、protected、public以及默认的访问权限
     *
     *
     * https://www.nowcoder.com/questionTerminal/deec39e906544bf788593b4a5ede42db
     * 一个文件中，可以有多个public class
     * 即，外部类为public，还可以有public的内部类
     */
    public class AA {
    }

    ;

    class BB {
    }

    ;

    private class CC {
    }

    ;

    protected class DD {
    }

    ;

    abstract class EE {
    }

    /**
     * https://www.nowcoder.com/questionTerminal/964cf7d6cd29449f91f1dbe21bae8176
     */
    public void test() {
        // 局部内部类指位于块、构造器、方法内的有名称类，最多只能有final修饰
        final class A {

        }

        class B {

        }

        abstract class C {
            public void a() {}
        }
    }
}

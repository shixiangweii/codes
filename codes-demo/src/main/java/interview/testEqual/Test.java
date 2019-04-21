package interview.testEqual;

import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        // 这种写法，就是用匿名类的简写罢了
        Object o = new Object() {
            public boolean equals(Object obj) {
                return true;
            }
        };
        // 所以当然返回true
        System.out.println(o.equals("Fred"));

        Object test2 = new Test2();
        System.out.println(test2.equals(new HashMap<>()));
    }
}

/**
 * 虽然Object是所有类父类，这里显示继承下不会报错的
 */
class Test2 extends Object {
    @Override
    public boolean equals(Object obj) {
        return true;
    }
}
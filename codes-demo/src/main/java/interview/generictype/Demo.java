package interview.generictype;

import java.util.*;

/**
 * https://www.nowcoder.com/questionTerminal/5dbdf7b89d9a4c27908a8c5c1e88c2fe
 * 1、static方法不能被子类覆写，在子类中定义了和父类完全相同的static方法，则父类的static方法被隐藏
 * 2、方法重载是静态分派
 */
public class Demo {
    public static void main(String[] args) {
        // 这种泛型数组是可以的，用"?"
        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<String>(),
                new HashMap<String, String>().values()
        };
        /*
            "Generic array creation"
            Collection<String>[] collections = {
                    new HashSet<String>(),
                    new ArrayList<String>(),
                    new HashMap<String, String>().values()
            };
         */
        Super subToSuper = new Sub();
        /*
        这是静态分派的过程，在编译时已经决定了使用super的方法，因为subToSuper 是指super对象
         */
        for (Collection<?> collection : collections) {
            /*
            链接：https://www.nowcoder.com/questionTerminal/5dbdf7b89d9a4c27908a8c5c1e88c2fe
            定义的泛型数组类似于 Collection col = new  HashSet<>();
            这样传入方法getType（）中的参数就是col，左边是静态类型，右边是实际类型。
            由于重载实际上是使用静态分派的，重载时是通过参数的静态类型而不是实际类型作为判定依据的
             */
            System.out.println(subToSuper.getType(collection));
        }
    }

    abstract static class Super {
        public static String getType(Collection<?> collection) {
            return "Super:collection";
        }

        public static String getType(List<?> list) {
            return "Super:list";
        }

        public String getType(ArrayList<?> list) {
            return "Super:arrayList";
        }

        public static String getType(Set<?> set) {
            return "Super:set";
        }

        public String getType(HashSet<?> set) {
            return "Super:hashSet";
        }
    }

    /**
     * static方法不能被子类覆写，在子类中定义了和父类完全相同的static方法，则父类的static方法被隐藏
     */
    static class Sub extends Super {
        public static String getType(Collection<?> collection) {
            return "Sub";
        }
    }
}

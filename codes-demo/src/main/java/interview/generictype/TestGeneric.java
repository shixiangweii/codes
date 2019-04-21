package interview.generictype;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-01-26
 * Time: 13:00
 *
 * <? super T>表示包括T在内的任何T的父类
 * <? extends T>表示包括T在内的任何T的子类
 *
 *
 * @author shixiangweii
 */
public class TestGeneric {
    /**
     * https://www.cnblogs.com/lucky_dai/p/5485421.html
     */
    @Test
    public void test() {
        func1(new ArrayList<String>());
        func2(new ArrayList<String>());

        //func1(new ArrayList<Object>());
        func2(new ArrayList<Object>());


        func3(new ArrayList<Exception>());
        // func3(new ArrayList<Throwable>());

        func4(new ArrayList<Exception>());
        func4(new ArrayList<Throwable>());

    }

    void func1(List<? extends String> list) {

    }

    void func2(List<? super String> list) {

    }

    void func3(List<? extends Exception> list) {

    }

    void func4(List<? super Exception> list) {

    }

    private <T extends Comparable<T>> void func5(List<T> list) {

    }

    @Test
    public void test2() {

    }
}


class A {
}

class B extends A {
}

class C extends A {
}

class D extends B {
}
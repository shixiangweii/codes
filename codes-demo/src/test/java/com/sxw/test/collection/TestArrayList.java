package com.sxw.test.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-01-24
 * Time: 17:55
 *
 * @author shixiangweii
 */
public class TestArrayList {
    /**
     * https://www.nowcoder.com/questionTerminal/b41c88359c8a4d69a35c2f37400e49f0
     *
     * https://blog.csdn.net/scyatcs/article/details/9003295
     * https://blog.csdn.net/lxh123456789asd/article/details/80248418
     */
    @Test
    public void testArrayListDelete() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        Iterator it = list.iterator();
        int index = 0;
        while (it.hasNext()) {
            Object obj = it.next();
            if (index == 0) {

                // 1.remove（）将会删除上次调用next（）时返回的元素，也就是说先调用next（）方法，再调用remove方法才会删除元素。
                // next（）和romove方法具有依赖性，必须先用next，再使用romove。
                // 如果先用remove方法会出现IllegalStateException异常
                it.remove();
                // https://www.cnblogs.com/dolphin0520/p/3933551.html
                // 报错 java.util.ConcurrentModificationException
                // https://blog.csdn.net/qq_41620160/article/details/79445297
                // list.remove(obj);
            }
            index++;
        }

        // [2, 3]
        System.out.println(list);
    }

    @Test
    public void testArrayListDeleteFor() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        for (int i = 0; i < list.size(); i++) {
            list.remove(i);
            i--;
        }
        // []
        System.out.println(list);
    }
}

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

                //
                it.remove();

                // 报错 java.util.ConcurrentModificationException
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

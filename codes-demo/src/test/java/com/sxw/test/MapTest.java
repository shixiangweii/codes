package com.sxw.test;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-10-03
 * Time: 11:06
 *
 * @author shixiangweii
 */
public class MapTest {

    int max = 3000000;
    ConcurrentMap<String, String> mark = new ConcurrentHashMap<>(max);

    @Test
    public void mapTest() {
        // 300万还是很快的
        int max = 3000000;
        ConcurrentMap<String, String> mark = new ConcurrentHashMap<>(max);
        for (int i = 0; i < max; i++) {
            mark.put(i + "-oHV1V0UWQy29k8fU-xgkXL4N7A-E", "oHV1V0UWQy29k8fU-xgkXL4N7A-E");
        }
        System.out.println(mark.size());
    }

    @Test
    public void testSyncByMark() {
        String userMark = "shixiangweii789";


        // set nx
        String syncMark = mark.putIfAbsent(userMark, userMark);

        if (syncMark == null) {
            syncMark = userMark;
        } else {
            syncMark = mark.get(userMark);
        }

        synchronized (syncMark) {
            System.out.println("start");
            System.out.println("end");
        }

    }
}

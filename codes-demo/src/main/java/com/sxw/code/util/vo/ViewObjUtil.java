package com.sxw.code.util.vo;

import java.util.*;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-12-09
 * Time: 20:05
 *
 * @author shixiangweii
 */
public class ViewObjUtil {

    public static Map<String, Object> getOneMap(int size) {
        return new HashMap<>(size <= 0 ? 16 : size);
    }

    public static List<Map<String, Object>> getList() {
        return new LinkedList<>();
    }

    public static List<Map<String, Object>> getList(int size) {
        return new ArrayList<>(size <= 0 ? 10 : size);
    }

    public static <K, V> Map<K, V> getMap(int size) {
        return new HashMap<>(size <= 0 ? 16 : size);
    }
}

package com.sxw.test;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-06-17
 * Time: 8:47
 *
 * @author shixiangweii
 */
public class GenArrayTest {

    @Test
    public void testArray() {
        String[] strings = {};
        // Generic array creation
        // List<String>[] lists = {};
        List<String> list = new LinkedList<>();
        // 无法设置
        // List<Object> objs = list;
        // 这样居然是可以的！！
        Object[] objArr = strings;
    }
}

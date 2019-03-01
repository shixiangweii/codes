package com.sxw.test;

import com.sxw.code.util.CodeUtil;
import com.sxw.code.util.vo.ViewObjUtil;
import org.junit.Test;

import java.util.Map;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-12-19
 * Time: 9:29
 *
 * @author shixiangweii
 */
public class CodeTest {
    @Test
    public void test() {
        System.out.println(Integer.valueOf(1) == Integer.valueOf(1));
        System.out.println(Integer.valueOf(1) == new Integer(1));
        System.out.println(Integer.valueOf(1) == CodeUtil.INT_1);
    }

    /**
     * https://zhidao.baidu.com/question/382259508.html
     * <p>
     * x++只能作为右值，而++x既可作为左值又可作为右值
     * C语言里，"++x=++y"是可以的
     * "x++=y++"不可以
     * 因为x++是把x的值增一，并返回x原来的值，这个值并不储存在变量x的内存地址中，
     * 是计算时的一个临时值，因此不能向它赋值。而++x是将x的值增一，并返回x的值，因此可以向这个变量赋值
     */
    @Test
    public void testAdd() {
        int x = 0, y = 0;

        x += y++;

        // 0,1
        System.out.println(x + "," + y);

        // x++ = y++;

        // 2,2
        x = ++y;
        System.out.println(x + "," + y);

        // ++x = ++y;
    }

    @Test
    public void testViewObjectUtil() {
        Map<String, String> map = ViewObjUtil.getMap(12);
        Map<String, Object> map2 = ViewObjUtil.getMap(12);
        Map<Integer, Object> map3 = ViewObjUtil.getMap(12);
    }

    @Test
    public void testArr() {
        String[] arr = {"a", "b"};
        for (String str : arr) {
            str = "c";
        }
        System.out.println(arr);
    }
}

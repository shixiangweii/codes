package com.sxw.test;

import com.sxw.code.util.CodeUtil;
import org.junit.Test;

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
        System.out.println(Integer.valueOf(1)==Integer.valueOf(1));
        System.out.println(Integer.valueOf(1)==new Integer(1));
        System.out.println(Integer.valueOf(1)== CodeUtil.INT_1);
    }
}

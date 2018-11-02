package com.sxw.test.bit;

import org.junit.Test;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-11-02
 * Time: 11:00
 *
 * @author shixiangweii
 */
public class TestBitOperation {

    @Test
    public void test() {
        int num = 6;
        System.out.println(31 * num);
        // num << 5 相当于 num*(2^5)
        System.out.println((num << 5) - num);
    }
}

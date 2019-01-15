package com.sxw.test.basejava;

import org.junit.Test;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-01-15
 * Time: 8:17
 *
 * @author shixiangweii
 */
public class TestBaseJava {
    /**
     * https://www.nowcoder.com/questionTerminal/5ce602538d784f51a531bf9760592773
     * count0=count1++的执行步骤：
     tmp=count1；
     count1++；
     count0=tmp；
     */
    @Test
    public void test() {
        int count = 0;
        // count = count++  原理是 temp = count； count = count+1 ； count = temp；   因此count始终是0 这仅限于java 与c是不一样的
        count = count++;
        System.out.println(count);
    }
}

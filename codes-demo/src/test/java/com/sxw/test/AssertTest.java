package com.sxw.test;

import org.junit.Test;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-11-02
 * Time: 14:56
 *
 * @author shixiangweii
 */
public class AssertTest {

    /**
     * 其实断言这种思想，在那种发钱，算钱，算订单，倒计时那种还是很有用的
     * 最后算出来数据的时候，最后判断拦下来一刀
     * 防止那种红包发出去负数，倒计时导出来负数，库存数据等什么乱七八糟
     *
     * 但注意，这里是"AssertionError"，在代码中用的是“XXXException”
     */
    @Test
    public void testAssert() {
        int a = -1;
        assert (a > 0);
    }
}

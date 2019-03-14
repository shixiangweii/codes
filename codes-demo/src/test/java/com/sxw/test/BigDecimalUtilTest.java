package com.sxw.test;

import com.sxw.code.util.BigDecimalUtil;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-12-27
 * Time: 12:34
 *
 * @author shixiangweii
 */
public class BigDecimalUtilTest {

    @Test
    public void test() throws Exception {
        BigDecimalUtil.div(10d, 3d);
        // 3.3333333333
        System.out.println(BigDecimalUtil.div(10d, 3d));
    }

    @Test
    public void testDouble() {
        System.out.println(0.2 + 0.1);
        System.out.println(0.3 - 0.1);
        System.out.println(0.2 * 0.1);
        System.out.println(0.3 / 0.1);

        double v = new BigDecimal("0.2").add(new BigDecimal(0.1)).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(v);
        System.out.println(String.format("%.3f", v));
    }
}

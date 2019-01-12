package com.sxw.test;

import com.sxw.code.util.BigDecimalUtil;
import org.junit.Test;

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
}

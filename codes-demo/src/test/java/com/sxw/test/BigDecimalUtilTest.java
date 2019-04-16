package com.sxw.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sxw.code.util.BigDecimalUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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
        System.out.println(String.format("%.0f", 99.9d));
        Map<String, Object> map = new HashMap<>(10);
        map.put("1", new BigDecimal("12312.98098923428948"));
        map.put("2", new BigDecimal("12312.234237482347892489"));
        map.put("3", new BigDecimal("12312.234236482387"));
        map.put("package", 1);
        System.out.println(JSON.toJSONString(map));
        float v1 = new BigDecimal("0.001").setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
        System.out.println(v1);
        System.out.println(System.nanoTime());


        JSONObject jsonObject = JSON.parseObject("{\"cancelOrder\":1,\"orderId\":28457,\"delay_second\":172797000}");
        String from = jsonObject.getString("from");
        System.out.println(from);

    }
}

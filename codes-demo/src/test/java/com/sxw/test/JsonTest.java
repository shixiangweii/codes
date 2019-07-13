package com.sxw.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-06-24
 * Time: 14:30
 *
 * @author shixiangweii
 */
public class JsonTest {

    @Test
    public void testJson() {
        User user = new User();
        user.setIcon("123");
        user.setName("name");
        System.out.println(JSON.toJSONString(user));

        //User u = JSON.parseObject("{\"icon\":\"123\",\"name\":\"name\"}", User.class);
        //User u = JSON.parseObject("{\"icon\":\"123\"}", User.class);
        User u = JSON.parseObject("{\"icon\":\"123\",\"icon2\":\"123\"}", User.class);
        System.out.println(u);
    }
}

@Getter
@Setter
@ToString
class User {
    private String name;
    private String icon;
}
package com.sxw;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.*;
import java.util.Properties;

public class TestJson {

    @Test
    public void test() throws IOException {
        JSON.parseObject("{\"a\":\"{\\\"hj\\\":\\\"678\\\"}\"}");


        FileReader reader = new FileReader("./file/test.json");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        System.out.println(line);
        JSONObject x = JSON.parseObject(line);
        System.out.println(x);
        A object = JSON.parseObject(line, A.class);
        System.out.println(object);

        // propreties文件会干掉一层转义
        Properties properties = new Properties();
        properties.load(new InputStreamReader(new FileInputStream("./file/test2.properties")));
        System.out.println(properties.get("aaa"));
        System.out.println(properties.get("bbb"));
    }
}


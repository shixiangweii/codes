package com.sxw.code.reflect.schema.parse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sxw.code.reflect.schema.entity.GovApp;
import com.sxw.code.reflect.schema.entity.MarketApp;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * https://blog.csdn.net/woshizisezise/article/details/79374460
 * <p>
 * 解析json
 * [{"source":"getName","target":"setName"},{"source":"getUrl","target":"setUrl"}]
 *
 * @author shixi
 */
public class JsonSchemaParser {
    /**
     * 解析
     *
     * @param source 源数据
     * @param target 目标结果
     * @param json   schema描述，定义，计划，概要
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    public static void parse(Object source, Object target, String json) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();
        JSONArray array = JSON.parseArray(json);
        for (int i = 0; i < array.size(); i++) {
            JSONObject item = array.getJSONObject(i);
            String getterName = item.getString("source");
            String setterName = item.getString("target");
            Method getter = sourceClass.getMethod(getterName);
            Object value = getter.invoke(source);
            Class<?> paramClass = Class.forName(item.getString("paramClass"));
            Method setter = targetClass.getMethod(setterName, paramClass);
            setter.invoke(target, value);
        }
    }

    /**
     * [{
     * "source": "getName",
     * "target": "setName",
     * "paramClass": "java.lang.String"
     * }, {
     * "source": "getUrl",
     * "target": "setUrl",
     * "paramClass": "java.lang.String"
     * }]
     *
     * @param args
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws ClassNotFoundException
     */
    static public void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        MarketApp marketApp = new MarketApp();
        marketApp.setName("值班表");
        marketApp.setUrl("schedule.alibaba.com");
        GovApp govApp = new GovApp();
        parse(marketApp, govApp, "[{\"source\":\"getName\",\"target\":\"setName\",\"paramClass\":\"java.lang.String\"},{\"source\":\"getUrl\",\"target\":\"setUrl\",\"paramClass\":\"java.lang.String\"}]");
        System.out.println(govApp);
    }
}

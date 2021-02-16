package com.sxw.spring.spel.ongl;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * clientDeviceType = iOS
 * tenantId = 92
 * accountId in [92883, 200800]
 * tenantId = 92 && clientDeviceType = iOS
 * appVersion = 1.9.0
 * appVersion >= 2.0.0.1
 * orgCode = '4ba3b9e8baf726970e63'
 * <p>
 * 内置变量：tenantId, clientDeviceType...
 * 运算符：=,>,>=,in...
 * 表达式连接符：&&
 * 数据类型：字符串，数字，版本号，区间
 *
 * @author shixiangweii
 */
public class ConfigInstanceExp {

    @Test
    public void helloWorld() {
        // 创建【解析器】：SpEL使用ExpressionParser接口表示解析器，提供SpelExpressionParser默认实现；
        ExpressionParser parser = new SpelExpressionParser();

        // 解析【表达式】：使用ExpressionParser的parseExpression来解析相应的表达式为Expression对象。
        Expression expression =
                parser.parseExpression("('Hello' + ' World').concat(#end)");

        // 构造【上下文】：准备比如变量定义等等表达式需要的上下文数据。
        EvaluationContext context = new StandardEvaluationContext();

        // 求值：通过Expression接口的getValue方法根据上下文获得表达式值。
        context.setVariable("end", "!");

        Assert.assertEquals("Hello World!", expression.getValue(context));
    }


    @Test
    public void testConfigInstanceValues() throws NoSuchMethodException {
        long s = System.currentTimeMillis();
        // clientDeviceType = iOS
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("clientDeviceType", "iOS");
        System.out.println(parser.parseExpression("#clientDeviceType=='win'").getValue(context, String.class));
        System.out.println(parser.parseExpression("#clientDeviceType=='iOS'").getValue(context, String.class));

        // tenantId = 92
        context.setVariable("tenantId", 92);
        System.out.println(parser.parseExpression("#tenantId==92").getValue(context, String.class));

        // tenantId = 92 && clientDeviceType = iOS
        System.out.println(parser.parseExpression("#tenantId==92 and #clientDeviceType=='iOS'").getValue(context, String.class));

        // accountId in [92883, 200800]
        List<Integer> whiteList = Arrays.asList(92883, 200800, 1);
        context.setVariable("whiteList", whiteList);
        context.setVariable("accountId", 928831);
        System.out.println(parser.parseExpression("#whiteList.contains(#accountId)").getValue(context, String.class));


        // appVersion = 1.9.0
        context.setVariable("appVersion", "1.9.0");
        Method compareAppVersion = ConfigInstanceExp.class.getDeclaredMethod("compareAppVersion", String.class, String.class);
        context.setVariable("compareAppVersion", compareAppVersion);
        System.out.println(parser.parseExpression("#compareAppVersion(#appVersion, '1.9.0')").getValue(context, String.class));

        // appVersion >= 2.0.0.1
        System.out.println(parser.parseExpression("#compareAppVersion(#appVersion, '2.0.0.1')").getValue(context, String.class));
        System.out.println((System.currentTimeMillis() - s));
    }


    @Test
    public void testQlDemo() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("a", 1);
        context.put("b", 2);
        context.put("c", 3);
        String express = "a+b*c";
        Object r = runner.execute(express, context, null, true, false);
        System.out.println(r);
    }


    @Test
    public void testConfigInstanceValuesQLExpression() throws Exception {
        long s = System.currentTimeMillis();
        // 用QLExpression一样可以做，关注要解决的问题本身，具体的技术选择，会用就行
        // clientDeviceType = iOS
        ExpressRunner runner = new ExpressRunner();

        DefaultContext<String, Object> context = new DefaultContext<String, Object>();

        context.put("clientDeviceType", "iOS");


        System.out.println(runner.execute("clientDeviceType=='win'", context, null, true, false));
        System.out.println(runner.execute("clientDeviceType=='iOS'", context, null, true, false));


        // tenantId = 92
        context.put("tenantId", 92);
        System.out.println(runner.execute("tenantId==92", context, null, true, false));

        // tenantId = 92 && clientDeviceType = iOS
        System.out.println(runner.execute("tenantId==92 && clientDeviceType=='iOS'", context, null, true, false));

        // accountId in [92883, 200800]
        List<Integer> whiteList = Arrays.asList(92883, 200800, 1);
        context.put("whiteList", whiteList);
        context.put("accountId", 92883);
        System.out.println(runner.execute("whiteList.contains(accountId)", context, null, true, false));


        // appVersion = 1.9.0
        context.put("appVersion", "1.9.0");

        runner.addFunctionOfClassMethod("compareAppVersion", ConfigInstanceExp.class.getName(), "compareAppVersion", new String[]{"String", "String"}, null);

        System.out.println(runner.execute("compareAppVersion(appVersion, '1.9.0')", context, null, false, false));

        // appVersion >= 2.0.0.1
        System.out.println(runner.execute("compareAppVersion(appVersion, '2.0.0.1')", context, null, false, false));
        System.out.println((System.currentTimeMillis() - s));
    }


    public static int compareAppVersion(String a, String b) {
        return a.compareTo(b);
    }
}

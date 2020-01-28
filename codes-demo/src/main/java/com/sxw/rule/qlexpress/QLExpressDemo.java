package com.sxw.rule.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

/**
 * @author shixi
 *
 * https://github.com/alibaba/QLExpress
 *
 */
public class QLExpressDemo {

    public static void main(String[] args) throws Exception {
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("a", 1);
        context.put("b", 2);
        context.put("c", 3);
        String express = "a+b*c-1+4*a*b";
        Object r = runner.execute(express, context, null, true, false);
        System.out.println(r);
    }
}

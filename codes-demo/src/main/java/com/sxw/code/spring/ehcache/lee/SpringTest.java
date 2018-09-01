package com.sxw.code.spring.ehcache.lee;


import com.sxw.code.spring.ehcache.app.domain.User;
import com.sxw.code.spring.ehcache.app.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 *
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class SpringTest {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.io.tmpdir"));
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("com/sxw/code/spring/ehcache/beans.xml");
        UserService us = ctx.getBean("userService", UserService.class);
        User u1 = us.getUsersByNameAndAge("sxw", 500);
        User u2 = us.getAnotherUser("sxw", 500);
        System.out.println(u1 == u2);
        // 手动关闭容器，不然jvm无法结束
        ctx.close();
    }
}

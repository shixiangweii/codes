package com.sxw.code.spring.simplecache.lee;


import com.sxw.code.spring.simplecache.org.crazyit.app.domain.User;
import com.sxw.code.spring.simplecache.org.crazyit.app.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description:
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
        ApplicationContext ctx = new ClassPathXmlApplicationContext("com/sxw/code/spring/simplecache/beans.xml");
        UserService us = ctx.getBean("userService", UserService.class);
        User u1 = us.getUsersByNameAndAge("sxw", 500);
        User u2 = us.getAnotherUser("sxw", 500);
        System.out.println(u1 == u2);
    }
}

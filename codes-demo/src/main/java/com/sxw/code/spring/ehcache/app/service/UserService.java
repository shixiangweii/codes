package com.sxw.code.spring.ehcache.app.service;


import com.sxw.code.spring.ehcache.app.domain.User;

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
public interface UserService {
    User getUsersByNameAndAge(String name, int age);

    User getAnotherUser(String name, int age);
}

package com.sxw.code.spring.simplecache.org.crazyit.app.service.impl;


import com.sxw.code.spring.simplecache.org.crazyit.app.domain.User;
import com.sxw.code.spring.simplecache.org.crazyit.app.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Description: <br/>
 * Copyright (C), 2001-2014, Yeeku.H.Lee <br/>
 * This program is protected by copyright laws. <br/>
 * Program Name: <br/>
 * Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
@Service("userService")
@Cacheable(value = "users")
public class UserServiceImpl implements UserService {

	@Override
	public User getUsersByNameAndAge(String name, int age) {
		System.out.println("执行查询：getUsersByNameAndAge");
		return new User(name, age);
	}

	@Override
	public User getAnotherUser(String name, int age) {
		System.out.println("执行查询：getAnotherUser");
		return new User(name, age);
	}
}
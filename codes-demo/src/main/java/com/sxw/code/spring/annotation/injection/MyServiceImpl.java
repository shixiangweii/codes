package com.sxw.code.spring.annotation.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("customIdMyService")
public class MyServiceImpl implements MyService{
	@Autowired
	private MyDao dao;
	
	public MyServiceImpl(){}
	
	@Override
	public void print(String content) {
		dao.print(content);
	}

}

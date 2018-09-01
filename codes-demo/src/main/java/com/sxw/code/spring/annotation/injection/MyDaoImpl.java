package com.sxw.code.spring.annotation.injection;

import org.springframework.stereotype.Repository;

@Repository
public class MyDaoImpl implements MyDao{

	@Override
	public void print(String content) {
		System.out.println(content);
	}

}

package com.sxw.code.spring.annotation.jsr;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJsr {
	private ClassPathXmlApplicationContext context = null;

	@Before
	public void before() {
		context = new ClassPathXmlApplicationContext("com/sxw/code/spring/annotation/jsr/applicationContext-annotation.xml");
		context.start();
	}

	@After
	public void after() {
		context.close();
	}

	@Test
	public void testJsr() {
		JsrService s = (JsrService) context.getBean("jsrService");
		s.save();
	}

}

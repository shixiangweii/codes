package com.sxw.code.spring.annotation.javabase;

import com.sxw.code.spring.annotation.javabase.inter.Store;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestJavaBase {
	ClassPathXmlApplicationContext context=null;
	@Before
	public void before(){
		context=new ClassPathXmlApplicationContext("classpath:com/sxw/code/spring/annotation/javabase/applicationContext-annotation.xml");
		context.start();
	}
	
	@After
	public void after(){
		context.close();
	}
	
	@Test
	public void testStringStore(){
		Store<?> store=(Store<?>) context.getBean("store");
		System.out.println(store.getClass().getName());
	}
	
	@Test
	public void testStringStoreScopeProxy(){
		Store<?> s1=(Store<?>) context.getBean("stringStoreScopeProxy");
		Store<?> s2=(Store<?>) context.getBean("stringStoreScopeProxy");
		System.out.println(s1.getClass().getName());
		System.out.println(s2.getClass().getName());
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
	}
	
	@Test
	public void testStringStoreScope(){
		Store<?> s1=(Store<?>) context.getBean("stringStoreScope");
		Store<?> s2=(Store<?>) context.getBean("stringStoreScope");
		System.out.println(s1.getClass().getName());
		System.out.println(s2.getClass().getName());
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
	}	
	
	@Test
	public void testMyDriverManage(){
		MyDriverManager manager=(MyDriverManager) context.getBean("myDriverManager");
		manager.info();
	}	
}

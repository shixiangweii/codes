package com.sxw.code.spring.annotation.jsr;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class JsrService {
	@Inject
	private JsrDAO jsrDAO;
	
	public void save(){
		jsrDAO.save();
	}
	
	@PostConstruct
	public void init(){
		System.out.println("JserService init()");
	}
	
	@PreDestroy
	public void destroy(){
		System.out.println("JsrService destroy()");
	}
}

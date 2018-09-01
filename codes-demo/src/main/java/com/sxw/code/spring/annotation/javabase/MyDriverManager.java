package com.sxw.code.spring.annotation.javabase;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MyDriverManager {
	private String url;
	private String userName;
	private String password;

	void info(){
		System.out.println("info()"+"->"+toString());
	}
	
	@Override
	public String toString() {
		return "MyDriverManager [url=" + url + ", userName=" + userName + ", password=" + password + "]";
	}
}

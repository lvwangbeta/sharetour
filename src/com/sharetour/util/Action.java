package com.sharetour.util;

import javax.servlet.http.HttpServletRequest;

public interface Action {
	static final String TIP 		 = "tip";
	static final String USERNAMEEXIST= "用户名已存在";
	static final String CONFIRMERROR = "";
	static final String EMAILVALIDATE= "邮箱地址不合法";
	static final String EMAILEXIST	 = "该邮箱已注册";
	static final String PWDERROR	 = "用户名或密码错误";
	public String execute(HttpServletRequest request);
}
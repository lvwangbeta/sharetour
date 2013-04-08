package com.sharetour.util;

import java.util.HashMap;
import java.util.Map;

import com.sharetour.service.LoginAction;
import com.sharetour.service.RegisterAction;

/*
 * 获得Servlet对应的动作处理类
 * 
 */
public class ActionFactory {
	private static Map<String, Action> actions = new HashMap<String, Action>();
	static{
		actions.put("register", new RegisterAction());
		actions.put("login", new LoginAction());
	}
	public static Action getAction(String action){
		return actions.get(action);
	}
}

package com.sharetour.util;

import java.util.HashMap;
import java.util.Map;

import com.sharetour.control.CommentAction;
import com.sharetour.control.LoginAction;
import com.sharetour.control.NewPostAction;
import com.sharetour.control.RegisterAction;

/*
 * 获得Servlet对应的动作处理类
 * 
 */
public class ActionFactory {
	private static Map<String, Action> actions = new HashMap<String, Action>();
	static{
		actions.put("register", new RegisterAction());
		actions.put("login", new LoginAction());
		actions.put("newpost", new NewPostAction());
		actions.put("comment", new CommentAction());
	}
	public static Action getAction(String action){
		return actions.get(action);
	}
}

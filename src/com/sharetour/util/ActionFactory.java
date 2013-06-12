package com.sharetour.util;

import java.util.HashMap;
import java.util.Map;

import com.sharetour.control.AccountSettingAction;
import com.sharetour.control.AlbumAction;
import com.sharetour.control.AvatorAction;
import com.sharetour.control.CommentAction;
import com.sharetour.control.LikeAction;
import com.sharetour.control.LoginAction;
import com.sharetour.control.NewPostAction;
import com.sharetour.control.RegisterAction;
import com.sharetour.control.SubscriptionAction;

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
		actions.put("like", new LikeAction());
		actions.put("subscribe", new SubscriptionAction());
		actions.put("album", new AlbumAction());
		actions.put("avator", new AvatorAction());
		actions.put("accountsetting", new AccountSettingAction());
	}
	public static Action getAction(String action){
		return actions.get(action);
	}
}

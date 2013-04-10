package com.sharetour.control;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import com.sharetour.model.UserInfo;
import com.sharetour.service.UserService;
import com.sharetour.util.Action;

public class LoginAction implements Action{
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("encode error");
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserInfo user = UserService.loginCheck(username, password);
		
		if(user == null){
			request.setAttribute(TIP, PWDERROR);
			return "login";
		}
		else{
			request.getSession().setAttribute("user", user);
			return "home";
		}
		
		
	}

}

package com.sharetour.control;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import com.sharetour.model.UserInfo;
import com.sharetour.service.UserService;
import com.sharetour.util.Action;

public class RegisterAction implements Action{
	
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("encode error");
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String birth = year + "-" + month + "-" + day;
		
		boolean username_validate = UserService.checkUsername(username);
		if(username_validate == false){
			request.setAttribute(TIP, USERNAMEEXIST);
			return "register";
		}	
		UserInfo user = new UserInfo();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setGender(Integer.parseInt(gender));
		user.setBirth(birth);
		user.Save();
		request.getSession().setAttribute("user", user);
		return "home";
		
	}

}

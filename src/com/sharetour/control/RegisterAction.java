package com.sharetour.control;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import com.sharetour.model.UserInfo;
import com.sharetour.service.UserService;
import com.sharetour.util.Action;

public class RegisterAction implements Action{
	
	private static final String FAILURE = "register";
	private static final String SUCCESS = "home";
	private static final String GENDER_ERROR = "gender error";
	@Override
	public String execute(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("encode error");
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirm	= request.getParameter("confirm");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String birth = year + "-" + month + "-" + day;
		
		/*
		 * check field empty
		 */
		if(!UserService.emptyCheck(username, password, confirm, email, gender))
		{
			request.setAttribute(TIP, "field empty");
			return FAILURE;
		}
		
		/*
		 * confirm password
		 */
		if(!UserService.confirmPassword(password, confirm)){
			request.setAttribute(TIP, "password confirm error");
			return FAILURE;
		}
		/*
		 * check whether user name is exist
		 */
		if(UserService.checkUsernameExist(username)){
			request.setAttribute(TIP, USERNAMEEXIST);
			return FAILURE;
		}	
		
		/*
		 * check email validate
		 */
		if(!UserService.emailValidate(email)){
			request.setAttribute(TIP, EMAILVALIDATE);
			return FAILURE;
		}
		/*
		 * check whether email exist 
		 */
		if(UserService.checkEmailExist(email)){
			request.setAttribute(TIP, EMAILEXIST);
			return FAILURE;
		}
		
		/*
		 * check gender validate
		 */
		if(!UserService.checkGenderValidate(gender)){
			request.setAttribute(TIP, GENDER_ERROR);
			return FAILURE;
		}
		
		UserInfo user = new UserInfo();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setGender(Integer.parseInt(gender));
		user.setBirth(birth);
		if(UserService.Register(user)){
			user.setPassword("");
			request.getSession().setAttribute("user", user);
		}
		return SUCCESS;
		
	}

}

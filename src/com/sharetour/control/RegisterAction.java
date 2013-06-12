package com.sharetour.control;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import com.sharetour.model.UserInfo;
import com.sharetour.service.UserService;
import com.sharetour.util.Action;

public class RegisterAction implements Action{
	
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
		if(!UserService.emptyCheck(username, password, confirm, email, gender)) {
			request.setAttribute(TIP, "field empty");
			return ERROR;
		}
		
		/*
		 * confirm password
		 */
		if(!UserService.confirmPassword(password, confirm)) {
			request.setAttribute(TIP, "password confirm error");
			return ERROR;
		}
		/*
		 * check whether user name is exist
		 */
		if(UserService.checkUsernameExist(username)){
			request.setAttribute(TIP, USERNAMEEXIST);
			return ERROR;
		}	
		
		/*
		 * check email validate
		 */
		if(!UserService.emailValidate(email)){
			request.setAttribute(TIP, EMAILVALIDATE);
			return ERROR;
		}
		/*
		 * check whether email exist 
		 */
		if(UserService.checkEmailExist(email)){
			request.setAttribute(TIP, EMAILEXIST);
			return ERROR;
		}
		
		/*
		 * check gender validate
		 */
		if(!UserService.checkGenderValidate(gender)){
			request.setAttribute(TIP, GENDER_ERROR);
			return ERROR;
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

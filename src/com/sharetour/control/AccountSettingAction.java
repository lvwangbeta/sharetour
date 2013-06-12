package com.sharetour.control;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import com.sharetour.model.UserInfo;
import com.sharetour.service.UserService;
import com.sharetour.util.Action;

public class AccountSettingAction implements Action{

	@Override
	public String execute(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			return ERROR;
		}
		String nickname = request.getParameter("nickname");
		String intro = request.getParameter("intro");
		UserInfo user = (UserInfo) request.getSession().getAttribute("user");
		if(user == null) {
			return ERROR;
		}
		UserService userService = new UserService();
		if(userService.updateAccount(user.getId(), nickname, intro)) {
			user.setNickname(nickname);
			user.setIntro(intro);
			return SUCCESS;
		}
		return ERROR;
	}

}

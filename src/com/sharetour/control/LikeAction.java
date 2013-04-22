package com.sharetour.control;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import com.sharetour.model.UserInfo;
import com.sharetour.util.Action;

public class LikeAction implements Action{

	@Override
	public String execute(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return ERROR;
		}
		UserInfo user = (UserInfo) request.getSession().getAttribute("user");
		if(user == null){
			
		}
	}

}

package com.sharetour.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.sharetour.model.UserInfo;
import com.sharetour.service.FollowService;
import com.sharetour.util.Action;

public class FollowAction implements Action{

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("user");
		if(user == null)
			return ERROR;
		
		String action = request.getParameter("action");
		if(action == null || action.length() == 0)
			return ERROR;
		
		Long uid = user.getId();
		String followingid_str = request.getParameter("followingid");
		if(followingid_str == null || followingid_str.length() == 0)
			return ERROR;
		Long followingid = Long.parseLong(followingid_str);
		FollowService followService = new FollowService();
		boolean res = false;
		if("follow".equals(action)) {
			res = followService.follow(uid, followingid);
		} else if("undofollow".equals(action)) {
			res = followService.undoFollow(uid, followingid);
		}
		if(res)
			return SUCCESS;
		return ERROR;
	}

}

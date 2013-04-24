package com.sharetour.control;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;

import com.sharetour.model.PostUserRelation;
import com.sharetour.model.UserInfo;
import com.sharetour.service.PostLikeService;
import com.sharetour.util.Action;

public class LikeAction implements Action{
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			return ERROR;
		}
		UserInfo user = (UserInfo) request.getSession().getAttribute("user");
		if(user == null){
			return NOLOGIN;
		}
		else{
			Long pid = Long.parseLong(request.getParameter("postid"));
			Long uid = user.getId();
			String action = request.getParameter("action");
			PostUserRelation purelation = new PostUserRelation();
			purelation.setPid(pid);
			purelation.setUid(uid);
			purelation.setLik(action);
			PostLikeService postlikeservice = new PostLikeService();
			if("like".equals(action) && postlikeservice.savePostLike(purelation)){
				return SUCCESS;		
			}
			else if("dislike".equals(action) && postlikeservice.undoPostLike(purelation)){
				return SUCCESS;
			}
			return ERROR;
		}
	}
	
	
}

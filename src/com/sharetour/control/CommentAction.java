package com.sharetour.control;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import com.sharetour.model.PostComment;
import com.sharetour.model.UserInfo;
import com.sharetour.service.PostCommentService;
import com.sharetour.util.Action;

public class CommentAction implements Action{

	@Override
	public String execute(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			return ERROR;
		}
		
		/*
		 * 登录用户评论信息处理
		 */
		if(request.getSession().getAttribute("user") != null){
			UserInfo user = (UserInfo) request.getSession().getAttribute("user");
			long authorid = user.getId();
			System.out.println("postid:"+request.getParameter("postid"));
			int postid	  = Integer.parseInt(request.getParameter("postid"));
			String name   = user.getUsername();
			String content= request.getParameter("content");

			PostComment comment = new PostComment();
			comment.setAuthorid(authorid);
			comment.setPostid(postid);
			comment.setName(name);
			comment.setContent(content);
			PostCommentService commentService = new PostCommentService(comment);
			if(commentService.saveComment()){
				return SUCCESS;
			}
			else{
				return ERROR;
			}
		}
		else{
			return NOLOGIN;
		}
	}

}

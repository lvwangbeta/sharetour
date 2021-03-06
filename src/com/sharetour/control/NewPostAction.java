package com.sharetour.control;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import com.sharetour.model.Post;
import com.sharetour.model.UserInfo;
import com.sharetour.service.CoverService;
import com.sharetour.service.PostService;
import com.sharetour.service.PostSummaryService;
import com.sharetour.util.Action;

public class NewPostAction implements Action{

	@Override
	public String execute(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("encode error");
		}
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String tags = request.getParameter("tags");	
		Post post = new Post();
		UserInfo user = (UserInfo) request.getSession().getAttribute("user");
		post.setAuthorid(user.getId());
		post.setTitle(title);
		post.setContent(content);
		post.setCtime(new Date());
		post.setTags(tags);	
		post.setSummary(new PostSummaryService().getSummary(content));
		post.setCover(new CoverService().getCover(request.getContextPath(), content));
		PostService service = new PostService();
		/*
		 * check title content, field not empty
		 */		
		if(!service.checkEmpty(post)){
			return ERROR;
		}		
		if(service.savePost(post)){
			return SUCCESS;
		}
		else{
			return ERROR;
		}
	}

}

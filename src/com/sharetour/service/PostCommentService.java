package com.sharetour.service;

import com.sharetour.dao.PostCommentDAO;
import com.sharetour.model.PostComment;

public class PostCommentService {

	private PostCommentDAO commentDAO;
	public PostCommentService(){
		this.commentDAO = new PostCommentDAO();
	}
	public boolean saveComment(PostComment comment){
		return this.commentDAO.saveComment(comment);
	}
	
	
	
}

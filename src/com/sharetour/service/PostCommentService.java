package com.sharetour.service;

import com.sharetour.dao.PostCommentDAO;
import com.sharetour.model.PostComment;

public class PostCommentService {
	private PostComment comment;
	private PostCommentDAO commentDAO;
	public PostCommentService(PostComment comment){
		this.comment = comment;
		this.commentDAO = new PostCommentDAO(this.comment);
	}
	public boolean saveComment(){
		return this.commentDAO.saveComment();
	}
}

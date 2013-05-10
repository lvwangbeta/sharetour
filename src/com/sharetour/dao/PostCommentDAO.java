package com.sharetour.dao;

import com.sharetour.model.PostComment;
import com.sharetour.util.QueryHelper;

public class PostCommentDAO {
	private PostComment comment;
	public PostCommentDAO(PostComment comment){
		this.comment = comment;
	}
	public boolean saveComment(){
		QueryHelper helper = new QueryHelper();
		this.comment.setQueryHelper(helper);
		if(this.comment.Save() == 0){
			return false;
		}
		helper.closeConnection();
		return true;
	}
}

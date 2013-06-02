package com.sharetour.dao;

import com.sharetour.model.PostComment;
import com.sharetour.util.QueryHelper;

public class PostCommentDAO {
	private PostComment comment;
	
	public boolean saveComment(PostComment comment){
		QueryHelper helper = new QueryHelper();
		this.comment.setQueryHelper(helper);
		if(this.comment.Save() == 0){
			return false;
		}
		helper.closeConnection();
		return true;
	}
}

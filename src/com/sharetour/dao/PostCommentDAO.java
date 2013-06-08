package com.sharetour.dao;

import com.sharetour.model.PostComment;
import com.sharetour.util.QueryHelper;

public class PostCommentDAO {
	
	public boolean saveComment(PostComment comment){
		QueryHelper helper = new QueryHelper();
		comment.setQueryHelper(helper);
		if(comment.Save() == 0){
			return false;
		}
		helper.closeConnection();
		return true;
	}
}

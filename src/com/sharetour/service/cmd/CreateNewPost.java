package com.sharetour.service.cmd;

import java.sql.Connection;

import com.sharetour.dao.DAOCommand;
import com.sharetour.model.Post;
import com.sharetour.util.QueryHelper;

public class CreateNewPost implements DAOCommand{

	private Post post;
	private Connection connection;
	public CreateNewPost(Post post){
		this.post = post;
	}
	
	@Override
	public Object execute(Connection connection) {
		this.connection = connection;
		QueryHelper helper = new QueryHelper(this.connection);
		post.setQueryHelper(helper);
		return post.Save();
	}

}

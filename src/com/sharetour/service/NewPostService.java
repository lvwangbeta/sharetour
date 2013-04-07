package com.sharetour.service;

import java.sql.Connection;
import java.util.List;

import com.sharetour.dao.DAOCommand;
import com.sharetour.dao.DBManager;
import com.sharetour.dao.impl.MySQLManager;
import com.sharetour.model.Post;
import com.sharetour.service.cmd.CreateNewPost;
import com.sharetour.service.cmd.PostTagCommand;
import com.sharetour.service.cmd.PostTagRelationCommand;
import com.sharetour.db.ConnectionPool;


public class NewPostService {
		
	private Post post;
	public void createNewPost(Post post){
		this.post = post;
		Connection connection = ConnectionPool.getInstance().getConnection();
		DBManager manager = new MySQLManager(connection);
		DAOCommand newpostcmd = new CreateNewPost(this.post);
		DAOCommand posttagcmd = new PostTagCommand(this.post.getTags());
		long pid = (Long) manager.execute(newpostcmd);
		//System.out.print(pid);
		List<Long> tids = (List<Long>) manager.execute(posttagcmd);
		DAOCommand relationcmd = new PostTagRelationCommand(pid, tids);
		manager.executeAndClose(relationcmd);
	}
	
}

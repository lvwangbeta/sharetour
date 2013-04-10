package com.sharetour.dao;

import java.sql.Connection;
import java.util.List;
import com.sharetour.dao.impl.MySQLManager;
import com.sharetour.model.Post;
import com.sharetour.model.PostComment;
import com.sharetour.service.cmd.CreateNewPost;
import com.sharetour.service.cmd.PostTagCommand;
import com.sharetour.service.cmd.PostTagRelationCommand;
import com.sharetour.util.QueryHelper;
import com.sharetour.db.ConnectionPool;


public class PostDAO {
		
	private Post post;
	public PostDAO(Post post){
		this.post = post;
	}	
	public boolean createNewPost(){
		Connection connection = ConnectionPool.getInstance().getConnection();
		DBManager manager = new MySQLManager(connection);
		DAOCommand newpostcmd = new CreateNewPost(this.post);
		DAOCommand posttagcmd = new PostTagCommand(this.post.getTags());
		long pid = (Long) manager.execute(newpostcmd);
		//System.out.print(pid);
		@SuppressWarnings("unchecked")
		List<Long> tids = (List<Long>) manager.execute(posttagcmd);
		DAOCommand relationcmd = new PostTagRelationCommand(pid, tids);
		manager.executeAndClose(relationcmd);
		return true;
	}
	
	public List<PostComment> getPostComment(){	
	 	List<PostComment> comments = new QueryHelper().query_slice(
		 	PostComment.class, 
		 	"select * from posts_comments where postid=?", 
		 	1, 
		 	10,
		 	new Object[]{post.getId()}
		 );
	 	return comments;
	}
	
}

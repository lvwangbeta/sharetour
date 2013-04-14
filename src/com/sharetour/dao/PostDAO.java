package com.sharetour.dao;

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
	public PostDAO(){
		
	}
	public PostDAO(Post post){
		this.post = post;
	}	
	public boolean createNewPost(){
		
		DBManager manager = new MySQLManager(ConnectionPool.getInstance().getConnection());
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
	
	public Post getPost(){
		QueryHelper helper = new QueryHelper();
		post.setQueryHelper(helper);
		Post p = post.Get();
		helper.closeConnection();
		return p;
	}
	
	public List<PostComment> getPostComment(){	
		QueryHelper helper = new QueryHelper();
	 	List<PostComment> comments = helper.query_slice(
		 	PostComment.class, 
		 	"select * from posts_comments where postid=?", 
		 	1, 
		 	10,
		 	new Object[]{post.getId()}
		 );
	 	helper.closeConnection();
	 	return comments;
	}
	@SuppressWarnings("unchecked")
	public List<Post> getHotPost(int limit, String order){
		QueryHelper helper = new QueryHelper();
		Post p = new Post();
		p.setQueryHelper(helper);
		List<Post> list =  (List<Post>) p.List(1, limit, order);
		helper.closeConnection();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Post> getPostList(int page, int limit){
		QueryHelper helper = new QueryHelper();
		Post p = new Post();
		p.setQueryHelper(helper);
		List<Post> list = (List<Post>) p.List(page, limit);
		helper.closeConnection();
		return list;
	}
	
}

package com.sharetour.service;
import java.util.List;

import com.sharetour.model.Post;
import com.sharetour.model.PostComment;
import com.sharetour.db.ConnectionPool;
import com.sharetour.util.QueryHelper;

/*
 * class: PostService 
 * 响应post.js调用 获取Post对象
 */
public class PostService {
	private Post post;
	private QueryHelper helper;
	public PostService(Post post){
		this.post = post;
		this.helper = new QueryHelper(ConnectionPool.getInstance().getConnection());
		this.post.setQueryHelper(helper);
	}
	public Post getPost(){
		return post.Get();		
	}
	public List<PostComment> getPostComment(){
		
	 	List<PostComment> comments = helper.query_slice(
		 	PostComment.class, 
		 	"select * from posts_comments where postid=?", 
		 	1, 
		 	10,
		 	new Object[]{post.getId()}
		 );
	 	return comments;
	}
}

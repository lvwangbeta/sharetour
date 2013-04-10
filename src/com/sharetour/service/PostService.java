package com.sharetour.service;

import java.util.List;
import com.sharetour.dao.PostDAO;
import com.sharetour.model.Post;
import com.sharetour.model.PostComment;

/*
 * class: PostService 
 * 响应post.jsp/NewpostAction调用 获取Post对象
 */
public class PostService {
	private Post post;
	private PostDAO postdao;
	public PostService(Post post){
		this.post = post;
		this.postdao = new PostDAO(this.post);
	}
	public Post getPost(){
		return post.Get();		
	}
	public boolean savePost(){
		return postdao.createNewPost();
	}
	public List<PostComment> getPostComment(){
		return postdao.getPostComment();
	}
}

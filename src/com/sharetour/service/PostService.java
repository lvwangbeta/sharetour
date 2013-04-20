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
		return postdao.getPost();	
	}
	
	public boolean savePost(){
		return postdao.createNewPost();
	}
	public List<PostComment> getPostComment(){
		return postdao.getPostComment();
	}
	public static List<Post> getPostList(int page, int limit){
		return new PostDAO().getPostList(page, limit);
	}
	/*
	 * 根据用户名获得用户ID
	 */
	public static int getAuthorid(String username){
		return UserService.getAuthorid(username);
	}	
	/*
	 * 获得作者的posts
	 */
	public static List<Post> getPostsOfAuthor(int authorid){
		return new PostDAO().getPostsOfAuthor(authorid);
	}
	public boolean checkEmpty(){
		if( (post.getTitle() == null || post.getTitle().length() == 0)
			|| (post.getContent() == null || post.getContent().length() == 0)	
			)
		{
			return false;
		}
		return true;
	}
}

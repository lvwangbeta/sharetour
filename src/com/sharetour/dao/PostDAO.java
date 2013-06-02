package com.sharetour.dao;

import java.util.List;
import com.sharetour.model.Post;
import com.sharetour.model.PostComment;
import com.sharetour.util.QueryHelper;


public class PostDAO {

	public boolean createNewPost(Post post){	
		QueryHelper helper = new QueryHelper();
		post.setQueryHelper(helper);
		long pid = post.Save();
		helper.closeConnection();
		if(pid == 0){
			return false;
		}
		return true;
	}
	
	
	public Post getPost(Long id){
		QueryHelper helper = new QueryHelper();
		Post post = new Post();
		post.setId(id);
		post.setQueryHelper(helper);
		Post p = post.Get();
		helper.closeConnection();
		return p;
	}
	
	public List<PostComment> getPostComment(Long id){	
		QueryHelper helper = new QueryHelper();
	 	List<PostComment> comments = helper.query_slice(
		 	PostComment.class, 
		 	"select * from posts_comments where postid=?", 
		 	1, 
		 	10,
		 	new Object[]{id}
		 );
	 	helper.closeConnection();
	 	return comments;
	}
	/*
	 * @param page 起始页
	 * @param limit 页面容量
	 * @param order 结果集按order排序
	 */
	public List<Post> getPostList(int page, int limit, String order){
		QueryHelper helper = new QueryHelper();
		Post p = new Post();
		p.setQueryHelper(helper);
		@SuppressWarnings("unchecked")
		List<Post> list = (List<Post>) p.List(page, limit, order);
		helper.closeConnection();
		return list;
	}
	
	
	/*
	 * @param page 起始页
	 * @param limit 页面容量
	 */
	public List<Post> getPostList(int page, int limit){
		QueryHelper helper = new QueryHelper();
		Post p = new Post();
		p.setQueryHelper(helper);
		@SuppressWarnings("unchecked")
		List<Post> list = (List<Post>) p.List(page, limit);
		helper.closeConnection();
		return list;
	}
	/*
	 * 根据authorid获得其所有posts
	 */
	public List<Post> getPostsOfAuthor(Long authorid){
		QueryHelper helper = new QueryHelper();
		List<Post> list = helper.executeQuery(Post.class, "select * from posts where authorid=?", authorid);
		helper.closeConnection();
		return list;
	}
	
	public List<Post> getHotPostOfThisWeek(int page, int limit){
		QueryHelper helper = new QueryHelper();
		List<Post> list = helper.query_slice(
				Post.class, 
				"select id, title, summary, ctime, authorid, likes, visit, cover, tags " +
				"from posts " +
				"where floor(datediff(ctime, '1900-01-01')/7)=floor(datediff(now(), '1900-01-01')/7) " +
				"order by likes desc",
				page, limit);
		helper.closeConnection();
		return list;
		
	}
	
	
	/*
	 * 获得year-month的热门post列表
	 * @param year
	 * @param month
	 * @param page
	 * @param limit
	 */
	public List<Post> getHotPostOfMonth(int year, int month, int page, int limit){
		QueryHelper helper = new QueryHelper();
		List<Post> list = helper.query_slice(
				Post.class, 
				"select id, title, summary, ctime, authorid, likes, visit, cover, tags " +
				"from posts " +
				"where year(ctime)=? and month(ctime)=? order by likes desc ",
				page, limit, year, month);
		helper.closeConnection();
		return list;
	}

	
	
}

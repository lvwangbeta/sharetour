package com.sharetour.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sharetour.dao.PostDAO;
import com.sharetour.dao.PostTagRelationDAO;
import com.sharetour.dao.TagDAO;
import com.sharetour.model.Post;
import com.sharetour.model.PostComment;

/*
 * class: PostService 
 * 响应post.jsp/NewpostAction调用 获取Post对象
 */
public class PostService {

	private static Log log = LogFactory.getLog(PostService.class);
	private PostDAO postDao = new PostDAO();
	private TagDAO tagDao = new TagDAO();
	private PostTagRelationDAO ptrelaDao = new PostTagRelationDAO();
	
	/*
	 * 从本地数据库获得Post对象
	 * @param post id
	 * @return Post
	 */
	private Post getPostFromDB(Long id){
		return postDao.getPost(id);			
	}
	
	/*
	 * 根据post id获得Post对象,
	 * 可以从缓存中获得
	 * @param post id
	 * @return Post
	 */
	public Post getPostById(Long id){
		log.info("get post by id:"+id);
		log.info("get post "+id+" from db");
		Post p = getPostFromDB(id);				
		return p;
	}
	/*
	 * 保存新post，并放入缓存
	 * 缓存结构:(map)
	 * authorid---->Post list
	 * @param post
	 * @return 
	 */
	public boolean savePost(Post post){
		log.info("new post saving...");
		if(createNewPost(post)){
			List<Long> tids = savePostTags(post.getTags());
			if(tids != null && tids.size() != 0){
				savePostTagRelation(post.getId(), tids);
			}
		}
		return true;
	}
	
	/*
	 * create a new post
	 * step 1
	 * create new post
	 */
	private boolean createNewPost(Post post){
		return postDao.createNewPost(post);
	}
	/*
	 * step 2
	 * save post tags
	 */
	private List<Long> savePostTags(String postTags){
		return tagDao.savePostTag(postTags);
	}
	
	/*
	 * step 3
	 * save post tag relation
	 * @param pid post id
	 * @param tids tag id list
	 */
	private void savePostTagRelation(Long pid, List<Long> tids){
		ptrelaDao.savePostTagRelation(pid, tids);
	}
	
	
	/*
	 * 根据post id获得对应评论
	 * @param postid
	 * @return List<PostComment>
	 */
	public List<PostComment> getCommentsByPostId(Long id){
		log.info("get comments of post:"+id);
		List<PostComment> clist = new ArrayList<PostComment>();
		clist = new ArrayList<PostComment>();
		clist = postDao.getPostComment(id);			
		return clist;
	}
	/*
	 * 单纯获得post 列表
	 * @param page
	 * @param limit
	 * @return List<Post>
	 */
	public List<Post> getPostList(int page, int limit){
		return postDao.getPostList(page, limit);
	}

	public List<Post> getPostList(int page, int limit, String order){
		return postDao.getPostList(page, limit, order);
	}

	
	/*
	 * 获得作者的posts
	 * @param authorid
	 */
	public List<Post> getPostsOfAuthor(Long authorid){		
		log.info("get posts list of author:"+authorid);
		List<Post> plist = new ArrayList<Post>();
		plist = postDao.getPostsOfAuthor(authorid);
		return plist;
	}
	/*
	 * 检测post关键域是否非空
	 */
	public boolean checkEmpty(Post post){
		if( (post.getTitle() == null || post.getTitle().length() == 0)
			|| (post.getContent() == null || post.getContent().length() == 0)	
			)
		{
			return false;
		}
		return true;
	}
}

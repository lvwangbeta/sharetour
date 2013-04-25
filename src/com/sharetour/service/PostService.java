package com.sharetour.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sharetour.cache.CacheHelper;
import com.sharetour.dao.PostDAO;
import com.sharetour.model.Post;
import com.sharetour.model.PostComment;

/*
 * class: PostService 
 * 响应post.jsp/NewpostAction调用 获取Post对象
 */
public class PostService {
	private final static String POST_CACHE_NAME = Post.class.getSimpleName();
	private final static String COMMENT_CACHE_NAME = PostComment.class.getSimpleName();
	private final static String USER_POSTS = "user_posts";
	private final static String SINGLE_POST = "single_post";
	private final static String POST_COMMENTS = "post_comments";
	
	private static Log log = LogFactory.getLog(PostService.class);
	
	/*
	 * 从本地数据库获得Post对象
	 * @param post id
	 * @return Post
	 */
	private Post getPostFromDB(Long id){
		Post post = new Post();
		post.setId(id);
		PostDAO postdao = new PostDAO(post);
		return postdao.getPost();			
	}
	
	/*
	 * 根据post id获得Post对象,
	 * 可以从缓存中获得
	 * @param post id
	 * @return Post
	 */
	public Post getPostById(Long id){
		log.info("get post by id:"+id);
		@SuppressWarnings("unchecked")
		List<Post> plist = (List<Post>) CacheHelper.getCacheData(POST_CACHE_NAME, SINGLE_POST);
		if(plist != null){
			for(Post post:plist){
				if(post.getId() == id){
					log.info("get post "+id+" from cache");
					return post;
				}
			}
		}
		else{
			plist = new LinkedList<Post>();
			CacheHelper.put(POST_CACHE_NAME, SINGLE_POST, plist);
		}
		log.info("get post "+id+" from db");
		Post p = getPostFromDB(id);
		plist.add(p);				
		return p;
	}
	/*
	 * 保存新post，并放入缓存
	 * @param post
	 * @return 
	 */
	public boolean savePost(Post post){
		log.info("new post saving...");
		if(new PostDAO(post).createNewPost()){
			log.info("new post saved success");
			@SuppressWarnings("unchecked")
			Map<Long, List<Post>> posts_map = (HashMap<Long, List<Post>>) CacheHelper.
											  getCacheData(POST_CACHE_NAME, USER_POSTS);
			List<Post> plist = null;
			if(posts_map == null){
				posts_map = new HashMap<Long, List<Post>>();
				CacheHelper.put(POST_CACHE_NAME, USER_POSTS, posts_map);
			}
			plist = posts_map.get(post.getAuthorid());
			if(plist == null){
				plist = new LinkedList<Post>();
				posts_map.put(post.getAuthorid(), plist);
			}
			plist.add(post);
			log.info("put new post to cache");
			return true;
		}
		log.error("new post save error");
		return false;
	}
	
	
	/*
	 * 根据post id获得对应评论，并缓存
	 * @param postid
	 * @return List<PostComment>
	 */
	public List<PostComment> getCommentsByPostId(Long id){
		log.info("get comments of post:"+id);
		@SuppressWarnings("unchecked")
		Map<Long, List<PostComment>> comms_map = (HashMap<Long, List<PostComment>>) 
					CacheHelper.getCacheData(COMMENT_CACHE_NAME, POST_COMMENTS);
		List<PostComment> clist = null;
		if(comms_map == null){
			comms_map = new HashMap<Long, List<PostComment>>();
			CacheHelper.put(COMMENT_CACHE_NAME, POST_COMMENTS, comms_map);
		}
		clist = comms_map.get(id);
		if(clist == null){
			Post post = new Post();
			post.setId(id);
			clist = new PostDAO(post).getPostComment();			
			comms_map.put(id, clist);
			log.info("get commnts of "+id+" from db");
		}
		else{
			log.info("get commnts of "+id+" from cache");
		}
		return clist;
	}
	/*
	 * 单纯获得post 列表
	 * @param page
	 * @param limit
	 * @return List<Post>
	 */
	public List<Post> getPostList(int page, int limit){
		return new PostDAO().getPostList(page, limit);
	}

	/*
	 * 获得作者的posts,并放入缓存
	 * @param authorid
	 */
	public List<Post> getPostsOfAuthor(Long authorid){		
		log.info("get posts list of author:"+authorid);
		@SuppressWarnings("unchecked")
		Map<Long, List<Post>> posts_map = (HashMap<Long, List<Post>>) CacheHelper.
										  getCacheData(POST_CACHE_NAME, USER_POSTS);
		List<Post> plist = null;
		if(posts_map == null){
			posts_map = new HashMap<Long, List<Post>>();
			plist = new PostDAO().getPostsOfAuthor(authorid);
			posts_map.put(authorid, plist);
			CacheHelper.put(POST_CACHE_NAME, USER_POSTS, posts_map);
		}
		else{
			plist = posts_map.get(authorid);
			if(plist == null){
				plist = new PostDAO().getPostsOfAuthor(authorid);
				posts_map.put(authorid, plist);
				log.info("get posts list of author:"+authorid+"from db");
			}
			else{
				log.info("get posts list of author:"+authorid+"from cache");
			}
		}
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

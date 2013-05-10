package com.sharetour.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sharetour.cache.CacheHelper;
import com.sharetour.dao.PostActionDAO;
import com.sharetour.model.PostUserRelation;

public class PostLikeService {
	
	private static final String POST_USER_RELATION_CACHE = PostUserRelation.class.getSimpleName();
	private static final String POST_LIKE_KEY = "users_who_like_the_post";
	private static Log log = LogFactory.getLog(PostUserRelation.class);
	private PostActionDAO postactiondao;
	public PostLikeService(){
		this.postactiondao = new PostActionDAO();
	}
	
	/*
	 * set post like
	 * Cache_name:PostUserRelation
	 * Element_name:users_who_like_the_post
	 * 缓存结构：(map)
	 * pid--->PostUserRelation list
	 * @param PostUserRelation
	 * 
	 */
	@SuppressWarnings("unchecked")
	public boolean savePostLike(PostUserRelation relation){
		log.info(relation.getUid()+ " like "+ relation.getPid());
		if(postactiondao.postLike(relation)){
			Map<Long, List<PostUserRelation>> relmap = (Map<Long, List<PostUserRelation>>)CacheHelper.
					getCacheData(POST_USER_RELATION_CACHE, POST_LIKE_KEY);
			if(relmap == null){
				relmap = new HashMap<Long, List<PostUserRelation>>();
				relmap.put(relation.getUid(), new LinkedList<PostUserRelation>());
				CacheHelper.put(POST_USER_RELATION_CACHE, POST_LIKE_KEY, relmap);
			}
			List<PostUserRelation> list = relmap.get(relation.getPid());
			if(list == null){
				list = postactiondao.findUsersLikePostByPid(relation.getPid());
				if(list == null){
					list = new LinkedList<PostUserRelation>();
				}
				relmap.put(relation.getPid(), list);
			}
			list.add(relation);
			log.info("add post like to cache");
			return true;
		}
		return false;
	}
	/*
	 * undo post like
	 */
	@SuppressWarnings("unchecked")
	public boolean undoPostLike(PostUserRelation relation){
		log.info(relation.getUid() + "undo like" + relation.getPid());
		if(postactiondao.undoPostLike(relation)){
			Map<Long, List<PostUserRelation>> relmap = (Map<Long, List<PostUserRelation>>)CacheHelper.
					getCacheData(POST_USER_RELATION_CACHE, POST_LIKE_KEY);
			if(relmap != null){
				List<PostUserRelation> list = relmap.get(relation.getPid());
				if(list != null){
					for(int i=0; i<list.size(); i++){
						PostUserRelation rel = list.get(i);
						if(rel.getUid() == relation.getUid()){
							list.remove(i);
							log.info("remove relation from "+relation.getUid()+" like list");
							break;
						}
					}
					return true;				
				}				
			}
		}
		return false;
	}
	/*
	 * 检测用户是否like这篇post
	 * Cache_name:PostUserRelation
	 * Element_name:users_who_like_the_post
	 * 缓存结构:(map)
	 * pid--->PostUserRelation list
	 * 
	 * @param uid
	 * @param pid
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public boolean checkPostLike(Long uid, Long pid){	
		Map<Long, List<PostUserRelation>> relmap = (Map<Long, List<PostUserRelation>>)CacheHelper.
				getCacheData(POST_USER_RELATION_CACHE, POST_LIKE_KEY);
		if(relmap == null){
			relmap = new HashMap<Long, List<PostUserRelation>>();
			CacheHelper.put(POST_USER_RELATION_CACHE, POST_LIKE_KEY, relmap);
		}
		List<PostUserRelation> list = relmap.get(pid);
		if(list == null){
			list = postactiondao.findUsersLikePostByPid(pid);
			if(list == null){
				list = new LinkedList<PostUserRelation>();
			}
			relmap.put(pid, list);
		}
		for(PostUserRelation relation:list){
			if(relation.getUid() == uid){
				return true;
			}
		}			
		return false;
	}
	
	/*
	 * 获得这篇post的like数
	 */
	public int getPostLikeCount(Long pid){
		@SuppressWarnings("unchecked")
		Map<Long, List<PostUserRelation>> relmap = (Map<Long, List<PostUserRelation>>)CacheHelper.
				getCacheData(POST_USER_RELATION_CACHE, POST_LIKE_KEY);
		if(relmap == null){
			relmap = new HashMap<Long, List<PostUserRelation>>();
			CacheHelper.put(POST_USER_RELATION_CACHE, POST_LIKE_KEY, relmap);
		}
		List<PostUserRelation> list = relmap.get(pid);
		if(list == null){
			list = postactiondao.findUsersLikePostByPid(pid);
			if(list == null){
				list = new LinkedList<PostUserRelation>();
			}
			relmap.put(pid, list);
		}
		return list.size();
	}
}

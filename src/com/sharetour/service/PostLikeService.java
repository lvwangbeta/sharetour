package com.sharetour.service;

import java.util.LinkedList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sharetour.cache.CacheHelper;
import com.sharetour.dao.PostActionDAO;
import com.sharetour.model.PostUserRelation;

public class PostLikeService {
	
	private static final String POST_USER_RELATION_CACHE = PostUserRelation.class.getSimpleName();
	private static Log log = LogFactory.getLog(PostUserRelation.class);
	/*
	 * set post like
	 */
	@SuppressWarnings("unchecked")
	public boolean savePostLike(PostUserRelation relation){
		log.info(relation.getUid()+ " like "+ relation.getPid());
		PostActionDAO postactiondao = new PostActionDAO();
		if(postactiondao.postLike(relation)){
			List<PostUserRelation> list = (List<PostUserRelation>) CacheHelper.
					getCacheData(POST_USER_RELATION_CACHE, relation.getUid());
			if(list == null){
				list = new LinkedList<PostUserRelation>();
				CacheHelper.put(POST_USER_RELATION_CACHE, relation.getUid(), list);
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
		PostActionDAO postactiondao = new PostActionDAO();
		if(postactiondao.undoPostLike(relation)){
			List<PostUserRelation> list = (List<PostUserRelation>) CacheHelper.
					getCacheData(POST_USER_RELATION_CACHE, relation.getUid());
			if(list != null){
				for(int i=0; i<list.size(); i++){
					PostUserRelation rel = list.get(i);
					if(rel.getPid() == relation.getPid()){
						list.remove(i);
						log.info("remove relation from "+relation.getUid()+" like list");
						break;
					}
				}
				return true;				
			}
		}
		return false;
	}
	/*
	 * 检测用户是否like这篇post
	 */
	@SuppressWarnings("unchecked")
	public boolean checkPostLike(Long uid, Long pid){
		
		List<PostUserRelation> list = null;
		list = (List<PostUserRelation>) CacheHelper.getCacheData(POST_USER_RELATION_CACHE, uid);
		if(list == null){
			PostActionDAO postactiondao = new PostActionDAO();
			list = postactiondao.findLikePostsByUid(uid);
			CacheHelper.put(POST_USER_RELATION_CACHE, uid, list);
		}
		for(PostUserRelation relation:list){
			if(relation.getPid() == pid){
				return true;
			}
		}
		return false;
	}
	
}

package com.sharetour.service;

import java.util.LinkedList;
import java.util.List;

import com.sharetour.cache.CacheHelper;
import com.sharetour.dao.PostActionDAO;
import com.sharetour.model.PostUserRelation;

public class PostLikeService {
	/*
	 * set post like
	 */
	@SuppressWarnings("unchecked")
	public boolean savePostLike(PostUserRelation relation){
		PostActionDAO postactiondao = new PostActionDAO();
		if(postactiondao.postLike(relation)){
			List<PostUserRelation> list = (List<PostUserRelation>) CacheHelper.
					getCacheData(PostUserRelation.class.getSimpleName(), relation.getUid());
			if(list == null){
				list = new LinkedList<PostUserRelation>();
			}
			list.add(relation);
			return true;
		}
		return false;
	}
	/*
	 * undo post like
	 */
	@SuppressWarnings("unchecked")
	public boolean undoPostLike(PostUserRelation relation){
		PostActionDAO postactiondao = new PostActionDAO();
		if(postactiondao.undoPostLike(relation)){
			List<PostUserRelation> list = (List<PostUserRelation>) CacheHelper.
					getCacheData(PostUserRelation.class.getSimpleName(), relation.getUid());
			if(list != null){
				for(int i=0; i<list.size(); i++){
					PostUserRelation rel = list.get(i);
					if(rel.getPid() == relation.getPid()){
						list.remove(i);
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
		list = (List<PostUserRelation>) CacheHelper.getCacheData(PostUserRelation.class.getSimpleName(), uid);
		if(list == null){
			PostActionDAO postactiondao = new PostActionDAO();
			list = postactiondao.findLikePostsByUid(uid);
			CacheHelper.put(PostUserRelation.class.getSimpleName(), uid, list);
		}
		for(PostUserRelation relation:list){
			if(relation.getPid() == pid){
				return true;
			}
		}
		return false;
	}
	
}

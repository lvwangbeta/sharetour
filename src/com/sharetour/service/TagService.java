package com.sharetour.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sharetour.cache.CacheHelper;
import com.sharetour.dao.TagDAO;
import com.sharetour.model.Post;
import com.sharetour.model.PostTag;

public class TagService {
	
	private static final String TAG_CACHE_NAME = PostTag.class.getSimpleName();
	private static final String HOT_TAG_KEY = "hot_tag";
	private static final String POSTS_WITH_TAG = "posts_with_tag";
	private static int limit = 10;
	private TagDAO tagdao;
	public TagService(){
		this.tagdao = new TagDAO();
	}
	
	/*
	 * @param tagname
	 * 获得与tag相关的Post
	 * 缓存结构:(map)
	 * tagname---->post list
	 * @return List<Post>
	 */
	public List<Post> getPostsRelatedToTag(String tagname){
		@SuppressWarnings("unchecked")
		Map<String, List<Post>> tagpostmap = (Map<String, List<Post>>) CacheHelper.
				getCacheData(TAG_CACHE_NAME, POSTS_WITH_TAG);
		if(tagpostmap == null){
			tagpostmap = new HashMap<String, List<Post>>();
			CacheHelper.put(TAG_CACHE_NAME, POSTS_WITH_TAG, tagpostmap);
		}
		List<Post> list = tagpostmap.get(tagname);
		if(list == null){
			list = tagdao.getPostsRelatedToTag(tagname);
			tagpostmap.put(POSTS_WITH_TAG, list);
		}
		return list;
	}
	
	/*
	 * 采用默认的page limit参数
	 */
	public static List<PostTag> getHotTag(){
		return getHotTag(1, limit);	
	}
	
	/*
	 * 可以设置page limit以增加缓存存储
	 * 缓存结构:(map)
	 * page---->PostTag list
	 */
	public static List<PostTag> getHotTag(int page, int limit){
		@SuppressWarnings("unchecked")
		Map<Integer, List<PostTag>> hottagmap = (Map<Integer, List<PostTag>>) CacheHelper.
		getCacheData(TAG_CACHE_NAME, HOT_TAG_KEY);
		if(hottagmap == null){
			hottagmap = new HashMap<Integer, List<PostTag>>();
			CacheHelper.put(TAG_CACHE_NAME, HOT_TAG_KEY, hottagmap);
		}
		List<PostTag> list = hottagmap.get(page);
		if(list == null){
			list = TagDAO.getHotTag(page, limit);
			hottagmap.put(page, list);
		}
		return list;
	}
	

	
}

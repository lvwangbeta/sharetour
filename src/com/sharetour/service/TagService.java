package com.sharetour.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sharetour.cache.CacheHelper;
import com.sharetour.dao.TagDAO;
import com.sharetour.model.Post;
import com.sharetour.model.PostTag;

public class TagService {
	
	private static Log log = LogFactory.getLog(TagService.class.getSimpleName());
	private static final String TAG_CACHE_NAME = PostTag.class.getSimpleName();
	private static final String HOT_TAG_KEY = "hot_tag";
	private static final String POSTS_WITH_TAG = "posts_with_tag";
	private static int limit = 10;
	private TagDAO tagdao;
	
	public TagService(){
		this.tagdao = new TagDAO();
	}
	
	/*
	 * 根据tagname获得tid
	 */
	public Long getTagId(String tagname){
		log.info("getting tag id by:"+tagname);
		PostTag tag = tagdao.getTagId(tagname);
		if(tag != null){
			return tag.getId();
		}
		else{
			return 0L;
		}
	}
	
	/*
	 * 获得与tag set中的tag的id
	 */
	public List<Long> getTagsId(Set<String> tags){
		log.info("getting tags id list");
		List<PostTag> tlist = tagdao.getTagsId(tags);
		List<Long> tidlist = new ArrayList<Long>();
		for(PostTag tag: tlist){
			tidlist.add(tag.getId());
		}
		return tidlist;
	}
	
	/*
	 * @param tagname
	 * 获得与tag相关的Post
	 * 缓存结构:(map)
	 * tagname---->post list
	 * @return List<Post>
	 */
	public List<Post> getPostsRelatedToTag(String tagname){
		log.info("getting posts with tag: "+tagname);
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
			log.info("get posts with tag:"+tagname+" from db and put to cache");
		}
		else{
			log.info("get posts with tag:"+tagname+" from cache");
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
		log.info("getting hot tags of all date");
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
			log.info("get hot tags of all date from db and put to cache");
		}
		else{
			log.info("get hot tags of all date from cache");
		}
		return list;
	}
}

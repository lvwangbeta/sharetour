package com.sharetour.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sharetour.cache.CacheHelper;
import com.sharetour.dao.TagDAO;
import com.sharetour.model.Post;
import com.sharetour.model.PostTag;

public class TagService {
	
	private static final Log log = LogFactory.getLog(TagService.class.getSimpleName());	
	private static final String HOT_TAG = "HotTag";
	private static int limit = 10;
	private TagDAO tagdao = new TagDAO();
	
	/*
	 * 根据tagname获得tid
	 * @param tagname
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
	 * @param tags
	 */
	public List<Long> getTagsId(List<String> tags){
		log.info("getting tags id list");
		List<PostTag> tlist = tagdao.getTagsId(tags);
		List<Long> tidlist = new ArrayList<Long>();
		if(tidlist != null){
			for(PostTag tag: tlist){
				tidlist.add(tag.getId());
			}
		}
		return tidlist;
	}
	
	/*
	 * 获得与tag相关的Post 
	 * @param tagname
	 * @return List<Post>
	 */
	public List<Post> getPostsRelatedToTag(String tagname){
		log.info("getting posts with tag: "+tagname);
		return tagdao.getPostsRelatedToTag(tagname);
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
		List<PostTag> list = (List<PostTag>) CacheHelper.getCacheData(HOT_TAG, page);
		if(list == null){
			list = TagDAO.getHotTag(page, limit);
			CacheHelper.put(HOT_TAG, page, list);
			log.info("get hot tags of all date from db and put to cache");
		}
		else{
			log.info("get hot tags of all date from cache");
		}
		return list;
	}
}

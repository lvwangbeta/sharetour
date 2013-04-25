package com.sharetour.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sharetour.cache.CacheHelper;
import com.sharetour.dao.PostDAO;
import com.sharetour.model.Post;

public class HotPostService{
	private final static String ORDER = "likes";
	private final static String HOTPOST_CACHE_NAME = Post.class.getSimpleName(); 
	private final static String HOT_KEY = "hot_posts";
	private static Log log = LogFactory.getLog(HotPostService.class);
	private int limit = 10;
	private PostDAO hpdao;
	public HotPostService(){
		hpdao = new PostDAO();
	}
	/*
	 * 获得热门post，并存入缓存
	 * 默认从第一页开始
	 * @return
	 */
	public List<Post> getHotPost(){	
		return getHotPost(1, limit);
	}
	
	/*
	 * 获得热门post，并存入缓存
	 * 可以指定起始页和limit
	 * @return
	 */
	public List<Post> getHotPost(int page, int limit){
		log.info("get hot post from "+page+" limit "+limit);
		@SuppressWarnings("unchecked")
		Map<Integer, List<Post>> page_posts_map = (HashMap<Integer, List<Post>>) CacheHelper.
		getCacheData(HOTPOST_CACHE_NAME, HOT_KEY);
		List<Post> list = null;
		if(page_posts_map == null){
			log.info("get hot post from db");
			list = hpdao.getPostList(page, limit, ORDER);
			page_posts_map = new HashMap<Integer, List<Post>>();
			page_posts_map.put(page, list);
			CacheHelper.put(HOTPOST_CACHE_NAME, HOT_KEY, page_posts_map);
		}
		else{
			list = page_posts_map.get(page);
			if(list == null){
				log.info("get hot post from db");
				list = hpdao.getPostList(page, limit, ORDER);
				page_posts_map.put(page, list);
			}
			else{
				log.info("get hot post from cache");
			}
		}
		return list;
	}
	
}

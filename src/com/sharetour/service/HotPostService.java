package com.sharetour.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sharetour.cache.CacheHelper;
import com.sharetour.dao.PostDAO;
import com.sharetour.model.Post;

public class HotPostService{
	
	private static Log log = LogFactory.getLog(HotPostService.class);	
	private final static String ORDER = "likes";
	private final static String HOTPOST_CACHE_NAME = Post.class.getSimpleName(); 
	private final static String HOT_POST_KEY = "hot_posts";
	private final static String WEEK_HOT_POST_KEY = "hot_posts_this_week";
	private final static String MONTH_HOT_POST_KEY = "hot_posts_of_month";
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
	 * 获得所有post中的热门post，并分页存入缓存
	 * 可以指定起始页和limit
	 * @return
	 */
	public List<Post> getHotPost(int page, int limit){
		log.info("get hot post from "+page+" limit "+limit);
		@SuppressWarnings("unchecked")
		Map<Integer, List<Post>> page_posts_map = (HashMap<Integer, List<Post>>) CacheHelper.
		getCacheData(HOTPOST_CACHE_NAME, HOT_POST_KEY);
		List<Post> list = null;
		if(page_posts_map == null){
			log.info("get hot post from db");
			list = hpdao.getPostList(page, limit, ORDER);
			page_posts_map = new HashMap<Integer, List<Post>>();
			page_posts_map.put(page, list);
			CacheHelper.put(HOTPOST_CACHE_NAME, HOT_POST_KEY, page_posts_map);
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
	
	/*
	 * 获得本周热门post
	 * 并分页缓存
	 * 缓存结构:(map)
	 * page--->Post list
	 */
	public List<Post> getHostPostOfThisWeek(int page, int limit){
		log.info("getting this week's hot posts...");
		@SuppressWarnings("unchecked")
		Map<Integer, List<Post>> whotposts = (Map<Integer, List<Post>>) CacheHelper.
		getCacheData(HOTPOST_CACHE_NAME, WEEK_HOT_POST_KEY);
		if(whotposts == null){
			whotposts = new HashMap<Integer, List<Post>>();
			CacheHelper.put(HOTPOST_CACHE_NAME, WEEK_HOT_POST_KEY, whotposts);
		}
		List<Post> list = whotposts.get(page);
		if(list == null){
			log.info("get this week's hot posts from db");
			list = hpdao.getHotPostOfThisWeek(page, limit);
			whotposts.put(page, list);
			log.info("put this week's hot posts to cache");
		}
		else{
			log.info("get this week's hot posts from cache");
		}
		return list;
	}
	
	
	/*
	 * 获得当月热门post
	 * 缓存结构:(map)
	 * page---->Post list
	 */
	public List<Post> getHotPostOfMonth(int page, int limit){
		log.info("getting month hot posts...");
		Calendar cal = Calendar.getInstance();
		int year  = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		@SuppressWarnings("unchecked")
		Map<Integer, List<Post>> mhotposts = (Map<Integer, List<Post>>) CacheHelper.
		getCacheData(HOTPOST_CACHE_NAME, MONTH_HOT_POST_KEY);
		if(mhotposts == null){
			mhotposts = new HashMap<Integer, List<Post>>();
			CacheHelper.put(HOT_POST_KEY, MONTH_HOT_POST_KEY, mhotposts);
		}
		List<Post> list = mhotposts.get(page);
		if(list == null){
			log.info("get month hot posts from db");
			list = hpdao.getHotPostOfMonth(year, month, page, limit);
			mhotposts.put(page, list);
			log.info("put month hot posts to cache");
		}
		else{
			log.info("get month hot posts from cache");
		}
		return list;
	}
}

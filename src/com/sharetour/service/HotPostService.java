package com.sharetour.service;

import java.util.Calendar;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sharetour.cache.CacheHelper;
import com.sharetour.dao.PostDAO;
import com.sharetour.model.Post;

public class HotPostService{
	
	private static Log log = LogFactory.getLog(HotPostService.class);	
	private final static String ORDER = "likes";
	private final static String HOTPOST = "HotPost"; 
	private final static String WEEK_HOT_POST = "WeekHotPost";
	private final static String MONTH_HOT_POST = "WeekHotPost";
	
	private static int LIMIT = 10;
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
		return getHotPost(1, LIMIT);
	}
	
	public List<Post> getHotPostOfPage(int page){
		return getHotPost(page, LIMIT);
	}
	public List<Post> getHotPostOfPage(int page, int limit){
		return getHotPost(page, limit);
	}
	
	/*
	 * 获得所有post中的热门post，并分页存入缓存
	 * 可以指定起始页和limit
	 * @return
	 */
	public List<Post> getHotPost(int page, int limit){
		log.info("get hot post from "+page+" limit "+limit);
		@SuppressWarnings("unchecked")
		List<Post> hp = (List<Post>) CacheHelper.
		getCacheData(HOTPOST, page);
		
		if(hp == null){
			log.info("get hot post from db");
			hp = hpdao.getPostList(page, limit, ORDER);
			CacheHelper.put(HOTPOST, page, hp);
		}
		else{
			log.info("get hot post from cache");
		}
		return hp;
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
		List<Post> whotposts = (List<Post>) CacheHelper.
		getCacheData(WEEK_HOT_POST, page);

		if(whotposts == null){
			log.info("get this week's hot posts from db");
			whotposts = hpdao.getHotPostOfThisWeek(page, limit);
			CacheHelper.put(WEEK_HOT_POST, page, whotposts);
			log.info("put this week's hot posts to cache");
		}
		else{
			log.info("get this week's hot posts from cache");
		}
		return whotposts;
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
		List<Post> mhotposts = (List<Post>) CacheHelper.
		getCacheData(MONTH_HOT_POST, page);

		if(mhotposts == null){
			log.info("get month hot posts from db");
			mhotposts = hpdao.getHotPostOfMonth(year, month, page, limit);
			CacheHelper.put(MONTH_HOT_POST, page, mhotposts);
			log.info("put month hot posts to cache");
		}
		else{
			log.info("get month hot posts from cache");
		}
		return mhotposts;
	}
}

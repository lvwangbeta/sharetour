package com.sharetour.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sharetour.cache.CacheHelper;
import com.sharetour.dao.FollowDAO;
import com.sharetour.dao.UserDAO;
import com.sharetour.model.Follower;
import com.sharetour.model.Following;
import com.sharetour.model.UserInfo;

public class FollowService {
	
	private static final Log log = LogFactory.getLog(FollowService.class);
	private static final String FOLLOWER_CACHE = Follower.class.getSimpleName();
	private static final String FOLLOWING_CACHE = Following.class.getSimpleName();
	private FollowDAO followDao = new FollowDAO();
	private UserDAO userDao = new UserDAO();
	/*
	 * 处理关注请求
	 * @param uid
	 * @param followingid
	 * 
	 */
	public boolean follow(Long uid, Long followingid) {
		log.info("handle "+uid+" follow " +followingid);
		if(followDao.saveFollowing(uid, followingid) &&
		   followDao.saveFollower(followingid, uid)) {
			log.info(uid + " follow " + followingid);
			log.info(followingid + " has been followed by " + uid);
			
			putInCache(FOLLOWING_CACHE, uid, followingid);
			log.info("put " + uid + "'s new following " + followingid + " to cache");
			putInCache(FOLLOWER_CACHE, followingid, uid);
			log.info("put " + followingid + "'s new follower " + uid + " to cache");
			return true;
		}
		return false;
	}
	
	/*
	 * 撤销关注关系
	 * @param uid
	 * @param followingid 
	 */
	public boolean undoFollow(Long uid, Long followingid) {		
		log.info("handle " + uid +" undo follow " + followingid);
		if(followDao.deleteFollowing(uid, followingid) && 
		   followDao.deleteFollower(followingid, uid)){
			log.info(uid + " undo follow " + followingid);
			log.info(followingid + " lose follower " + uid);
			
			removeFromCache(FOLLOWING_CACHE, uid, followingid);
			log.info("remove " + uid + "'s following " + followingid);
			removeFromCache(FOLLOWER_CACHE, followingid, uid);
			log.info("remove " + followingid + "'s follower " + uid);
			return true;
		}
		return false;
	}
	
	private void putInCache(String cache, Long a, Long b) {
		@SuppressWarnings("unchecked")
		List<UserInfo> follow = (List<UserInfo>) CacheHelper.getCacheData(cache, a);
		if(follow == null)
			follow = new ArrayList<UserInfo>();
		follow.add(userDao.findUserById(b));
	}
	
	private void removeFromCache(String cache, Long a, Long b) {
		@SuppressWarnings("unchecked")
		List<UserInfo> follow = (List<UserInfo>) CacheHelper.getCacheData(cache, a);
		if(follow != null) {
			Iterator<UserInfo> it = follow.iterator();
			while(it.hasNext()) {
				UserInfo user = it.next();
				if(user.getId() == b)
					it.remove();
			}
		}	
	}
	/*
	 * 获得用户的following表
	 */
	public List<UserInfo> getFollowingOfUser(Long uid) {
		log.info(uid + "is getting his following ");
		@SuppressWarnings("unchecked")
		List<UserInfo> following = (List<UserInfo>) CacheHelper.getCacheData(FOLLOWING_CACHE, uid);
		if(following == null || following.size() == 0) {
			log.info(uid + "'s following not in cache");
			following = followDao.getFollowingOfUser(uid);
			log.info("getting " + uid + "'s following from db");
			if(following != null && following.size() != 0) {
				log.info("putting " + uid + "'s following into cache ");
				CacheHelper.put(FOLLOWING_CACHE, uid, following);
			} else {
				log.info(uid + "has not following now");
			}
				
		}
		return following;
	}
	
	/*
	 * 获得用户的followers表
	 */
	public List<UserInfo> getFollowersOfUser(Long uid) {
		log.info("getting " + uid + "'s followers");
		@SuppressWarnings("unchecked")
		List<UserInfo> followers = (List<UserInfo>) CacheHelper.getCacheData(FOLLOWER_CACHE, uid);
		if(followers == null || followers.size() == 0) {
			log.info(uid + "'s followers not in cache");
			log.info("getting " + uid + "'s followers from db");
			followers = followDao.getFollowersOfUser(uid);
			if(followers != null && followers.size() != 0) {
				log.info("putting " +uid+"'s folloers into cache");
				CacheHelper.put(FOLLOWER_CACHE, uid, followers);
			} else {
				log.info(uid + "has not followers yet");
			}
		}
		return followers;
	}
	/*
	 * 判断follow关系
	 * if followed return true
	 * @param uid
	 * @param followingid
	 */
	public boolean checkFollowingRelation(Long uid, Long followingid) {
		List<UserInfo> followings = getFollowingOfUser(uid);
		for(UserInfo following : followings) {
			if(following.getId() == followingid) {
				log.info(uid + " has been followed " + followingid);
				return true;
			}
		}
		log.info(uid + " not follow " + followingid); 
		return false;
	}
}

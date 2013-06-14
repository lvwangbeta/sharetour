package com.sharetour.dao;

import java.util.ArrayList;
import java.util.List;
import com.sharetour.model.Follower;
import com.sharetour.model.Following;
import com.sharetour.model.UserInfo;
import com.sharetour.util.QueryHelper;

public class FollowDAO {

	/*
	 * 保存被关注关系
	 */
	public boolean saveFollower(Long uid, Long followerid) {
		Follower follower = new Follower();
		QueryHelper helper = new QueryHelper();
		follower.setQueryHelper(helper);
		follower.setUid(uid);
		follower.setFollowerid(followerid);
		Long re = follower.Save();
		helper.closeConnection();
		if(re > 0)
			return true;
		return false;
			
	}
	/*
	 * 取消被关注关系
	 */
	public boolean deleteFollower(Long uid, Long followerid) {
		QueryHelper helper = new QueryHelper();
		int re = helper.delete("delete from followers where uid=? and followerid=?", uid, followerid);
		helper.closeConnection();
		if(re > 0)
			return true;
		return false;
	}
	/*
	 * 保存关注关系
	 */
	public boolean saveFollowing(Long uid, Long followingid) {
		Following following = new Following();
		QueryHelper helper = new QueryHelper();
		following.setQueryHelper(helper);
		following.setUid(uid);
		following.setFollowingid(followingid);
		Long re = following.Save();
		helper.closeConnection();
		if(re > 0)
			return true;
		return false;
	}
	/*
	 * 取消关注关系
	 */
	public boolean deleteFollowing(Long uid, Long followingid) {
		QueryHelper helper = new QueryHelper();
		int re = helper.delete("delete from following where uid=? and followingid=?", uid, followingid);
		helper.closeConnection();
		if(re > 0)
			return true;
		return false;
	}
	
	public List<UserInfo> getFollowingOfUser(Long uid) {
		QueryHelper helper = new QueryHelper();
		List<UserInfo> following = new ArrayList<UserInfo>();
		following = helper.executeQuery(UserInfo.class, 
				"select users.id, users.username, gender,nickname,intro " +
				"from users,following " +
				"where following.uid=? and followingid=users.id", uid);
		return following;
	}
	
	public List<UserInfo> getFollowersOfUser(Long uid) {
		QueryHelper helper = new QueryHelper();
		List<UserInfo> followers = new ArrayList<UserInfo>();
		followers = helper.executeQuery(UserInfo.class,
				"select users.id, users.username, gender,nickname,intro " +
				"from users, followers " +
				"where followers.uid=? and followerid=users.id", uid);
		return followers;
	}

}

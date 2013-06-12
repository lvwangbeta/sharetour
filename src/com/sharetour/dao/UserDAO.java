package com.sharetour.dao;

import java.util.ArrayList;
import java.util.List;

import com.sharetour.model.UserInfo;
import com.sharetour.util.QueryHelper;

public class UserDAO {
	public UserDAO(){
		
	}
	/*
	 * 检测用户名是否重复
	 */
	public UserInfo checkUsername(String username){	
		QueryHelper helper = new QueryHelper();
		UserInfo user = helper.get(UserInfo.class, 
								   "select username from users where username=?", 
								   username);
		helper.closeConnection();
		return user;
	}
	
	public UserInfo findUserById(Long uid) {
		QueryHelper helper = new QueryHelper();
		UserInfo user = helper.get(UserInfo.class, 
								   "select username,id from users where id=?", 
								   uid);
		helper.closeConnection();
		return user;
	}
	
	
	/*
	 * 用户信息查找
	 */
	public UserInfo find(String username, String password){
		QueryHelper helper = new QueryHelper();
		UserInfo userinfo = helper.get(
				  UserInfo.class,
				  "select * from users where username=? and password=?", 
				  new Object[]{username, password});
		if(userinfo != null)
			userinfo.setPassword("");
		helper.closeConnection();
		return userinfo;
	}
	
	public int getAuthorid(String username){
		QueryHelper helper = new QueryHelper();
		UserInfo userinfo = helper.get(UserInfo.class,
				"select * from users where username=?", 
				username);
		helper.closeConnection();
		return (int)userinfo.getId();
	}
	
	/*
	 * 保存新用户
	 */
	public Long registerNewUser(UserInfo user){
		QueryHelper helper = new QueryHelper();
		user.setQueryHelper(helper);
		Long uid = 0L;
		uid = user.Save();
		helper.closeConnection();
		return uid;
	}
	
	public List<UserInfo> getUsersOrderByPostCount(int limit) {
		QueryHelper helper = new QueryHelper();
		List<UserInfo> popusers = new ArrayList<UserInfo>();
		popusers = helper.executeQuery(UserInfo.class, 
								  "select users.id from users left join posts on users.id=posts.authorid " +
								  "group by users.id order by count(*) desc limit ?", limit);
		helper.closeConnection();
		return popusers;
	}
	
	public int updateAccount(Long uid, String nickname, String intro) {
		QueryHelper helper = new QueryHelper();
		int rows_effected = helper.update("update users set nickname=? , intro=? where id=?", nickname, intro, uid);
		helper.closeConnection();
		return rows_effected;
	}
}

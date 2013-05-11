package com.sharetour.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.sharetour.db.ConnectionPool;
import com.sharetour.model.UserInfo;
import com.sharetour.util.QueryHelper;

public class UserDAO {
	public UserDAO(){
		
	}
	/*
	 * 检测用户名是否重复
	 */
	public UserInfo checkUsername(String username){
		Connection con = ConnectionPool.getInstance().getConnection();
		PreparedStatement pstm;
		ResultSet res = null;
		try {
			pstm = con.prepareStatement(
					"select username from users where username=?");
			pstm.setString(1, username);
			res = pstm.executeQuery();
			if(res.next())
			{
				UserInfo user = new UserInfo();
				user.setUsername(username);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
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
	public boolean registerNewUser(UserInfo user){
		QueryHelper helper = new QueryHelper();
		user.setQueryHelper(helper);
		if(user.Save() > 0){
			helper.closeConnection();
			return true;
		}
		else{
			helper.closeConnection();
			return false;
		}
	}
}

package com.sharetour.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sharetour.db.ConnectionPool;
import com.sharetour.model.UserInfo;
import com.sharetour.util.QueryHelper;

/*
 * 用户信息服务类
 * 完成注册信息检测，登录信息检测任务
 */
public class UserService {

	/*
	 * 检测用户名是否重复
	 */
	public static boolean checkUsername(String username){
		Connection con = ConnectionPool.getInstance().getConnection();
		PreparedStatement pstm;
		ResultSet res = null;
		try {
			pstm = con.prepareStatement(
					"select username from users where username=?");
			pstm.setString(1, username);
			res = pstm.executeQuery();
			if(res.next())
				return false;			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
		
	}
	/*
	 * 检测邮箱格式是否合法
	 */
	public static boolean emailValidate(String email){
		return false;
	}
	
	/*
	 * 检测邮箱是否存在
	 */
	public static boolean checkEmailExist(String email){
		return false;
	}
	
	/*
	 * 登录信息检测
	 */
	public static UserInfo loginCheck(String username, String password){
		QueryHelper helper = new QueryHelper();
		UserInfo userinfo = helper.get(
				  UserInfo.class, username,
				  "select * from users where username=? and password=?", 
				  new Object[]{username, password});
		return userinfo;
	}
	
}

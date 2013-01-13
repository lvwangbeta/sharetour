package com.sharetour.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sharetour.db.ConnectionPool;
import com.sharetour.model.User;


public class RegisterDAO extends BaseDAO{
	private User user;
	public RegisterDAO(User user)
	{
		this.user = user;
	}
	/*
	 * 查找用户名是否已经存在
	 * 如果存在返回true
	 * 不存在返回false
	 * search 
	 */
	public boolean search()
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		con = ConnectionPool.getInstance().getConnection();
		try {
			pstm = con.prepareStatement("select * from userinfo where username=?");
			pstm.setString(1, user.getUsername());
			res = pstm.executeQuery();
			if(res.next())
			{
				return true;
			}
			else
			{				
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		finally{
			closeCon(con, pstm, res);
		}
		return true;
	}
	
	/*
	 * 存储用户信息
	 * save
	 */
	public boolean save()
	{
		Connection con = null;
		PreparedStatement pstm = null;
		con = ConnectionPool.getInstance().getConnection();
		
		try {
			pstm = con.prepareStatement("insert into userinfo(username, password, email) values(?,?,?)");
			pstm.setString(1, user.getUsername());
			pstm.setString(2, user.getPassword());
			pstm.setString(3, user.getEmail());
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		finally{
			closeCon(con, pstm, null);
		}
		
	}
}

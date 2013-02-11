package com.sharetour.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sun.misc.BASE64Encoder;

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
	 * 加密用户的明文密码
	 */
	public String EncoderByMD5(String password)
	{
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64 = new BASE64Encoder();
			String encodedpwd = base64.encode(md5.digest(password.getBytes("utf-8")));
			return encodedpwd;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			return password;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return password;
		}
		
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
			pstm.setString(2, EncoderByMD5(user.getPassword()));
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

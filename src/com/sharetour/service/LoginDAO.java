package com.sharetour.service;

import com.sharetour.db.ConnectionPool;
import com.sharetour.model.*;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

import sun.misc.BASE64Encoder;

public class LoginDAO extends BaseDAO{
	private User user;
	public LoginDAO(User user)
	{
		this.user = user;
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
	public User find()
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		con = ConnectionPool.getInstance().getConnection();
		try {
			pstm = con.prepareStatement("select username, email, head, id from userinfo where username=? and password=?");
			pstm.setString(1, user.getUsername());
			pstm.setString(2, EncoderByMD5(user.getPassword()));
			res = pstm.executeQuery();
			if(res.next())
			{
				return new User(res.getString("username"), 
								res.getString("email"),
								res.getString("head"),
								res.getInt("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		finally{
			closeCon(con, pstm, res);
		}
		return null;
		
		
	}
	
}

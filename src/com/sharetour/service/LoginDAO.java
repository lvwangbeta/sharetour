package com.sharetour.service;

import com.sharetour.db.ConnectionPool;
import com.sharetour.model.*;
import java.sql.*;

public class LoginDAO extends BaseDAO{
	private User user;
	public LoginDAO(User user)
	{
		this.user = user;
	}
	public boolean find()
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		con = ConnectionPool.getInstance().getConnection();
		try {
			pstm = con.prepareStatement("select * from userinfo where username=? and password=?");
			pstm.setString(1, user.getUsername());
			pstm.setString(2, user.getPassword());
			res = pstm.executeQuery();
			if(res.next())
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		finally{
			closeCon(con, pstm, res);
		}
		return false;
		
		
	}
	
}

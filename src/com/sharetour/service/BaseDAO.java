package com.sharetour.service;

import java.sql.*;
public class BaseDAO {
	public void closeCon(Connection con, PreparedStatement pstm, ResultSet res)
	{
		if(con != null)
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				con = null;
			}
		}
		if(pstm != null)
		{
			try {
				pstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				pstm = null;
			}
		}
		if(res != null)
		{
			try {
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				res = null;
			}
		}
			
	}
}

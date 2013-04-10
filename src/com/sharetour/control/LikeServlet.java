package com.sharetour.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import com.sharetour.model.*;
import com.sharetour.db.*;

/**
 * Servlet implementation class LikeServlet
 */
public class LikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		// not login
		if(user == null)
		{
			out.write("-1");
			out.close();
			return;
		}
		
		String pid = request.getParameter("pid"); //get like pid
		if(pid == null)
		{
			out.write("0");
			out.close();
			return;
		}
		
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
	
		try {
			con = ConnectionPool.getInstance().getConnection();
			pstm = con.prepareStatement("update posts set likes=likes+1 where id=?");
			pstm.setInt(1, Integer.parseInt(pid));
			pstm.executeUpdate();
			out.write("1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.write("0");
		}
		out.close();
		if(con != null)
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				con = null;
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

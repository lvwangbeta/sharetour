package com.sharetour.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sharetour.db.ConnectionPool;

/**
 * Servlet implementation class NewPostServlet
 */
public class NewPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String content = request.getParameter("content");
		String title   = request.getParameter("title");
		String tags	   = request.getParameter("tags");
		String cover   = null; //日志封面
		
		/*
		 * test data
		 */
		System.out.println(content);
		System.out.println(title + "\n" + tags);
		
		if(tags == null || tags.length() == 0)
		{
			tags = "default";
		}
		
		if((title == null || title.length() == 0)
			|| (content == null || content.length() == 0))
		{
			return ;
		}
		
		/*
		 * 提取img元素
		 */
		Pattern pattern;
		Matcher matcher;
		String imgRex = "<img.*src=(.*?)[^>]*?>";
		pattern = Pattern.compile(imgRex, Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(content);
		if(matcher.find())
		{
			cover = matcher.group();
		}
		
		Connection con = null;
		PreparedStatement pstm = null;
		
		try {
			con = ConnectionPool.getInstance().getConnection();
			pstm = con.prepareStatement("insert into posts(title, content, tags, cover) values(?,?,?,?)");
			pstm.setString(1, title);
			pstm.setString(2, content);
			pstm.setString(3, tags);
			pstm.setString(4, cover);
			pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		response.sendRedirect("/index.jsp");
		return ;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.sharetour.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sharetour.util.Action;
import com.sharetour.util.ActionFactory;

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
		Action newpostaction = ActionFactory.getAction("newpost");
		String view = newpostaction.execute(request);
		if(view == "success"){
			response.sendRedirect("/u/space");
		}
		else if(view == "error"){
			response.sendRedirect("/u/space");
		}
		
		
		/*
		request.setCharacterEncoding("utf-8");
		String content = request.getParameter("content");
		String title   = request.getParameter("title");
		String summary = request.getParameter("summary");
		String tags	   = request.getParameter("tags");
		String cover   = null; //��־����
		

		System.out.println(content);
		System.out.println(title + "\n" + tags);
		
		if(tags == null || tags.length() == 0)
		{
			tags = "default";
		}
		
		if((title == null || title.length() == 0)
			|| (content == null || content.length() == 0)
			|| (summary == null || summary.length() == 0))
		{
			return ;
		}
		
		String[] tagsArray = tags.split(" ");

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
		ResultSet res; 
		
		try {
			int pid = 0, tid = 0;
			con = ConnectionPool.getInstance().getConnection();
			pstm = con.prepareStatement("insert into posts(title, content, tags, cover, summary, authorid) values(?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, title);
			pstm.setString(2, content);
			pstm.setString(3, tags);
			pstm.setString(4, cover);
			pstm.setString(5, summary);
			pstm.setInt(6, ((User)(request.getSession().getAttribute("user"))).getId());
			int affectedRows = pstm.executeUpdate();
			if(affectedRows == 0)
			{
				return;
			}
			res = pstm.getGeneratedKeys();
			if(res.next())
			{
				pid = res.getInt(1);
			}
			else
			{
				return;
			}
			

			con.setAutoCommit(false);
			for(int i=0; i<tagsArray.length; i++)
			{
				System.out.println(tagsArray[i]+"==");
				pstm = con.prepareStatement("select id from tags where tag=?");
				pstm.setString(1, tagsArray[i]);
				res = pstm.executeQuery();

				if(res.next())
				{
					tid = res.getInt("id");
				}
				else
				{
					pstm = con.prepareStatement("insert into tags(tag) values(?)",
							Statement.RETURN_GENERATED_KEYS);
					pstm.setString(1, tagsArray[i]);
					affectedRows = pstm.executeUpdate();
					if(affectedRows == 0)
					{
						con.rollback();
						return;
					}
					res = pstm.getGeneratedKeys();
					if(res.next())
					{
						tid = res.getInt(1);
					}	
				}
				
		
				pstm = con.prepareStatement("insert into PostTagRelation(pid, tid) values(?,?)");
				pstm.setInt(1, pid);
				pstm.setInt(2, tid);
				affectedRows = pstm.executeUpdate();
				if(affectedRows == 0)
				{
					con.rollback();
					return;
				}
				
			}
			con.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		finally{
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
		}

		response.sendRedirect("u/space");
		return ;
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

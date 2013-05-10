package com.sharetour.control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sharetour.util.Action;
import com.sharetour.util.ActionFactory;

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
		Action likeAction = ActionFactory.getAction("like");
		String view = likeAction.execute(request);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		if("success".equals(view)){
			out.write("success");
		}
		else if("nologin".equals(view)){
			out.write("nologin");
		}
		else{
			out.write("error");
		}
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

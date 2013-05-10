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
 * Servlet implementation class CommentServlet
 */
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Action commentAction = ActionFactory.getAction("comment");
		String view = commentAction.execute(request);
		//String basePath = request.getScheme()+"://"+request.getServerName()+request.getContextPath();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		if("success".equals(view)){
			out.write("success");
		}
		else if("nologin".equals(view)){
			out.write("nologin");
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

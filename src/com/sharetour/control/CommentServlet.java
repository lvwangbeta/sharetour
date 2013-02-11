package com.sharetour.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sharetour.model.Comment;
import com.sharetour.model.User;
import com.sharetour.service.CommentDAO;

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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String comment = request.getParameter("comment");
		String pid = request.getParameter("pid");
		String parentcid = request.getParameter("parentcid"); //parent comment id
		User user = (User)request.getSession().getAttribute("user");
		
		if(user == null)
		{
			out.write("-1");
			out.close();
			return;
		}
		/*
		 * test data
		 */
		System.out.println(pid+"="+comment);
		
		if((pid == null || pid.length() == 0) ||
			(comment == null|| comment.length() ==0))
			{
				out.write("0");
				out.close();
				return;
			}
		Comment cm = new Comment();
		cm.setPid(Integer.parseInt(pid));
		cm.setAuthorid(user.getId());
		cm.setUsername(user.getUsername());
		cm.setComment(comment);
		
		int res = new CommentDAO().save(cm);
		System.out.println(res);
		if(res > 0)
		{
			out.write(String.valueOf(res));
		}
		else
		{
			out.write("0");
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

package com.sharetour.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sharetour.model.User;
import com.sharetour.service.RegisterDAO;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String conform = request.getParameter("conform");
		String email    = request.getParameter("email");
		
		if((username == null || username.length() == 0) 
				|| (password == null || password.length() == 0)
				|| (email == null || email.length() == 0)
				|| (conform == null || conform.length() == 0))
			{
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
		
		if(!password.equals(conform))
		{
			request.setAttribute("tips", "密码验证不一致");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return ;
		}
		RegisterDAO register = new RegisterDAO(new User(username, password, email));
		//发现用户名重复
		if(register.search())
		{
			request.setAttribute("tips", "用户名已存在");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return ;
		}
		//存储用户信息
		if(register.save())
		{
			request.getSession().setAttribute("user", new User(username, password,email));
			response.sendRedirect("index.jsp");
			return;
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

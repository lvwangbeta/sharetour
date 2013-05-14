package com.sharetour.control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImgUploadServlet
 */
public class ImgUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String temppath;
    
    public void init()
    {
    	temppath = getInitParameter("temppath");
    	temppath = getServletContext().getRealPath(temppath);
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImgUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ImgAction imgAction = new ImgAction();
		String url = imgAction.saveImg(request, temppath);
		String resp = null;
		if(url != null && url.length() != 0){
			resp = "{\"success\":true,\"url\":\""+url+"\"}";
		}
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();	
		out.write(resp);
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

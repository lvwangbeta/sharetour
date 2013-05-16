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
		String filename = imgAction.saveImg(request, temppath);
		String url = "/imgs?id=" + filename;
		//String resp = null;
		
		/*
		@SuppressWarnings("unchecked")
		List<String> photos = (List<String>) request.getSession().getAttribute("photos");
		if(photos == null){
			photos = new ArrayList<String>();
			request.getSession().setAttribute("photos", photos);
		}
		photos.add(filename);
		
		if(url != null && url.length() != 0){
			resp = "{\"success\":true,\"url\":\""+url+"\"}";
		}
		response.setContentType("application/json; charset=utf-8");
		*/
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String callback = request.getParameter("CKEditorFuncNum");
		out.println("<script type=\"text/javascript\">");
		out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
		+ ",'" + url + "',''" + ")");
		out.println("</script>");	
		//out.write(resp);
		out.close();

	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

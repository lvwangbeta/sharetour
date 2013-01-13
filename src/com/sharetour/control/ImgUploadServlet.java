package com.sharetour.control;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;

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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4 * 1024);			//4M
		factory.setRepository(new File(temppath));  //临时目录
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(4*1024*1024);
		
		try {
			List items = upload.parseRequest(request);
			Iterator it = items.iterator(); 
			while(it.hasNext())
			{
				FileItem item = (FileItem)it.next();
				if(item.isFormField())
				{
					processFormField(item);
				}
				else
				{
					String url = processUploadFile(item);
					String callback = request.getParameter("CKEditorFuncNum");
					response.getWriter().println("<script type=\"text/javascript\">");
					response.getWriter().println("window.parent.CKEDITOR.tools.callFunction(" + callback
					+ ",'" + url + "',''" + ")");
					response.getWriter().println("</script>");
				}
			}
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void processFormField(FileItem item)
	{
		String name = item.getFieldName();
		String value = item.getString();
		System.out.println(name+":"+value);
	}
	
	/*
	 * 存储上传的文件并返回缩略图地址url
	 */
	public String processUploadFile(FileItem item) throws Exception
	{
		String filename = item.getName();
		int index = filename.lastIndexOf(".");
		filename = System.currentTimeMillis() + filename.substring(index);
		System.out.println(filename);
		long filesize = item.getSize();
		if("".equals(filename))
			return null;
		//System.out.println(storepath + "/" + filename);
		//File file = new File(storepath + "/" + filename);
		//item.write(file);	
		
		MongoClient client = new MongoClient("172.30.48.73", 25273);
		DB db = client.getDB("db");
		db.authenticate("b2b10f1d-b31c-4a10-b6d7-b4914bb48292", "de446439-2fc5-43ec-8c0f-7568932b3d27".toCharArray());
		GridFS photo = new GridFS(db, "imgs");
		GridFSInputFile in = null;
		in = photo.createFile(item.getInputStream());
		in.setFilename(filename);
		in.save();
		client.close();
		String url = "imgs?id=" + filename;
		System.out.println(url);
		return url;
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

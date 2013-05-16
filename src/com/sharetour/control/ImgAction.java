package com.sharetour.control;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sharetour.service.ImgService;
import com.sharetour.util.Action;

public class ImgAction implements Action{

	private static final Log log = LogFactory.getLog(ImgAction.class);
	private static String TEMPPATH;	
	
	public String saveImg(HttpServletRequest request, String temppath){
		TEMPPATH = temppath;
		return execute(request);
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4 * 1024);			//4M
		factory.setRepository(new File(TEMPPATH));  //
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(4*1024*1024);

		
		ImgService imgService = new ImgService();
		String imgUrl = null;
		
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> it = items.iterator(); 
			while(it.hasNext())
			{
				FileItem item = (FileItem)it.next();
				if(item.isFormField())
				{
					
				}
				else
				{
					/*
					 * handle only one image
					 */
					imgUrl = imgService.processUploadFile(item);
					/*
					String callback = request.getParameter("CKEditorFuncNum");
					response.getWriter().println("<script type=\"text/javascript\">");
					response.getWriter().println("window.parent.CKEDITOR.tools.callFunction(" + callback
					+ ",'" + url + "',''" + ")");
					response.getWriter().println("</script>");
					*/
					log.info("img url:"+imgUrl);
				}
			}
			
		} catch (FileUploadException e) {
			log.info("img upload error");
			return null;
		} catch (Exception e) {
			log.info("img upload error");
			return null;
		}
		return imgUrl;
	}

}

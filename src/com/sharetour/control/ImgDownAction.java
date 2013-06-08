package com.sharetour.control;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.mongodb.gridfs.GridFSDBFile;
import com.sharetour.service.ImgService;
import com.sharetour.util.ImgTools;


public class ImgDownAction {

	private static final Log log = LogFactory.getLog(ImgDownAction.class);
	
	public void downLoadImg(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");   //[0-9]+.jpg
		String height = request.getParameter("height");
		String width = request.getParameter("width");
		if(id == null){
			log.error("img name null");
			return ;
		}
		
		String coll = request.getParameter("coll");
		if(coll == null)
			coll = "imgs";
		ImgService imgService = new ImgService();
		GridFSDBFile imgout = imgService.getImgFrom(coll, id);
		if(imgout == null){
			return;
		}
		String ext = imgout.getContentType();
		String type = "image/" + ext;
		response.setContentType(type);
		
		ServletOutputStream out = response.getOutputStream();
		BufferedImage img = null;
		if((height != null && height.length() != 0) &&
		   (width != null && width.length() != 0)){
			int h = Integer.parseInt(height);
			int w = Integer.parseInt(width);
			img = imgService.resizeImg(ImageIO.read(imgout.getInputStream()), w, h);
		}
		else{
			img = ImageIO.read(imgout.getInputStream());
		}
		ImgTools.write(img, ext, out);
	}
}

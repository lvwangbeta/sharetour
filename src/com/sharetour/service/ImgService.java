package com.sharetour.service;


import java.awt.image.BufferedImage;

import org.apache.commons.fileupload.FileItem;
import com.mongodb.gridfs.GridFSDBFile;
import com.sharetour.dao.ImgDAO;
import com.sharetour.model.Photo;
import com.sharetour.util.ImgTools;

public class ImgService {
	
	private ImgDAO imgdao = new ImgDAO();

	/* not use here
	public void processFormField(FileItem item){
		String name = item.getFieldName();
		String value = item.getString();
		System.out.println(name+":"+value);
	}
	*/
	
	/*
	 * 处理上传图片
	 * return img filename
	 */
	public Photo processUploadFile(FileItem item) throws Exception{
		return imgdao.saveImg(item);
	}
	
	/*
	 * get img stream
	 * @param img filename
	 */
	public GridFSDBFile getImg(String filename){
		return imgdao.getImg(filename);
	}
	
	/*
	 * get img from collection
	 * @param collection
	 * @param img filename
	 */
	public GridFSDBFile getImgFrom(String collection, String filename){
		imgdao.setCollection(collection);
		return imgdao.getImg(filename);
	}
	
	/* 
	 * @param imgBuff
	 * @param width
	 * @parma height
	 */
	public BufferedImage resizeImg(BufferedImage imgBuff, int width, int height) {
		if(width <= 0 || height <= 0)
			return imgBuff;
		double w_ratio = imgBuff.getWidth()*1.0 / width;
		double h_ratio = imgBuff.getHeight()*1.0 / height;
		double ratio = w_ratio>h_ratio?h_ratio:w_ratio;
		int crop_w = (int) (ratio * width);
		int crop_h = (int) (ratio * height);
		BufferedImage croped_img = ImgTools.crop(imgBuff, 0, 0, crop_w, crop_h);
		return ImgTools.scale(croped_img, width*1.0/crop_w);
	}
	
}

package com.sharetour.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.coobird.thumbnailator.Thumbnails;

public class ImgTools {
	
	private static final Log log = LogFactory.getLog(ImgTools.class);
	private static final String JPG = "jpg";
	private static final String JPEG = "jpeg";
	private static final String PNG = "png";
	/*
	 * resize the image
	 */
	public static BufferedImage resizeImg(InputStream imgstream, int height, int width){
		if(imgstream == null){
			log.info("img stream null");
			return null;
		}
		BufferedImage imgbuff = null;
		try {
			 imgbuff = Thumbnails.of(imgstream).
					   size(width, height).
					   keepAspectRatio(false).
					   asBufferedImage();
		} catch (IOException e) {
			log.info("img resize error");
		}
		return imgbuff;
	}
	
	public static BufferedImage 
	crop(InputStream imgstream, int x1, int y1, int x2, int y2, int width, int height){
		if(imgstream == null){
			log.info("img stream null");
			return null;
		}
		BufferedImage imgbuff = null;
		try {
			 imgbuff = Thumbnails.of(imgstream).
					   sourceRegion(x1, y1, x2, y2).
					   size(width, height).
					   keepAspectRatio(false).
					   asBufferedImage();
		} catch (IOException e) {
			log.info("img crop error");
		}
		log.info("img crop success");
		return imgbuff;		
	}
	
	/*
	 * write img to output stream
	 */
	public static void write(BufferedImage imgbuff, String format, OutputStream out){
		try {
			ImageIO.write(imgbuff, format, out);
		} catch (IOException e) {
			log.error("img output error");
		}
		log.info("img output success");
	}
	/*
	 * 检测图片扩展名是否正确
	 */
	public static boolean checkImgFormatValidata(String ext){
		if(ext != null && ext.length() != 0){
			if(ext.equalsIgnoreCase(PNG) || ext.equalsIgnoreCase(JPEG) || ext.equalsIgnoreCase(JPG))
				return true;
		}
		return false;
	}
	
}

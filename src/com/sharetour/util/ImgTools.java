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
	 * @param imgStream
	 * @param height
	 * @param width
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
					   asBufferedImage();
		} catch (IOException e) {
			log.info("img resize error");
		}
		return imgbuff;
	}
	
	/*
	 * resize the image 
	 * @param imgBuffer
	 * @param height
	 * @param width
	 */
	public static BufferedImage resizeImg(BufferedImage imgBuff, int height, int width){
		if(imgBuff == null){
			log.info("img stream null");
			return null;
		}
		BufferedImage imgbuff = null;
		try {
			 imgbuff = Thumbnails.of(imgBuff).
					   size(width, height).
					   asBufferedImage();
		} catch (IOException e) {
			log.info("img resize error");
		}
		return imgbuff;
	}
	/*
	 * 按比例缩放
	 * @param imgstream
	 * @param scale
	 * @return BufferedImage
	 */
	public static BufferedImage scale(InputStream imgstream, double scale) {
		if(imgstream == null){
			log.info("img stream null");
			return null;
		}
		BufferedImage imgbuff = null;
		try {
			 imgbuff = Thumbnails.of(imgstream).
					   scale(scale).				   
					   asBufferedImage();
		} catch (IOException e) {
			log.info("img resize error");
		}
		return imgbuff;
	}
	
	/*
	 * 按比例缩放
	 * @param imgBuffer
	 * @param scale
	 * @return BufferedImage
	 */
	public static BufferedImage scale(BufferedImage imgBuff, double scale) {
		if(imgBuff == null){
			log.info("img stream null");
			return null;
		}
		BufferedImage imgbuff = null;
		try {
			 imgbuff = Thumbnails.of(imgBuff).
					   scale(scale).				   
					   asBufferedImage();
		} catch (IOException e) {
			log.info("img resize error");
		}
		return imgbuff;
	}
	
	
	/*
	 * 裁剪图像
	 * @param x - The horizontal-compoennt of the top left-hand corner of the source region.
	 * @param y - The vertical-compoennt of the top left-hand corner of the source region.
	 * @param width - Width of the source region.
	 * @param height - Height of the source region.
	 * @return Bufferediamge
	 */
	public static BufferedImage 
	crop(InputStream imgstream, int x, int y, int width, int height){
		if(imgstream == null){
			log.info("img stream null");
			return null;
		}
		BufferedImage imgbuff = null;
		try {
			 imgbuff = Thumbnails.of(imgstream).
					   sourceRegion(x, y, width, height).
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
	 * 裁剪图像
	 * @param x - The horizontal-compoennt of the top left-hand corner of the source region.
	 * @param y - The vertical-compoennt of the top left-hand corner of the source region.
	 * @param width - Width of the source region.
	 * @param height - Height of the source region.
	 * @return Bufferediamge
	 */
	public static BufferedImage 
	crop(BufferedImage imgBuff, int x, int y, int width, int height){
		if(imgBuff == null){
			log.info("imgBuff stream null");
			return null;
		}
		BufferedImage imgbuff = null;
		try {
			 imgbuff = Thumbnails.of(imgBuff).
					   sourceRegion(x, y, width, height).
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

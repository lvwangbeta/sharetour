package com.sharetour.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;

import com.mongodb.gridfs.GridFSDBFile;
import com.sharetour.dao.AvatorDAO;
import com.sharetour.dao.ImgDAO;
import com.sharetour.model.Avator;
import com.sharetour.model.Crop;
import com.sharetour.util.ImgTools;

public class AvatorService {
	
	private static final Log log = LogFactory.getLog(AvatorService.class);
	private AvatorDAO avatorDao = new AvatorDAO();
	private ImgDAO imgDao = new ImgDAO();
	/*
	 * 生成头像缩略图
	 * @param id image id
	 * @param crop 
	 * @return id
	 */
	public ObjectId generateAvatorThumb(ObjectId id, Crop crop) throws IOException{
		if(crop.getX1() < 0 || crop.getY1() < 0 
		   || crop.getX2() < 0 || crop.getX2() <= crop.getX1() 
		   || crop.getY2() < 0 || crop.getY2() <= crop.getY1() )
			return null;
		
		log.info("getting avator origin");
		GridFSDBFile avator_orig = imgDao.getImg(id.toString());
		
		BufferedImage avator_buffer = ImageIO.read(avator_orig.getInputStream());
		log.info("origin width:"+avator_buffer.getWidth());
		BufferedImage temp = ImgTools.scale(avator_orig.getInputStream(), 370.0/avator_buffer.getWidth());
		log.info("scale width:"+temp.getWidth());
		log.info("scale height:"+temp.getHeight());
		BufferedImage imgBuff = ImgTools.crop(temp, 
			      crop.getX1(), crop.getY1(),
			      crop.getWidth(), crop.getHeight());
		
		//covert bufferedimg to inputstream
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(imgBuff, avator_orig.getContentType(), baos);
		log.info("get avator origin success");
		ByteArrayInputStream imgIn = new ByteArrayInputStream(baos.toByteArray());
		ObjectId thumb_id = avatorDao.saveAvatorThumb(imgIn, avator_orig.getContentType());
		baos.close();
		imgIn.close();
		return thumb_id;
	}
	
	/*
	 * 保存头像信息
	 * @param avator
	 */
	public boolean saveAvator(Avator avator){
		log.info("saving avator");	
		return avatorDao.saveAvator(avator);
	}
	
	/*
	 * @param uid
	 * @return Avator
	 */
	public Avator getAvatorOfUser(Long uid) {
		return avatorDao.getAvatorByUid(uid);
	}
	
}

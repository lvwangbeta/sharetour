package com.sharetour.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;

import com.mongodb.gridfs.GridFSDBFile;
import com.sharetour.dao.AvatorDAO;
import com.sharetour.dao.ImgDAO;
import com.sharetour.model.Avator;
import com.sharetour.model.Crop;

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
	public ObjectId generateAvatorThumb(ObjectId id, Crop crop){
		if(crop.getX1() < 0 || crop.getY1() < 0 
		   || crop.getX2() < 0 || crop.getX2() <= crop.getX1() 
		   || crop.getY2() < 0 || crop.getY2() <= crop.getY1() )
			return null;
		log.info("getting avator origin");
		GridFSDBFile avator_orig = imgDao.getImg(id.toString());
		log.info("get avator origin success");
		return avatorDao.saveAvatorThumb(avator_orig);
	}
	
	/*
	 * 保存头像信息
	 * @param avator
	 */
	public boolean saveAvator(Avator avator){
		log.info("saving avator");	
		return avatorDao.saveAvator(avator);
	}
	
}

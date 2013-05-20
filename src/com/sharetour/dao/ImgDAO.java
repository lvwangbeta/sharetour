package com.sharetour.dao;

import java.io.InputStream;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.sharetour.db.MongoDBPool;

public class ImgDAO {
	
	private static final Log log = LogFactory.getLog(ImgDAO.class);
	
	public String saveImg(FileItem item) throws Exception{
		String filename = item.getName();
		int index = filename.lastIndexOf(".");
		filename = new ObjectId() + filename.substring(index);
		System.out.println(filename);
		if(filename == null || filename.length() == 0)
			return null;
		GridFS photo = new GridFS(MongoDBPool.getInstance().getDB(), "imgs");
		GridFSInputFile in = null;
		in = photo.createFile(item.getInputStream());
		in.setFilename(filename);
		in.save();
		return filename;
	}
	
	public InputStream getImg(String filename){
		log.info("getting img:"+filename+" from mongo");
		if(filename == null || filename.length() == 0){
			log.info("img filename null");
			return null;
		}
		GridFS photo = new GridFS(MongoDBPool.getInstance().getDB(), "imgs");
		GridFSDBFile imgout = photo.findOne(filename);
		if(imgout != null)
			return imgout.getInputStream();
		return null;
	}
}

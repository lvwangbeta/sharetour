package com.sharetour.dao;

import java.io.InputStream;
import java.net.UnknownHostException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class ImgDAO {
	
	private static final Log log = LogFactory.getLog(ImgDAO.class);
	
	public String saveImg(FileItem item) throws Exception{
		String filename = item.getName();
		int index = filename.lastIndexOf(".");
		filename = System.currentTimeMillis() + filename.substring(index);
		System.out.println(filename);
		if(filename == null || filename.length() == 0)
			return null;
		
		MongoClient client = new MongoClient("172.30.48.73", 25273);
		DB db = client.getDB("db");
		db.authenticate("b2b10f1d-b31c-4a10-b6d7-b4914bb48292", "de446439-2fc5-43ec-8c0f-7568932b3d27".toCharArray());
		GridFS photo = new GridFS(db, "imgs");
		GridFSInputFile in = null;
		in = photo.createFile(item.getInputStream());
		in.setFilename(filename);
		in.save();
		client.close();
		String url = "/imgs?id=" + filename;
		return url;
	}
	
	public InputStream getImg(String filename){
		log.info("getting img:"+filename+" from mongo");
		if(filename == null || filename.length() == 0){
			log.info("img filename null");
			return null;
		}
		MongoClient mongo = null;
		try {
			mongo = new MongoClient("172.30.48.73", 25273);
		} catch (UnknownHostException e) {
			log.error("mongo server can not connect");
			return null;
		}
		DB db = mongo.getDB("db");
		db.authenticate("b2b10f1d-b31c-4a10-b6d7-b4914bb48292", "de446439-2fc5-43ec-8c0f-7568932b3d27".toCharArray());
		GridFS photo = new GridFS(db, "imgs");
		GridFSDBFile imgout = photo.findOne(filename);
		if(imgout != null)
			return imgout.getInputStream();
		return null;
	}
}

package com.sharetour.dao;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.sharetour.db.MongoDBPool;
import com.sharetour.model.Photo;
import com.sharetour.util.ImgTools;
import com.sharetour.util.ObjectIdGenerator;

public class ImgDAO {
	
	private static final Log log = LogFactory.getLog(ImgDAO.class);
	private String collection = "imgs";
	public Photo saveImg(FileItem item) throws Exception{
		Photo photo = new Photo();
		String filename = item.getName();
		if(filename == null || filename.length() == 0){
			log.error("img name illegal");
			return null;
		}
		int index = filename.lastIndexOf(".");
		String type = filename.substring(index+1);
		if(!ImgTools.checkImgFormatValidata(type)){
			log.error("img type illegal");
			return null;
		}
		ObjectId id = ObjectIdGenerator.generate();
		//filename = new ObjectId() + filename.substring(index);
		photo.setId(id.toString());
		photo.setType(type);
		
		GridFS mphoto = new GridFS(MongoDBPool.getInstance().getDB(), collection);
		GridFSInputFile in = null;
		in = mphoto.createFile(item.getInputStream());
		in.setId(id);
		in.setFilename(id.toString());
		in.setContentType(type);
		in.save();
		item.getInputStream().close();
		return photo;
	}
	
	public Photo saveImg(FileItem item, String coll) throws Exception {
		setCollection(coll);
		return saveImg(item);
	}
	
	public void setCollection(String coll){
		this.collection = coll;
	}
	public GridFSDBFile getImg(String id){
		log.info("getting img:"+id+" from mongo");
		if(id == null || id.length() == 0){
			log.info("img filename null");
			return null;
		}
		GridFS photo = new GridFS(MongoDBPool.getInstance().getDB(), "imgs");
		return photo.findOne(id);
	}
}

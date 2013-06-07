package com.sharetour.dao;

import java.io.InputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import com.sharetour.db.MongoDBPool;
import com.sharetour.model.Avator;
import com.sharetour.util.ObjectIdGenerator;

public class AvatorDAO {
	
	private static final String THUMB_COLL  = "avator_thumb";
	private static final String AVATOR_COLL = "avator";
	private static final Log log = LogFactory.getLog(AvatorDAO.class);
	
	public ObjectId saveAvatorThumb(InputStream input, String type){
		if(input == null || type == null)
			return null;
		
		GridFS avator_thumb = new GridFS(MongoDBPool.getInstance().getDB(), THUMB_COLL);
		GridFSInputFile in = null;
		in = avator_thumb.createFile(input);
		in.setContentType(type);
		ObjectId id = ObjectIdGenerator.generate();
		in.setId(id);
		in.setFilename(id.toString());
		in.save();
		return id;
	}
	
	public boolean saveAvator(Avator avator) {
		if(avator == null){
			log.error("avator null");
			return false;		
		}
			
		DBCollection avator_coll = MongoDBPool.getInstance().getCollection(AVATOR_COLL);
		avator_coll.update(new BasicDBObject("uid", avator.getUid()), 
						   new BasicDBObject("$set", 
								 new BasicDBObject("avator", avator.getAvatorId()).
								 append("username", avator.getUsername())),
								 true, false);
		return true;
	}
	

	public Avator getAvatorByUid(Long uid) {
		if(uid == null || uid == 0)
			return null;
		DBCollection avator_coll = MongoDBPool.getInstance().getCollection(AVATOR_COLL);
		BasicDBObject query = new BasicDBObject("uid", uid);
		BasicDBObject doc = (BasicDBObject) avator_coll.findOne(query);
		if(doc == null)
			return null;
		return fromJson(doc);
	}
	
	/*
	 * convert json to avator obj
	 * @param BasicDBObject json
	 */
	private Avator fromJson(BasicDBObject json) {
		Avator avator = new Avator();
		avator.setId(json.getObjectId("_id"));
		avator.setUid(json.getLong("uid"));
		avator.setUsername(json.getString("username"));
		avator.setAvatorId(json.getObjectId("avator"));
		return avator;
	}
}

package com.sharetour.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.sharetour.db.MongoDBPool;
import com.sharetour.model.Avator;
import com.sharetour.util.ObjectIdGenerator;

public class AvatorDAO {
	
	private static final String THUMB_COLL  = "avator_thumb";
	private static final String AVATOR_COLL = "avator";
	private static final Log log = LogFactory.getLog(AvatorDAO.class);
	
	public ObjectId saveAvatorThumb(GridFSDBFile avator_orig){
		if(avator_orig == null)
			return null;
		GridFS avator_coll = new GridFS(MongoDBPool.getInstance().getDB(), THUMB_COLL);
		GridFSInputFile in = null;
		in = avator_coll.createFile(avator_orig.getInputStream());
		in.setContentType(avator_orig.getContentType());
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
			
		DBCollection albumColl = MongoDBPool.getInstance().getCollection(AVATOR_COLL);
		BasicDBObject doc = new BasicDBObject("_id", avator.getId()).
						    append("uid", avator.getUid()).
						    append("username", avator.getUsername()).
						    append("avator", avator.getAvatorId());
		albumColl.insert(doc);
		return true;
	}
}

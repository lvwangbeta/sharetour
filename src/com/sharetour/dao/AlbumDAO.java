package com.sharetour.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.sharetour.db.MongoDBPool;
import com.sharetour.model.Album;
import com.sharetour.model.Photo;

public class AlbumDAO {

	private static final Log log = LogFactory.getLog(AlbumDAO.class);
	
	public boolean saveAlbum(Album album){
		DBCollection albumColl = MongoDBPool.getInstance().getCollection("album");
		BasicDBObject doc = new BasicDBObject("_id",new ObjectId(album.getId())).
							append("albumname", album.getAlbumname()).
					        append("uid", album.getUid()).
					        append("username", album.getUsername()).
					        append("size", album.getSize()).
					        append("ctime", album.getCtime()).
					        append("visit", album.getVisit()).
					        append("desc", album.getDesc());
		
		List<BasicDBObject> photos = new ArrayList<BasicDBObject>();	
		for(Photo photo: album.getPhotos()){
			photos.add(new BasicDBObject("_id", new ObjectId(photo.getId())).
										 append("desc", photo.getDesc()).
										 append("ctime", photo.getCtime()).
										 append("likes", photo.getLikes()) );
		}
		doc.append("photos", photos);
		albumColl.insert(doc);
		return true;
	}
	public Album getAlbum(String id){
		DBCollection albumColl = MongoDBPool.getInstance().getCollection("album");
		BasicDBObject doc = new BasicDBObject("_id",new ObjectId(id));
		BasicDBObject albumjson = (BasicDBObject)albumColl.findOne(doc);
		return formJson(albumjson);
	}
	
	public List<Album> getAlbumsOfUser(Long uid){
		DBCollection albumColl = MongoDBPool.getInstance().getCollection("album");
		BasicDBObject doc = new BasicDBObject("uid",uid);
		DBCursor cursor = albumColl.find(doc);
		List<Album> albums = new ArrayList<Album>();
		
		while(cursor.hasNext()){
			BasicDBObject albumjson = (BasicDBObject) cursor.next();
			albums.add(formJson(albumjson));
		}
		return albums;
	}
	
	/*
	 *  反序列化，转化为Album对象
	 */
	private Album formJson(BasicDBObject albumjson){
		Album album = new Album();
		album.setId(albumjson.getString("_id"));
		album.setAlbumname(albumjson.getString("albumname"));
		album.setDesc(albumjson.getString("desc"));
		album.setCtime(albumjson.getDate("ctime"));
		album.setUid(albumjson.getLong("uid"));
		album.setUsername(albumjson.getString("username"));
		album.setSize(albumjson.getInt("size"));
		album.setVisit(albumjson.getInt("visit"));
		album.setLikers(null);
		
		BasicDBList photosjson = (BasicDBList) albumjson.get("photos");
		List<Photo> photos = new ArrayList<Photo>();
		for(Object pjson: photosjson){
			BasicDBObject p = (BasicDBObject)pjson;
			Photo photo = new Photo();
			photo.setId(p.getString("_id"));
			photo.setDesc(p.getString("desc"));
			photo.setCtime(p.getDate("ctime"));
			photo.setLikes(p.getInt("likes"));
			photo.setType(p.getString("type"));

			photos.add(photo);
		}
		album.setPhotos(photos);
		return album;
	}
	
}

package com.sharetour.service;

import java.util.Date;

import org.bson.types.ObjectId;
import com.sharetour.dao.AlbumDAO;
import com.sharetour.model.Album;
import com.sharetour.model.Photo;
import com.sharetour.util.ObjectIdGenerator;

public class AlbumService {
	
	private AlbumDAO albumdao = new AlbumDAO();
	public boolean saveAlbum(Album album){
		album.setId(ObjectIdGenerator.generate().toString());
		album.setCtime(new Date());
		album.setSize(album.getPhotos().size());
		album.setVisit(0);
		album.setCtime(new Date());
		
		for(Photo photo: album.getPhotos()){
			photo.setLikes(0);
			photo.setCtime(new Date());
		}		
		return albumdao.saveAlbum(album);
	}
	
	public Album getAlbum(ObjectId id){
		return null;
	}
}

package com.sharetour.service;

import org.bson.types.ObjectId;
import com.sharetour.dao.AlbumDAO;
import com.sharetour.model.Album;

public class AlbumService {
	
	private AlbumDAO albumdao = new AlbumDAO();
	public boolean saveAlbum(Album album){
		return albumdao.saveAlbum(album);
	}
	
	public Album getAlbum(ObjectId id){
		return null;
	}
}

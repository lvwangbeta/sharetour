package com.sharetour.service;

import java.util.Date;
import java.util.List;

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
	
	public Album getAlbum(String id){
		return null;
	}
	
	/*
	 * 获得某人的相册
	 */
	public List<Album> getAlbumsOfUser(Long uid){
		return null;
	}
}

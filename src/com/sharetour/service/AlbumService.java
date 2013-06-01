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
		List<Photo> photos = album.getPhotos();
		if(photos == null)
			return false;
		for(Photo photo: album.getPhotos()){
			photo.setLikes(0);
			photo.setCtime(new Date());
		}		
		album.setCoverid(photos.get(0).getId());
		return albumdao.saveAlbum(album);
	}
	
	public Album getAlbum(String id){
		return albumdao.getAlbum(id);
	}
	
	/*
	 * 获得某人的相册列表
	 */
	public List<Album> getAlbumsOfUser(Long uid){
		return albumdao.getAlbumsOfUser(uid);
	}
	
	/*
	 * 获得热门相册
	 * @param order 排序规则
	 * @param size  
	 */
	public List<Album> getHotAlbums(Object order, int size){
		return albumdao.getAlubms(order, size);
	}
	
	
}

package com.sharetour.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

public class Album implements Serializable {

	private static final long serialVersionUID = -8343545984379551338L;
	private ObjectId id;
	private ObjectId coverid;
	private String albumname;
	private String desc;
	private List<Photo> photos = new ArrayList<Photo>();
	private Date ctime;
	private Long uid;
	private Long username;
	private int visit;
	private List<UserInfo> likers = new ArrayList<UserInfo>();
	
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public ObjectId getCoverid() {
		return coverid;
	}
	public void setCoverid(ObjectId coverid) {
		this.coverid = coverid;
	}
	public String getAlbumname() {
		return albumname;
	}
	public void setAlbumname(String albumname) {
		this.albumname = albumname;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Long getUsername() {
		return username;
	}
	public void setUsername(Long username) {
		this.username = username;
	}
	public int getVisit() {
		return visit;
	}
	public void setVisit(int visit) {
		this.visit = visit;
	}
	public List<UserInfo> getLikers() {
		return likers;
	}
	public void setLikers(List<UserInfo> likers) {
		this.likers = likers;
	}
	
}

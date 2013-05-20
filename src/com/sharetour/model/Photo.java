package com.sharetour.model;

import java.util.List;

import org.bson.types.ObjectId;

public class Photo {
	private ObjectId _id;
	private String desc;
	private int likes;
	private List<PhotoComment> comments;
	
	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<PhotoComment> getComments() {
		return comments;
	}

	public void setComments(List<PhotoComment> comments) {
		this.comments = comments;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}
}

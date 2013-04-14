package com.sharetour.service;

import java.util.List;

import com.sharetour.dao.PostDAO;
import com.sharetour.model.Post;

public class HotPostService{
	private final static String ORDER = "likes";
	private int limit = 10;
	private PostDAO hpdao;
	public HotPostService(){
		hpdao = new PostDAO();
	}
	public HotPostService(int limit){
		this();
		this.limit = limit;
	}
	public List<Post> getHotPost(){
		return hpdao.getHotPost(this.limit, ORDER);
	}
	public List<Post> getHotPost(int limit){
		this.limit = limit;
		return hpdao.getHotPost(this.limit, ORDER);
	}
}

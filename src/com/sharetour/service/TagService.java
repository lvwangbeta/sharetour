package com.sharetour.service;

import java.util.List;

import com.sharetour.dao.TagDAO;
import com.sharetour.model.Post;
import com.sharetour.model.PostTag;

public class TagService {

	private PostTag posttag;
	private TagDAO tagdao;
	
	public TagService(PostTag posttag){
		this.posttag = posttag;
		this.tagdao = new TagDAO(this.posttag);
	}
	
	/*
	 * 采用默认的page limit参数
	 */
	public static List<PostTag> getHotTag(){
		return new TagDAO().getHotTag();	
	}
	/*
	 * 可以设置page limit以增加缓存存储
	 */
	public static List<PostTag> getHotTag(int page, int limit){
		return new TagDAO().getHotTag(page, limit);
	}
	
	/*
	 * @param tag
	 * 获得与tag相关的Post
	 * @return List<Post>
	 */
	public List<Post> getPostsRelatedToTag(){
		return tagdao.getPostsRelatedToTag();
	}
	
}

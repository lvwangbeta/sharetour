package com.sharetour.model;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import com.sharetour.util.POJO;

public class Post extends POJO {

	private static final long serialVersionUID = 1L;
	private long authorid;
	private String title;
	private String content;
	private String summary;
	private Date ctime;
	private int likes;
	private int visit;
	private String tags;
	private String cover;		//封面图片地址
	public Post()
	{
		super("posts");
	}
	public Post(long id)
	{
		super("posts");
		setId(id);
	}

	public void setAuthorid(long authorid)
	{
		this.authorid = authorid;
	}
	public long getAuthorid()
	{
		return authorid;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getTitle()
	{
		return title;
	}
	public void setSummary(String summary)
	{
		this.summary = summary;
	}
	public String getSummary()
	{
		return summary;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getContent()
	{
		return content;
	}
	public void setCtime(Date ctime)
	{
		this.ctime = ctime;
	}
	public Date getCtime()
	{
		return ctime;
	}
	
	public void setVisit(int visit)
	{
		this.visit = visit;
	}
	public int getVisit()
	{
		return visit;
	}
	public void setTags(String tags)
	{
		this.tags = tags;
	}
	public String getTags()
	{
		return tags;
	}
	public void setLikes(int likes)
	{
		this.likes = likes;
	}
	public int getLikes()
	{
		return likes;
	}
	/*
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	*/
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	@Override
	public Map<String, Object> ListInsertableFields() 
	{
		//set params
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("authorid", getAuthorid());
		//map.put("category", getCategory());
		map.put("title", getTitle());
		map.put("content", getContent());
		map.put("summary", getSummary());
		map.put("tags", getTags());
		return map;
	}


}

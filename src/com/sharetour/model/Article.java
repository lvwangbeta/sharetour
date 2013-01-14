package com.sharetour.model;
import java.util.Date;
import java.util.List;

public class Article {	
	private long   id;
	private String title;
	private String content;
	private Date   date;				//创建时间
	private int	   likes;				//喜欢数
	private int    rate;				//评级
	private int    visit;				//访问量
	private List<String> tags;			//标签
	private String cover;				//封面图片连接
	
	public Article()
	{
		
	}
	public Article(String title, String content)
	{
		this.title = title;
		this.content = content;
	}
	public void setCover(String cover)
	{
		this.cover = cover;
	}
	public String getCover()
	{
		return cover;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getId()
	{
		return id;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getTitle()
	{
		return title;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getContent()
	{
		return content;
	}
	public void setDate(Date date)
	{
		this.date = date;
	}
	public Date getDate()
	{
		return date;
	}
	public void setLike(int likes)
	{
		this.likes = likes;
	}
	public int getLikes()
	{
		return likes;
	}
	
	
	
}

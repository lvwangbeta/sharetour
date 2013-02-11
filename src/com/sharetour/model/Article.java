package com.sharetour.model;
import java.util.Date;
import java.util.List;

public class Article {	
	private long   id;
	private String title;
	private String summary;
	private String content;
	private Date   date;				//创建时间
	private int	   likes;				//喜欢数
	private int    rate;				//评级
	private int    visit;				//访问量
	private String[] tags;				//标签
	private String cover;				//封面图片连接
	private int authorid;				//作者
	
	
	public Article()
	{
		
	}
	public Article(String title, String content)
	{
		this.title = title;
		this.content = content;
	}
	public void setAuthorid(int authorid)
	{
		this.authorid = authorid;
	}
	public int getAuthorid()
	{
		return authorid;
	}
	public void setSummary(String summary)
	{
		this.summary = summary;
	}
	public String getSummary()
	{
		return summary;
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
	
	public void setVisit(int visit)
	{
		this.visit = visit;
	}
	public int getVisit()
	{
		return visit;
	}
	public void setTags(String[] tags)
	{
		this.tags = tags;
	}
	public String[] getTags()
	{
		return tags;
	}
	
}

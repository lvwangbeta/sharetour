package com.sharetour.model;
import java.util.Date;
import java.util.List;

public class Article {	
	private long   id;
	private String title;
	private String content;
	private Date   date;				//����ʱ��
	private int	   likes;				//ϲ����
	private int    rate;				//����
	private int    visit;				//������
	private List<String> tags;			//��ǩ
<<<<<<< HEAD
	private String cover;				//����ͼƬ����
	
	public Article()
	{
		
	}
=======
	
>>>>>>> a0d7ab5f17f4ebcd58db42942b92165c52e75dbe
	public Article(String title, String content)
	{
		this.title = title;
		this.content = content;
	}
<<<<<<< HEAD
	public void setCover(String cover)
	{
		this.cover = cover;
	}
	public String getCover()
	{
		return cover;
	}
=======
>>>>>>> a0d7ab5f17f4ebcd58db42942b92165c52e75dbe
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

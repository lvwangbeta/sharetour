package com.sharetour.model;

import java.util.Date;

public class Comment {
	private int id;
	private int pid;			//related post id
	private int authorid;		//评论用户id
	private Date ctime;			//comment create time
	private String username;	//评论用户用户名
	private String comment;
	private int parent;			//父评论id 可以为空如果不是评论的回复的话
	
	public void setId(int id)
	{
		this.id = id;
	}
	public int getId()
	{
		return id;
	}
	
	public void setPid(int pid)
	{
		this.pid = pid;
	}
	public int getPid()
	{
		return pid;
	}
	public void setAuthorid(int authorid)
	{
		this.authorid = authorid;
	}
	public int getAuthorid()
	{
		return authorid;
	}
	public void setCtime(Date ctime)
	{
		this.ctime = ctime;
	}
	public Date getCtime()
	{
		return ctime;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getUsername()
	{
		return username;
	}
	
	public void setComment(String comment)
	{
		this.comment = comment;
	}
	public String getComment()
	{
		return comment;
	}
	
	public void setParent(int parent)
	{
		this.parent = parent;
	}
	public int getParent()
	{
		return parent;
	}
}

package com.sharetour.model;

import java.util.Date;

public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private Date ctime;     //account create time
	private String head;	//Í·ÏñµØÖ·
	
	public User(String username, String password, String email)
	{
		this.username = username;
		this.password = password;
		this.email = email;
	}
	public User(String username, String email, String head, int id)
	{
		this.username = username;
		this.email = email;
		this.head = head;
		this.id = id;
		
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getId()
	{
		return id;
	}
	public String getHead()
	{
		return head;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getUsername()
	{
		return username;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getPassword()
	{
		return password;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getEmail()
	{
		return email;
	}
	
}

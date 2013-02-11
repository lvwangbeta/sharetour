package com.sharetour.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sharetour.db.ConnectionPool;
import com.sharetour.model.Article;
import com.sharetour.model.User;

public class PostListDAO extends BaseDAO{
	private static final int N = 15;	//热门列表post初始数量
	private static final int M = 10;	//博客列表罗列post数量
	/*
	 * 返回文章列表
	 */
	public List<Article> getPostList(User user)
	{
		List<Article> list = new ArrayList<Article>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			pstm = con.prepareStatement("select id, title, likes, visit, tags from posts where authorid=? limit ?");
			pstm.setInt(1, user.getId());
			pstm.setInt(2, M);
			res = pstm.executeQuery();
			while(res.next())
			{
				Article article = new Article();
				article.setId(res.getLong("id"));
				article.setTitle(res.getString("title"));
				article.setVisit(res.getInt("visit"));
				article.setLike(res.getInt("likes"));
				article.setTags(res.getString("tags").split(" "));
				list.add(article);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			closeCon(con, pstm, res);
		}
		return list;
	}
		
	/*
	 * 返回热门文章列表
	 */
	public List<Article> getHot()
	{
		List<Article> list = new ArrayList<Article>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		
		try {
			con = ConnectionPool.getInstance().getConnection();
			pstm = con.prepareStatement("select id,title,cover,summary,likes from posts where cover is not null order by visit desc limit ?");
			pstm.setInt(1, N);
			res = pstm.executeQuery();
			while(res.next())
			{
				Article article = new Article();
				article.setId(res.getInt("id"));
				article.setTitle(res.getString("title"));
				article.setCover(res.getString("cover"));
				article.setSummary(res.getString("summary"));
				article.setLike(res.getInt("likes"));
				list.add(article);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			closeCon(con, pstm, res);
		}
		return list;
	}
	
	/*
	 *返回单篇文章 
	 */
	public Article getPost(int pid)
	{

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		
		try {
			con = ConnectionPool.getInstance().getConnection();
			pstm = con.prepareStatement("select title,summary,content,tags,visit,likes from posts where id=?");
			pstm.setInt(1, pid);
			res = pstm.executeQuery();
			if(res.next())
			{
				Article article = new Article();
				article.setTitle(res.getString("title"));
				article.setSummary(res.getString("summary"));
				article.setContent(res.getString("content"));
				article.setTags(res.getString("tags").split(" "));
				article.setVisit(res.getInt("visit"));
				article.setLike(res.getInt("likes"));
				return article;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		finally{
			closeCon(con, pstm, res);
		}
		return null;
	}
}

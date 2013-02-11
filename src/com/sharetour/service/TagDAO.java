package com.sharetour.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sharetour.db.ConnectionPool;
import com.sharetour.model.Article;
import com.sharetour.model.Tag;

public class TagDAO extends BaseDAO{
	
	public List<Tag> getHotTag()
	{
		List<Tag> list = new ArrayList<Tag>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			pstm = con.prepareStatement("select tid, count(pid) as pid, tag from PostTagRelation,tags " +
					"where PostTagRelation.tid=tags.id group by tid ");
			res = pstm.executeQuery();
			while(res.next())
			{
				list.add(new Tag(res.getInt("tid"), res.getString("tag")));				
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
	 * 返回tag对应的文章
	 */
	public List<Article> getTagPost(String tag)
	{
		List<Article> list = new ArrayList<Article>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			pstm = con.prepareStatement("select distinct(posts.id), posts.title, posts.likes "+
										"from posts join PostTagRelation "+
										"on posts.id = PostTagRelation.pid "+
										"join tags on PostTagRelation.tid = tags.id "+
										"where tag=?");
			pstm.setString(1, tag);
			res = pstm.executeQuery();
			
			while(res.next())
			{		
				Article article = new Article();
				article.setId(res.getInt("id"));
				article.setTitle(res.getString("title"));
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
	
	
}

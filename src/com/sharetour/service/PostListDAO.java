package com.sharetour.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sharetour.db.ConnectionPool;
import com.sharetour.model.Article;

public class PostListDAO extends BaseDAO{
	private static final int N = 10;	//热门列表post初始数量
	/*
	 * 返回文章列表
	 *
	public ArraList<Article> getPostList()
	{
		
	}
	*/
	
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
			pstm = con.prepareStatement("select title,cover from posts order by visit desc limit ?");
			pstm.setInt(1, N);
			res = pstm.executeQuery();
			while(res.next())
			{
				Article article = new Article();
				article.setTitle(res.getString("title"));
				article.setCover(res.getString("cover"));
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

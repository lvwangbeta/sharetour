package com.sharetour.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sharetour.db.ConnectionPool;
import com.sharetour.model.Comment;


public class CommentDAO extends BaseDAO{
	
	/*
	 * 保存评论
	 */
	public int save(Comment comment)
	{
		int id = 0;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			pstm = con.prepareStatement("insert into comments(pid,authorid,username,comment) values(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			pstm.setInt(1, comment.getPid());
			pstm.setInt(2, comment.getAuthorid());
			pstm.setString(3, comment.getUsername());
			pstm.setString(4, comment.getComment());
			pstm.executeUpdate();
			res = pstm.getGeneratedKeys();
			if(res.next())
			{
				id = res.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			id = 0;
		}
		finally{
			closeCon(con, pstm, res);
		}
		return id;
	}
	
	/*
	 * 获得谋篇文章的comments
	 * 
	 */
	public List<Comment> getComments(int pid)
	{
		List<Comment> list = new ArrayList<Comment>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			pstm = con.prepareStatement("select * from comments where pid=?");
			pstm.setInt(1, pid);
			res = pstm.executeQuery();
			while(res.next())
			{
				Comment comment = new Comment();
				comment.setId(res.getInt("id"));
				comment.setPid(res.getInt("pid"));
				comment.setAuthorid(res.getInt("authorid"));
				comment.setUsername(res.getString("username"));
				comment.setComment(res.getString("comment"));
				comment.setParent(res.getInt("parent"));
				list.add(comment);
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

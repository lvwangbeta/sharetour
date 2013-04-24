package com.sharetour.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.sharetour.db.ConnectionPool;
import com.sharetour.model.PostUserRelation;
import com.sharetour.util.QueryHelper;

public class PostActionDAO {
	/*
	 * post like action
	 */
	public boolean postLike(PostUserRelation relation){
		String sql = "insert into post_user_relation(pid, uid, lik) values(?,?,?) " +
					 "on duplicate key update lik='like'";
		Connection connection = null; 
		PreparedStatement pstm = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			pstm = connection.prepareStatement(sql);
			pstm.setLong(1, relation.getPid());
			pstm.setLong(2, relation.getUid());
			pstm.setString(3, "like");
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				connection = null;
			}
		}		
	}
	
	/*
	 * undo post like
	 */
	public boolean undoPostLike(PostUserRelation relation){
		QueryHelper helper = new QueryHelper();
		if(helper.update("update post_user_relation set lik='dislike' where pid=? and uid=?",
					  relation.getPid(), relation.getUid()) > 0)
		{
			helper.closeConnection();
			return true;
		}
		helper.closeConnection();
		return false;
	}
	
	/*
	 * post share action
	 */
	public boolean postShare(PostUserRelation relation){
		throw new UnsupportedOperationException("method not ready");
	}
	
	
	/*
	 * 根据用户id查找其like的post
	 */
	public List<PostUserRelation> findLikePostsByUid(Long uid){
		QueryHelper helper = new QueryHelper();
		List<PostUserRelation> list = helper.executeQuery(PostUserRelation.class, 
				"select * from post_user_relation where uid=? and lik='like'", uid);
		helper.closeConnection();
		return list;
	}
	
}

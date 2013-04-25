package com.sharetour.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.sharetour.model.Post;
import com.sharetour.model.PostComment;
import com.sharetour.model.Relation;
import com.sharetour.util.QueryHelper;


public class PostDAO {
	private final static String SEPARATOR = " ";	
	private Post post;
	public PostDAO(){
		
	}
	public PostDAO(Post post){
		this.post = post;
	}	
	public boolean createNewPost(){
		
		QueryHelper helper = new QueryHelper();
		post.setQueryHelper(helper);
		long pid = post.Save();
		if(pid == 0){
			return false;
		}
		List<Long> tids = savePostTag(helper, post.getTags());
		if(tids == null || tids.size() == 0){
			return false;
		}
		savePostTagRelation(helper, pid, tids);
		helper.closeConnection();
		return true;
	}
	
	public void savePostTagRelation(QueryHelper helper, long pid, List<Long> tids){
		Relation relation = new Relation();
		relation.setQueryHelper(helper);
		relation.setPid(pid);
		for(long tid: tids)
		{
			relation.setTid(tid);
			relation.Save();
		}
	}
	
	public List<Long> savePostTag(QueryHelper helper, String ptags){
		Connection connection = helper.getConnection();
		String[] tags = StringUtils.split(ptags, SEPARATOR);
		if(tags == null || tags.length == 0)
			return null;
		//insert new entry and update count number column of old entry
		StringBuilder sql = new StringBuilder(
				"insert into posts_tags(tagname) values(?) on duplicate key update postcount=postcount+1");
		int len = tags.length;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			pstm = connection.prepareStatement(sql.toString());
			for(int i=0; i<len; i++)
			{
				pstm.setString(1, tags[i]);
				pstm.addBatch();
			}			
			pstm.executeBatch();
			//select tag id 
			sql = new StringBuilder("select id from posts_tags where tagname in (");
			for(int i=0; i<len; i++)
			{		
				sql.append("'");
				sql.append(tags[i]);
				sql.append("'");
				if(i<len-1)
					sql.append(",");
			}
			sql.append(")");	
			pstm = connection.prepareStatement(sql.toString(), 
					PreparedStatement.RETURN_GENERATED_KEYS);		
			res = pstm.executeQuery();
			List<Long> list = new ArrayList<Long>();
			while(res.next())
			{
				list.add(res.getLong(1));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public Post getPost(){
		QueryHelper helper = new QueryHelper();
		post.setQueryHelper(helper);
		Post p = post.Get();
		helper.closeConnection();
		return p;
	}
	
	public List<PostComment> getPostComment(){	
		QueryHelper helper = new QueryHelper();
	 	List<PostComment> comments = helper.query_slice(
		 	PostComment.class, 
		 	"select * from posts_comments where postid=?", 
		 	1, 
		 	10,
		 	new Object[]{post.getId()}
		 );
	 	helper.closeConnection();
	 	return comments;
	}
	/*
	 * @param page 起始页
	 * @param limit 页面容量
	 * @param order 结果集按order排序
	 */
	public List<Post> getPostList(int page, int limit, String order){
		QueryHelper helper = new QueryHelper();
		Post p = new Post();
		p.setQueryHelper(helper);
		@SuppressWarnings("unchecked")
		List<Post> list = (List<Post>) p.List(page, limit, order);
		helper.closeConnection();
		return list;
	}
	
	
	/*
	 * @param page 起始页
	 * @param limit 页面容量
	 */
	public List<Post> getPostList(int page, int limit){
		QueryHelper helper = new QueryHelper();
		Post p = new Post();
		p.setQueryHelper(helper);
		@SuppressWarnings("unchecked")
		List<Post> list = (List<Post>) p.List(page, limit);
		helper.closeConnection();
		return list;
	}
	/*
	 * 根据authorid获得其所有posts
	 */
	public List<Post> getPostsOfAuthor(Long authorid){
		QueryHelper helper = new QueryHelper();
		List<Post> list = helper.executeQuery(Post.class, "select * from posts where authorid=?", authorid);
		helper.closeConnection();
		return list;
	}
	

}

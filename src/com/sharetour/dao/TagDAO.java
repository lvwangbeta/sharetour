package com.sharetour.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sharetour.model.Post;
import com.sharetour.model.PostTag;
import com.sharetour.util.QueryHelper;

public class TagDAO {
	private final static String TABLE_POSTS_TAG = "posts_tags";
	private final static String TABLE_POST_TAG_RELA = "post_tag_relation";
	private final static String KEY = "hottags";
	private final static String TABLE = "posts_tags";
	private final static String SEPARATOR = " ";	
	private static Log log = LogFactory.getLog(TagDAO.class);
	
	
	public List<Long> savePostTag(String ptags){
		QueryHelper helper = new QueryHelper();
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
		}finally{
			helper.closeConnection();
		}
		return null;		
	}
	
	
	public PostTag getTagId(String tagname){
		QueryHelper helper = new QueryHelper();
		PostTag tag = helper.get(PostTag.class, 
								 "select * from posts_tags " +
								 "where tagname=?", 
								 tagname);
		helper.closeConnection();
		return tag;
	}
	
	public List<PostTag> getTagsId(List<String> tags){
		log.info("getting tagsid");
		QueryHelper helper = new QueryHelper();
		StringBuffer buffer = new StringBuffer("select id from posts_tags where tagname in (");
		Iterator<String> it = tags.iterator();
		for(int i=0; i<tags.size(); i++){
			if(i != 0){
				buffer.append(",");
			}
			buffer.append("'");
			buffer.append(it.next());
			buffer.append("'");
		}
		buffer.append(")");
		log.info(buffer.toString());
		List<PostTag> tlist = helper.executeQuery(PostTag.class, buffer.toString());
		helper.closeConnection();
		return tlist;
	}
	
	public static List<PostTag> getHotTag(int page, int limit){
		QueryHelper helper = new QueryHelper();
		List<PostTag> list = helper.
			query_slice(
						PostTag.class,
						"select tagname, postcount from " + TABLE + " order by postcount desc",
						page,
						limit
						);
		helper.closeConnection();
		return list;		
	}

	public List<Post> getPostsRelatedToTag(String tagname){
		QueryHelper helper = new QueryHelper();
		List<Post> list = helper.
			query_slice(
						Post.class,
						"select posts.id, posts.authorid, posts.title,posts.cover, " +
						"posts.summary, posts.tags, posts.visit, posts.ctime " +
						"from posts,post_tag_relation,posts_tags where " +
						"posts.id=post_tag_relation.pid " +
						"and post_tag_relation.tid=posts_tags.id and posts_tags.tagname=?", 
						1, 
						1000,
						tagname
						);
		helper.closeConnection();
		return list;		
	}
}

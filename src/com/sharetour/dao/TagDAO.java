package com.sharetour.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sharetour.model.Post;
import com.sharetour.model.PostTag;
import com.sharetour.util.QueryHelper;

public class TagDAO {
	private final static String KEY = "hottags";
	private final static String TABLE = "posts_tags";
	private static Log log = LogFactory.getLog(TagDAO.class);
	
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
						"select posts.id, posts.authorid, posts.title, " +
						"posts.summary, posts.tags, posts.visit, posts.ctime " +
						"from posts,relations,posts_tags where " +
						"posts.id=relations.pid " +
						"and relations.tid=posts_tags.id and posts_tags.tagname=?", 
						1, 
						1000,
						tagname
						);
		System.out.println(tagname);
		helper.closeConnection();
		return list;		
	}
}

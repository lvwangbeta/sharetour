package com.sharetour.dao;

import java.util.List;
import com.sharetour.model.Post;
import com.sharetour.model.PostTag;
import com.sharetour.util.QueryHelper;

public class TagDAO {
	private final static String KEY = "hottags";
	private final static String TABLE = "posts_tags";
	private int page = 1;
	private int limit = 1000;
	private PostTag posttag;
	public TagDAO(){
		
	}
	public TagDAO(PostTag posttag){
		this.posttag = posttag;
	}
	
	public List<PostTag> getHotTag(){
		QueryHelper helper = new QueryHelper();
		List<PostTag> list = helper.
			query_slice_cache(
							  PostTag.class,
							  KEY,
							  "select tagname, postcount from " + TABLE + " order by postcount desc",
							  page,
							  limit
							  );
		helper.closeConnection();
		return list;
		
	}
	public List<PostTag> getHotTag(int page, int limit){
		QueryHelper helper = new QueryHelper();
		List<PostTag> list = helper.
			query_slice_cache(
							  PostTag.class,
							  KEY,
							  "select tagname, postcount from " + TABLE + " order by postcount desc",
							  page,
							  limit
							  );
		helper.closeConnection();
		return list;		
	}
	public List<Post> getPostsRelatedToTag(){
		QueryHelper helper = new QueryHelper();
		List<Post> list = helper.
			query_slice_cache(
							  Post.class,
							  "posts_relatedto_"+posttag.getTagname(), 
							  "select posts.id, posts.authorid, posts.title, " +
							  "posts.summary, posts.tags, posts.visit, posts.ctime " +
							  "from posts,relations,posts_tags where " +
							  "posts.id=relations.pid " +
							  "and relations.tid=posts_tags.id and posts_tags.tagname=?", 
							  page, 
							  1000,
							  posttag.getTagname()
							  );
		System.out.println(posttag.getTagname());
		helper.closeConnection();
		return list;		
	}
}

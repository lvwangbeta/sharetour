package com.sharetour.dao;

import java.util.List;
import com.sharetour.model.Post;
import com.sharetour.model.PostTag;
import com.sharetour.util.QueryHelper;

public class TagDAO {
	private final static String KEY = "hottags";
	private final static String TABLE = "posts_tags";

	public static List<PostTag> getHotTag(int page, int limit){
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

	public List<Post> getPostsRelatedToTag(String tagname){
		QueryHelper helper = new QueryHelper();
		List<Post> list = helper.
			query_slice_cache(
							  Post.class,
							  "posts_relatedto_"+tagname, 
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

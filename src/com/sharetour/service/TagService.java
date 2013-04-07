package com.sharetour.service;

import java.util.List;
import com.sharetour.model.PostTag;
import com.sharetour.util.QueryHelper;

public class TagService {
	private final static String KEY = "hottags";
	private final static String TABLE = "posts_tags";
	/*
	 * 
	 */
	public List<PostTag> getHotTag(){
		List<PostTag> list = (List<PostTag>)new QueryHelper().
			query_slice_cache(
								PostTag.class,
								KEY,
								"select tagname, postcount from " + TABLE + " order by postcount desc",
								1,
								10,
								(Object[])null
							  );
		
		return list;
		
	}
}

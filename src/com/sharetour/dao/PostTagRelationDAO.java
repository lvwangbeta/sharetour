package com.sharetour.dao;

import java.util.List;

import com.sharetour.model.PostTagRelation;
import com.sharetour.util.QueryHelper;

public class PostTagRelationDAO {
	
	public void savePostTagRelation(long pid, List<Long> tids){
		QueryHelper helper = new QueryHelper();
		PostTagRelation relation = new PostTagRelation();
		relation.setQueryHelper(helper);
		relation.setPid(pid);
		for(long tid: tids)
		{
			relation.setTid(tid);
			relation.Save();
		}
		helper.closeConnection();
	}
}

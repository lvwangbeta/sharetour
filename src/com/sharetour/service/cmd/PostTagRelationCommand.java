package com.sharetour.service.cmd;

import java.sql.Connection;
import java.util.List;

import com.sharetour.dao.DAOCommand;
import com.sharetour.model.Relation;
import com.sharetour.util.QueryHelper;

public class PostTagRelationCommand implements DAOCommand{

	private long pid;			//post id
	private List<Long> tids;		//tag id
	private Connection connection;
	public PostTagRelationCommand(long pid, List<Long> tids){
		this.pid = pid;
		this.tids = tids;
	}
	
	@Override
	public Object execute(Connection connection) {
		this.connection = connection;
		Relation relation = new Relation();
		QueryHelper helper = new QueryHelper(this.connection);
		relation.setQueryHelper(helper);
		relation.setPid(pid);
		for(long tid: tids)
		{
			relation.setTid(tid);
			relation.Save();
		}
		return null;
	}

}

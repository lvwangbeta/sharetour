package com.sharetour.service.cmd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.sharetour.dao.DAOCommand;

public class PostTagCommand implements DAOCommand{

	private String[] tags;
	private Connection connection;
	private final static String SEPARATOR = " ";
	private final static String TABLE = "posts_tags";
	
	public PostTagCommand(String tags){
		this.tags = StringUtils.split(tags, SEPARATOR);
	}
	
	@Override
	public Object execute(Connection connection) {
		this.connection = connection;
		if(tags == null || tags.length == 0)
			return null;
		//insert new entry and update count number column of old entry
		StringBuilder sql = new StringBuilder(
				"insert into posts_tags(tagname) values(?) on duplicate key update postcount=postcount+1");
		int len = tags.length;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			pstm = this.connection.prepareStatement(sql.toString());
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

}

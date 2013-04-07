package com.sharetour.service.cmd;

import java.sql.Connection;
import com.sharetour.dao.DAOCommand;
import com.sharetour.model.UserInfo;
import com.sharetour.util.QueryHelper;

public class LoginCheckCommand implements DAOCommand{

	private UserInfo user;
	public LoginCheckCommand(UserInfo user)
	{
		this.user = user;
	}
	
	@Override
	public Object execute(Connection connection) {
		// TODO Auto-generated method stub
		QueryHelper helper = new QueryHelper(connection);
		UserInfo userinfo = helper.get(
				  UserInfo.class, this.user.getUsername(),
				  "select * from users where username=? and password=?", 
				  new Object[]{this.user.getUsername(), this.user.getPassword()});
		return userinfo;
	}

}

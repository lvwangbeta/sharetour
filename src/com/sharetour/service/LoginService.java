package com.sharetour.service;

import java.sql.Connection;
import com.sharetour.dao.DAOCommand;
import com.sharetour.dao.DBManager;
import com.sharetour.dao.impl.MySQLManager;
import com.sharetour.db.ConnectionPool;
import com.sharetour.model.UserInfo;
import com.sharetour.service.cmd.LoginCheckCommand;

public class LoginService {
	private UserInfo user;
	public UserInfo LoginCheck(UserInfo user)
	{
		this.user = user;
		Connection connection = ConnectionPool.getInstance().getConnection();
		DBManager manager = new MySQLManager(connection);
		DAOCommand loginCheckCmd = new LoginCheckCommand(this.user);
		return (UserInfo) manager.executeAndClose(loginCheckCmd);
	}
	
}

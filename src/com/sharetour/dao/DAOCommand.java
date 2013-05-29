/*
 * 操作数据库对象的命令接口
 */
package com.sharetour.dao;

import java.sql.Connection;

public interface DAOCommand {
	/*
	 * Connection应该设计成接口的 
	 * 因为可能不止有MySQl
	 * 过两天再说吧 :)
	 */
	public Object execute(Connection connection);
}

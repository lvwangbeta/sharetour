/*
 * DBManger接口定义POJO类不能完成的
 * 事务处理，使其在model和service层之间提供一个桥梁
 */
package com.sharetour.dao;

import java.sql.SQLException;


public interface DBManager {
	/*
	 * 传入命令对象
	 * 执行命令对象
	 * @param {DAOCommand} cmd
	 * @return {Object}
	 */
	public Object execute(DAOCommand cmd);
	
	/*
	 * 传入命令对象
	 * 执行命令对象并关闭数据库连接
	 * @param {DAOCommand} cmd
	 * @return {Object}
	 */
	public Object executeAndClose(DAOCommand cmd);
	
	/*
	 * 数据库的事务处理
	 * @param {DAOCommand} cmd
	 * @return {Object}
	 */
	public Object transaction(DAOCommand cmd) throws SQLException;	
	
	/*
	 * 开启事务
	 */
	public void beginTransaction() throws SQLException;
	
	/*
	 * 提交并关闭事务
	 */
	public void endTransaction() throws SQLException;
	
	
	

}

package com.sharetour.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.sharetour.dao.DAOCommand;
import com.sharetour.dao.DBManager;
import com.sharetour.db.ConnectionPool;

public class MySQLManager implements DBManager{
	
	protected Connection connection;
	public MySQLManager(){
		connection = ConnectionPool.getInstance().getConnection();
	}
	public MySQLManager(Connection con){
		connection = con;
	}
	
	@Override
	public Object execute(DAOCommand cmd) {
		return cmd.execute(connection);
	}

	@Override
	public Object executeAndClose(DAOCommand cmd) {
		try{
			return cmd.execute(connection);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				connection = null;
			}
		}	
	}

	/*
	 * (non-Javadoc)
	 * @see org.blog.dao.DBManager#transaction(org.blog.dao.DAOCommand)
	 * 完整的事务处理
	 */
	@Override
	public Object transaction(DAOCommand cmd) throws SQLException{
		try {
			connection.setAutoCommit(false);
			Object obj = cmd.execute(connection);
			connection.commit();
			return obj;
		} catch (SQLException e) {
			connection.rollback();
		}finally{
			connection.setAutoCommit(true);
		}
		return null;	
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.blog.dao.DBManager#beginTransaction()
	 */
	@Override
	public void beginTransaction() throws SQLException {
		this.connection.setAutoCommit(false);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.blog.dao.DBManager#endTransaction()
	 */
	@Override
	public void endTransaction() throws SQLException{
		try {
			this.connection.commit();
		} catch (SQLException e) {
			this.connection.rollback();
		}finally{
			this.connection.setAutoCommit(true);
		}			
	}

}

package com.sharetour.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.*;

public class ConnectionPool {
	
	private static DataSource DS;
	private volatile static ConnectionPool instance;
	private static String url;
	private static String username;
	private static String password;
	private static String driver;
	private static int initialSize;
	private static int maxActive;
	private static int maxIdle;
	private static int maxWait;
	
	/*
	 * setPoolFromPram()
	 * 从用户传入参数设置数据库连接池
	 * @param url
	 * @param username
	 * @param password
	 * @param diver
	 * @param initialSize
	 * @param maxActive
	 * @param minIdle
	 * @param maxWait
	 */
	public static void setPoolFromParam(String purl, String pusername, String ppassword, String pdriver,
			int pinitialSize, int pmaxActive, int pmaxIdle, int pmaxWait)
	{
		url = purl;
		driver = pdriver;
		username = pusername;
		password = ppassword;
		initialSize = pinitialSize;
		maxActive = pmaxActive;
		maxIdle = pmaxIdle;
		maxWait = pmaxWait;
	}
	/*
	 * setPoolFromProperty()
	 * 从property文件设置数据库连接池
	 * @param filePath
	 */
	public static void setPoolFromProperty(String filePath)
	{
		Properties properties = new Properties();
		try {
			InputStream input = new FileInputStream(filePath);
			properties.load(input);
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		url = properties.getProperty("url");
		driver = properties.getProperty("driver");
		username = properties.getProperty("username");
		password = properties.getProperty("password");
	    initialSize = Integer.parseInt(properties.getProperty("initialSize"));
		maxActive = Integer.parseInt(properties.getProperty("maxActive"));
		maxIdle = Integer.parseInt(properties.getProperty("maxIdle"));
		maxWait = Integer.parseInt(properties.getProperty("maxWait"));		
	}
	
	
	private static void InitDS()
	{
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setInitialSize(initialSize);
		ds.setMaxActive(maxActive);
		ds.setMaxIdle(maxIdle);
		ds.setMaxWait(maxWait);
		DS = ds;
	}
	private ConnectionPool()
	{
		InitDS();
	}
	
	/*
	 * getInstance()
	 * Singleton保证连接池唯一
	 */
	public static ConnectionPool getInstance()
	{
		if(instance == null)
		{
			synchronized(ConnectionPool.class)
			{
				if(instance == null)
				{  
					instance = new ConnectionPool();
				}
			}
		}
		return instance;
	}
	public Connection getConnection()
	{
		Connection con = null;
		if(DS == null)
			InitDS();
		if(DS != null)
		{		
			try {
				con = DS.getConnection();
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return con;
	}
	
	public static void CloseCon(Connection con, PreparedStatement pstm, ResultSet res)
	{
		if(con != null)
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				con = null;
			}
		}
		if(pstm != null)
		{
			try {
				pstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				pstm = null;
			}
		}
		if(res != null)
		{
			try {
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				res = null;
			}
		}
	}
}
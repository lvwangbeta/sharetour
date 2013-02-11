package com.sharetour.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.*;

public class ConnectionPool {
	private static DataSource DS;
	private static ConnectionPool instance;
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
	 * �����û������ʼ�����ݿ����ӱ���
	 * @param url:���ݿ�����url
	 * @param username:�û���
	 * @param password:����
	 * @param diver:���ݿ�����
	 * @param initialSize����ʼ�����ӳش�С
	 * @param maxActive����󼤻�������
	 * @param minIdle: ������
	 * @param maxWait:���ȴ�������
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
	 * @param filePath�������ļ�λ��
	 * �������ļ���ʼ�����ݿ����ӱ���
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
	 * Singleton �������ݿ����ӳص�Ψһʵ��
	 */
	public static ConnectionPool getInstance()
	{
		if(instance == null)
		{
			synchronized(ConnectionPool.class)
			{
				if(instance == null)  //check again��֤ȷʵinstanceΪ��
				{					  //��Ϊ�п��ܼ���ʱ��instanceǡ�ñ�����ʵ����ʼ��
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
}

package com.sharetour.util;

import java.util.List;
import java.sql.*;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.sharetour.cache.CacheHelper;

import com.sharetour.db.ConnectionPool;

public class QueryHelper {
	
	private Connection connection;
	
	/*
	 * 如不不提供连接
	 * 则自动从连接池中获取
	 * 不过不提倡这样
	 */
	public QueryHelper()
	{
		this.connection = ConnectionPool.getInstance().getConnection();
	}
	public QueryHelper(Connection connection)
	{
		this.connection = connection;
	}
	public void setConnection(Connection connection)
	{
		this.connection = connection;
	}
	public Connection getConnection()
	{
		return connection;
	}
	
	public <T> List<T> executeQuery(Class<T> beanClass, String sql, Object...params)
	{
		QueryRunner query = new QueryRunner();
		try {
			return query.query(connection, sql, new BeanListHandler<T>(beanClass), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * 分页查询
	 * 
	 * @param {Class<T>} beanClass
	 * @param {String} sql
	 * @param {int} page num
	 * @param {size} size of per page
	 * @return {list<T>} 返回数据库数据生成的T对象的列表
	 */
	public <T> List<T> query_slice(Class<T> beanClass, String sql, int page, int size, Object...params)
	{
		if(page < 0 || size < 0)
			throw new IllegalArgumentException("Illegal parameter of 'page' or 'size', Must be positive.");
		int from = (page - 1)*size;
		sql = sql + " LIMIT "+ from + " , " + size ;
		
		try {
			QueryRunner query = new QueryRunner();
			return query.query(connection, sql, new BeanListHandler<T>(beanClass), params);		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * 分页缓存查询
	 * @param {Class<T>} beanClass
	 * @param {String} key
	 * @param {String} sql
	 * @param {int} page num
	 * @param {size} size of per page
	 * @return {list<T>} 返回数据库数据生成的T对象的列表
	 */
	public <T> List<T> query_slice_cache(
			Class<T> beanClass, String key, String sql, int page, int size, Object...params)
	{
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>)CacheHelper.getCacheData(beanClass.getSimpleName(), key);
		if(list == null)
		{
			System.out.println(beanClass.getSimpleName()+" not use cache");
			list = query_slice(beanClass, sql, page, size, params);
			CacheHelper.put(beanClass.getSimpleName(), key, list);
		}
		return list;
	}
	
	/*
	 * 获得数据库数据并生成对象
	 * 
	 * @param {Class<T>} beanClass 要建立的对象类型信息
	 * @param {Object}   key	cache的key
	 * @param {String}   sql 
	 * @param {Object}   preparedStatement方法中的?位置参数
	 * @return {T extends POJO}
	 */
	public <T> T get(Class<T> beanClass, Object key, String sql, Object...params)
	{
		@SuppressWarnings("unchecked")
		T obj = (T)CacheHelper.getCacheData(beanClass.getSimpleName(), key);
		//not in the cache, get direct from DB and put in cache
		if(obj == null)
		{
			try {
				QueryRunner query = new QueryRunner();
				obj = query.query(connection, sql, new BeanHandler<T>(beanClass), params);
				CacheHelper.put(beanClass.getSimpleName(), key, obj);			
				System.out.println("sql");
			} catch (SQLException e) {
				obj = null;
			}			
		}
		return obj;
	}
	
	/*
	 * 批处理操作
	 * @param {String} sql
	 * @param {Object[][]} params
	 * @return {int[]} the rows effected of each sql 
	 */
	public int[] batch(String sql, Object[][] params)
	{
		int[] effected = null;
		QueryRunner query = new QueryRunner();
		try {
			effected = query.batch(connection, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return effected;
	}
	/*
	 * 关闭数据库连接
	 */
	public void closeConnection()
	{
		if(this.connection != null)
		{
			try {
				this.connection.close();
			} catch (SQLException e) {
				this.connection = null;
			}
		}
	}
	
}

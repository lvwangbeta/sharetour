package com.sharetour.cache;

public class CacheHelper {
	private static CacheProvider provider;
	
	static
	{
		init();
	}
	
	public static void init()
	{
		provider = new EhCacheProvider();
		provider.start();
	}
	
	/*
	 * 获得缓存
	 * @param {String} cache name
	 * @return {Cache} 实现了Cache接口的Cache类
	 */
	public static Cache getCache(String cache_name)
	{
		if(provider == null)
		{
			provider = new EhCacheProvider();
			provider.start();
		}
		return provider.getCache(cache_name);
	}
	
	/*
	 * 获得缓存中数据
	 * @param {String} cache  name
	 * @param {Object} cache  key
	 * @return {Object} cache value
	 */
	public static Object getCacheData(String cache_name, Object key)
	{
		if(cache_name != null && cache_name.length() !=0 && key != null)
			return getCache(cache_name).get(key);
		return null;
	}
	
	/*
	 * 写入缓存
	 * @param {String} cache name
	 * @param {Object} key
	 * @param {Object} value
	 */
	public static void put(String cache_name, Object key, Object value)
	{
		getCache(cache_name).put(key, value);
	}
	
	/*
	 * stop cache 
	 */
	public static void stop(){
		if(provider != null){
			provider.stop();
		}
	}
	
	
	
}

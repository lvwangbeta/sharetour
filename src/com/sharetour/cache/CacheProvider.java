package com.sharetour.cache;

/*
 * CacheProvider接口，CacheHelper类通过该接口获得CacheProvier
 * 进而获得Cache
 */
public interface CacheProvider {
	/*
	 * 获得cache
	 */
	public Cache getCache(String cache_name);
	/*
	 * CacheProvider在使用前需通过start()设置配置
	 */
	public void start();
	/*
	 * cache stop and destroy
	 */
	public void stop();
}

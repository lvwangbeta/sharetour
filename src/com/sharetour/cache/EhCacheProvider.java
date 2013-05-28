package com.sharetour.cache;

import java.util.HashMap;
import java.util.Map;

import net.sf.ehcache.CacheManager;

public class EhCacheProvider implements CacheProvider{

	private CacheManager manager;
	private Map<String, Cache> cacheMap; 
	
	@Override
	public Cache getCache(String cache_name) {
		Cache cache_m = cacheMap.get(cache_name);
		if(cache_m == null)
		{
			net.sf.ehcache.Cache cache = manager.getCache(cache_name);
			if(cache == null)
			{
				manager.addCache(cache_name);
				cache = manager.getCache(cache_name);
				
			}
			synchronized(cacheMap)
			{
				cache_m = new EhCache(cache);
				cacheMap.put(cache_name, cache_m);
				return cache_m;
			}
			
		}
		return cache_m;
	}

	@Override
	public void start() {
		manager = CacheManager.create();
		cacheMap = new HashMap<String, Cache>();
	}

	@Override
	public void stop() {
		if(manager != null){
			manager.shutdown();
		}
			
	}


}

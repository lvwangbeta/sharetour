package com.sharetour.cache;

import net.sf.ehcache.Element;

public class EhCache implements Cache{

	private net.sf.ehcache.Cache cache;
	
	public EhCache(net.sf.ehcache.Cache cache)
	{
		this.cache = cache;
	}
	@Override
	public void put(Object key, Object value) {
		Element element = new Element(key, value);
		cache.put(element);
	}

	@Override
	public Object get(Object key) {
		Element element = cache.get(key);
		if(element != null)
		{
			return element.getObjectValue();
		}
		return null;
	}
	@Override
	public void remove(Object key) {
		cache.remove(key);
	}

}

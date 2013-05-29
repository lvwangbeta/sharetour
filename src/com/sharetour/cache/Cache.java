package com.sharetour.cache;

/*
 * Cache操作接口
 * 完成对cache内数据的CRUD
 */
public interface Cache {
	/*
	 * 向cache中存入数据
	 */
	public void put(Object key, Object value);	
	/*
	 * 从cache中得到数据
	 */
	public Object get(Object key);
	
	/*
	 * 从cache中移除数据
	 */
	public void remove(Object key);
}

package com.immoc.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义缓存管理
 * @author LUKE
 *
 */
public class CustomCacheManager implements CacheManager {

	@Autowired
	private RedisCache redisCache;
	
	@Override
	public <K, V> Cache<K, V> getCache(String s) throws CacheException {
		return redisCache;
	}

}

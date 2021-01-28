package com.immoc.shiro.cache;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import com.immoc.shiro.util.JedisUtil;

@Component
public class RedisCache<K,V> implements Cache<K, V> {
	
	@Autowired
	private JedisUtil jedisUtil;
	
	private final String CACHE_PREFIX ="permission-cache";
	
	public byte[] getKey(K k){
		if(k instanceof String){
			return (CACHE_PREFIX+k).getBytes();
		}
		return SerializationUtils.serialize(k);
	}

	@Override
	public V put(K k, V v) throws CacheException {
		byte[] key =getKey(k);
		byte[] value =SerializationUtils.serialize(v);
		jedisUtil.set(key, value);
		jedisUtil.expire(key, 600);
		return v;
	}
	
	@Override
	public V remove(K k) throws CacheException {
		byte[] key =getKey(k);
		byte[] value = jedisUtil.get(key);
		jedisUtil.del(key);
		if(value!=null){
			return (V) SerializationUtils.serialize(value);
		}
		return null;
	}
	
	@Override
	public V get(K k) throws CacheException {
		System.out.println("从缓存中读取授权信息");
		byte[] key =getKey(k);
		byte[] value = jedisUtil.get(key);
		if(value!=null){
			return (V) SerializationUtils.deserialize(value);
		}
		return null;
	}
	
	@Override
	public void clear() throws CacheException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

}

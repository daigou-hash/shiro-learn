package com.immoc.shiro.util;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisUtil {

	@Autowired
	private JedisPool jedisPool;

	private Jedis getResource() {
		return jedisPool.getResource();
	}

	public void set(byte[] key, byte[] value) {
		Jedis jedis = getResource();
		try {
			jedis.set(key, value);
		} finally {
			jedis.close();
		}
	}

	public void expire(byte[] key, int i) {
		Jedis jedis = getResource();
		try {
			jedis.expire(key, i);
		} finally {
			jedis.close();
		}
	}

	public void del(byte[] key) {
		Jedis jedis = getResource();
		try {
			jedis.del(key);
		} finally {
			jedis.close();
		}
	}

	public byte[] get(byte[] key) {
		Jedis jedis = getResource();
		try {
			byte[] value = jedis.get(key);
			return value;
		} finally {
			jedis.close();
		}
	}

	public Set<byte[]> keys(String shiroSessionPrefix) {
		Jedis jedis = getResource();
		try {
			Set<byte[]> keys = jedis.keys((shiroSessionPrefix+"*").getBytes());
			return keys;
		} finally {
			jedis.close();
		}
	}

}

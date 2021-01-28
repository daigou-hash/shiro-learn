package com.immoc.shiro.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import com.immoc.shiro.util.JedisUtil;

@Component
public class RedisSessionDao extends AbstractSessionDAO {

	@Autowired
	private JedisUtil jedisUtil;

	private static final String SHIRO_SESSION_PREFIX = "shiro-session-prefix:";

	private byte[] getKey(Serializable id) {

		return (SHIRO_SESSION_PREFIX + id).getBytes();
	}

	private Serializable saveSession(Session session) {
		if (session != null && session.getId() != null) {
			byte[] key = getKey(session.getId());
			byte[] value = SerializationUtils.serialize(session);
			jedisUtil.set(key, value);
			jedisUtil.expire(key, 600);
			return session.getId();
		}
		return null;
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId =generateSessionId(session);
		assignSessionId(session, sessionId);//绑定sessionId和session
		saveSession(session);
		return sessionId;
	}

	@Override
	public void update(Session session) throws UnknownSessionException {
		if(session!=null && session.getId()!=null){
			saveSession(session);
		}
	}

	@Override
	public void delete(Session session) {
		if (session != null && session.getId() != null) {
			jedisUtil.del(getKey(session.getId()));
		}
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		if(sessionId!=null){
			System.out.println("read session");
			byte[] value = jedisUtil.get(getKey(sessionId));
			return (Session) SerializationUtils.deserialize(value);
		}
		return null;
	}

	@Override
	public Collection<Session> getActiveSessions() {
		
		Set<byte[]> keys = jedisUtil.keys(SHIRO_SESSION_PREFIX);
		Set<Session> sessions = new HashSet<>();
		if(CollectionUtils.isNotEmpty(keys)){
			for (byte[] key : keys) {
				byte[] value = jedisUtil.get(key);
				sessions.add((Session) SerializationUtils.deserialize(value));
			}
		}
		return sessions;
	}

}

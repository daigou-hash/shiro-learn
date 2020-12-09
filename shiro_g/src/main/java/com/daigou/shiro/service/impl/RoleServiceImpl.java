package com.daigou.shiro.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daigou.shiro.dao.RoleDao;
import com.daigou.shiro.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;

	@Override
	public Set<String> getRolesByUsername(String username) {
		return roleDao.getRolesByUsername(username);
	}

}

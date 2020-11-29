package com.daigou.shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daigou.shiro.dao.UserDao;
import com.daigou.shiro.entity.User;
import com.daigou.shiro.service.UserService;

@Component
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public void saveUser(User user) {
		
	}

	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

}

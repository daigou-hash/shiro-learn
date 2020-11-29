package com.daigou.shiro.dao;

import com.daigou.shiro.entity.User;

public interface UserDao {

	public void saveUser(User user);
	
	public User getUserByUsername(String username);
}

package com.daigou.shiro.service;

import com.daigou.shiro.entity.User;

public interface UserService {

	public void saveUser(User user);
	
	public User getUserByUsername(String username);
}

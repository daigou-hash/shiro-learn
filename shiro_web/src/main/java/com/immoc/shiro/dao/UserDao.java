package com.immoc.shiro.dao;

import java.util.List;

import com.immoc.shiro.controller.User;

public interface UserDao {

	User getPasswordByUsername(String username);

	List<String> getRolesByUsername(String username);

	List<String> getPermissionsByUsername(String username);

}

package com.daigou.shiro.dao;

import java.util.Set;

public interface RoleDao {

	Set<String> getRolesByUsername(String username);
}

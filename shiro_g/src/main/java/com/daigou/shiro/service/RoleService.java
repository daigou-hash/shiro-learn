package com.daigou.shiro.service;

import java.util.Set;

public interface RoleService {

	Set<String> getRolesByUsername(String username);

	int getRoleId(String roleName);
}

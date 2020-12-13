package com.daigou.shiro.service;

import java.util.List;

import com.daigou.shiro.entity.Menu;

public interface MenuService {

	List<Menu> getMenuList(int roleId);

}

package com.daigou.shiro.dao;

import java.util.List;

import com.daigou.shiro.entity.Menu;

public interface MenuDao {

	List<Menu> getMenuList(int roleId);

}

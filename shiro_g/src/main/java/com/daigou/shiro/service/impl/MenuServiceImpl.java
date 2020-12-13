package com.daigou.shiro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daigou.shiro.dao.MenuDao;
import com.daigou.shiro.entity.Menu;
import com.daigou.shiro.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuDao menuDao;

	@Override
	public List<Menu> getMenuList(int roleId) {
		return menuDao.getMenuList(roleId);
	}

}

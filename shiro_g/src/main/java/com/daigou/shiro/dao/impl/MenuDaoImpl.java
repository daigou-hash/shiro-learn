package com.daigou.shiro.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.daigou.shiro.dao.MenuDao;
import com.daigou.shiro.entity.Menu;

@Component
public class MenuDaoImpl implements MenuDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Menu> getMenuList(int roleId) {
		String sql = "SELECT m.id,m.menuName FROM menus m , role_menu rm where m.id=rm.menu_id and rm.role_id = ?";

		List<Menu> menuList = jdbcTemplate.query(sql, new Integer[] { roleId }, new RowMapper<Menu>() {
			@Override
			public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
				Menu menu = new Menu();
				menu.setId(rs.getInt("id"));
				menu.setMenuName(rs.getString("menuName"));
				return menu;
			}

		});
		return menuList;
	}
}

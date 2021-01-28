package com.immoc.shiro.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.immoc.shiro.controller.User;
import com.immoc.shiro.dao.UserDao;

@Component
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User getPasswordByUsername(String username) {

		String sql = "select username,password from users where username = ?";

		List<User> userList = jdbcTemplate.query(sql, new String[] { username }, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user;
			}

		});
		if (CollectionUtils.isEmpty(userList)) {
			return null;
		}
		return userList.get(0);
	}

	@Override
	public List<String> getRolesByUsername(String username) {

		String sql = "select role_name from user_roles where username = ?";
		List<String> roles = jdbcTemplate.query(sql, new String[] { username }, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("role_name");
			}
		});
		return roles;
	}

	@Override
	public List<String> getPermissionsByUsername(String username) {
		String sql = "select permission from roles_permissions where role_name in (select role_name from user_roles where username = ?)";
		List<String> permissions = jdbcTemplate.query(sql, new String[]{username}, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("permission");
			}
		});
		return permissions;
	}

}

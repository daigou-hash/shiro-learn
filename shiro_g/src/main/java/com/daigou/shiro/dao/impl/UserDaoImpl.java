package com.daigou.shiro.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.daigou.shiro.dao.UserDao;
import com.daigou.shiro.entity.User;

@Component
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User getUserByUsername(String username) {

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

}

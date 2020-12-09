package com.daigou.shiro.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.daigou.shiro.dao.RoleDao;

@Component
public class RoleDaoImpl implements RoleDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Set<String> getRolesByUsername(String username) {
		String sql = "SELECT role_name FROM `roles` r,users u,user_role ur where u.id = ur.user_id and r.id = ur.role_id and u.username =?";

		List<String> roleList = jdbcTemplate.query(sql, new String[] { username }, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				String roleName =rs.getString("role_name");
				return roleName;
			}

		});
		if (CollectionUtils.isEmpty(roleList)) {
			return null;
		}
		return new HashSet<>(roleList);
	}

}

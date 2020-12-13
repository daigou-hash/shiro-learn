package com.daigou.shiro.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daigou.shiro.entity.Menu;
import com.daigou.shiro.entity.User;
import com.daigou.shiro.service.MenuService;
import com.daigou.shiro.service.RoleService;

/**
 * 菜单
 * @author LUKE
 * 
 * @date   2020年12月13日
 */
@Controller
public class MenuController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MenuService menuService;

	@RequestMapping(value="getMenuList",produces="application/json;charset=utf-8")
	@ResponseBody
	public String getMenuList(HttpServletRequest request){
		//取得用户信息
		User user = (User)request.getSession().getAttribute("userInfo");
		if(user!=null){
			Set<String> roleNames = roleService.getRolesByUsername(user.getUsername());
			Set<Menu> menuSet = new HashSet<>();
			for (String roleName : roleNames) {
				int roleId = roleService.getRoleId(roleName);
				List<Menu> menuList =menuService.getMenuList(roleId);
				menuSet.addAll(menuList);
			}
			StringBuffer sb = new StringBuffer();
			for (Menu menu : menuSet) {
				sb.append(menu.getMenuName());
				sb.append("&");
			}
			return sb.toString();
		}
		return null;
	}
}

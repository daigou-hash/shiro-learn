package com.immoc.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

	@RequestMapping(value="/subLogin",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String login(User user){
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		token.setRememberMe(user.isRememberMe());
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			return e.getMessage();
		}
		if(subject.hasRole("admin")){
			if(subject.isPermitted("user:delete")){
				return "有admin角色有用户删除权限";
			}else {
				return "有admin角色有用户删除权限";
			}
		}else {
			return "无admin角色";
		}
	}
	
	@RequestMapping("testRole")
	@ResponseBody
	public String testRole(){
		return "testRole:has admin role";
	}
	
	@RequestMapping("testRole1")
	@ResponseBody
	public String testRole1(){
		return "testRole:has admin1 role";
	}
	
	@RequestMapping("testPerm")
	@ResponseBody
	public String testPerm(){
		return "testPerm:has user:delete perm";
	}
	
	@RequestMapping("testPerm1")
	@ResponseBody
	public String testPerm1(){
		return "testPerm:has user:update perm";
	}
	@RequestMapping("testRoleOr")
	@ResponseBody
	public String testRoleOr(){
		return "testRole:has admin or admin1 role";
	}
	
}

package com.daigou.shiro.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daigou.shiro.entity.User;

/**
 * 
 * @author LUKE
 * 
 * @date   2020年11月29日
 */
@Controller
public class UserController {

	@RequestMapping(value="/subLogin",produces="application/json;charset=utf-8")
	@ResponseBody
	public String login(User user){
		
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		Subject subject = SecurityUtils.getSubject();
		try{
			subject.login(token);
		}catch(AuthenticationException e){
			return e.getMessage();
		}
		return "登录成功";
	}
	
	@RequestMapping(value="/testRole",produces="application/json;charset=utf-8")
	@ResponseBody
	public String testRole(){
		
		return "拥有admin角色";
	}
}

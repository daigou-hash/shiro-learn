package com.daigou.shiro.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpRequest;
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
	public String login(User user,HttpServletRequest request){
		
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		Subject subject = SecurityUtils.getSubject();
		try{
			subject.login(token);
			//将用户信息存入session
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", user);
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

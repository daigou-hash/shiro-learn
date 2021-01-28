package com.immoc.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

public class RoleOrFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		//此处object为roles
		
		//获取Subject
		Subject subject =getSubject(request, response);
		//判断角色
		String[] roles =(String[]) mappedValue;
		if(roles==null || roles.length==0){
			return true; //没有角色要求
		}else{
			for (String role : roles) {
				if(subject.hasRole(role)){
					return true;
				}
			}
		}
		return false;
	}

}

package com.daigou.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.daigou.shiro.entity.User;
import com.daigou.shiro.service.UserService;

/**
 * 
 * @author LUKE
 * 
 * @date   2020年11月29日
 */
public class CustomRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String)token.getPrincipal();
		User user = userService.getUserByUsername(username);
		if(user==null){
			return null;
		}else {
			return new SimpleAuthenticationInfo(username, user.getPassword(),getName());
		}
	}

}

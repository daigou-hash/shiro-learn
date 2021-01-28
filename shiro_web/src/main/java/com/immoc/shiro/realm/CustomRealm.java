package com.immoc.shiro.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.immoc.shiro.controller.User;
import com.immoc.shiro.dao.UserDao;

public class CustomRealm extends AuthorizingRealm {

	@Autowired
	private UserDao userDao;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("从数据库读取授权信息");
		String username = (String) principals.getPrimaryPrincipal();
		//
		Set<String> roles = getRolesByUsername(username);
		//
		Set<String> permissions = getPermissionsByUsername(username);
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(roles);
		authorizationInfo.setStringPermissions(permissions);
		return authorizationInfo;
	}

	private Set<String> getPermissionsByUsername(String username) {
		List<String> permissionList =userDao.getPermissionsByUsername(username);
		
		
		Set<String> permissions = new HashSet<>(permissionList);
		return permissions;
	}

	private Set<String> getRolesByUsername(String username) {
		
		List<String> roleList = userDao.getRolesByUsername(username);
		
		Set<String> roles = new HashSet<>(roleList);
		return roles;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//
		String username = (String) token.getPrincipal();
		//
		String password = getPasswordByUsername(username);
		if (password == null) {
			return null;
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, "customRealm");
		authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(username));
		return authenticationInfo;
	}

	private String getPasswordByUsername(String username) {
		 User user = userDao.getPasswordByUsername(username);
		 if(user!=null){
			 return user.getPassword();
		 }else {
			 return null;
		 }
	}
	
	public static void main(String[] args) {
		Md5Hash hash = new Md5Hash("123","Mark");
		System.out.println(hash.toString());
	}

}

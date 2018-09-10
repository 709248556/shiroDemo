package com.yan.shiro.common;

import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.yan.shiro.model.User;
import com.yan.shiro.service.UserService;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("UserRealm的doGetAuthorizationInfo方法开始执行");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		//获取当前登陆的用户名
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		//从数据库中查找该用户名拥有的角色
		Set<String> roleList = userService.selectRoleByUserName(username);
		// 添加角色
		authorizationInfo.addRoles(roleList);
		// 添加权限
		// authorizationInfo.setStringPermissions(userService.findPermissions(username));
		return authorizationInfo;
	}
	
	/**
	 * 登陆验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("UserRealm的doGetAuthenticationInfo方法开始执行");
		//获取当前登陆的用户名
		String userName = (String) token.getPrincipal();
		//从数据库中获取user
		User user = userService.selectByUserName(userName);
		// 为密码加盐
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());// 这里的参数要给个唯一的;
		// 校验
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getName(), user.getPassword(),credentialsSalt, getName());
		return authenticationInfo;
	}
	
	public static void main(String[] args) {
		SimpleHash MD5 = new SimpleHash("MD5", "123456", ByteSource.Util.bytes("小明"), 1024);
		System.out.println(MD5);
	}
}

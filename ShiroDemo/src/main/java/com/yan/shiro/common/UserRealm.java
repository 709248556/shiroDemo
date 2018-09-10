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
	 * ��Ȩ
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("UserRealm��doGetAuthorizationInfo������ʼִ��");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		//��ȡ��ǰ��½���û���
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		//�����ݿ��в��Ҹ��û���ӵ�еĽ�ɫ
		Set<String> roleList = userService.selectRoleByUserName(username);
		// ��ӽ�ɫ
		authorizationInfo.addRoles(roleList);
		// ���Ȩ��
		// authorizationInfo.setStringPermissions(userService.findPermissions(username));
		return authorizationInfo;
	}
	
	/**
	 * ��½��֤
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("UserRealm��doGetAuthenticationInfo������ʼִ��");
		//��ȡ��ǰ��½���û���
		String userName = (String) token.getPrincipal();
		//�����ݿ��л�ȡuser
		User user = userService.selectByUserName(userName);
		// Ϊ�������
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());// ����Ĳ���Ҫ����Ψһ��;
		// У��
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getName(), user.getPassword(),credentialsSalt, getName());
		return authenticationInfo;
	}
	
	public static void main(String[] args) {
		SimpleHash MD5 = new SimpleHash("MD5", "123456", ByteSource.Util.bytes("С��"), 1024);
		System.out.println(MD5);
	}
}

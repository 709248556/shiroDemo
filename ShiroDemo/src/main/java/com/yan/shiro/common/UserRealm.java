package com.yan.shiro.common;

import java.util.List;
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

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("UserRealm��doGetAuthorizationInfo������ʼִ��");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		Set<String> roleList = userService.selectRoleByUserName(username);
		// ��ӽ�ɫ
		authorizationInfo.addRoles(roleList);
		// ���Ȩ��
		// authorizationInfo.setStringPermissions(userService.findPermissions(username));
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("UserRealm��doGetAuthenticationInfo������ʼִ��");
		String userName = (String) token.getPrincipal();
		System.out.println("userName:" + userName);
		User user = userService.selectByUserName(userName);
		System.out.println("user:" + user);
		// ���û�����Ϊ��
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());// ����Ĳ���Ҫ����Ψһ��;
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getName(), user.getPassword(),
				credentialsSalt, getName());
		return authenticationInfo;
	}

	public static void main(String[] args) {
		SimpleHash MD5 = new SimpleHash("MD5", "123456", ByteSource.Util.bytes("abcd"), 1024);
		System.out.println(MD5);
	}

	/**
	 * �Զ�����֤��Ϊ���쳣 AuthenticationExceptionΪShiro��֤������쳣,��ͬ�������ͼ̳и��쳣����
	 */
	public class CaptchaEmptyException extends AuthenticationException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	}

	/**
	 * �Զ�����֤������쳣 AuthenticationExceptionΪShiro��֤������쳣,��ͬ�������ͼ̳и��쳣����
	 */
	public class CaptchaErrorException extends AuthenticationException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	}
}

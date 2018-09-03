package com.yan.shiro.common;

import org.apache.shiro.authc.UsernamePasswordToken;

public class CaptchaUsernamePasswordToken extends UsernamePasswordToken{
	//ÑéÖ¤Âë×Ö·û´®
	private String captcha;
 
	public CaptchaUsernamePasswordToken(String username, char[] password,
			boolean rememberMe, String host, String captcha) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}
 
	public String getCaptcha() {
		return captcha;
	}
 
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
}

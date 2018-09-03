<%@page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@page import="com.yan.shiro.exception.IncorrectCaptchaException"%>
<%@page import="org.apache.shiro.authc.AuthenticationException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.error {
	color: red;
}
</style>
<script type="text/javascript">
function refreshCaptcha(){
	document.getElementById("img_captcha").src="http://localhost:8081/ShiroDemo/kaptcha?t=" + Math.random();
}
</script>
</head>
<body>
 
	<%
		Object obj = request
				.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		String msg = "";
		if (obj != null) {
			if (obj instanceof IncorrectCaptchaException)
				msg = "验证码错误！";
			else 
				msg = "账号或密码错误！";
		}
		
		out.println("<div class='error'>" + msg + "</div>");
	%>
 
	<form action="login.jsp" method="post">
		<input type="hidden" name="rememberMe" value="true" /> <br />
		<table>
 
			<tr>
				<td>用户帐号：</td>
				<td><input type="text" name="username" id="username" value="" /></td>
			</tr>
			<tr>
				<td>登录密码：</td>
				<td><input type="password" name="password" id="password"
					value="" /></td>
			</tr>
			<tr>
				<td>验证码：</td>
				<td><input type="text" name="captcha" /></td>
			</tr>
			<tr>
				<td> </td>
				<td><img alt="验证码" src="http://localhost:8081/ShiroDemo/kaptcha" title="点击更换"
					id="img_captcha" onclick="javascript:refreshCaptcha();">(看不清<a href="javascript:void(0)" onclick="javascript:refreshCaptcha()">换一张</a>)</td>
			</tr>
			<tr>
				<td colspan="2"><input value="登录" type="submit"></td>
			</tr>
		</table>
 
	</form>
</body>
</html>
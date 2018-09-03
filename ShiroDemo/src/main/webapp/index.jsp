<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<body>
<h2>Hello World!</h2>
<shiro:guest>
欢迎游客访问，<a href="${pageContext.request.contextPath}/login.jsp">登录</a>
</shiro:guest>&nbsp;
<shiro:user>
欢迎 用户访问，<a href="${pageContext.request.contextPath}/login.jsp">登录</a>
</shiro:user>&nbsp;
<shiro:authenticated>
    用户[<shiro:principal/>]已身份验证通过
</shiro:authenticated>&nbsp;
<shiro:notAuthenticated>
    未身份验证（包括记住我）
</shiro:notAuthenticated> 
</body>
</html>

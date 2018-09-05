<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<body>
<h2>Hello World!</h2>
<shiro:guest>
欢迎游客访问，<a href="${pageContext.request.contextPath}/login.jsp">登录</a><br/>
</shiro:guest>&nbsp;
<shiro:user>
欢迎 用户访问，<a href="${pageContext.request.contextPath}/login.jsp">登录</a><br/>
</shiro:user>&nbsp;


<!-- 显示用户身份信息，默认调用 Subject.getPrincipal() 获取-->
<shiro:principal type="java.lang.String"/><br/><!-- 相当于 Subject.getPrincipals().oneByType(String.class)  -->

<shiro:principal type="java.lang.String"/><br/><!-- 相当于 Subject.getPrincipals().oneByType(String.class) -->


<shiro:hasAnyRoles name="admin,user">
    用户[<shiro:principal/>]拥有角色admin或user<br/>
</shiro:hasAnyRoles>

<shiro:lacksRole name="abc">
    用户[<shiro:principal/>]没有角色abc<br/> <!-- 如果当前 Subject abc角色将显示 body 体内容。 -->
</shiro:lacksRole>

<shiro:hasPermission name="user:create">
    用户[<shiro:principal/>]拥有权限user:create<br/> <!-- 如果当前 Subject 有create权限将显示 body 体内容。 -->
</shiro:hasPermission>

<shiro:lacksPermission name="org:create">
    用户[<shiro:principal/>]没有权限org:create<br/> <!-- 如果当前 Subject 没有create权限将显示 body 体内容。  -->
</shiro:lacksPermission>

</body>
</html>

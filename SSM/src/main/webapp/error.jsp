<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!--输出,条件,迭代标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%> <!--数据格式化标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="sql"%> <!--数据库相关标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fn"%> <!--常用函数标签库-->
<%@ page isELIgnored="false"%> <!--支持EL表达式，不设的话，EL表达式不会解析-->
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title></title>
</head>
<body>
登入失败!
${message}
<br>
<a href="<%=path%>/login.jsp">返回</a>
<br>
<%  
String[] names = {"zhangSan", "liSi", "wangWu", "zhaoLiu"};  
pageContext.setAttribute("ns", names);  
%>  
<c:forEach var="item" items="${ns}">  
    <c:out value="name: ${item}"/><br/>  
</c:forEach> 
<c:out value="aaa"/>
</body>
</html>
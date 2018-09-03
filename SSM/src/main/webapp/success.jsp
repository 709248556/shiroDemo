<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>

</script>
    <title></title>
</head>
<body>
登入成功!
<br>
您好!${user.username}
<br>
<a href="/SSM/login.jsp">返回</a>
<br>


</body>
</html>



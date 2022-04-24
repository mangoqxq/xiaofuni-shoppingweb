<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录失败</title>
<meta http-equiv="refresh" content="3; url=login.jsp"><!-- 3秒后跳转到登录页面 -->
</head>
<body>
<div>对不起，
<!-- 将存放在session中的用户名取出 -->
<%=(String)session.getAttribute("username")%>
</div>
请您确认用户名和密码!<p>三秒后跳转回登录页面</p>
</body>
</html>
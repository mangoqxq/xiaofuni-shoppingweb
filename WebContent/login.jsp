<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户登录</title>
<link type="text/css" href="css/login.css" rel="stylesheet"/>
<script type="text/javascript">
function change() {
document.getElementById("validate").src="ValidateServlet?random="+ Math.random();
}
</script>
</head>
<body>
<div class="login">
<h2>用户登录</h2>
<div class="content">
<form action="LoginServlet" method="post">
<div class="frm">用户名：
 <input type="text" name="username" class="txtuser" />
</div>
<div class="frm">密 码：
<input type="password" name="password" class="txtpwd" />
</div>
<div > 验证码：
<input type="text" name="userVCode" class="txtVcode" />
<span>
<img src="ValidateServlet" id="validate" onclick="change()" />
<a href="javascript:change()"> 看不清，换一张 </a>
</span>
</div>
<div class="btns">
<input type="submit" name="login" class="btnlogin" value="" />
<input type="button" name="register" class="btnregister"
onclick="window.open('register.html')"/>
</div>
</form>
</div>
</div>
</body>
</html>
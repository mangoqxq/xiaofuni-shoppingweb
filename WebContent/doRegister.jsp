<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import= "pojo.User,db.UserDB" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册成功信息检查页面</title>
<script type="text/javascript">

	var myTime = setInterval("clock_12h()",1000);  
	function goback(){    
	history.go(-1);    
	}
	</script>
</head>
<body>

<%
//设置编码
request.setCharacterEncoding("utf-8");
//获取信息
String name = request.getParameter("username");
String password = request.getParameter("userpwd");
String sex = request.getParameter("sex");
String birthday = request.getParameter("birthday");
String city = request.getParameter("selProvince");
String city1 = request.getParameter("selCity");
String email = request.getParameter("mail");
String job = request.getParameter("work");
String[] strLike = request.getParameterValues("like");
String add = request.getParameter("intro");
%>


<% 
//将复选框得到的数组值以字符串形式输出
String like = Arrays.toString(strLike);
//利用session进行多个页面之间进行传值
//User user = new User("李四","123","男","1980-07-01","182@qq.com","江苏","扬州","学生","手镯","打篮球");
User user = new User(name,password,sex,birthday,email,city,city1,job,like,add);

//创建对象
UserDB userDB = new UserDB();
//调用方法
userDB.regAdd(user);
//页面跳转
response.sendRedirect("login.jsp");

%>

</body>
</html>
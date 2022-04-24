<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,pojo.Decorations"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>小福泥饰品网->查看购物车</title>
<link href="css/mycar.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript " src="js/myCar.js"></script>
<style type="text/css">
ul {
	list-style: none;
	align:left
}
li {
	float: left;
}
a {
	text-decoration: none;
}
ul a {
	color: black;
	font-size: 15px;
}
a:hover{
color:red;
}
</style>
</head>
<body>
<jsp:useBean id="myCar" class="pojo.ShopCar" scope="session"/>
    <% request.setCharacterEncoding("utf-8");
    //HashMap<String, Decorations> decorations = (HashMap<String, Decorations>) session.getAttribute("decorations");
   HashMap<String, Decorations> decorations= myCar.getBuyList();
 
    %>
<div>
<form action="DoCarServlet" method="post" name="myform">
<div><h3>您的位置：<span><a href="homePage.jsp">首页</a></span>&gt;我的购物车</h3></div>
<div>
<%
String username = (String)session.getAttribute("username");
if(username==null||username.equals("游客")){
	response.sendRedirect("login.jsp");
}else{
out.print("欢迎您："+username);
session.setAttribute("username", username);
}
%>
</div>
<div>
<table class="table1" id="table1">
    <tr>
        <th><input type="checkbox" id="checkAll" onclick="selectAll()"/>全选</th>
        <th>图片</th>
        <th>饰品名称</th>
        <th>单价（元）</th>
        <th>数量</th>
        <th>小计（元）</th>
        <th>操作</th>
    </tr>
     <tbody id="lists">
<%

Decorations decoration;
//从 session 中将购物车（HashMap 对象）取出，遍历 HashMap，显示购物车中饰品列表信息。
for (HashMap.Entry<String, Decorations> entry : decorations.entrySet()){
 decoration=(Decorations)entry.getValue();
%>
    <tr>
        <td><input type="checkbox" name="checkOne" value="<%=entry.getKey() %>" onclick="selectAll()"></td>
        <td><img class="imgclass" src="images/<%=decoration.getDecCover() %>" alt="<%=decoration.getDecCover() %>"/></td>
        <td><a href="#"><%=decoration.getDecName() %></a></td>
        <td class="unitPrice"><%=decoration.getNowPrice() %></td>
       <td>
            <input id="subtract" class="num-minus num" type="button" value="-"/>
            <input id="text" class="amount" type="text" size="5" value="1"/>
            <input id="plus" class="num-plus num" type="button" value="+"/>
        </td>
        <td class="subtotal"></td>
        <td><a href="DoCarServlet?action=removeone&decNo=<%=entry.getKey()%>">删除</a>
</td>
    </tr>
 

<%
}
%>
 </tbody>
    </table>
    

<input type="submit" name="action" value="批量删除" id="idelete">
<div id="total_prices">商品总价（不含运费）：<span id="total">0</span>元</div>
  <div id="buy"><input type="submit" name="action" value="立即购买" id="ibuy"></div>

   
</div>

</form>
</div>
</body>
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
</html>

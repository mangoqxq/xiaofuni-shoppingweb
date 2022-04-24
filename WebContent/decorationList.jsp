<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,pojo.Decorations,db.DecDB"%>
<!DOCTYPE html>
<html>
<%
	//HashMap<String, Decorations> decs = (HashMap<String, Decorations>) session.getAttribute("decorations");
    //通过链接数据库,实现selectDec()方法
    DecDB adminDao = new DecDB();
    HashMap<String,Decorations> decs = adminDao.selectDec();
    //问题:没写这句话一直报空指针异常,用session可以实现跨页面传递信息
       session.setAttribute("decorations", decs);
   %>


<head>
<meta charset="UTF-8">
<title>小福泥饰品展示页面</title>
<link type="text/css" href="css/decorations.css" rel="stylesheet" />
<style type="text/css">
.vote-number{
vertical-align:middle;
font-family:微软雅黑,Verdana,Geneva, sans-serif;
font-size:12px;
}

</style>
</head>
<body>

	<%request.setCharacterEncoding("utf-8");
		//如果没有饰品信息
		if (decs == null || decs.size() == 0) {
	%>
	<div>没有商品可显示!</div>
<%
		}
		 else {
			
	%>
	<%String username = (String)session.getAttribute("username");
          if(username==null)
        	  username = "游客";
	out.print("欢迎您："+ username);
	session.setAttribute("username", username);
	%>
	<div class="content">
	
		<ul>
			<%
			
				Decorations dec;
					//遍历HashMap,显示所有饰品信息	通过 HashMap 的 entrySet()方法，可遍历 HashMap。
					for (HashMap.Entry<String, Decorations> entry : decs.entrySet()) {
						dec = (Decorations) entry.getValue();
			%>
			<li class="single">
			<a class="pic" href="" target="_blank">
			<img class="imgclass" src='images/<%=dec.getDecCover()%>' />
			</a>
				<div class="desc">
					<p>
						<a href="" target="_blank"> <%=dec.getDecName()%>
						</a>
					</p>
					<p>
						<span>现价：&yen;<%=dec.getNowPrice()%></span> <span class="orgprice">&nbsp;&nbsp;定价：
							<span class="org">&yen;<%=dec.getOrgPrice()%></span>
						</span>
					</p>
				<div class="star">
					<span class="vote-number">9.7分</span>
             	</div>
					<p>
						<a href="" target="_blank"><%=300%>条评论
						</a>
					</p>
					<p>

						<span> <a href=""><%=dec.getAuthor()%></a>&nbsp;创作

						</span> <span> /<%=dec.getPressDate()%></span> <span> /<a href=""><%=dec.getPress()%></a>
						</span>
					</p>
					<p class="detail"></p>
					<div>
						<p>
							<a class="bn"
								href="DoCarServlet?action=buy&decNo=<%=entry.getKey() %>">加入购物车</a>
								
							<a class="bn" href="">收藏</a>
						</p>
					</div>
				</div></li>
			<%
			}
			%>
		</ul>
		
		<div class="bncar">
			<a class="bn" href='showCar.jsp'>查看购物车</a>
		</div>


</div>
<%
		 }
%>
</body>
</html>
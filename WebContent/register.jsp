<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户注册界面</title>
<link type="text/css" href="css/register.css" rel = "stylesheet"/>
<script type="text/javascript " src="js/register.js"></script>
<style>
.banner{
	position:relative;
	background:url("images/theme-pic.jpg") no-repeat 0 200px;
	margin: 0 auto;
	width:1000px;
	height:678px;
}
.aside{
	position:absolute;
	left:60%;
	top:120px;
	width:400px;
	padding:20px;
	background:rgb(252,252,252);
	box-shadow:0px 1px 5px 0px rgb(0,0,0,3);
	order-radius:8px;
}
body{
	background:rgb(242,242,242);
}

</style>
</head>
<body>
<div class = "banner">
	<div class="aside">
		<form  action="doRegister.jsp" method="post" name="form1" onSubmit="return check(this)">
			<!-- action表示当提交表单时，向何处发送表单数据 -->
			<div class = "box">
				<label class = "i-label">*用户名</label>
				<!-- label标签主要是方便鼠标点击使用，增强用户操作体验 -->
				<input type="text" name="username" id="username" class = "i-text" onfocus="usernameFocus()" onblur="usernameBlur()"/>
			</div>
			<div id="usernameId"></div>
			<div class = "box">
				<label class = "i-label" >*密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
				 <input type="password" name="userpwd" id="userpwd" class = "i-text" onfocus="userpwdFocus()" onblur="userpwdBlur()"/>
			</div>
			<div id="userpwdId"></div>
			<div class = "box">
				<label class = "i-label">*密码确认</label> 
				<input type="password" name="userpwd2" id="userpwd2" class = "i-text" onfocus="userpwd2Focus()" onblur="userpwd2Blur()"/>
			</div>
			<div id="userpwd2Id"></div>
			<div class = "box">
				<label class="i-label">性&nbsp;&nbsp;&nbsp;&nbsp;别</label> 
				<span class="i-text"> 
				<input type="radio" name="sex" value = "男" checked>男 
				<input type="radio" name="sex" value = "女">女
				</span>
			</div>
			<div class="box">
				<label class="i-label">出生年月</label>
			   <input type="date" name="birthday" class="i-text">
			</div>
			 <div class="box">
			<label class="i-label">现居住地</label>
			<span class="i-text">
			 <select name="selProvince" onchange="changeCity()">
			
			 </select>
			  </span>
			  <span class="i-text">
			  <select name = "selCity"  >
			  </select>
			  </span>
			</div> 
			<div class="box">
				<label class="i-label">*邮&nbsp;&nbsp;&nbsp;&nbsp;箱</label> 
				<input type="text" name="mail" id="mail" class="i-text" onfocus="mailFocus()" onblur="mailBlur()">
			</div>
			<div id="mailId"></div>
			<div class="box">
				<label class="i-label">职&nbsp;&nbsp;&nbsp;&nbsp;业</label> 
				<select name="work" size="1" class="i-text" style="padding:4px;"><!-- 内联式进行局部调整 -->
					<option value="teacher">教师</option>
					<option value="student" selected="selected">学生</option>
					<option value="student">自由职业者</option>
					<option value="student">其他</option>
					<!-- selected设为默认选项 -->
				</select>
			</div>
			<div class="box">
				<label class="i-label">喜欢的饰品种类</label>
				 <span class="i-text" style="display:block;width:300px;"><!--将span设为块级元素  -->
				  <input type="checkbox" name="like" value="手链" checked>手链
				  <input type="checkbox" name="like" value="项链">项链
				  <input type="checkbox" name="like" value="耳饰">耳饰
				</span> 
				<span class="i-text" style="display:block;width:300px;"> 
				<input type="checkbox" name="like" value="戒指">戒指
				<input type="checkbox" name="like" value="发饰">发饰
			    <input type="checkbox" name="like" value="胸针">胸针
				</span>
			</div>
			<div class="box">
				<label class="i-label">个人说明</label>
				<textarea rows="8" cols="30" name="intro" class="i-text"></textarea>
			</div>
			<div class="box">
				<span class="i-text"> 
				<input type="submit" value="提 交">&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="reset" value="清 除">
				</span>
			</div>
			
		</form>
		</div>
	</div>
</body>

</html>
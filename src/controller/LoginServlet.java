package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.UserDB;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码
				request.setCharacterEncoding("utf-8");
				//获取用户名
				String username = request.getParameter("username");
				//获取密码
				String password = request.getParameter("password");
				String userVCode = request.getParameter("userVCode");
				//通过 HttpServletRequest 类型的 request 取得 session 对象
				HttpSession session = request.getSession();
				//利用session进行多个页面之间传值
				session.setAttribute("username", username);
				session.setAttribute("password", password);
				//创建对象
				UserDB userDao = new UserDB();
				//调用方法
				int result=0;
				try {
					result = userDao.login(username, password);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String validateCode = (String) session.getAttribute("validateCode");
				if (userVCode.equalsIgnoreCase(validateCode)) {

					//返回一表示登录成功跳转到登录成功界面
					if (result == 1) {
						response.sendRedirect("decorationList.jsp");
					} else if (result == 0) {
						response.sendRedirect("error.jsp");//登录失败
					} else {
						PrintWriter out = response.getWriter();
						out.print("系统出现异常");
					}
				} else{
					//验证码输入不正确时，向浏览器输出 js 脚本，给出错误提示，并后退到登录页面
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
			 out.println("<script>alert('验证码输入错误！');history.go(-1);</script>");
				}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}

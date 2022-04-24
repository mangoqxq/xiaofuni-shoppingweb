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
		//���ñ���
				request.setCharacterEncoding("utf-8");
				//��ȡ�û���
				String username = request.getParameter("username");
				//��ȡ����
				String password = request.getParameter("password");
				String userVCode = request.getParameter("userVCode");
				//ͨ�� HttpServletRequest ���͵� request ȡ�� session ����
				HttpSession session = request.getSession();
				//����session���ж��ҳ��֮�䴫ֵ
				session.setAttribute("username", username);
				session.setAttribute("password", password);
				//��������
				UserDB userDao = new UserDB();
				//���÷���
				int result=0;
				try {
					result = userDao.login(username, password);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String validateCode = (String) session.getAttribute("validateCode");
				if (userVCode.equalsIgnoreCase(validateCode)) {

					//����һ��ʾ��¼�ɹ���ת����¼�ɹ�����
					if (result == 1) {
						response.sendRedirect("decorationList.jsp");
					} else if (result == 0) {
						response.sendRedirect("error.jsp");//��¼ʧ��
					} else {
						PrintWriter out = response.getWriter();
						out.print("ϵͳ�����쳣");
					}
				} else{
					//��֤�����벻��ȷʱ������������ js �ű�������������ʾ�������˵���¼ҳ��
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
			 out.println("<script>alert('��֤���������');history.go(-1);</script>");
				}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}

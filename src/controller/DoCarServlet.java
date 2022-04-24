package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.HashMap;

import pojo.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.OrderDB;

/**
 * Servlet implementation class DoCarServlet
 */
@WebServlet("/DoCarServlet")
public class DoCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoCarServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		// ��HashMap�洢���ﳵ�����е���Ʒ�����м�ΪdecNo,ֵΪDecoraions
		HashMap<String, Decorations> decorations = (HashMap<String, Decorations>) session.getAttribute("decorations");
		// ����session��һ�ε�¼�ĸ���ҳ��֮�乲���ﳵ����session��ȡ��������Ʒ��Ϣ�͹��ﳵmyCar
		ShopCar myCar = (ShopCar) session.getAttribute("myCar");
		if (myCar == null)
			myCar = new ShopCar();
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if (action == null)
			action = "";
		if (action.equals("buy")) {
			String decNo = request.getParameter("decNo");
			System.out.println(decNo);
			Decorations temp = (Decorations) decorations.get(decNo);

			myCar.addItem(temp);
			session.setAttribute("myCar", myCar);
			response.sendRedirect("decorationList.jsp");
			return;
		}
		if (action.equals("add")) {
			String decNo = request.getParameter("decNo");
			Decorations temp = (Decorations) decorations.get(decNo);
			myCar.addItem(temp);
			session.setAttribute("myCar", myCar);
		} else if (action.equals("minus")) {
			String decNo = request.getParameter("decNo");
			myCar.minusItem(decNo);
			session.setAttribute("myCar", myCar);
		} else if (action.equals("removeone")) {
			String decNo = request.getParameter("decNo");
			myCar.removeItem(decNo);
			session.setAttribute("myCar", myCar);
		} else if (action.equals("����ɾ��")) {
			String[] decNos = request.getParameterValues("checkOne");
			myCar.removeItem(decNos);
			session.setAttribute("myCar", myCar);
		} else if (action.equals("��������")) {
			String[] decNos = request.getParameterValues("checkOne");
			//�ж��Ƿ��й�����Ʒ
			if (decNos.length == 0)
				return;
			//�ж��Ƿ����û���¼
			String flag = (String) session.getAttribute("username");
			if (flag == null || flag.equals("�ο�")) {
				response.sendRedirect("login.jsp");
				return;
			} else {
				String username = (String) session.getAttribute("username");
				float totalPrice = 0;
				Order order = new Order();
				order.createOrderId();//�Զ����ɶ������
				System.out.println(order.getOrderId());
				order.setUserName(username);
				order.setOrderTime(new Date());//����ʱ��ȡϵͳʱ��

				ArrayList<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
				//����ϸ����Ϣ
				for (int i = 0; i <decNos.length; i++) {
					OrderDetail orderDetail = new OrderDetail();
					Decorations decoration = myCar.getBuyList().get(decNos[i]);
					//�������Ϊ�Զ����ɵı��
					orderDetail.setOrderId(order.getOrderId());
                        //��ȡ��Ʒ�ı�ţ��۸񣬹����������������ܼ�
					orderDetail.setDecNo(decoration.getDecNo());
					orderDetail.setNowPrice(decoration.getNowPrice());
					orderDetail.setBuyNum(decoration.getBuyNum());
					orderDetails.add(orderDetail);
					//��������ܼ۸��Ƹ���������
					totalPrice += orderDetail.getNowPrice() * orderDetail.getBuyNum();
				}

				order.setTotalPrice(totalPrice);
                  //�������ݿ�ķ������������Ͷ���ϸ����������ݿ�
				OrderDB orderDB = new OrderDB();
				orderDB.addOrderDetail(orderDetails);
				
				orderDB.addOrder(order);
				orderDB.close();
                //Ȼ���ѹ����ͼ���Ƴ�
				myCar.removeItem(decNos);
				session.setAttribute("myCar", myCar);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('����ɹ���');window.location='showCar.jsp'; </script>");
				return;
			}
		}else {}

		response.sendRedirect("showCar.jsp");
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

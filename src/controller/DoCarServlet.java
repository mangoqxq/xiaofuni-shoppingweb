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
		// 用HashMap存储购物车中所有的饰品，其中键为decNo,值为Decoraions
		HashMap<String, Decorations> decorations = (HashMap<String, Decorations>) session.getAttribute("decorations");
		// 利用session在一次登录的各个页面之间共享购物车，从session中取出所有饰品信息和购物车myCar
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
		} else if (action.equals("批量删除")) {
			String[] decNos = request.getParameterValues("checkOne");
			myCar.removeItem(decNos);
			session.setAttribute("myCar", myCar);
		} else if (action.equals("立即购买")) {
			String[] decNos = request.getParameterValues("checkOne");
			//判断是否有购买商品
			if (decNos.length == 0)
				return;
			//判断是否有用户登录
			String flag = (String) session.getAttribute("username");
			if (flag == null || flag.equals("游客")) {
				response.sendRedirect("login.jsp");
				return;
			} else {
				String username = (String) session.getAttribute("username");
				float totalPrice = 0;
				Order order = new Order();
				order.createOrderId();//自动生成订单编号
				System.out.println(order.getOrderId());
				order.setUserName(username);
				order.setOrderTime(new Date());//订单时间取系统时间

				ArrayList<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
				//订单细节信息
				for (int i = 0; i <decNos.length; i++) {
					OrderDetail orderDetail = new OrderDetail();
					Decorations decoration = myCar.getBuyList().get(decNos[i]);
					//订单编号为自动生成的编号
					orderDetail.setOrderId(order.getOrderId());
                        //获取饰品的编号，价格，购买数量，并计算总价
					orderDetail.setDecNo(decoration.getDecNo());
					orderDetail.setNowPrice(decoration.getNowPrice());
					orderDetail.setBuyNum(decoration.getBuyNum());
					orderDetails.add(orderDetail);
					//将计算的总价复制给订单对象
					totalPrice += orderDetail.getNowPrice() * orderDetail.getBuyNum();
				}

				order.setTotalPrice(totalPrice);
                  //调用数据库的方法，将订单和订单细节其插入数据库
				OrderDB orderDB = new OrderDB();
				orderDB.addOrderDetail(orderDetails);
				
				orderDB.addOrder(order);
				orderDB.close();
                //然后将已购买的图书移除
				myCar.removeItem(decNos);
				session.setAttribute("myCar", myCar);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('购买成功！');window.location='showCar.jsp'; </script>");
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

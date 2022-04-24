package db;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import pojo.Order;
import pojo.OrderDetail;

public class OrderDB extends UserDB {
	// ���ݿ�URL
	private static String dbUrl = "jdbc:sqlserver://localhost:1433;decorations?useUnicode=true&characterEncoding=UTF-8";
	// ���ݿ��û���
	private static String dbUser = "sa";
	// ���ݿ�����
	private static String dbPwd = "123456";
	// jdbc����
	private static String jdbcName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	private static Connection connection;

	//private PreparedStatement preparedStatement;
	// ��̬����飬����ʱ���Զ�ִ��
	static {

		// ע�����ݿ�����
		try {
			Class.forName(jdbcName);
			// ��ȡ���ݿ�����
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int addOrder(Order order) {
		int result = 0;
		if (connection != null) {
			try {
				PreparedStatement pst = connection.prepareStatement("USE [decorations] insert into orders values(?,?,?,?)");
				pst.setString(1, order.getOrderId());
				pst.setString(2, order.getUserName());
				pst.setTimestamp(3, new java.sql.Timestamp(order.getOrderTime().getTime()));
				pst.setFloat(4, order.getTotalPrice());
				pst.execute();
				result = 1;
			} catch (SQLException ex) {
				System.err.println(this.getClass() + ex.getMessage());
			}
		}
		return result;
	}

	public int addOrderDetail(ArrayList<OrderDetail> orderDetails) {
		int result = 0;
		if (connection != null) {
			try {
				PreparedStatement pst = connection.prepareStatement("USE [decorations] insert into orderDetail values(?,?,?,?)");
				//ͨ��ѭ���� SQL �����뵽һ�����δ�����
				for (int i = 0; i < orderDetails.size(); i++) {
					//ͨ�� pst.setXxx(�����ţ�����ֵ)����,��������ֵ
					pst.setString(1, orderDetails.get(i).getOrderId());
					pst.setString(2, orderDetails.get(i).getDecNo());
					pst.setFloat(3, orderDetails.get(i).getNowPrice());
					pst.setInt(4, orderDetails.get(i).getBuyNum());
                   //����addBatch()�������뵽������					
					pst.addBatch();
				}
				//���� executeBatch()����һ����ִ�����������������
				pst.executeBatch();
			} catch (SQLException ex) {
				System.err.println(this.getClass() + ex.getMessage());
			}
		}
		return result;
	}
	public static void main(String[] args) {
		 
		InitialContext ctx;
		 PreparedStatement preparedStatement = null;
	
		String sql = "select * from users";
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/users");
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt(1));
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}

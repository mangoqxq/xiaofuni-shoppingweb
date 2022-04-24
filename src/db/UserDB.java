package db;

import java.sql.*;

import pojo.User;

public class UserDB {
	// ���ݿ�URL
	private static String dbUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=decorations";
	// ���ݿ��û���
	private static String dbUser = "sa";
	// ���ݿ�����
	private static String dbPwd = "123456";
	// jdbc����
	private static String jdbcName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	private static Connection connection;

	private PreparedStatement preparedStatement;
   //��̬����飬����ʱ���Զ�ִ��
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

	// �û���Ϣ��ӹ���
	public void regAdd(User user) throws SQLException {
	
		String city = user.getSelProvince() + user.getSelCity();
		System.out.println(city);
		// ������ӵ�sql���
		String sql = "insert into users values(?,?,?,?,?,?,?,?,?)";
		preparedStatement = connection.prepareStatement(sql);

		preparedStatement.setString(1, user.getUsername());
		preparedStatement.setString(2, user.getUserpwd());
		preparedStatement.setString(3, user.getSex());
		preparedStatement.setString(4, user.getBirthday());
		preparedStatement.setString(5, user.getMail());
		preparedStatement.setString(6, city);
		preparedStatement.setString(7, user.getWork());
		preparedStatement.setString(8, user.getLike());
		preparedStatement.setString(9, user.getIntro());
		// ִ�����
		preparedStatement.execute();

	}
//�û���¼����
	public int login(String userName,String userPwd) throws SQLException{
		int result=0;
		//��ѯ���ݿ���Ϣ,�ж��û��Ƿ����
		String sql = "select * from users where username=? and userpwd=?";
		preparedStatement = connection.prepareStatement(sql);
		//����sql������
		preparedStatement.setString(1, userName);
		preparedStatement.setString(2,userPwd);
		//ִ�в�ѯ,���ؽ����
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()) {
		  result = 1;
		}
		return result;
	}
	
	//����Ա��¼
	//public int adminLogin(String userName,String userPwd) throws SQLException{
		//int result=0;
		//��ѯ���ݿ���Ϣ,�ж��û��Ƿ����
		//String sql = "select * from admin where username=? and userpwd=?";
		//preparedStatement = connection.prepareStatement(sql);
		//����sql������
		//preparedStatement.setString(1, userName);
		//preparedStatement.setString(2,userPwd);
		//ִ�в�ѯ,���ؽ����
		//ResultSet rs = preparedStatement.executeQuery();
		//if(rs.next()) {
		  //result = 1;
		//}
		//return result;
	//}
	public void close() {

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
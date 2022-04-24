package db;

import java.sql.*;

import pojo.User;

public class UserDB {
	// 数据库URL
	private static String dbUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=decorations";
	// 数据库用户名
	private static String dbUser = "sa";
	// 数据库密码
	private static String dbPwd = "123456";
	// jdbc名称
	private static String jdbcName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	private static Connection connection;

	private PreparedStatement preparedStatement;
   //静态代码块，编译时就自动执行
	static {

		// 注册数据库驱动
		try {
			Class.forName(jdbcName);
			// 获取数据库连接
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 用户信息添加功能
	public void regAdd(User user) throws SQLException {
	
		String city = user.getSelProvince() + user.getSelCity();
		System.out.println(city);
		// 定义添加的sql语句
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
		// 执行添加
		preparedStatement.execute();

	}
//用户登录功能
	public int login(String userName,String userPwd) throws SQLException{
		int result=0;
		//查询数据库信息,判断用户是否存在
		String sql = "select * from users where username=? and userpwd=?";
		preparedStatement = connection.prepareStatement(sql);
		//设置sql语句参数
		preparedStatement.setString(1, userName);
		preparedStatement.setString(2,userPwd);
		//执行查询,返回结果集
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()) {
		  result = 1;
		}
		return result;
	}
	
	//管理员登录
	//public int adminLogin(String userName,String userPwd) throws SQLException{
		//int result=0;
		//查询数据库信息,判断用户是否存在
		//String sql = "select * from admin where username=? and userpwd=?";
		//preparedStatement = connection.prepareStatement(sql);
		//设置sql语句参数
		//preparedStatement.setString(1, userName);
		//preparedStatement.setString(2,userPwd);
		//执行查询,返回结果集
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
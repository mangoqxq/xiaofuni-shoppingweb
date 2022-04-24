package db;

import java.sql.*;
import java.util.HashMap;
import pojo.Decorations;;
public class DecDB extends UserDB{
	
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

//查询商品信息
public HashMap<String,Decorations> selectDec() {
	HashMap<String,Decorations> decoration = new HashMap<String,Decorations>();
	String sql = "select * from decs";
	try {
		Statement statement = connection.createStatement();
		
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()) {
			Decorations decorations = new Decorations();
			decorations.setDecNo(rs.getString("decNo").trim());
			decorations.setDecName(rs.getString("decName"));
			decorations.setAuthor(rs.getString("author"));
			decorations.setPress(rs.getString("press"));
			decorations.setPressDate(rs.getString("pressDate"));
			decorations.setDecNum(rs.getInt("decNum"));
			decorations.setOrgPrice(rs.getFloat("orgPrice"));
			decorations.setNowPrice(rs.getFloat("nowPrice"));
			decorations.setDecCover(rs.getString("decCover"));
		decoration.put(decorations.getDecNo(),decorations);
		}
		rs.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return decoration;
}

//添加商品信息
public int addDec(Decorations decoration) {
	int result =0;
	String sql ="insert into decs values(?,?,?,?,?,?,?,?,?)";
	try {
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		
		prepareStatement.setString(1, decoration.getDecNo());
		prepareStatement.setString(2, decoration.getDecName());
		prepareStatement.setString(3, decoration.getAuthor());
		prepareStatement.setString(4, decoration.getPress());
		prepareStatement.setString(5, decoration.getPressDate());
		prepareStatement.setInt(6, decoration.getDecNum());
		prepareStatement.setFloat(7, decoration.getOrgPrice());
		prepareStatement.setFloat(8, decoration.getNowPrice());
		prepareStatement.setString(9, decoration.getDecCover());
		prepareStatement.execute();
		result = 1;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 return result;
}
  
}

package db;

import java.sql.*;
import java.util.HashMap;
import pojo.Decorations;;
public class DecDB extends UserDB{
	
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

//��ѯ��Ʒ��Ϣ
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

//�����Ʒ��Ϣ
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

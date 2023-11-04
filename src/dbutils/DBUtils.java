package dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

	static Connection con ;
	static Statement stmt ;
	
	 static {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_management" , "root" , "Gaurav@8423");
			stmt =  con.createStatement();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public static void ExecuteQuery(String Query) {
		try {
			stmt.execute(Query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static ResultSet executeQueryGetResult(String Query) {
		ResultSet  resultset = null ;
		try {
			resultset = stmt.executeQuery(Query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultset;
	}
}

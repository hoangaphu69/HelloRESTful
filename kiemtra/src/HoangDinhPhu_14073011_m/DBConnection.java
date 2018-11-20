package HoangDinhPhu_14073011_m;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection con = null;
	private static DBConnection instance = new DBConnection();
	public static DBConnection getInstance() {
		return instance;
	}
	public void connection() {
		String url = "jdbc:sqlserver://localhost:49933;databasename=QLSV";
		String user = "sa";
		String password = "sapassword";
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static Connection getConnection() {
		String url = "jdbc:sqlserver://localhost:49933;databasename=QLSV";
		String user = "sa";
		String password = "sapassword";
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	} 

}

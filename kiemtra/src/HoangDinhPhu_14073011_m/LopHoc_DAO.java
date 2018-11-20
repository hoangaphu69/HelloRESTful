package HoangDinhPhu_14073011_m;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class LopHoc_DAO {
	public ArrayList<LopHoc> getAllTableLopHoc() {
		//		do something
		ArrayList<LopHoc> dslh = new ArrayList<LopHoc>();

		try {
			Connection con = DBConnection.getInstance().getConnection();
			String sql = "select * from tbLop";
			Statement statement = con.createStatement();
			
			//thuc thi cau lenh sql roi tra ve choi doi tuong resultSet
			ResultSet resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				String maLop = resultSet.getString(1);
				String tenLop = resultSet.getString(2);
				LopHoc lopHoc = new LopHoc(maLop, tenLop);
				dslh.add(lopHoc);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return dslh;
	}

}

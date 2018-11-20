package HoangDinhPhu_14073011_m;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SinhVien_DAO implements SinhVien_interface {

	@Override
	public ArrayList<SinhVien> getAllSinhVien() {
		ArrayList<SinhVien> dssv = new ArrayList<SinhVien>();
		Connection con = DBConnection.getInstance().getConnection();
		
		String sql = "select * from tbSinhVien";
		Statement statement;
		try {
			statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				int maSV = resultSet.getInt(1);
				String hoTen = resultSet.getString(2);
				String email = resultSet.getString(3);
				String diaChi = resultSet.getString(4);
				String maLop = resultSet.getString(5);
				SinhVien  sinhVien= new SinhVien(maSV, hoTen, email, diaChi, maLop);
				dssv.add(sinhVien);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dssv;
	}

	@Override
	public ArrayList<SinhVien> getSinhVienTheoMaLop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(SinhVien sinhVien) {
		// TODO Auto-generated method stub
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		int n= 0;
		String sql = "insert into tbSinhVien values(?,?,?,?,?)";
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, sinhVien.getMaSV());
			preparedStatement.setString(2, sinhVien.getHoTen());
			preparedStatement.setString(3, sinhVien.getEmail());
			preparedStatement.setString(4, sinhVien.getDiaChi());
			preparedStatement.setString(5, sinhVien.getMaLop());
			n = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n>0;
	}

	@Override
	public boolean delete(SinhVien sinhVien) {
		// TODO Auto-generated method stub
		return false;
	}

}

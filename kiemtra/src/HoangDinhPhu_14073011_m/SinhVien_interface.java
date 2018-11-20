package HoangDinhPhu_14073011_m;

import java.util.ArrayList;

public interface SinhVien_interface {
	ArrayList<SinhVien> getAllSinhVien();
	ArrayList<SinhVien> getSinhVienTheoMaLop();
	public boolean create(SinhVien sinhVien);
	public boolean delete(SinhVien sinhVien);

}

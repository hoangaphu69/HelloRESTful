package HoangDinhPhu_14073011_m;

public class SinhVien {
	private int maSV;
	private String hoTen;
	private String email;
	private String diaChi;
	private String maLop;
	public SinhVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SinhVien(int maSV, String hoTen, String email, String diaChi, String maLop) {
		super();
		this.maSV = maSV;
		this.hoTen = hoTen;
		this.email = email;
		this.diaChi = diaChi;
		this.maLop = maLop;
	}
	public SinhVien(int maSV) {
		super();
		this.maSV = maSV;
	}
	public int getMaSV() {
		return maSV;
	}
	public void setMaSV(int maSV) {
		this.maSV = maSV;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getMaLop() {
		return maLop;
	}
	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}
	@Override
	public String toString() {
		return "SinhVien [maSV=" + maSV + ", hoTen=" + hoTen + ", email=" + email + ", diaChi=" + diaChi + ", maLop="
				+ maLop + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((diaChi == null) ? 0 : diaChi.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((hoTen == null) ? 0 : hoTen.hashCode());
		result = prime * result + ((maLop == null) ? 0 : maLop.hashCode());
		result = prime * result + maSV;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SinhVien other = (SinhVien) obj;
		if (diaChi == null) {
			if (other.diaChi != null)
				return false;
		} else if (!diaChi.equals(other.diaChi))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (hoTen == null) {
			if (other.hoTen != null)
				return false;
		} else if (!hoTen.equals(other.hoTen))
			return false;
		if (maLop == null) {
			if (other.maLop != null)
				return false;
		} else if (!maLop.equals(other.maLop))
			return false;
		if (maSV != other.maSV)
			return false;
		return true;
	}
	
}

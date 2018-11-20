package HoangDinhPhu_14073011_m;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;




public class FrmSinhVien extends JFrame implements  MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1554680235689968471L;
	private JTextField txtMaSV;
	private JTextField txtHoTen;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private JButton btnDau;
	private JButton btnTruoc;
	private JButton btnSau;
	private JButton btnCuoi;

	private JButton btnThem;
	private JButton btnLuu;
	private JButton btnSua;
	private JButton btnXoa;
	private DefaultTableModel dataModel;
	private JTable table;

	private JScrollPane scroll;
	private JButton btnXemDSSV;
	private JLabel lblMalop;
	private JComboBox<String> cbxMaLop;
	//	private SinhVienDao sv_dao;
	//	private LopDao lop_dao;
	private SinhVien_DAO sv_dao;
	private LopHoc_DAO lh_dao;

	public FrmSinhVien() {
		Connection con = DBConnection.getInstance().getConnection();
		sv_dao = new  SinhVien_DAO();
		lh_dao = new LopHoc_DAO();
		setTitle("ho ten sv: hoang dinh phu;  Massv:14073011   lop:dhkhmt10a");
		setSize(800, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Box b, b1, b2, b3, b4, b5, b6, b7,b8;
		add(b = Box.createVerticalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b5 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b8 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b6 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));

		b.add(b7 = Box.createHorizontalBox());

		JLabel lblTieuDe, lblMaSV, lblHoTen, lblEmail, lblDiaChi;
		b1.add(lblTieuDe = new JLabel("THONG TIN SINH VIEN", JLabel.CENTER));
		lblTieuDe.setFont(new Font("Arila", Font.BOLD, 26));

		b2.add(lblMaSV = new JLabel("Ma so sinh vien: ", JLabel.RIGHT));
		b2.add(txtMaSV = new JTextField());
		b3.add(lblHoTen = new JLabel("Ho ten: ", JLabel.RIGHT));
		b3.add(txtHoTen = new JTextField());
		b4.add(lblEmail = new JLabel("Email: ", JLabel.RIGHT));
		b4.add(txtEmail = new JTextField());
		b5.add(lblDiaChi = new JLabel("Dia chi: ", JLabel.RIGHT));		
		b5.add(txtDiaChi = new JTextField());

		b8.add(lblMalop = new JLabel("Ma lop: ", JLabel.RIGHT));		
		b8.add(cbxMaLop = new JComboBox<String>());
		ArrayList<LopHoc> dslh = lh_dao.getAllTableLopHoc();
		for (LopHoc lopHoc : dslh) {
			cbxMaLop.addItem(lopHoc.getMaLop());
		}


		lblHoTen.setPreferredSize(lblMaSV.getPreferredSize());
		lblEmail.setPreferredSize(lblMaSV.getPreferredSize());
		lblDiaChi.setPreferredSize(lblMaSV.getPreferredSize());
		lblMalop.setPreferredSize(lblMaSV.getPreferredSize());

		b6.add(btnThem = new JButton("Them"));
		b6.add(btnLuu = new JButton("Luu"));
		b6.add(btnXoa = new JButton("Xoa"));
		b6.add(btnXemDSSV = new JButton("Xem Danh Sach Sinh Vien Cua Lop"));





		String[] tieuDe = { "Ma so", "Ho ten", "Email", "Dia chi","Ma lop"};
		dataModel = new DefaultTableModel(tieuDe , 0);
		JScrollPane scroll;
		b7.add(scroll = new JScrollPane(table = new JTable(dataModel)));
		scroll.setBorder(BorderFactory.createTitledBorder("Danh sach sinh vien hien tai"));
		docdulieuvaomodel();

		table.addMouseListener(this);
		btnThem.addActionListener(actionListener);

	}

	private void docdulieuvaomodel() {

		ArrayList<SinhVien> dssv = sv_dao.getAllSinhVien();
		for (SinhVien sinhVien : dssv) {
			dataModel.addRow(new Object[] {sinhVien.getMaSV(),sinhVien.getHoTen(),sinhVien.getEmail(),sinhVien.getDiaChi(),sinhVien.getMaLop()});
		}

	}
	private void xoadulieutrongbang() {
		dataModel.getDataVector().removeAllElements();
		revalidate();
	}

	ActionListener actionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			if(o.equals(btnThem)) {
				xoadulieutrongbang();
			}

		}
	};
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		int row = table.getSelectedRow();
		txtMaSV.setText(dataModel.getValueAt(row, 0).toString());
		txtHoTen.setText(dataModel.getValueAt(row, 1).toString());
		txtEmail.setText(dataModel.getValueAt(row, 2).toString());
		txtDiaChi.setText(dataModel.getValueAt(row, 4).toString());

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	public static void main(String[] args) {
		FrmSinhVien frmSinhVien = new FrmSinhVien();
		frmSinhVien.setVisible(true);
	}
}

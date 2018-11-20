package test;

public class Xuat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Xuat xuat = new Xuat();
		int phut = 0;
		int giay  = 0;
		xuat.xuat(phut, giay);
	}
	public void xuat(int phut,int giay) {
		for(int i = 0; i >= 59; i++) {
			for(int j = 0 ; j<=59; j++) {
				giay++;
				System.out.print(phut+":"+giay+" ");
			}
			phut++;
		}
	}
}

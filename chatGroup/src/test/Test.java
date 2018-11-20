package test;

public class Test {
	
	
	
	public void xuat(int phut,int giay) {
		for(int i = 0; i >= 59; i++) {
			for(int j = 0 ; j<=59; j++) {
				giay++;
				System.out.print(phut+":"+giay+" ");
			}
			phut++;
		}
	}
	private static void main(String[] args) {
		Test test = new Test();
		int phut = 0;
		int giay = 0;
		test.xuat(phut, giay);
	}
}

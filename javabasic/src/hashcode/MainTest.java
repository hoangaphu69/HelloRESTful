package hashcode;

public class MainTest {
	
	public static void main(String[] args) {
		
		Object1 ob = new Object1(1, "hoangaphu");
		Object1 ob2 = new Object1(1, "hoang dinh phu");
		
		System.out.println("object 1: "+ ob.getObName());
		System.out.println("object 2: "+ ob2.getObName());
		
		System.out.println("kiem tra hai doi tuong co bang nhau khong");
		System.out.println(ob.equals(ob2));
	}

}

package thread;

public class MainForThread {

	public static void main(String[] args) {
		Thread1 thread1 = new Thread1();
		Thread thread = new Thread(thread1);
		
		thread1.start();
		Thread2 thread2 = new Thread2();
		thread2.start();
	}

}

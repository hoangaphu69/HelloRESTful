package thread;

public class Thread2 extends Thread  {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				sleep(7000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("thread b: " + i );
		}
	}
	
}

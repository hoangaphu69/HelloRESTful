package thread;

public class Thread1  extends Thread{
	@Override
	public void run() {
		
		for (int i = 0; i < 10; i++) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("thread a: " + i );
		}
		
	}
	
}

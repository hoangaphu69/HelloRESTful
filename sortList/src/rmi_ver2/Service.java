package rmi_ver2;

import java.rmi.Naming;
import java.util.Random;
import java.util.Scanner;

public class Service {
	private static Scanner input= new Scanner(System.in);;

	public static void main(String[] args) throws Exception {

		
		
		int[] unsorted1 = {1,5,2,4,6,879,6};
		int[] unsorted2 = {9,5,2,1,58,878,1};

		int sorted[];
		long start = System.currentTimeMillis();
		SortThread thread1, thread2;
		String host1 = "localhost", host2 = "localhost";
		if (args.length == 2) {
			host1 = args[0];
			host2 = args[1];
		}
// lookup SortServerInf remote object in rmiregistry
		ISortList ss1 = (ISortList) Naming.lookup("rmi://" + host1 + "/Sort");
		ISortList ss2 = (ISortList) Naming.lookup("rmi://" + host1 + "/Sort");
		ISortList ss3 = (ISortList) Naming.lookup("rmi://" + host1 + "/Merge");

// Start sort threads
		thread1 = new SortThread(ss1, unsorted1, "Sort1");
		thread2 = new SortThread(ss2, unsorted2, "Sort2");
		thread1.start();
		thread2.start();
// Wait while either thread is sorting
		while (thread1.sorting() || thread2.sorting()) {
//		
		}

		sorted = ss3.merge(thread1.getSorted(), thread2.getSorted());
		System.out.println("------------Sorted-------------------");
		for (int i = 0; i < sorted.length; i++)
			System.out.print(sorted[i] + "  ");
		long end = System.currentTimeMillis();
		System.out.println("Time: " + (end - start));
	}

}




class SortThread extends Thread {
	int unsorted[], sorted[];
	ISortList ss;
	String name;
	boolean sorting;

	public SortThread(ISortList ss, int unsorted[], String name) {
		this.unsorted = unsorted;
		this.ss = ss;
		this.name = name;
		sorting = true;
	}

	public int[] getSorted() {
		return sorted;
	}

	public void run() {

		try {
			sorted = ss.sort(unsorted);
		} catch (Exception e) {
		}

		sorting = false;
	}

	public boolean sorting() {
		return sorting;
	}
}
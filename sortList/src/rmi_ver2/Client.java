package rmi_ver2;

import java.rmi.Naming;


public class Client {
//	public static void main(String[] args) throws Exception {
//		int unsorted1[] = { 1, 3, 5, 7 };
//		int unsorted2[] = { 2, 4, 6, 8 };
//		int sorted1[], sorted2[], sorted[];
//		long start = System.currentTimeMillis();
//		String host1 = "localhost", host2 = "localhost";
//		if (args.length == 2) {
//			host1 = args[0];
//			host2 = args[1];
//		}
//// lookup SortServerInf remote object in rmiregistry
//		ISortList ss1 = (ISortList) Naming.lookup("rmi://" + host1 + "/Sort1");
//		ISortList ss2 = (ISortList) Naming.lookup("rmi://" + host2 + "/Sort2");
//// Sort on server
//		sorted1 = ss1.sort(unsorted1);
//		sorted2 = ss2.sort(unsorted2);
//		sorted = new Client().merge(sorted1, sorted2);
////sorted = ss1.sort(unsorted1);
//		System.out.println("Sorted");
//		for (int i = 0; i < sorted.length; i++)
//			System.out.println(sorted[i]);
//		long end = System.currentTimeMillis();
//		System.out.println("Time: " + (end - start));
//	}
//
//	private int[] merge(int s1[], int s2[]) {
//		int[] merged = new int[s1.length + s2.length];
//		int i1 = 0, i2 = 0;
//		for (int i = 0; i < merged.length; i++)
//			if (i1 == s1.length)
//				merged[i] = s2[i2++];
//			else if (i2 == s2.length)
//				merged[i] = s1[i1++];
//			else if (s1[i1] > s2[i2])
//				merged[i] = s1[i1++];
//			else
//				merged[i] = s2[i2++];
//		return merged;
//	}
}
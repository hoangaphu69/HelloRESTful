package rmi_ver2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SortListImpl extends UnicastRemoteObject implements ISortList {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SortListImpl() throws RemoteException {
		super();
	}

	public int[] sort(int data[]) throws RemoteException {
		int n = data.length;
		int temp;
		for (int pass = 0; pass < n - 1; pass++)
			for (int pair = 0; pair < n - pass - 1; pair++)
				if (data[pair] > data[pair + 1]) {
					temp = data[pair];
					data[pair] = data[pair + 1];
					data[pair + 1] = temp;
				}
		return data;
	}


	public int[] merge(int s1[], int s2[]) {
		int[] merged = new int[s1.length + s2.length];
		int i1 = 0, i2 = 0;
		for (int i = 0; i < merged.length; i++)
			
			if (i1 == s1.length)
				merged[i] = s2[i2++];
			else if (i2 == s2.length)
				merged[i] = s1[i1++];
			else if (s1[i1] < s2[i2])
				merged[i] = s1[i1++];
			else
				merged[i] = s2[i2++];
		
		return merged;
	}
}
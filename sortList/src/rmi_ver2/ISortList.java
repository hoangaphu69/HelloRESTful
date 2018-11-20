package rmi_ver2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISortList extends Remote {
	public int[] sort(int data[]) throws RemoteException;
	public int[] merge(int s1[], int s2[]) throws RemoteException;
}
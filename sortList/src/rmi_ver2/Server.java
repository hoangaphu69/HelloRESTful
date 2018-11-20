package rmi_ver2;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
	public static void main(String[] args) throws Exception {
		
		System.out.println("Initializing server: please wait.");
		LocateRegistry.createRegistry(1099);
		Naming.rebind("rmi://localhost/Sort", new SortListImpl());
		Naming.rebind("rmi://localhost/Merge", new SortListImpl());
		System.out.println("The Sort Server is up and running.");
	}
}
package thucHanhTuan_8;

import java.rmi.RemoteException;

public class ServiceImpl implements Service{

	@Override
	public int[] sort(int[] data) throws RemoteException {
		int sorted[] = null;
		int unsort1[] = null,unsort2[] = null;
		int n = data.length;
		
		// chia mang lam hai
		for(int i = 0;i <= n;i++) {
			if(i % 2==0){
				unsort1[i] = data[i];
			}else {
				unsort2[i] = data[i];
			}
		}
		
		
		
		return sorted;
	}

}

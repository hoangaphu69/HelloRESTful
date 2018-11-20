package map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapTest {
	public static void main(String[] args) {
		Map map = new HashMap();
		
		map.put(11, "Toan");
		map.put(12, "Ly");
		map.put(13, "Hoa");
		
		Set set = map.keySet();
		for (Object key : set) {
			System.out.println(key+" "+map.get(key));
		}
		
	}
}

package map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class mainTest {

	public static void main(String[] args) {
		
		UserGroup group = new UserGroup();
		group.setGroupID(1);
		group.setGroupName("G1");
		
		UserGroup group2 = new UserGroup();
		group2.setGroupID(2);
		group2.setGroupName("G2");
		
		
		List<UserGroup> groups = new ArrayList<>();
		groups.add(group);
		groups.add(group2);
		
		User user = new User();
		user.setUserID(1);
		user.setUserName("Son");
		user.setIdgroup(2);
		
		List<User> list = new ArrayList<>();
		list.add(user);
		
		for (User u : list) {
			int idus = u.getIdgroup();
			for (UserGroup gr : groups) {
				int grid = gr.getGroupID();
				if(idus == grid) {
					System.out.println(u.getUserName());
					System.out.println("group name = "+ gr.getGroupName());
				}
			}	
		}
	}

}

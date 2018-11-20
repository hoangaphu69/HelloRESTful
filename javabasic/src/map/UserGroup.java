package map;

import java.util.ArrayList;
import java.util.List;

public class UserGroup {
	private int GroupID;
	private String GroupName;
	
	public UserGroup(int groupID, String groupName) {
		super();
		GroupID = groupID;
		GroupName = groupName;
	}

	public int getGroupID() {
		return GroupID;
	}

	public void setGroupID(int groupID) {
		GroupID = groupID;
	}

	public String getGroupName() {
		return GroupName;
	}

	public void setGroupName(String groupName) {
		GroupName = groupName;
	}

	public UserGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

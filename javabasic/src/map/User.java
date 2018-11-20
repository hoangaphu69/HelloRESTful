package map;

public class User {
	private int UserID;
	private String UserName;
	private int Idgroup;
	private String namegrp;
	
	
	
	public int getIdgroup() {
		return Idgroup;
	}
	public void setIdgroup(int idgroup) {
		Idgroup = idgroup;
	}
	public String getNamegrp() {
		return namegrp;
	}
	public void setNamegrp(String namegrp) {
		this.namegrp = namegrp;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public User(int userID, String userName) {
		super();
		UserID = userID;
		UserName = userName;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}

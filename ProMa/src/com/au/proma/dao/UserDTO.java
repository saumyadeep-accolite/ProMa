package com.au.proma.dao;

public class UserDTO {
	private String username;
	private String userpassword;
	private String useremail;
	
	
	public UserDTO() {
		super();
	}
	public UserDTO(String username, String userpassword, String useremail) {
		super();
		this.username = username;
		this.userpassword = userpassword;
		this.useremail = useremail;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	
	

}

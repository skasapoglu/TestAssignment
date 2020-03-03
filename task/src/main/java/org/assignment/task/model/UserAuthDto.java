package org.assignment.task.model;

public class UserAuthDto {
	
	private final String username;
	private final String password;
	
	public UserAuthDto(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	
	

}

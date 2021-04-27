package com.revature.models;

public class User {

	private int userId;
	private String username;
	private String passowrd;
	private String firstName;
	private String lastName;
	private String email;
	private Account type;
	private Role role;
	
	public User(int userId, String username, String passowrd, String firstName, String lastName, String email, Account type,
			Role role) {
		super();
		this.userId = userId;
		this.username = username;
		this.passowrd = passowrd;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.type = type;
		this.role = role;
	}

	
}

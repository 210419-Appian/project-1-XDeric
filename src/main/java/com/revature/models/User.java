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
	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}



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
	
	

	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassowrd() {
		return passowrd;
	}



	public void setPassowrd(String passowrd) {
		this.passowrd = passowrd;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Account getType() {
		return type;
	}



	public void setType(Account type) {
		this.type = type;
	}



	public Role getRole() {
		return role;
	}



	public void setRole(Role role) {
		this.role = role;
	}
	
	



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((passowrd == null) ? 0 : passowrd.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + userId;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (passowrd == null) {
			if (other.passowrd != null)
				return false;
		} else if (!passowrd.equals(other.passowrd))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (userId != other.userId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", passowrd=" + passowrd + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", type=" + type + ", role=" + role + "]";
	}

	
}

package com.revature.dao;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	
	//users
	public User findUser(int id);
	public boolean addUser(User user);
	public User getUser(String username);
	public boolean updateUser(User usr);
	//employee
	public List<User> getAllUsers();
	//Admin
	public boolean deleteUser(int id);
}

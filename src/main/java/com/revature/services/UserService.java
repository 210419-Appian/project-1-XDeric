package com.revature.services;

import java.util.List;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.models.User;

public class UserService {
	
	private UserDAO uDao = new UserDAOImpl();
	
	public User oneUser(String username) {
		return uDao.getUser(username);
	}
	
	public List<User> getAllUsers() {
		return uDao.getAllUsers();
	}
	
	public boolean addUser(User u) {
		return uDao.addUser(u);
	}
	
	public boolean deleteUser(int id) {
		return uDao.deleteUser(id);
	}
	
	public boolean patchUser(User user) {
		User uData = oneUser(user.getUsername());
		
		if(user.getUsername() == null){
			user.setUsername(uData.getUsername());
		}
		if(user.getPassword() == null){
			user.setPassword(uData.getPassword());
		}
		if(user.getFirstName() == null){
			user.setFirstName(uData.getFirstName());
		}
		if(user.getLastName() == null){
			user.setLastName(uData.getLastName());
		}
		
		user.setRole(uData.getRole());
		
		if(user.getEmail() == null){
			user.setEmail(uData.getEmail());
		}		
		return uDao.updateUser(user);
	}

}

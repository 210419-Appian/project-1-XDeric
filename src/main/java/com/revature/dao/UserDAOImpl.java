package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDAOImpl implements UserDAO {

	private static AccountDAO actDao = new AccountDAOImpl();
	private static RoleDAO rDAO = new RoleDAOImpl();

	@Override
	public boolean addUser(User user) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			// There is no chance for sql injection with just an integer so this is safe.
			String sql = "INSERT INTO users (username, password, firstname, lastname, email, usr_role)"
					+ "	VALUES (?, ?, ?, ?, ?, ?);";

			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;
			statement.setString(++index, user.getUsername());
			statement.setString(++index, user.getPassword());
			statement.setString(++index, user.getFirstName());
			statement.setString(++index, user.getLastName());
			statement.setString(++index, user.getEmail());
			if (user.getRole() != null) {
				statement.setString(++index, user.getRole().getRoleName());
			} else {
				statement.setString(++index, null);
			}

			statement.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User getUser(String username) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM users WHERE username = ?;";
			
			PreparedStatement pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, username);
			
			ResultSet result = pStatement.executeQuery();
			
			User usr = new User();
			
			while(result.next()) {
				usr.setUserId(result.getInt("user_id"));
				usr.setUsername(result.getString("username"));
				usr.setPassword(result.getString("password"));
				usr.setFirstName(result.getString("firstName"));
				usr.setLastName(result.getString("LastName"));
				usr.setEmail(result.getString("email"));
				String rName = result.getString("usr_role");
				if (rName != null) {
					usr.setRole(rDAO.findRole(rName));
				}
			}
			return usr;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM users;";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			List<User> list = new ArrayList<>();

			while (result.next()) {
				User usr = new User(result.getInt("user_id"), 
						result.getString("username"),
						result.getString("password"), 
						result.getString("firstname"), 
						result.getString("lastname"),
						result.getString("email"), null);
				String rName = result.getString("usr_role");
				if (rName != null) {
					usr.setRole(rDAO.findRole(rName));
				}
				
				list.add(usr);
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteUser(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "DELETE FROM users WHERE user_id = " + id + ";";

			Statement statement = conn.createStatement();

			statement.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User findUser(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM users WHERE user_id = ?;";
			
			PreparedStatement pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, id);
			
			ResultSet result = pStatement.executeQuery();
			
			User usr = new User();
			
			while(result.next()) {
				usr.setUserId(result.getInt("user_id"));
				usr.setUsername(result.getString("username"));
				usr.setPassword(result.getString("password"));
				usr.setFirstName(result.getString("firstName"));
				usr.setLastName(result.getString("LastName"));
				usr.setEmail(result.getString("email"));
				String rName = result.getString("usr_role");
				if (rName != null) {
					usr.setRole(rDAO.findRole(rName));
				}
			}
			return usr;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

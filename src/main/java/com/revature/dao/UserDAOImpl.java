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
			String sql = "INSERT INTO users (username, passowrd, firstname, lastname, email, usr_role)"
					+ "	VALUES (?, ?, ?, ?, ?, ?);";

			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;
			statement.setString(++index, user.getUsername());
			statement.setString(++index, user.getPassowrd());
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
	public User getUser(int id) {
		// TODO Auto-generated method stub
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
				User usr = new User(result.getInt("user_id"), result.getString("username"),
						result.getString("password"), result.getString("firstName"), result.getString("lastname"),
						result.getString("email"), null);


				String rName = result.getString("usr_role");
				if (rName != null) {
					usr.setRole(rDAO.findRole(rName));
				}

				return list;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}

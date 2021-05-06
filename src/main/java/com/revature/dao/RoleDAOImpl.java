package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Role;
import com.revature.utils.ConnectionUtil;

public class RoleDAOImpl implements RoleDAO {
	
	public Role findRole(String rName) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM roles where role_name = ?;";

			PreparedStatement pStatement = conn.prepareStatement(sql);

			pStatement.setString(1, rName);

			ResultSet result = pStatement.executeQuery();

			Role role = new Role();

			while (result.next()) {
				role.setRole(result.getString("role_name"));
			}
			return role;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

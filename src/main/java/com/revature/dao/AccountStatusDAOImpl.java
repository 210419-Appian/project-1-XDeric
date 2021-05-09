package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.AccountStatus;
import com.revature.utils.ConnectionUtil;

public class AccountStatusDAOImpl implements AccountStatusDAO {

	@Override
	public AccountStatus findStatus(String name) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM account_status where status = ?;";

			PreparedStatement pStatement = conn.prepareStatement(sql);

			pStatement.setString(1, name);

			ResultSet result = pStatement.executeQuery();

			AccountStatus aStat = new AccountStatus();

			while (result.next()) {
				aStat.setStatusId(result.getInt("status_id"));
				aStat.setStatus(result.getString("status"));
			}
			return aStat;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

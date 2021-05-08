package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.AccountType;
import com.revature.utils.ConnectionUtil;

public class AccountTypeDAOImpl implements AccountTypeDAO {

	@Override
	public AccountType getAccountType(String type) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM account_type WHERE type_name = ?;";

			PreparedStatement pStatement = conn.prepareStatement(sql);

			pStatement.setString(1, type);

			ResultSet result = pStatement.executeQuery();

			AccountType aType = new AccountType();

			while (result.next()) {
				aType.setTypeId(result.getInt("type_id"));
				aType.setType(result.getString("type_name"));
			}
			return aType;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

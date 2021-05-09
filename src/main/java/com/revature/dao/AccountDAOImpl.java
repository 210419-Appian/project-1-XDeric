package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.AccountType;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO {

	private static AccountTypeDAO aTypeDao = new AccountTypeDAOImpl();
	private static AccountStatusDAO aStatDao = new AccountStatusDAOImpl();
	private static UserDAO uDao = new UserDAOImpl();

	@Override
	public double getBalance(AccountType type, int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM account WHERE fk_user_id = " + id + " AND account_type = " 
			+ type.getType() + ";";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			Account a = null;

			while (result.next()) {
				a = new Account(result.getInt("account_id"), result.getDouble("balance"));
			}

			return a.getBalance();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String getStatus() {
		return null;
	}

	@Override
	public AccountType getType() {
		return null;
	}

	@Override
	public boolean deposit(Account act, double amount) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "UPDATE account SET balance = (balance + ?) WHERE fk_user_id = ?" 
			+ " AND account_type = ?;";

			// account_id|balance|account_status|account_type|fk_user_id

			PreparedStatement statement = conn.prepareStatement(sql);
			int index = 0;
			statement.setDouble(++index, amount);
			statement.setInt(++index, act.getUser().getUserId());
			statement.setString(++index, act.getType().getType());

			statement.execute();
			return true;

		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean withdraw(Account act, double amount) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "UPDATE account SET balance = (balance - ?) WHERE fk_user_id = ?" 
			+ " AND account_type = ?;";

			// account_id|balance|account_status|account_type|fk_user_id

			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			statement.setDouble(++index, amount);
			statement.setInt(++index, act.getUser().getUserId());
			statement.setString(++index, act.getType().getType());

			statement.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Account> getAllAccounts() {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM account;";
			
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			List<Account> alist = new ArrayList<>();
			
			while (result.next()) {
			Account	a = new Account(result.getInt("account_id"), result.getDouble("balance"), null, null, null);
				String status = result.getString("account_status");
				String type = result.getString("account_type");
				int uID = result.getInt("fk_user_id");
				a.setUser(uDao.findUser(uID));

				if (status != null) {
					a.setStatus(aStatDao.findStatus(status));
				}
				if (type != null) {
					a.setType(aTypeDao.getAccountType(type));
				}
				
				alist.add(a);
			}
			return alist;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
			
		return null;
	}

	@Override
	public Account findAccount(AccountType aType, int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM account WHERE fk_user_id = " + id + " AND account_type = "
					+ aType.getType() +";";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			Account a = null;

			// account_id|balance|account_status|account_type|fk_user_id

			while (result.next()) {
				a = new Account(result.getInt("account_id"), result.getDouble("balance"), null, null, null);
				String status = result.getString("account_status");
				String type = result.getString("account_type");
				int uID = result.getInt("fk_user_id");
				a.setUser(uDao.findUser(uID));

				if (status != null) {
					a.setStatus(aStatDao.findStatus(status));
				}
				if (type != null) {
					a.setType(aTypeDao.getAccountType(type));
				}

			}

			return a;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Account findAccountById(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM account WHERE account_id = " + id + ";";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			Account a = null;

			// account_id|balance|account_status|account_type|fk_user_id

			while (result.next()) {
				a = new Account(result.getInt("account_id"), result.getDouble("balance"), null, null, null);
				String status = result.getString("account_status");
				String type = result.getString("account_type");
				int uID = result.getInt("fk_user_id");
				a.setUser(uDao.findUser(uID));

				if (status != null) {
					a.setStatus(aStatDao.findStatus(status));
				}
				if (type != null) {
					a.setType(aTypeDao.getAccountType(type));
				}

			}

			return a;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean createAccount(Account act) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO account (balance, account_status, account_type, fk_user_id) "
					+ "VALUES (?, ?, ?, ?);";
//		|balance|account_status|account_type|fk_user_id
			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;
			statement.setDouble(++index, act.getBalance());
			if (act.getStatus() != null) {
				statement.setString(++index, act.getStatus().getStatus());
			} else {
				statement.setString(++index, null);
			}
			if (act.getType() != null) {
				statement.setString(++index, act.getType().getType());
			} else {
				statement.setString(++index, null);
			}
			statement.setInt(++index, act.getUser().getUserId());
			
			statement.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User getUserID(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}

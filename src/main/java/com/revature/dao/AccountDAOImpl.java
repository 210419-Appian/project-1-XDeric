package com.revature.dao;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.AccountType;

public class AccountDAOImpl implements AccountDAO{

	@Override
	public int getBalance(int id) {
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
	public boolean deposit(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean withdraw(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findAccount(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createAccount(AccountType type) {
		// TODO Auto-generated method stub
		return false;
	}

}

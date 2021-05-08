package com.revature.dao;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.AccountType;

public interface AccountDAO {

	public String getStatus();
	public List<Account> getAllAccounts();
	public AccountType getType();
	public Account findAccount(int id);
	
	public int getBalance(int id);
	public boolean deposit(int id);
	public boolean withdraw(int id);
	
	
	public boolean createAccount(AccountType type);
}

package com.revature.dao;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.AccountType;
import com.revature.models.User;

public interface AccountDAO {

	public String getStatus();
	public List<Account> getAllAccounts();
	public AccountType getType();
	public Account findAccount(int id);
	public User getUserID(String username);
	
	public double getBalance(AccountType type, int id);
	public boolean deposit(AccountType type, double amount, int id);
	public boolean withdraw(AccountType type, double amount, int id);
	
	
	public boolean createAccount(Account act);
}

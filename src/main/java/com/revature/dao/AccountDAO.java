package com.revature.dao;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.AccountType;
import com.revature.models.User;

public interface AccountDAO {

	public String getStatus();
	public List<Account> getAllAccounts();
	public AccountType getType();
	public Account findAccountById(int id);
	public Account findAccount(AccountType type, int id);
	public User getUserID(String username);
	
	public double getBalance(AccountType type, int id);
	public boolean deposit(Account act, double amount);
	public boolean withdraw(Account act, double amount);
	public boolean transfer(Account act, Account act2, double amount);
	
	
	public boolean createAccount(Account act);
}

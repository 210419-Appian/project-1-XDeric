package com.revature.dao;

import com.revature.models.AccountType;

public interface AccountDAO {
	public int getBalance(int id);
	public String getStatus();
	public AccountType getType();
}

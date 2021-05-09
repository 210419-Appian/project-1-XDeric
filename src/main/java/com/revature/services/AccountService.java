package com.revature.services;

import java.util.List;

import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.AccountStatusDAO;
import com.revature.dao.AccountStatusDAOImpl;
import com.revature.dao.AccountTypeDAO;
import com.revature.dao.AccountTypeDAOImpl;
import com.revature.models.Account;

public class AccountService {
	
	private AccountStatusDAO astatDao = new AccountStatusDAOImpl();
	private AccountTypeDAO atypeDao = new AccountTypeDAOImpl();
	private AccountDAO aDao = new AccountDAOImpl();
	
	public boolean addAccount(Account act) {
		return aDao.createAccount(act);
	}
	
	public Account getAccount(int id) {
		return aDao.findAccount(id);
	}
	
	public List<Account> getAllAccounts() {
		return aDao.getAllAccounts();
	}

}

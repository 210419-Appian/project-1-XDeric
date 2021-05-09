package com.revature.services;

import java.util.List;

import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.AccountStatusDAO;
import com.revature.dao.AccountStatusDAOImpl;
import com.revature.dao.AccountTypeDAO;
import com.revature.dao.AccountTypeDAOImpl;
import com.revature.models.Account;
import com.revature.models.AccountType;

public class AccountService {
	
	private AccountStatusDAO astatDao = new AccountStatusDAOImpl();
	private AccountTypeDAO atypeDao = new AccountTypeDAOImpl();
	private AccountDAO aDao = new AccountDAOImpl();
	
	public boolean addAccount(Account act) {
		return aDao.createAccount(act);
	}
	
	public Account getAccount(AccountType type , int id) {
		return aDao.findAccount(type , id);
	}
	
	public Account findById(int id) {
		return aDao.findAccountById(id);
	}
	
	public List<Account> getAllAccounts() {
		return aDao.getAllAccounts();
	}
	
	public boolean deposit(Account act, double amount) {
		
		Account actData = findById(act.getAccountId());
		
		if(act.getBalance() >= 0) {
			act.setBalance(act.getBalance());
		}
		if(act.getStatus() == null) {
			act.setStatus(actData.getStatus());
		}
		if(act.getType() == null) {
			act.setType(actData.getType());
		}
		if(act.getUser() == null) {
			act.setUser(actData.getUser());
		}
		
		return aDao.deposit(act, act.getBalance());
	}
	
	public boolean withdraw(Account act, double amount) {
		
		Account actData = findById(act.getAccountId());
		//Account actData = getAccount(act);
		
		if(actData.getBalance() == 0 || act.getBalance() > actData.getBalance()) {
			return false;
		}else {
			if(act.getBalance() >= 0) {
				act.setBalance(act.getBalance());
			}
			if(act.getStatus() == null) {
				act.setStatus(actData.getStatus());
			}
			if(act.getType() == null) {
				act.setType(actData.getType());
			}
			if(act.getUser() == null) {
				act.setUser(actData.getUser());
			}
		}
		
		return aDao.withdraw(act, act.getBalance());
	}
	
	public boolean transfer() {
		
		return false;
	}

}

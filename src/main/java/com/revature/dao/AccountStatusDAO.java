package com.revature.dao;

import com.revature.models.AccountStatus;

public interface AccountStatusDAO {
	
	public AccountStatus findStatus(String name);

}

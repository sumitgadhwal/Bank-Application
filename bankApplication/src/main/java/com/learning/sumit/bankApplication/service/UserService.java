package com.learning.sumit.bankApplication.service;


import java.math.BigDecimal;

import com.learning.sumit.bankApplication.entities.AccountEntity;
import com.learning.sumit.bankApplication.entities.UserEntity;

public interface UserService {
	UserEntity getUser(String userName,String userEmail);

	AccountEntity createAccount(UserEntity userDetails);
	BigDecimal getBalance(long accountNumber);
	boolean deposit(long accountNumber,BigDecimal money);
	boolean withdraw(long accountNumber,BigDecimal money)throws Exception;

	
}

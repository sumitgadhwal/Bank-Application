package com.example.bankApplication.service;


import java.math.BigDecimal;

import com.example.bankApplication.entities.UserEntity;

public interface UserService {
	UserEntity getUser(String userName,String userEmail);

	UserEntity createAccount(UserEntity userDetails);
	BigDecimal getBalance(long accountNumber);
	boolean deposit(long accountNumber,BigDecimal money);
	boolean withdraw(long accountNumber,BigDecimal money)throws Exception;

	
}

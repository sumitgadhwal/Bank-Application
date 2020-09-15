package com.example.bankApplication.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankApplication.api.repository.AccountRepository;
import com.example.bankApplication.api.repository.UserRepository;
import com.example.bankApplication.entities.AccountEntity;
import com.example.bankApplication.entities.UserEntity;
import com.example.bankApplication.exception.NotEnoughBalance;
import com.example.bankApplication.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;
	@Autowired
	AccountRepository accountRepository;
	long counter =910000000000L;
	@Override
	public UserEntity getUser(String userName, String userEmail) {
		UserEntity userEntity=userRepository.findByUserNameAndEmail(userName,userEmail);

	    return userEntity;
	}


	@Override
	public UserEntity createAccount(UserEntity userDetails) {
		//System.out.println(userDetails.getUserName());
		UserEntity userEntity=getUser(userDetails.getUserName(),userDetails.getEmail());
	
		AccountEntity accountEntity=new AccountEntity();
		accountEntity.setBalance(new BigDecimal("0.0"));
		accountEntity.setAccountNumber(counter++);
		if(userEntity==null)
		{accountEntity.setUserEntity(userDetails);
		userRepository.save(userDetails);
		accountRepository.save(accountEntity);
		return userDetails;
		}
		else
		{
			accountEntity.setUserEntity(userEntity);
			accountRepository.save(accountEntity);
			return userEntity;
		}


		
	}

	@Override
	public BigDecimal getBalance(long accountNumber) {
		AccountEntity accountEntity =accountRepository.findByAccountNumber(accountNumber);
		
		return accountEntity.getBalance();
	}

	@Override
	public boolean deposit(long accountNumber, BigDecimal money) {
		AccountEntity accountEntity =accountRepository.findByAccountNumber(accountNumber);
		MathContext mc = new MathContext(4);
		
		accountEntity.setBalance(accountEntity.getBalance().add(money));
		accountRepository.save(accountEntity);
		return true;
	}
	@Override
	public boolean withdraw(long accountNumber,BigDecimal money) throws Exception {
		AccountEntity accountEntity =accountRepository.findByAccountNumber(accountNumber);
		
		BigDecimal balance=accountEntity.getBalance();
		int res=money.compareTo(balance);

		if(res==1)
		{
			NotEnoughBalance e=new NotEnoughBalance("Account has not enough balance");
			throw e;
		}
		

		MathContext mc = new MathContext(4);	
		accountEntity.setBalance(accountEntity.getBalance().subtract(money));
		accountRepository.save(accountEntity);
		return true;
	}



}

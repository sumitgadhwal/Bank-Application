package com.learning.sumit.bankApplication.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;

import com.learning.sumit.bankApplication.repository.UserRepository;
import com.learning.sumit.bankApplication.exception.NotEnoughBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.sumit.bankApplication.repository.AccountRepository;
import com.learning.sumit.bankApplication.entities.AccountEntity;
import com.learning.sumit.bankApplication.entities.UserEntity;
import com.learning.sumit.bankApplication.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	UserRepository userRepository;
	AccountRepository accountRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository,AccountRepository accountRepository)
	{
		super();
		this.accountRepository=accountRepository;
		this.userRepository=userRepository;
	}


	long counter =910000000000L;
	@Override
	public UserEntity getUser(String userName, String userEmail) {
		UserEntity userEntity=userRepository.findByUserNameAndEmail(userName,userEmail);

	    return userEntity;
	}


	@Override
	public AccountEntity createAccount(UserEntity userDetails) {
		//System.out.println(userDetails.getUserName());
		UserEntity userEntity=getUser(userDetails.getUserName(),userDetails.getEmail());
	
		AccountEntity accountEntity=new AccountEntity();
		accountEntity.setBalance(new BigDecimal("0.0"));
		accountEntity.setAccountNumber(counter++);
		if(userEntity==null) {
			userEntity=userRepository.save(userDetails);
			accountEntity.setUserEntity(userEntity);
			accountEntity = accountRepository.save(accountEntity);
			return accountEntity;
		}
		else
		{
			accountEntity.setUserEntity(userEntity);
			accountRepository.save(accountEntity);
			return accountEntity;
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
			NotEnoughBalance e=new NotEnoughBalance("Account doesn't have enough balance");
			throw e;
		}
		

		MathContext mc = new MathContext(4);	
		accountEntity.setBalance(accountEntity.getBalance().subtract(money));
		accountRepository.save(accountEntity);
		return true;
	}



}

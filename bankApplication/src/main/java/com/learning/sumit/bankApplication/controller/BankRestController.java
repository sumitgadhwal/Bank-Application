package com.learning.sumit.bankApplication.controller;

import java.math.BigDecimal;

import com.learning.sumit.bankApplication.entities.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.learning.sumit.bankApplication.entities.UserEntity;
import com.learning.sumit.bankApplication.service.UserService;

@RestController
public class BankRestController {

	private UserService userService;

	@Autowired
	public BankRestController(UserService userService)
	{
		this.userService=userService;
	}

	
	@RequestMapping(value="/user/get-user/{userName}&{userEmail}",method = RequestMethod.GET)
	public UserEntity getUser(@PathVariable String userName, @PathVariable String userEmail) throws Exception
	{
		return userService.getUser(userName,userEmail);
	}
	@RequestMapping(value="/bank/create-account/",method = RequestMethod.POST)
    public Object createAccount(@RequestBody UserEntity userDetails)
    {
		//System.out.println(userDetails.getMobileNumber());
        return "Your Account Number is: "+userService.createAccount(userDetails).getAccountNumber();
    }
	@RequestMapping(value="/bank/get-balance/{accountNumber}",method = RequestMethod.GET)
	public BigDecimal getBalance(@PathVariable long accountNumber)throws Exception
	{
		return userService.getBalance(accountNumber);
	}
	@RequestMapping(value="/bank/deposit/{accountNumber}&{money}",method = RequestMethod.GET)
	public boolean deposit(@PathVariable long accountNumber,@PathVariable BigDecimal money )throws Exception
	{
		return userService.deposit(accountNumber,money);
	}
	@RequestMapping(value="/bank/withdraw/{accountNumber}&{money}",method = RequestMethod.GET)
	public boolean withdraw(@PathVariable long accountNumber,@PathVariable BigDecimal money ) throws Exception
	{

		return userService.withdraw(accountNumber,money);

	}
	

}
